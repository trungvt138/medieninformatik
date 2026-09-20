# -*- coding: utf-8 -*-
"""
Aufgabe 12 – Offset zwischen Audio und Video erzeugen

Untersuchen Sie unterschiedliche Versatz-Werte (Offset) zwischen
Audio (A) und Video (V) auf ihre Wahrnehmbarkeit.

Vorgehen:
  1. gewünschten Offset einlesen und in ms umrechnen
  2. Dauer des Videos feststellen
  3. je nach Vorzeichen des Offsets Audiospur nach vorn oder
     nach hinten ziehen
  4. das bearbeitete File unter neuem Namen speichern

Referenzwerte nach ITU-T J.248:
  Wahrnehmungsgrenzen: +45 ms (Audio vor Video) .. -125 ms (Audio nach Video)
  Akzeptanz-Grenzen:    90 ms .. -185 ms
"""

import json
import subprocess

INPUT = "Poesie-Ambulanz ｜ Sandra Flubacher liest Wilhelm Busch [zhNmH9ldews].webm"

# Positiver Offset  -> Audio kommt zu spät  (Audio nach Video)
# Negativer Offset  -> Audio kommt zu früh  (Audio vor Video)
OFFSETS_MS = [-185, -125, -45, 0, 45, 90]


# ─────────────────────────────────────────────────────────────
# Schritt 2 – Dauer des Videos feststellen
# ─────────────────────────────────────────────────────────────

def get_duration(path):
    cmd = [
        "ffprobe", "-v", "error",
        "-print_format", "json",
        "-show_format",
        path
    ]
    result = subprocess.run(cmd, capture_output=True, text=True, check=True)
    info = json.loads(result.stdout)
    return float(info["format"]["duration"])


# ─────────────────────────────────────────────────────────────
# Schritt 3 – Audiospur je nach Vorzeichen des Offsets verschieben
# ─────────────────────────────────────────────────────────────

def build_audio_filter(delay_ms, video_duration):
    if delay_ms >= 0:
        # Positiver Offset: Audio wird verzögert
        return (
            f"adelay={delay_ms}:all=1,"
            f"atrim=duration={video_duration},"
            f"asetpts=PTS-STARTPTS"
        )
    else:
        # Negativer Offset: Anfang von Audio wird abgeschnitten,
        # am Ende wird mit Stille aufgefüllt
        audio_start_s = -delay_ms / 1000
        return (
            f"atrim=start={audio_start_s},"
            f"asetpts=PTS-STARTPTS,"
            f"apad,"
            f"atrim=duration={video_duration}"
        )


def offset_filename(delay_ms):
    sign = "p" if delay_ms >= 0 else "m"
    return f"poesie_ambulanz_offset_{sign}{abs(delay_ms):03d}ms.mp4"


# ─────────────────────────────────────────────────────────────
# Schritt 1 + 4 – Offset einlesen/umrechnen und Datei erzeugen
# ─────────────────────────────────────────────────────────────

video_duration = get_duration(INPUT)
print(f"Eingabedatei:   {INPUT}")
print(f"Videodauer:     {video_duration:.2f} s\n")

for delay_ms in OFFSETS_MS:
    output_file = offset_filename(delay_ms)
    audio_filter = build_audio_filter(delay_ms, video_duration)

    cmd = [
        "ffmpeg", "-y",
        "-i", INPUT,
        "-af", audio_filter,
        "-c:v", "copy",
        "-t", str(video_duration),
        output_file
    ]
    subprocess.run(cmd, check=True, capture_output=True, text=True)

    richtung = "Audio nach Video" if delay_ms >= 0 else "Audio vor Video"
    print(f"Offset {delay_ms:+5d} ms ({richtung:<17}) -> {output_file}")

print("\nFertig. Vergleichen Sie die erzeugten Dateien beim Abspielen")
print("und beurteilen Sie subjektiv, ab welchem Offset der A/V-Versatz")
print("wahrnehmbar bzw. störend wird.")
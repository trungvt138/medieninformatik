# -*- coding: utf-8 -*-
"""
Aufgabe 11 – Videodatei für ein Nutzungsszenario optimieren

Nutzungsszenario: Mobile Wiedergabe bei begrenzter Bandbreite
  - Ziel: kleinstmögliche Dateigröße bei akzeptabler Qualität
  - Typische Ziel-Bitrate: ≤ 300 kbit/s (mobiles Datenvolumen schonen)
  - Verbreitung: H.264 (Baseline/Main) hat universelle Hardware-Dekodierung
                 auf Android und iOS ohne zusätzliche App

Technische Entscheidungen:
  - Codec:      AV1 → H.264 (bessere Gerätekompatibilität, HW-Decoder vorhanden)
  - Auflösung:  640×360 → 320×180 (halbe lineare Auflösung, 1/4 Pixel)
  - Bildrate:   30 fps → 24 fps (reduziert Datenmenge ~20 %, visuell kaum spürbar)
  - Bitrate:    CRF 28 (qualitätsorientiert) + maxrate 250k + bufsize 500k
"""

import json
import os
import subprocess
import cv2

INPUT  = "video.mp4"
OUTPUT = "video_mobil.mp4"

# ─────────────────────────────────────────────────────────────
# Schritt 1 – Basisinformationen mit OpenCV auslesen
# ─────────────────────────────────────────────────────────────

cap = cv2.VideoCapture(INPUT)
if not cap.isOpened():
    raise OSError(f"Datei konnte nicht geöffnet werden: {INPUT}")

cv_width  = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
cv_height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
cv_fps    = cap.get(cv2.CAP_PROP_FPS)
cv_frames = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
cap.release()

print("=" * 55)
print("Schritt 1 – OpenCV-Basisinformationen")
print("=" * 55)
print(f"  Datei:          {INPUT}")
print(f"  Auflösung:      {cv_width} × {cv_height} px")
print(f"  Bildrate (FPS): {cv_fps}")
print(f"  Frame-Anzahl:   {cv_frames}")
print(f"  Dateigröße:     {os.path.getsize(INPUT) / 1024:.1f} KB")

# ─────────────────────────────────────────────────────────────
# Schritt 2 – Container und Videostream mit ffprobe analysieren
# ─────────────────────────────────────────────────────────────

ffprobe_cmd = [
    "ffprobe", "-v", "error",
    "-print_format", "json",
    "-show_format", "-show_streams",
    INPUT
]
result = subprocess.run(ffprobe_cmd, capture_output=True, text=True, check=True)
info   = json.loads(result.stdout)

fmt          = info["format"]
video_stream = next(s for s in info["streams"] if s["codec_type"] == "video")

print("\n" + "=" * 55)
print("Schritt 2 – ffprobe: Container und Videostream")
print("=" * 55)
print(f"  Container:      {fmt['format_name']}")
print(f"  Codec:          {video_stream.get('codec_name')} "
      f"({video_stream.get('codec_long_name', '')})")
print(f"  Auflösung:      {video_stream['width']} × {video_stream['height']} px")
print(f"  Pixel-Format:   {video_stream.get('pix_fmt')}")
print(f"  Bildrate:       {video_stream.get('avg_frame_rate')} fps")
print(f"  Bitrate (ges.): {int(fmt.get('bit_rate', 0)) // 1000} kbit/s")
print(f"  Dauer:          {float(fmt.get('duration', 0)):.2f} s")

# ─────────────────────────────────────────────────────────────
# Schritt 3 – Technische Anforderungen des Nutzungsszenarios
# ─────────────────────────────────────────────────────────────

print("\n" + "=" * 55)
print("Schritt 3 – Anforderungen: Mobile / begrenzter Bandbreite")
print("=" * 55)
print("  Ziel-Codec:     H.264 (Baseline/Main) – Hardware-Decoder")
print("                  auf praktisch jedem Smartphone vorhanden")
print("  Ziel-Auflösung: 320 × 180 px (HD-ready → SD-Mobile)")
print("  Ziel-FPS:       24 (reduziert Datenmenge, visuell kaum")
print("                  merklich bei Inhalten ohne schnelle Bewegung)")
print("  Ziel-Bitrate:   ≤ 250 kbit/s Video (CRF 28, VBR)")
print("  Container:      MP4 (breite Geräteunterstützung)")
print("  Audio:          AAC 64 kbit/s Stereo (kein Qualitätsverlust")
print("                  bei Sprache, deutliche Einsparung)")

# ─────────────────────────────────────────────────────────────
# Schritt 4 – Datenreduzierte Variante mit ffmpeg erzeugen
# ─────────────────────────────────────────────────────────────

ffmpeg_cmd = [
    "ffmpeg", "-y",
    "-i", INPUT,
    "-vf", "scale=320:180",        # halbe lineare Auflösung
    "-r", "24",                    # 24 fps
    "-c:v", "libx264",             # H.264-Encoder
    "-profile:v", "baseline",      # maximale Gerätekompatibilität
    "-level", "3.0",               # unterstützt 320×180 @ 24fps problemlos
    "-crf", "28",                  # qualitätsorientierte Bitrate (0=lossless, 51=schlecht)
    "-maxrate", "250k",            # Spitzenbitrate begrenzen (Puffer für mobile Netze)
    "-bufsize", "500k",
    "-c:a", "aac",                 # AAC-Audio
    "-b:a", "64k",                 # 64 kbit/s – ausreichend für Sprache/Musik
    "-movflags", "+faststart",     # MP4 für progressives Streaming vorbereiten
    OUTPUT
]

print("\n" + "=" * 55)
print("Schritt 4 – ffmpeg-Optimierung läuft …")
print("=" * 55)
subprocess.run(ffmpeg_cmd, check=True)

# ─────────────────────────────────────────────────────────────
# Ergebnisvergleich
# ─────────────────────────────────────────────────────────────

out_info_raw = subprocess.run(
    ["ffprobe", "-v", "error", "-print_format", "json",
     "-show_format", "-show_streams", OUTPUT],
    capture_output=True, text=True, check=True
)
out_info   = json.loads(out_info_raw.stdout)
out_fmt    = out_info["format"]
out_vs     = next(s for s in out_info["streams"] if s["codec_type"] == "video")

orig_size = os.path.getsize(INPUT)
opt_size  = os.path.getsize(OUTPUT)

print("\n" + "=" * 55)
print("Ergebnisvergleich: Original vs. Optimiert")
print("=" * 55)
print(f"{'':25} {'Original':>12}  {'Optimiert':>12}")
print(f"{'Container':25} {fmt['format_name']:>12}  {out_fmt['format_name']:>12}")
print(f"{'Codec':25} {video_stream.get('codec_name','?'):>12}  {out_vs.get('codec_name','?'):>12}")
print(f"{'Auflösung':25} "
      f"{video_stream['width']}×{video_stream['height']:>3}px  "
      f"{out_vs['width']}×{out_vs['height']}px")
print(f"{'Bildrate':25} {video_stream.get('avg_frame_rate','?'):>12}  "
      f"{out_vs.get('avg_frame_rate','?'):>12}")
print(f"{'Bitrate gesamt (kbit/s)':25} {int(fmt.get('bit_rate',0))//1000:>12}  "
      f"{int(out_fmt.get('bit_rate',0))//1000:>12}")
print(f"{'Dateigröße (KB)':25} {orig_size/1024:>12.1f}  {opt_size/1024:>12.1f}")
print(f"{'Reduktion':25} {'':>12}  {(1 - opt_size/orig_size)*100:>11.1f}%")

print("\n" + "=" * 55)
print("Dokumentation")
print("=" * 55)
print("""
Nutzungsszenario:  Mobile Wiedergabe bei begrenzter Bandbreite
                   (z. B. 3G/LTE mit geringem Datenvolumen)

Technische Änderung:
  - Codec von AV1 auf H.264 (Baseline 3.0) gewechselt, da AV1
    auf älteren Smartphones keinen Hardware-Decoder hat und die
    Software-Dekodierung den Akku stark belastet.
  - Auflösung auf 320×180 halbiert: Mobilbildschirme sind klein
    genug, dass der Qualitätsunterschied kaum wahrnehmbar ist.
  - Bildrate auf 24 fps reduziert: spart ~20 % Datenmenge ohne
    für typische Inhalte sichtbaren Qualitätsverlust.
  - CRF 28 + maxrate 250k: qualitätsorientierte Variable Bitrate
    mit Deckelbegrenzung – verhindert Bitrate-Spitzen bei Szenen
    mit schneller Bewegung.

Vorteile:
  + Deutlich kleinere Datei → weniger mobiles Datenvolumen
  + H.264 Baseline: Hardware-Dekodierung auf allen Smartphones
    → geringerer Akkuverbrauch
  + Faststart-Flag: Video beginnt sofort zu spielen, kein
    vollständiger Download nötig (Progressive Download)

Nachteile:
  - Geringere räumliche Auflösung: auf großen Displays sichtbarer
    Qualitätsverlust
  - H.264 Baseline: kein B-Frame-Support, geringfügig weniger
    effizient als Main/High Profile
  - AV1-Qualität des Originals kann H.264 bei gleicher Bitrate
    nicht vollständig erreichen
""")

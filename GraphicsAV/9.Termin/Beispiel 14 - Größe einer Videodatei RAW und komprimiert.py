# -*- coding: utf-8 -*-
"""
Created on Sun May 31 21:38:55 2026

AV Programming - Medieninformatik
SoSe 2026
"""

import json
import subprocess
from pathlib import Path

# ------------------------------------------------------------
# Name der Videodatei
# ------------------------------------------------------------
video = Path("video.mp4")

# ------------------------------------------------------------
# 1) Metadaten mit ffprobe auslesen
#
# ffprobe liefert strukturierte Informationen über
# - das Containerformat
# - die enthaltenen Streams
#
# JSON zur Weiterverarbeitung der Werte dann in Python
# ------------------------------------------------------------
cmd = [
    "ffprobe",
    "-v", "error",           # nur Fehlermeldungen ausgeben
    "-print_format", "json", # Ausgabe als JSON
    "-show_format",          # Containerinformationen
    "-show_streams",         # Streaminformationen
    str(video)
]

result = subprocess.run(cmd, capture_output=True, text=True, check=True)
info = json.loads(result.stdout)

# ------------------------------------------------------------
# 2) Den Videostream auswählen
#
# Eine Datei kann mehrere Streams enthalten:
# z. B. Video, Audio, Untertitel.
# ------------------------------------------------------------
v = next(s for s in info["streams"] if s["codec_type"] == "video")

# ------------------------------------------------------------
# 3) Relevante Parameter auslesen
# ------------------------------------------------------------

# Räumliche Auflösung
width = int(v["width"])
height = int(v["height"])

# Gesamtdauer der Datei in Sekunden
duration = float(info["format"]["duration"])

# ------------------------------------------------------------
# 4) Bildrate aus dem von ffprobe gelieferten Bruch berechnen
#
# ffprobe gibt die Bildrate als String wie "30/1" aus.
# Daraus numerischer fps-Wert.
# ------------------------------------------------------------
num, den = v["avg_frame_rate"].split("/")
fps = float(num) / float(den) if float(den) != 0 else 0.0

# ------------------------------------------------------------
# 5) Rohdatenmenge grob abschätzen
#
# ------------------------------------------------------------
bytes_per_frame = width * height * 3

# Geschätzte Anzahl der Frames
frame_count_est = duration * fps

# Geschätzte Rohgröße in Byte
raw_size_est = bytes_per_frame * frame_count_est

# Tatsächliche Dateigröße der komprimierten Datei
file_size = video.stat().st_size

# ------------------------------------------------------------
# 6) Ergebnisse ausgeben
# ------------------------------------------------------------
print("Datei:", video.name)
print("Auflösung:", width, "x", height)
print("Bildrate:", fps, "fps")
print("Dauer:", round(duration, 2), "s")
print("Geschätzte Rohgröße (MB):", round(raw_size_est / 1_000_000, 2))
print("Tatsächliche Dateigröße (MB):", round(file_size / 1_000_000, 2))

if file_size > 0:
    print("Kompressionsrelation ca.:", round(raw_size_est / file_size, 1), ": 1")
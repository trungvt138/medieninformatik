# -*- coding: utf-8 -*-
"""
Created on Sun Jun 14 15:51:42 2026
AV-Programming SoSe 26
E. Wilk

Analyse Videodatei
"""

import json
import subprocess

DATEI = "trailer_1080p.ogg"

cmd = [
    "ffprobe",
    "-v", "error",
    "-show_format",
    "-show_streams",
    "-of", "json",
    DATEI
]

result = subprocess.run(cmd, capture_output=True, text=True, check=True)
info = json.loads(result.stdout)

print("Datei:", DATEI)
print("Container:", info["format"]["format_name"])
print("Gesamtdauer:", round(float(info["format"]["duration"]), 2), "s")
print()

audio_found = False
video_found = False

for s in info["streams"]:
    typ = s["codec_type"]
    codec = s.get("codec_name", "unbekannt")
    print(f"Stream {s['index']}: {typ}, Codec={codec}")

    if typ == "video":
        video_found = True
        print("  Auflösung:", s.get("width"), "x", s.get("height"))
        print("  FPS:", s.get("r_frame_rate"))

    if typ == "audio":
        audio_found = True
        print("  Samplerate:", s.get("sample_rate"))
        print("  Kanäle:", s.get("channels"))

    print()

if video_found and audio_found:
    print("Praxis-Hinweis: Datei enthält Audio und Video -> getrennte Weiterverarbeitung ist möglich.")
elif video_found:
    print("Praxis-Hinweis: Nur Video vorhanden.")
elif audio_found:
    print("Praxis-Hinweis: Nur Audio vorhanden.")
else:
    print("Praxis-Hinweis: Keine auswertbaren Audio-/Videostreams gefunden.")
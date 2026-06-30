# -*- coding: utf-8 -*-
"""
Created on Sun Jun 14 15:52:07 2026
AV-Programming SoSe 26
E. Wilk

Audio extrahieren
"""

import subprocess
import wave

INPUT = "trailer_1080p.ogg"
OUTPUT = "audio_for_analysis.wav"

subprocess.run([
    "ffmpeg", "-y",
    "-i", INPUT,
    "-map", "0:a:0",
    "-ac", "1",
    "-ar", "16000",
    OUTPUT
], check=True)

with wave.open(OUTPUT, "rb") as wf:
    channels = wf.getnchannels()
    framerate = wf.getframerate()
    nframes = wf.getnframes()
    duration = nframes / framerate

print("Erzeugte Datei:", OUTPUT)
print("Kanäle:", channels)
print("Samplerate:", framerate)
print("Dauer:", round(duration, 2), "s")
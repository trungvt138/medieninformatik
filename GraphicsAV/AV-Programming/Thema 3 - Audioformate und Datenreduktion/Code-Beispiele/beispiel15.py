# -*- coding: utf-8 -*-
"""
Created on Mon May 18 22:31:14 2026

AV-Programming
E. Wilk - HAW Hamburg
"""

from pathlib import Path
import subprocess

eingabe = "signal.wav"

subprocess.run(["ffmpeg", "-y", "-i", eingabe, "signal.flac"])  # -y: vorhandene Datei überschreiben
subprocess.run(["ffmpeg", "-y", "-i", eingabe, "-b:a", "128k", "signal_128k.mp3"])
subprocess.run(["ffmpeg", "-y", "-i", eingabe, "-b:a", "64k", "signal_64k.mp3"])

for datei in ["signal.wav", "signal.flac", "signal_128k.mp3", "signal_64k.mp3"]:
    groesse = Path(datei).stat().st_size
    print(datei, ":", groesse, "Bytes =", groesse * 8, "bit")
    
subprocess.run(["ffprobe", "-hide_banner", "-show_format", "-show_streams", "signal_128k.mp3"])
ergebnis = subprocess.run(
    ["ffprobe", "-hide_banner", "-show_format", "-show_streams", "signal_128k.mp3"],
    capture_output=True,
    text=True
)

print("\n Ergebnis der Analyse von signal_128k.mp3 mit ffprobe:")
print(ergebnis.stdout[:800])
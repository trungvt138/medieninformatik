# -*- coding: utf-8 -*-
"""
Created on Mon May 18 21:05:58 2026
AV-Programming
E. Wilk - HAW Hamburg
"""

from pathlib import Path
import numpy as np
import soundfile as sf

sr = 44100
dauer_s = 5.0
t = np.linspace(0, dauer_s, int(sr * dauer_s), endpoint=False)

signal = (
    0.5 * np.sin(2 * np.pi * 220 * t) +
    0.2 * np.sin(2 * np.pi * 440 * t)
).astype(np.float32)

sf.write("signal1.wav", signal, sr, subtype="PCM_24")
sf.write("signal1.flac", signal, sr, subtype="PCM_24")

wav_size_bytes = Path("signal1.wav").stat().st_size
flac_size_bytes = Path("signal1.flac").stat().st_size

print("Samplerate:", sr, "Hz")
print("Dauer:", dauer_s, "s")
print("WAV-Größe:", wav_size_bytes, "Bytes =", wav_size_bytes * 8, "bit")
print("FLAC-Größe:", flac_size_bytes, "Bytes =", flac_size_bytes * 8, "bit")
print("Verhältnis FLAC/WAV:", round(flac_size_bytes / wav_size_bytes, 3))

# Warum is FLAC kleiner als WAV? 
# Weil FLAC ein verlustfreies Kompressionsformat ist, das die Daten effizienter speichert, während WAV unkomprimiert ist. 
# FLAC verwendet Algorithmen, um redundante Informationen zu entfernen und die Dateigröße zu reduzieren, 
# ohne die Audioqualität zu beeinträchtigen.
# Fehler ist kleiner als Abtastwert -> weniger Bits bei gleicher Samplerate -> kleinere Dateigröße

# Warum ist mono_8bit.wav kleiner als signal.wav?
# andere Qualität: mono statt stereo, 8-bit statt 16-bit -> weniger Bits pro Sample -> kleinere Dateigröße
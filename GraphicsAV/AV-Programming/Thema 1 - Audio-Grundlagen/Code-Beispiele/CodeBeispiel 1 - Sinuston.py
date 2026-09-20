# -*- coding: utf-8 -*-
"""
Created on Sun Apr 19 18:16:27 2026

@author: idefix
"""

import numpy as np
from scipy.io.wavfile import write

sr = 2000                  # Samples pro Sekunde
dauer = 1.0                 # Sekunden
frequenz = 880              # Hz

t = np.linspace(0, dauer, int(sr * dauer), endpoint=False)
signal = 0.5 * np.sin(2 * np.pi * frequenz * t)

audio_int16 = (signal * 32767).astype(np.int16)
write("sinus_880Hz_2000sr.wav", sr, audio_int16)

print("Datei geschrieben:", "sinus_880Hz_2000sr.wav")
print("Shape:", audio_int16.shape)
print("Dtype:", audio_int16.dtype)
# -*- coding: utf-8 -*-
"""
Created on Sun Jul  5 20:12:05 2026

AV-Programming SoSe 26
E. Wilk
"""

import numpy as np
import matplotlib.pyplot as plt
import librosa

y, sr = librosa.load("audio_Ottos_Mops.wav", sr=None, mono=True)
#y, sr = librosa.load("sinus_440Hz.wav",sr=None, mono=True)

# librosa.yin:
# schätzt die Grundfrequenz (F0) frameweise.
# Dafür wird pro Frame untersucht, bei welcher Verzögerung
# sich das Signal möglichst periodisch wiederholt.
# Die gefundene Periodendauer wird anschließend in Hertz umgerechnet.

f0 = librosa.yin(
    y,
    fmin=librosa.note_to_hz("C2"),
    fmax=librosa.note_to_hz("C7"),
    sr=sr
)

times = librosa.times_like(f0, sr=sr)

plt.figure(figsize=(10, 4))
plt.plot(times, f0)
plt.xlabel("Zeit [s]")
plt.ylim(0,2500)
plt.ylabel("F0 [Hz]")
plt.title("Grundfrequenzschätzung mit YIN")
plt.tight_layout()
plt.show()
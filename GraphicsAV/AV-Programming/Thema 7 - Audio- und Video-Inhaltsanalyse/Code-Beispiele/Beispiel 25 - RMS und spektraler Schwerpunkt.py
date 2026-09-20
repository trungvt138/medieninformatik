# -*- coding: utf-8 -*-
"""
Created on Sun Jul  5 19:34:10 2026

AV-Programming SoSe 26
E. Wilk
"""

import matplotlib.pyplot as plt
import librosa


y, sr = librosa.load("audio_Ottos_Mops.wav", sr=None, mono=True)


rms = librosa.feature.rms(y=y)[0]
centroid = librosa.feature.spectral_centroid(y=y, sr=sr)[0]
times = librosa.times_like(rms, sr=sr)

fig, ax = plt.subplots(2, 1, figsize=(10, 6), sharex=True)
ax[0].plot(times, rms)
ax[0].set_ylabel("RMS")
ax[0].set_title("Aktivität / Energie")

ax[1].plot(times, centroid)
ax[1].set_ylabel("Hz")
ax[1].set_xlabel("Zeit [s]")
ax[1].set_title("Spektraler Schwerpunkt")

plt.tight_layout()
plt.show()
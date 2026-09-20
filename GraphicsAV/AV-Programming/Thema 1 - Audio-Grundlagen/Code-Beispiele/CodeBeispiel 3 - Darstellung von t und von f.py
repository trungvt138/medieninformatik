# -*- coding: utf-8 -*-
"""
Created on Sun Apr 19 18:20:41 2026

@author: idefix
"""

import numpy as np
import matplotlib.pyplot as plt
from scipy.io.wavfile import read

sr, data = read("sinus_440Hz.wav")

# Falls mehrkanalig, nur den ersten Kanal verwenden
if data.ndim == 2:
    data = data[:, 0]

# In float umwandeln und ggf. auf [-1, 1] normieren
if np.issubdtype(data.dtype, np.integer):
    max_val = np.iinfo(data.dtype).max
    data = data.astype(np.float64) / max_val
else:
    data = data.astype(np.float64)

N = len(data)
dauer = N / sr
zeit = np.arange(N) / sr



# FFT für reelle Signale
X = np.fft.rfft(data)
frequenzen = np.fft.rfftfreq(N, d=1/sr)

# Einseitiges Amplitudenspektrum
amplitude = np.abs(X) / N
amplitude[1:] *= 2   

anz_samples = 1000
anz_freqs = 2000

print("Samplerate:", sr)
print("Shape:", data.shape)
print("Dtype:", data.dtype)
print("Dauer in Sekunden:", dauer)

# Dominante Frequenz und deren Amplitude
idx = np.argmax(amplitude)
print("Max bei:", frequenzen[idx], "Hz")
print(f"Amplitude: {amplitude[idx]:.2f}")

plt.figure()
plt.plot(zeit[:anz_samples], data[:anz_samples])
plt.xlabel("Zeit in s")
plt.ylabel("Amplitude")
plt.title(f"Erste {anz_samples} Samples")
plt.show()

plt.figure()
plt.plot(frequenzen, amplitude)
plt.xlim(0, anz_freqs)
plt.xlabel("Frequenz in Hz")
plt.ylabel("Amplitude")
plt.title("Einseitiges Amplitudenspektrum")
plt.show()
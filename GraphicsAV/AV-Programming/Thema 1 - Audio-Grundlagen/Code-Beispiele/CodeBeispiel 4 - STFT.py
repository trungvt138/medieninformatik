# -*- coding: utf-8 -*-
"""
Created on Sun Apr 19 18:13:11 2026

@author: idefix
"""

import numpy as np
import matplotlib.pyplot as plt
from scipy.io.wavfile import write, read
from scipy.signal import stft

sr = 44100
dauer = 1.0
t = np.linspace(0, dauer, int(sr * dauer), endpoint=False)
# signal: zuerst 440 Hz, dann 880 Hz
signal = np.concatenate([    
	
	0.5 * np.sin(2 * np.pi * 440 * t[:len(t)//2]),    
	0.5 * np.sin(2 * np.pi * 880 * t[len(t)//2:])
])
write("signal.wav", sr, (signal * 32767).astype(np.int16))

sr2, signal_1 = read("signal.wav")
f, tt, Zxx = stft(signal_1, fs=sr2, nperseg=1024)
plt.pcolormesh(tt, f, np.abs(Zxx), shading="gouraud")
plt.ylim(0, 2000)
plt.xlabel("Zeit in s")
plt.ylabel("Frequenz in Hz")
plt.title("STFT des Signals")
plt.show()
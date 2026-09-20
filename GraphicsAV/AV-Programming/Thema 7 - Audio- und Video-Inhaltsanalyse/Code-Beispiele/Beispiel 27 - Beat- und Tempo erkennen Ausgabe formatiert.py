# -*- coding: utf-8 -*-
"""
Created on Sun Jul  5 20:16:51 2026

AV-Programming SoSe 26
E. Wilk
"""

import librosa

y, sr = librosa.load("Waltz_for_Ruth.wav", sr=None, mono=True)

tempo, beats = librosa.beat.beat_track(y=y, sr=sr, units="time")

print(f"Geschätztes Tempo (BPM): {float(tempo[0]):.2f}")
#print("Beat-Zeitpunkte (s)", beats[:20])

beat_times_formatted = [f"{b:.2f}" for b in beats[:20]]

print("Beat-Zeitpunkte (s):", beat_times_formatted)

#Aufgabe: erstellen Sie eine Metronomspur, und spielen Sie
# sie gleichzeitig mit der Musik ab.
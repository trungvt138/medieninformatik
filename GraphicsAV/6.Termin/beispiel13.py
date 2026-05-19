# -*- coding: utf-8 -*-
"""
Created on Mon May 18 20:31:26 2026
AV-Programming
E. Wilk - HAW Hamburg

"""

from pathlib import Path
import wave

datei = "beispiel.wav"

with wave.open(datei, "rb") as wf:  #rb: read only, binary mode
    kanaele = wf.getnchannels() #Anzahl der Kanäle (1=mono, 2=stereo)
    samplebreite = wf.getsampwidth() #Anzahl der Bytes pro Sample (z.B. 2 für 16-bit, 4 für 32-bit)
    samplerate = wf.getframerate() #Anzahl der Samples pro Sekunde (z.B. 44100 für CD-Qualität)
    frames = wf.getnframes()    #Anzahl der Samples insgesamt (für alle Kanäle zusammen, z.B. 44100 für 1 Sekunde bei 44100 Hz)

dauer = frames / samplerate     #Dauer der Audiodatei in Sekunden (z.B. 1 Sekunde bei 44100 Hz)
print(kanaele, samplebreite, samplerate, frames, dauer)
print("Dateigröße:", Path(datei).stat().st_size)  #Dateigröße in Bytes (z.B. 88200 für 1 Sekunde bei 44100 Hz, 16-bit, stereo)
size = samplerate * samplebreite * kanaele * dauer  #Berechnung der Dateigröße anhand der Parameter (z.B. 88200 für 1 Sekunde bei 44100 Hz, 16-bit, stereo)
print(f"Berechnete Dateigröße: {size:.0f}")  #Ausgabe der berechneten Dateigröße (z.B. 88200 für 1 Sekunde bei 44100 Hz, 16-bit, stereo)
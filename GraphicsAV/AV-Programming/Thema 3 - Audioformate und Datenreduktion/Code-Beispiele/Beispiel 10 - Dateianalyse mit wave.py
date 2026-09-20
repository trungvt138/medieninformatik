# -*- coding: utf-8 -*-
"""
Created on Mon May 18 20:35:08 2026
AV-Programming
E. Wilk - HAW Hamburg

"""

from pathlib import Path   # für den Zugriff auf die Dateigröße
import wave               # Standardmodul zum Lesen von WAV-Metadaten

datei = "beispiel.wav"    # Name der zu untersuchenden WAV-Datei

# WAV-Datei im Lesemodus öffnen
with wave.open(datei, "rb") as wf:
    kanaele = wf.getnchannels()      # Anzahl der Audiokanäle, z. B. 1=Mono, 2=Stereo
    wortbreite = wf.getsampwidth() # Wortbreite (Quantisierung mit) in Bytes, z. B. 2 Bytes = 16 Bit
    samplerate = wf.getframerate()   # Abtastrate in Hz, z. B. 44100
    frames = wf.getnframes()         # Anzahl der Samples pro Kanal

# Dauer der Datei in Sekunden berechnen
dauer = frames / samplerate

# Datenmenge aus fs, Anzahl Kanäle, Bytes pro Abtastwert, Dauer
datenmenge = kanaele*samplerate*wortbreite*dauer

# Dateigröße auf Dateisystemebene auslesen
dateigroesse = Path(datei).stat().st_size

# Ergebnisse ausgeben
print("Datei:", datei)
print("Kanäle:", kanaele)
print("Wortbreite (Bytes):", wortbreite)
print("Samplerate (Hz):", samplerate)
print("Frames:", frames)
print("Dauer (s):", dauer)
print("Datenmenge (Bytes):", datenmenge)
print("Dateigröße (Bytes):", dateigroesse)
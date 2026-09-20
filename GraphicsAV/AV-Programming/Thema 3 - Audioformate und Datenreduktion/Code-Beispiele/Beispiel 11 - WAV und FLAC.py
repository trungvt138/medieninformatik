# -*- coding: utf-8 -*-
"""
Created on Mon May 18 21:09:19 2026
AV-Programming
E. Wilk - HAW Hamburg
"""

from pathlib import Path
import numpy as np
import soundfile as sf

# Abtastrate in Hertz: 44100 Samples pro Sekunde
sr = 44100

# Dauer des Signals in Sekunden
dauer_s = 3.0

# Zeitachse erzeugen:
# endpoint=False sorgt dafür, dass der Endwert nicht mitgenommen wird
t = np.linspace(0, dauer_s, int(sr * dauer_s), endpoint=False)

# Einfaches Testsignal erzeugen:
# zwei Sinustöne mit 220 Hz und 440 Hz werden addiert
signal = (
    0.5 * np.sin(2 * np.pi * 220 * t) +
    0.2 * np.sin(2 * np.pi * 440 * t)
).astype(np.float32)

# Signal als WAV-Datei speichern:
# subtype="PCM_16" bedeutet 16-Bit-PCM, also nicht datenreduziert Abtastwerte
sf.write("signal.wav", signal, sr, subtype="PCM_16")

# Dasselbe Signal als FLAC-Datei speichern:
# Auch hier PCM_16 als Basis,
# FLAC speichert diese Daten verlustfrei datenreduziert
sf.write("signal.flac", signal, sr, subtype="PCM_16")

# Dateigrößen auf Dateisystemebene auslesen
wav_size_bytes = Path("signal.wav").stat().st_size
flac_size_bytes = Path("signal.flac").stat().st_size

# Ergebnisse mit Einheiten ausgeben
print("Samplerate:", sr, "Hz")
print("Dauer:", dauer_s, "s")
print("WAV-Größe:", wav_size_bytes, "Bytes =", wav_size_bytes * 8, "bit")
print("FLAC-Größe:", flac_size_bytes, "Bytes =", flac_size_bytes * 8, "bit")

# Verhältnis der beiden Dateigrößen berechnen
# Ein Wert kleiner als 1 bedeutet: FLAC ist kleiner als WAV
print("Verhältnis FLAC/WAV:", round(flac_size_bytes / wav_size_bytes, 3))
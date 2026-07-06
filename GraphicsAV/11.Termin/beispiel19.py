# -*- coding: utf-8 -*-
"""
Beispiel 19: Audio bearbeiten (Thema 6)

  - Audio vom Mikrofon aufnehmen
  - +6 dB Gain
  - 20 ms-Fenster bilden
  - RMS berechnen
  - Abschnitte mit Signalen unter einem Schwellwert herausschneiden
"""

import wave

import numpy as np
import sounddevice as sd
import subprocess

fgr = 200
subprocess.run([
    
])

SR = 16000
DURATION_S = 5


def read_wav_mono(path):
    with wave.open(path, "rb") as wf:
        sr = wf.getframerate()
        raw = wf.readframes(wf.getnframes())
        x = np.frombuffer(raw, dtype=np.int16)
    return sr, x


def write_wav_mono(path, sr, x):
    with wave.open(path, "wb") as wf:
        wf.setnchannels(1)
        wf.setsampwidth(2)
        wf.setframerate(sr)
        wf.writeframes(x.astype(np.int16).tobytes())


def record_wav_mono(sr, duration_s):
    print(f"Aufnahme startet ({duration_s} s) ...")
    x = sd.rec(int(duration_s * sr), samplerate=sr, channels=1, dtype="int16")
    sd.wait()
    print("Aufnahme beendet.")
    return x.reshape(-1)


x = record_wav_mono(SR, DURATION_S)
write_wav_mono("speech.wav", SR, x)

sr, x = read_wav_mono("speech.wav")
gain_db = 6
gain = 10 ** (gain_db / 20)
x_boost = np.clip(x.astype(np.float32) * gain, -32768, 32767).astype(np.int16)
frame_len = int(0.02 * sr)
usable = len(x_boost) // frame_len * frame_len
frames = x_boost[:usable].reshape(-1, frame_len)
rms = np.sqrt(np.mean(frames.astype(np.float32) ** 2, axis=1))
threshold = 500
keep = rms > threshold
x_short = frames[keep].reshape(-1)
write_wav_mono("speech_edit.wav", sr, x_short)

print(f"Fenster behalten: {int(keep.sum())} von {len(frames)}")
print("Ergebnis gespeichert in speech_edit.wav")

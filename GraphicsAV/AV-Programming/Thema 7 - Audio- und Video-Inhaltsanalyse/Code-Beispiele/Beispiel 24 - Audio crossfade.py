# -*- coding: utf-8 -*-
"""
Created on Mon Jun 29 20:43:43 2026


AV-Programming
E. Wilk - HAW Hamburg
"""

import wave
import numpy as np

def read_wav_mono(path):
    with wave.open(path, "rb") as wf:
        sr = wf.getframerate()
        ch = wf.getnchannels()
        sw = wf.getsampwidth()
        n = wf.getnframes()
        raw = wf.readframes(n)

    if sw != 2:
        raise ValueError("Beispiel nur für 16-Bit-PCM-WAV")

    x = np.frombuffer(raw, dtype=np.int16)
    if ch > 1:
        x = x.reshape(-1, ch).mean(axis=1).astype(np.int16)
    return sr, x.astype(np.float32)

def write_wav_mono(path, sr, x):
    x = np.clip(x, -32768, 32767).astype(np.int16)
    with wave.open(path, "wb") as wf:
        wf.setnchannels(1)
        wf.setsampwidth(2)
        wf.setframerate(sr)
        wf.writeframes(x.tobytes())

sr1, a = read_wav_mono("a1.wav")
sr2, b = read_wav_mono("b1.wav")
if sr1 != sr2:
    raise ValueError("Sampleraten müssen gleich sein.")
sr = sr1

fade_dur_s = 2.0
fade_n = int(fade_dur_s * sr)

fade_out = np.linspace(1.0, 0.0, fade_n, endpoint=True)
fade_in  = np.linspace(0.0, 1.0, fade_n, endpoint=True)

head = a[:-fade_n]
a_tail = a[-fade_n:] * fade_out
b_head = b[:fade_n] * fade_in
cross = a_tail + b_head
tail = b[fade_n:]

y = np.concatenate([head, cross, tail])
write_wav_mono("audio_crossfade.wav", sr, y)
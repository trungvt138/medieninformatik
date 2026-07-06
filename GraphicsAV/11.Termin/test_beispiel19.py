# -*- coding: utf-8 -*-
"""
Test für Beispiel 19 (Thema 6, Folie 7): Audio bearbeiten
  - WAV lesen
  - +6 dB Gain
  - 20 ms-Fenster bilden
  - RMS berechnen
  - Abschnitte mit Signalen unter einem Schwellwert herausschneiden

Im Skript sind read_wav_mono/write_wav_mono nicht ausimplementiert und
"speech.wav" existiert nicht. Dieses Skript ergänzt beides:
  1. Hilfsfunktionen read_wav_mono / write_wav_mono auf Basis von `wave`
  2. eine synthetische Testdatei mit abwechselnd lauten und leisen
     Abschnitten, damit der Schwellwert-Schnitt nachprüfbar ist
  3. die Original-Logik aus beispiel19.py
  4. Prüfungen (Asserts), die das erwartete Verhalten bestätigen
"""

import wave

import numpy as np


# ─────────────────────────────────────────────────────────────
# Hilfsfunktionen (im Skript nicht ausgeschrieben)
# ─────────────────────────────────────────────────────────────

def read_wav_mono(path):
    with wave.open(path, "rb") as wf:
        assert wf.getnchannels() == 1, "erwartet Mono-WAV"
        assert wf.getsampwidth() == 2, "erwartet 16-bit PCM"
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


# ─────────────────────────────────────────────────────────────
# Testdatei erzeugen: laut - leise - laut - leise (je 0.5 s)
# ─────────────────────────────────────────────────────────────

def make_test_wav(path, sr=16000):
    t_seg = np.arange(int(0.5 * sr)) / sr
    loud = (8000 * np.sin(2 * np.pi * 440 * t_seg)).astype(np.int16)
    quiet = (50 * np.sin(2 * np.pi * 440 * t_seg)).astype(np.int16)
    x = np.concatenate([loud, quiet, loud, quiet])
    write_wav_mono(path, sr, x)
    return sr, x


# ─────────────────────────────────────────────────────────────
# Ablauf aus Beispiel 19
# ─────────────────────────────────────────────────────────────

def main():
    in_path = "speech_test.wav"
    out_path = "speech_test_edit.wav"

    make_test_wav(in_path)

    sr, x = read_wav_mono(in_path)

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
    write_wav_mono(out_path, sr, x_short)

    # ── Prüfungen ──
    n_frames = len(frames)
    n_kept = int(keep.sum())
    print(f"Eingabe:        {len(x)} Samples, {sr} Hz")
    print(f"Fenster gesamt: {n_frames} (à {frame_len} Samples = 20 ms)")
    print(f"Fenster behalten: {n_kept} von {n_frames}")
    print(f"Ausgabe:        {len(x_short)} Samples -> {out_path}")

    # Gain wirkt: Spitzenwert des lauten Segments sollte ~6 dB lauter sein (mit Clipping-Grenze)
    expected_peak = min(8000 * gain, 32767)
    assert abs(int(x_boost[: int(0.5 * sr)].max()) - expected_peak) < 5, "Gain falsch angewendet"

    # Schwellwert-Schnitt wirkt: es wurden tatsächlich Fenster entfernt
    assert n_kept < n_frames, "es wurden keine leisen Fenster herausgeschnitten"
    assert len(x_short) < len(x), "Ausgabe ist nicht kürzer als Eingabe"

    # alle behaltenen Fenster liegen tatsächlich über dem Schwellwert
    kept_rms = rms[keep]
    assert np.all(kept_rms > threshold), "ein behaltenes Fenster liegt unter dem Schwellwert"

    # die rausgeschnittenen Fenster lagen tatsächlich unter dem Schwellwert
    dropped_rms = rms[~keep]
    assert np.all(dropped_rms <= threshold), "ein entferntes Fenster lag über dem Schwellwert"

    print("Alle Prüfungen erfolgreich.")


if __name__ == "__main__":
    main()

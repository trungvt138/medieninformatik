# -*- coding: utf-8 -*-
"""
Created on Sat Jul 11 21:35:40 2026

AV-Programming SoSe 26
E. Wilk
"""
import csv
import numpy as np
import tensorflow as tf
import tensorflow_hub as hub
from scipy.io import wavfile

# --------------------------------------------------
# 1) Hilfsfunktion: Class-Namen aus der YAMNet-Class-Map laden
# --------------------------------------------------
def class_names_from_csv(class_map_csv_path):
    class_names = []
    with tf.io.gfile.GFile(class_map_csv_path) as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            class_names.append(row["display_name"])
    return class_names

# --------------------------------------------------
# 2) Modell laden
# --------------------------------------------------
model = hub.load("https://tfhub.dev/google/yamnet/1")

# Die Labels-Datei liegt in den Modell-Assets
class_map_path = model.class_map_path().numpy()
class_names = class_names_from_csv(class_map_path)

# --------------------------------------------------
# 3) Audio laden
# --------------------------------------------------
sr, wav = wavfile.read("audio_Ottos_Mops.wav")
wav = wav.astype(np.float32)

# Falls Stereo: auf Mono reduzieren
if wav.ndim == 2:
    wav = wav.mean(axis=1)

# Auf [-1, 1] normieren
wav = wav / np.max(np.abs(wav))

# --------------------------------------------------
# 4) Modell ausführen
# --------------------------------------------------
scores, embeddings, spectrogram = model(wav)

# Über alle Zeitfenster mitteln
mean_scores = tf.reduce_mean(scores, axis=0)

# Top-5-Indizes bestimmen
top5 = tf.argsort(mean_scores, direction="DESCENDING")[:5]

print("Top-5-Klassen:")
for i in top5.numpy():
    print(i, class_names[i], float(mean_scores[i]))
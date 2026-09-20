# -*- coding: utf-8 -*-
"""
Created on Sat Jul 11 20:48:29 2026

AV-Programming SoSe 26
E. Wilk
"""

import cv2
import numpy as np
import matplotlib.pyplot as plt

#cap = cv2.VideoCapture("Ottos_Mops.mp4")
cap = cv2.VideoCapture("Sintel_Trailer.mp4")

prev_hist = None
scores = []
fps = cap.get(cv2.CAP_PROP_FPS) or 25.0

while True:
    ret, frame = cap.read()
    if not ret:
        break
# Für jedes Frame wird ein Farbhistogramm berechnet.
# Große Unterschiede zwischen aufeinanderfolgenden Histogrammen
# deuten auf starke visuelle Änderungen und mögliche Schnitte hin.    

    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    hist = cv2.calcHist([hsv], [0, 1], None, [32, 32], [0, 180, 0, 256])
    hist = cv2.normalize(hist, hist).flatten()

    if prev_hist is not None:
        score = cv2.compareHist(prev_hist, hist, cv2.HISTCMP_BHATTACHARYYA)
        scores.append(score)

    prev_hist = hist

cap.release()

times = np.arange(len(scores)) / fps

plt.figure(figsize=(10, 4))
plt.plot(times, scores)
plt.xlabel("Zeit [s]")
plt.ylabel("Histogrammdifferenz")
plt.title("Hinweise auf Shot-/Schnittwechsel")
plt.tight_layout()
plt.show()
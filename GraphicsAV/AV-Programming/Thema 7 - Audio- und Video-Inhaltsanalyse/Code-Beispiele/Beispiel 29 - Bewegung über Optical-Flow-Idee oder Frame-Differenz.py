# -*- coding: utf-8 -*-
"""
Created on Sat Jul 11 21:15:23 2026

AV-Programming SoSe 26
E. Wilk
"""

import cv2
import numpy as np
import matplotlib.pyplot as plt

cap = cv2.VideoCapture("Sintel_Trailer.mp4")
ret, prev = cap.read()
if not ret:
    raise ValueError("Video konnte nicht gelesen werden.")

prev_gray = cv2.cvtColor(prev, cv2.COLOR_BGR2GRAY)
fps = cap.get(cv2.CAP_PROP_FPS) or 25.0

motion_scores = []

while True:
    ret, frame = cap.read()
    if not ret:
        break
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    diff = cv2.absdiff(gray, prev_gray)
    motion_scores.append(diff.mean())
    prev_gray = gray

cap.release()

times = np.arange(len(motion_scores)) / fps
plt.figure(figsize=(10, 4))
plt.plot(times, motion_scores)
plt.xlabel("Zeit [s]")
plt.ylabel("Bewegungsmaß")
plt.title("Einfache Bewegungskurve")
plt.tight_layout()
plt.show()
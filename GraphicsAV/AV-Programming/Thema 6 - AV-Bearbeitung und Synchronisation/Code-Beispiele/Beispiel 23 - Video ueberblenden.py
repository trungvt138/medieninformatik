# -*- coding: utf-8 -*-
"""
Created on Mon Jun 29 19:21:49 2026

AV-Programming
E. Wilk - HAW Hamburg
"""

import cv2

cap1 = cv2.VideoCapture("Ottos_Mops.mp4")
cap2 = cv2.VideoCapture("Wilhelm_Busch.mp4")

fps = cap1.get(cv2.CAP_PROP_FPS) or 25.0
width = int(cap1.get(cv2.CAP_PROP_FRAME_WIDTH))
height = int(cap1.get(cv2.CAP_PROP_FRAME_HEIGHT))

writer = cv2.VideoWriter(
    "crossfade.mp4",
    cv2.VideoWriter_fourcc(*"mp4v"),
    fps,
    (width, height)
)

fade_frames = int(2.0 * fps)  # 2 Sekunden Überblendung

# Teil 1: clip1 bis zur Überblendung
frames1 = []
while True:
    ok, frame = cap1.read()
    if not ok:
        break
    frames1.append(frame)

# Teil 2: clip2 komplett laden
frames2 = []
while True:
    ok, frame = cap2.read()
    if not ok:
        break
    frames2.append(frame)

# Sicherheitscheck
n1 = len(frames1)
n2 = len(frames2)

# 1) erster Teil von clip1 ohne die letzten fade_frames
for i in range(max(0, n1 - fade_frames)):
    writer.write(frames1[i])

# 2) Überblendung
for i in range(fade_frames):
    if n1 - fade_frames + i < 0 or i >= n2:
        break

    frame1 = frames1[n1 - fade_frames + i]
    frame2 = frames2[i]

    alpha = 1.0 - (i / fade_frames)
    beta = 1.0 - alpha

    blended = cv2.addWeighted(frame1, alpha, frame2, beta, 0)
    writer.write(blended)

# 3) Rest von clip2 nach der Überblendung
for i in range(fade_frames, n2):
    writer.write(frames2[i])

cap1.release()
cap2.release()
writer.release()
print("Export fertig.")
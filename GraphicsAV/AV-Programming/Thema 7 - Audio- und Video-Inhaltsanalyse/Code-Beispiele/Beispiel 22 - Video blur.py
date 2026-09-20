# -*- coding: utf-8 -*-
"""
Created on Mon Jun 29 19:11:30 2026

AV-Programming
E. Wilk - HAW Hamburg
"""

import cv2

cap = cv2.VideoCapture("Ottos_Mops.mp4")

fps = cap.get(cv2.CAP_PROP_FPS) or 25.0
width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))

writer = cv2.VideoWriter(
    "video_blur.mp4",
    cv2.VideoWriter_fourcc(*"mp4v"),
    fps,
    (width, height)
)

start_s = 2.0
end_s = 5.0
frame_idx = 0

while True:
    ok, frame = cap.read()
    if not ok:
        break

    t = frame_idx / fps
    if t >= end_s:
        break

    if t >= start_s:
        out = cv2.GaussianBlur(frame, (31, 31), 0)
        writer.write(out)

    frame_idx += 1

cap.release()
writer.release()
print("Export fertig.")
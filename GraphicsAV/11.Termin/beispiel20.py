# -*- coding: utf-8 -*-
"""
Beispiel 20: Video bearbeiten (Thema 6)

  - Zeitfenster Sekunde 2 bis Sekunde 6
  - Umwandlung in Graustufen
  - Helligkeit um den Wert 30 erhöhen, dabei Clipping vermeiden
  - Export mit VideoWriter
"""

import os
import subprocess

import cv2
import numpy as np

INPUT = "OttosMops.webm"
OUTPUT = "ottos_mops_grau_hell.mp4"
SILENT_VIDEO = "ottos_mops_grau_hell_silent.mp4"

cap = cv2.VideoCapture(INPUT)
fps = cap.get(cv2.CAP_PROP_FPS)
width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))

fourcc = cv2.VideoWriter_fourcc(*"mp4v")
writer = cv2.VideoWriter(SILENT_VIDEO, fourcc, fps, (width, height))

start_s = 2.0
end_s = 6.0
frame_idx = 0

while True:  # Schleife bis Dateiende
    ok, frame = cap.read()
    if not ok:
        break

    t = frame_idx / fps

    if start_s <= t < end_s:
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)  # Graustufenbild mit nur 1 Kanal
        gray3 = cv2.cvtColor(gray, cv2.COLOR_GRAY2BGR)  # Umwandeln in 3kanaliges Bild, gleicher Grauwert auf jedem Kanal

        bright = np.clip(gray3.astype(np.int16) + 30, 0, 255).astype(np.uint8)
        writer.write(bright)

    frame_idx += 1

cap.release()
writer.release()

# Audio aus dem Originalvideo für dasselbe Zeitfenster extrahieren
# und mit dem stummen, bearbeiteten Video zusammenführen
subprocess.run([
    "ffmpeg", "-y",
    "-i", SILENT_VIDEO,
    "-ss", str(start_s), "-t", str(end_s - start_s), "-i", INPUT,
    "-map", "0:v:0", "-map", "1:a:0",
    "-c:v", "copy", "-c:a", "aac",
    OUTPUT
], check=True)

os.remove(SILENT_VIDEO)

print(f"Eingabedatei:  {INPUT}")
print(f"FPS:           {fps:.2f}")
print(f"Zeitfenster:   {start_s}s - {end_s}s")
print(f"Ausgabedatei:  {OUTPUT}")

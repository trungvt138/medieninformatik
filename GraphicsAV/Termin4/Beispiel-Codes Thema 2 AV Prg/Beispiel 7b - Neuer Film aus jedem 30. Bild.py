"""
created: 03-05-2026
for: AV programming

Öffnet das File "video.mp4", speichert jedes 30. Bild und fügt diese
Bilder zu einem neuen Video zusammen
"""
import cv2 as cv
import os

cap = cv.VideoCapture("video.mp4")
if not cap.isOpened():
    print("Video konnte nicht geöffnet werden.")
    raise SystemExit

frame_nr = 0
saved_nr = 0
saved_files = []

while True:
    ret, frame = cap.read()
    if not ret:
        break

    if frame_nr % 30 == 0:    # diesen Wert auch einmal ändern
        filename = f"frame_{saved_nr:03d}.jpg"
        cv.imwrite(filename, frame)
        print("gespeichert:", filename)
        saved_files.append(filename)
        saved_nr += 1

    frame_nr += 1

cap.release()

if not saved_files:
    print("Keine JPG-Dateien gespeichert.")
    raise SystemExit

# Erstes Bild laden, um die Bildgröße zu bestimmen
first_frame = cv.imread(saved_files[0])
height, width, channels = first_frame.shape

# VideoWriter anlegen
# fps hier frei wählbar; 1.0 bedeutet: 1 Bild pro Sekunde
fps = 5.0
name = "neues_video.mp4"
out = cv.VideoWriter(
    name,
    cv.VideoWriter_fourcc(*"mp4v"),  # = Codec-Angabe für das Schreiben des Videos
                                    # = FourCC-Code aus den vier Zeichen m p 4 v
    fps,
    (width, height)
)

for filename in saved_files:
    img = cv.imread(filename)
    if img is None:
        print("Datei konnte nicht gelesen werden:", filename)
        continue
    out.write(img)

out.release()

print(f"Neues Video gespeichert als: {name}")
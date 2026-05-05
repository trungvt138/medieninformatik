"""
created: 03-05-2026
for: AV programming

Öffnet das File "video.mp4" und speichert jedes 30. Bild
"""
import cv2 as cv

cap = cv.VideoCapture("video.mp4")
if not cap.isOpened():
    print("Video konnte nicht geöffnet werden.")
    raise SystemExit

frame_nr = 0
saved_nr = 0
while True:
    ret, frame = cap.read()
    if not ret:
        break
    if frame_nr % 30 == 0:
        filename = f"frame_{saved_nr:03d}.jpg"
        cv.imwrite(filename, frame)
        print("gespeichert:", filename)
        saved_nr += 1
    frame_nr += 1
cap.release()

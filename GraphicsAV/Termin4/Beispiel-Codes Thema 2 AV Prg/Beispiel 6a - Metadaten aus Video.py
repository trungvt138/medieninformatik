"""
created: 03-05-2026
for: AV programming

Öffnet das File "video.mp4", liest erstes Frame ein
und zeigt die Metadaten an.
"""
import cv2 as cv

cap = cv.VideoCapture("video.mp4")

if not cap.isOpened():
    print("Video konnte nicht geöffnet werden.")
    raise SystemExit

width = cap.get(cv.CAP_PROP_FRAME_WIDTH)
height = cap.get(cv.CAP_PROP_FRAME_HEIGHT)
fps = cap.get(cv.CAP_PROP_FPS)
frame_count = cap.get(cv.CAP_PROP_FRAME_COUNT)
dauer = frame_count / fps

print(f"Breite: {width:.0f}")
print(f"Höhe: {height:.0f}")
print(f"FPS: {fps:.2f}")
print(f"Frames: {frame_count:.0f}")
print(f"Dauer: {dauer:.0f}s")

cap.release()

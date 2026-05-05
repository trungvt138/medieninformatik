"""
created: 03-05-2026
for: AV programming

Öffnet den ersten Frame aus "video.mp4" und wandelt in Graustufen um
"""
import cv2 as cv

cap = cv.VideoCapture("video.mp4")

if not cap.isOpened():
    print("Video konnte nicht geöffnet werden.")
    raise SystemExit

ret, frame = cap.read()
if not ret:
    print("Kein Frame lesbar.")
    cap.release()
    raise SystemExit

gray = cv.cvtColor(frame, cv.COLOR_BGR2GRAY)
print("Farbbild:", frame.shape)
print("Graubild:", gray.shape)
cv.imwrite("frame_color.jpg", frame)
cv.imwrite("frame_gray.jpg", gray)
cap.release()

import cv2 as cv

cap = cv.VideoCapture("video.mp4")

if not cap.isOpened():
    print("Video konnte nicht geöffnet werden.")
    raise SystemExit

ret, frame = cap.read()

if ret:
    print("Shape des ersten Frames:", frame.shape)

cap.release()
import cv2 as cv

SPEED_FACTOR = 0.5  # > 1 = Zeitraffer, < 1 = Zeitlupe

cap = cv.VideoCapture("video.mp4")
fps = cap.get(cv.CAP_PROP_FPS)          # Original-FPS auslesen
delay_ms = max(1, int(1000 / (fps * SPEED_FACTOR)))

while True:
    ret, frame = cap.read()
    if not ret:
        break
    cv.imshow("Video", frame)
    if cv.waitKey(delay_ms) & 0xFF == ord("q"):
        break

cap.release()
cv.destroyAllWindows()
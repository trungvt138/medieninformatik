"""
created: 03-05-2026
for: AV programming

Öffnet das File "video.mp4", liest erstes Frame ein
und zeigt das Kamerabild an.
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

print(f"Breite: {width:.0f}")
print(f"Höhe: {height:.0f}")
print(f"FPS: {fps:.2f}")
print(f"Frames: {frame_count:.0f}")

ziel_frame = 100  # z. B. den 100. Frame anzeigen

if ziel_frame >= frame_count:
    print("Der gewünschte Frame liegt außerhalb des Videos.")
    cap.release()
    raise SystemExit

cap.set(cv.CAP_PROP_POS_FRAMES, ziel_frame)

ret, frame = cap.read()

if not ret:
    print("Der gewünschte Frame konnte nicht gelesen werden.")
    cap.release()
    raise SystemExit

print("angezeigter Frame:", ziel_frame)
print("frame.shape:", frame.shape)

cv.imshow(f"Frame {ziel_frame}", frame)
cv.waitKey(0)

cap.release()
cv.destroyAllWindows()
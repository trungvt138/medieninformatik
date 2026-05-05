"""
created: 03-05-2026
for: AV programming

Öffnet die interne Kamera über OpenCV, liest fortlaufend Videoframes ein
und zeigt das Kamerabild in Echtzeit an.
Die Schleife wird durch Drücken der Taste "q" im Bildfenster beendet.
"""
import cv2 as cv

cap = cv.VideoCapture(0)

if not cap.isOpened():
    print("Kamera konnte nicht geöffnet werden.")
    raise SystemExit

ret, frame = cap.read()

if not ret:
    print("Kein Frame lesbar.")
    cap.release()
    raise SystemExit

print("Erster Frame:", frame.shape)

while True:
    ret, frame = cap.read()
    if not ret:
        print("Frame konnte nicht gelesen werden.")
        break

    cv.imshow("Kamerabild", frame)

    if cv.waitKey(1) == ord("q"):  # Achtung: q im Kamerabild drücken
        break

cap.release()
cv.destroyAllWindows()
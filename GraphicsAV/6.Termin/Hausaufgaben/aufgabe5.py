import cv2 as cv
import numpy as np

video_path = "video.mp4"
output_image = "hellstes_frame.jpg"

cap = cv.VideoCapture(video_path)

if not cap.isOpened():
    print("Video konnte nicht geöffnet werden.")
    raise SystemExit

fps = cap.get(cv.CAP_PROP_FPS)

# 0. Initialisieren
max_brightness = 0
max_frame = 0
max_frame_nr = 0
frame_nr = 0

while True:
    ret, frame = cap.read()
    if not ret:
        break

    # 1. Frame in Graustufen umwandeln
    frame_bw = cv.cvtColor(frame, cv.COLOR_BGR2GRAY)
    
    # 2. mittlere Helligkeit berechnen
    brightness = np.mean(frame_bw)
    # 3. prüfen, ob dieses Frame bisher das hellste ist
    #    falls ja: Helligkeitswert, Frame und Framenummer merken
    
    if (brightness > max_brightness):
        max_brightness = brightness
        max_frame = frame
        max_frame_nr = frame_nr

    frame_nr += 1

cap.release()

# 4. Zeitpunkt in Sekunden berechnen
max_time = max_frame_nr / fps

# 5. hellstes Frame speichern
cv.imwrite(output_image, max_frame)
print("gespeichert:", output_image)

# 6. Ergebnisse ausgeben
print(f"Hellstes Frame: Nr. {max_frame_nr}")
print(f"Zeitpunkt: {max_time:.2f} Sekunden")
print(f"Mittlere Helligkeit: {max_brightness:.2f}")

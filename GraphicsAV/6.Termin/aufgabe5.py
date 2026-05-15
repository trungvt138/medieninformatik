import cv2 as cv

video_path = "video.mp4"
output_image = "hellstes_frame.jpg"

cap = cv.VideoCapture(video_path)

if not cap.isOpened():
    print("Video konnte nicht geöffnet werden.")
    raise SystemExit

fps = cap.get(cv.CAP_PROP_FPS)

# 0. Initialisieren

while True:
    ret, frame = cap.read()
    if not ret:
        break

    # 1. Frame in Graustufen umwandeln

    # 2. mittlere Helligkeit berechnen

    # 3. prüfen, ob dieses Frame bisher das hellste ist
    #    falls ja: Helligkeitswert, Frame und Framenummer merken

    frame_nr += 1

cap.release()

# 4. Zeitpunkt in Sekunden berechnen

# 5. hellstes Frame speichern

# 6. Ergebnisse ausgeben

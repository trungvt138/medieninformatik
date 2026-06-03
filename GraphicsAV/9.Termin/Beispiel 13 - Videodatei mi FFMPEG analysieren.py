# -*- coding: utf-8 -*-
"""
Created on Sun May 31 21:23:05 2026

AV Programming - Medieninformatik
SoSe 2026

"""

import json
import subprocess
import cv2

# ------------------------------------------------------------
# Name der Videodatei, die untersucht und abgespielt werden soll
# ------------------------------------------------------------
video = "video.mp4"

# ------------------------------------------------------------
# 1) Metadaten mit ffprobe auslesen
#
# ffprobe ist ein Werkzeug aus dem FFmpeg-Paket.
# Es kann Container- und Streaminformationen aus Mediendateien
# strukturiert ausgeben, hier im JSON-Format.
# ------------------------------------------------------------

cmd = [
    "ffprobe",
    "-v", "error",              # nur Fehlermeldungen ausgeben
    "-print_format", "json",    # Ausgabe als JSON
    "-show_format",             # Informationen zum Containerformat
    "-show_streams",            # Informationen zu allen enthaltenen Streams
    video
]

# subprocess.run() führt den externen Befehl aus.
# capture_output=True sammelt stdout/stderr in Python ein.
# text=True liefert Text statt Bytes.
# check=True erzeugt eine Exception, falls der Befehl fehlschlägt.
result = subprocess.run(cmd, capture_output=True, text=True, check=True)

# Die JSON-Ausgabe von ffprobe wird in ein Python-Dictionary umgewandelt.
info = json.loads(result.stdout)

# ------------------------------------------------------------
# 2) Den Videostream aus der ffprobe-Ausgabe herausfiltern
#
# Eine Mediendatei kann mehrere Streams enthalten:
# z. B. Video, Audio, Untertitel.
# Hier: Stream mit codec_type == "video".
# ------------------------------------------------------------
video_stream = next(s for s in info["streams"] if s["codec_type"] == "video")

# ------------------------------------------------------------
# 3) Wichtige Informationen ausgeben
#
# Dabei werden zwei Ebenen unterschieden:
# - Containerinformationen (format)
# - Streaminformationen (video_stream)
# ------------------------------------------------------------

print("Datei:", video)

# format_name beschreibt das Containerformat aus Sicht von FFmpeg.
# Bei MP4-Dateien erscheint hier oft eine Formatfamilie wie
# "mov,mp4,m4a,3gp,3g2,mj2".
print("Container:", info["format"]["format_name"])

# codec_name bezeichnet den eigentlichen Videocodec,
# z. B. h264, hevc, av1, vp9 ...
print("Videocodec:", video_stream.get("codec_name"))

# width und height geben die räumliche Auflösung des Videostreams an.
print("Auflösung:", video_stream.get("width"), "x", video_stream.get("height"))

# pix_fmt beschreibt das Pixel-Format, z. B. yuv420p.
# Das ist wichtig für die interne Repräsentation der Bilddaten.
print("Pixel-Format:", video_stream.get("pix_fmt"))

# avg_frame_rate wird von ffprobe oft als Bruch dargestellt, z. B. "30/1".
print("Bildrate:", video_stream.get("avg_frame_rate"))

# bit_rate auf Format-Ebene ist die Gesamtbitrate der Datei.
# Sie kann Video, Audio und weitere Streams umfassen.
print("Bitrate gesamt:", info["format"].get("bit_rate"))

# duration ist die Gesamtdauer der Datei in Sekunden.
print("Dauer:", info["format"].get("duration"))

# ------------------------------------------------------------
# 4) Video mit OpenCV abspielen
#
# ------------------------------------------------------------

cap = cv2.VideoCapture(video)

# Prüfen, ob die Datei überhaupt erfolgreich geöffnet wurde.
if not cap.isOpened():
    raise OSError(f"Video konnte nicht geöffnet werden: {video}")

# OpenCV kann die Bildrate ebenfalls auslesen.
# Diese Information nutzen wir hier, um eine sinnvolle Wartezeit
# zwischen zwei Frames für die Wiedergabe zu berechnen.
fps = cap.get(cv2.CAP_PROP_FPS)

# delay gibt an, wie viele Millisekunden zwischen zwei Frames
# gewartet werden soll.
# Beispiel:
# 30 fps -> 1000 / 30 ≈ 33 ms pro Frame
# Falls fps nicht sinnvoll ausgelesen werden kann, wird 33 ms genutzt.
delay = int(1000 / fps) if fps > 0 else 33

print("\nWiedergabe startet – Taste 'q' beendet.")

# ------------------------------------------------------------
# 5) Hauptschleife für die Wiedergabe
#
# cap.read() liest jeweils ein Frame.
# ret gibt an, ob das Lesen erfolgreich war.
# frame enthält das eigentliche Bild als Array.
# ------------------------------------------------------------
while True:
    ret, frame = cap.read()

    # Wenn kein weiteres Frame gelesen werden kann,
    # ist das Video zu Ende oder es gab ein Problem.
    if not ret:
        break

    # Das aktuelle Frame in einem Fenster anzeigen.
    cv2.imshow("Video", frame)

    # cv2.waitKey(delay) wartet 'delay' Millisekunden.
    # Gleichzeitig können Tastatureingaben abgefragt werden.
    # Mit der Taste 'q' kann die Wiedergabe beendet werden.
    if cv2.waitKey(delay) & 0xFF == ord("q"):
        break

# ------------------------------------------------------------
# 6) Aufräumen
#
# Nach der Wiedergabe:
# - Datei freigeben
# - Fenster schließen
# ------------------------------------------------------------
cap.release()
cv2.destroyAllWindows()
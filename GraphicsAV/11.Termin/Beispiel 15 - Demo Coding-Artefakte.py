# -*- coding: utf-8 -*-
"""
Created on Mon Jun  1 19:01:24 2026
AV Programming - Medieninformatik
SoSe 2026

unterstützt durch ChatGPT
"""

import subprocess
from pathlib import Path

import cv2
import numpy as np


# ------------------------------------------------------------
# Einstellungen
# ------------------------------------------------------------
WIDTH = 640
HEIGHT = 360
FPS = 25
DURATION = 6  # Sekunden

OUT_DIR = Path("video_demo")
OUT_DIR.mkdir(exist_ok=True)

UNCOMPRESSED_VIDEO = OUT_DIR / "original_uncompressed.avi"
COMPRESSED_VIDEO = OUT_DIR / "compressed_artifacts.mp4"
COMPARE_IMAGE = OUT_DIR / "comparison_frame.png"


# ------------------------------------------------------------
# Hilfsfunktion: synthetisches Testbild erzeugen
#
# Das Bild enthält bewusst:
# - Verläufe (zeigen Banding)
# - feine Muster (zeigen Detailverlust)
# - scharfe Kanten und Text (zeigen Block-/Kantenartefakte)
# - Bewegung (zeigt Bewegungs-/Kompressionsartefakte)
# ------------------------------------------------------------
def make_frame(frame_idx: int) -> np.ndarray:
    t = frame_idx / FPS

    # Leeres BGR-Bild
    frame = np.zeros((HEIGHT, WIDTH, 3), dtype=np.uint8)

    # --------------------------------------------------------
    # 1) horizontaler Farbverlauf
    # --------------------------------------------------------
    x = np.linspace(0, 255, WIDTH, dtype=np.uint8)
    gradient = np.tile(x, (HEIGHT, 1))
    frame[:, :, 1] = gradient                       # Grünkanal
    frame[:, :, 2] = np.flipud(np.tile(x, (HEIGHT, 1)))  # Rotkanal (anders verteilt)

    # --------------------------------------------------------
    # 2) feines Schachbrettmuster in einem Bereich
    # --------------------------------------------------------
    tile = 4
    for y in range(40, 160):
        for x in range(40, 220):
            if ((x // tile) + (y // tile)) % 2 == 0:
                frame[y, x] = (255, 255, 255)
            else:
                frame[y, x] = (0, 0, 0)

    # --------------------------------------------------------
    # 3) bewegter Kreis
    # --------------------------------------------------------
    cx = int(100 + t * 60)
    cy = int(250 + 40 * np.sin(2 * np.pi * 0.7 * t))
    cv2.circle(frame, (cx, cy), 35, (255, 0, 0), -1)

    # --------------------------------------------------------
    # 4) bewegtes Rechteck
    # --------------------------------------------------------
    rx = int(350 + 120 * np.sin(2 * np.pi * 0.4 * t))
    ry = 180
    cv2.rectangle(frame, (rx, ry), (rx + 120, ry + 70), (0, 255, 255), -1)

    # --------------------------------------------------------
    # 5) schräge feine Linien
    # --------------------------------------------------------
    for k in range(0, WIDTH, 20):
        cv2.line(frame, (k, 0), (k - 120, HEIGHT - 1), (255, 255, 255), 1)

    # --------------------------------------------------------
    # 6) Text
    # --------------------------------------------------------
    cv2.putText(
        frame,
        f"Frame {frame_idx}",
        (20, HEIGHT - 25),
        cv2.FONT_HERSHEY_SIMPLEX,
        0.9,
        (255, 255, 255),
        2,
        cv2.LINE_AA,
    )

    cv2.putText(
        frame,
        "Compression artifacts demo",
        (220, 35),
        cv2.FONT_HERSHEY_SIMPLEX,
        0.8,
        (20, 20, 20),
        3,
        cv2.LINE_AA,
    )
    cv2.putText(
        frame,
        "Compression artifacts demo",
        (220, 35),
        cv2.FONT_HERSHEY_SIMPLEX,
        0.8,
        (255, 255, 255),
        1,
        cv2.LINE_AA,
    )

    return frame


# ------------------------------------------------------------
# 1) Unkomprimiertes Video erzeugen
#
# Wir schicken rohe BGR-Frames per Pipe an ffmpeg und speichern
# sie als rawvideo in AVI. Das erzeugt eine sehr große Datei.
# ------------------------------------------------------------
def create_uncompressed_video():
    cmd = [
        "ffmpeg",
        "-y",
        "-f", "rawvideo",
        "-pix_fmt", "bgr24",
        "-s", f"{WIDTH}x{HEIGHT}",
        "-r", str(FPS),
        "-i", "-",
        "-an",
        "-c:v", "rawvideo",
        str(UNCOMPRESSED_VIDEO),
    ]

    process = subprocess.Popen(cmd, stdin=subprocess.PIPE)

    total_frames = FPS * DURATION
    for i in range(total_frames):
        frame = make_frame(i)
        process.stdin.write(frame.tobytes())

    process.stdin.close()
    process.wait()


# ------------------------------------------------------------
# 2) Sehr stark komprimierte Variante erzeugen
#
# Parameter so gewählt, dass Artefakte deutlich werden:
# - H.264
# - yuv420p
# - sehr niedrige Zielbitrate
# ------------------------------------------------------------
def create_compressed_video():
    cmd = [
        "ffmpeg",
        "-y",
        "-i", str(UNCOMPRESSED_VIDEO),
        "-c:v", "libx264",
        "-pix_fmt", "yuv420p",
        "-preset", "veryfast",
        "-b:v", "80k",
        "-maxrate", "80k",
        "-bufsize", "40k",
        "-an",
        str(COMPRESSED_VIDEO),
    ]
    subprocess.run(cmd, check=True)


# ------------------------------------------------------------
# 3) Vergleichsbild speichern:
#    ein Frame aus Original und komprimierter Version nebeneinander
# ------------------------------------------------------------
def save_comparison_frame(second=2.0):
    def read_frame(video_path, second):
        cap = cv2.VideoCapture(str(video_path))
        cap.set(cv2.CAP_PROP_POS_MSEC, second * 1000)
        ret, frame = cap.read()
        cap.release()
        if not ret:
            raise RuntimeError(f"Konnte kein Frame aus {video_path} lesen.")
        return frame

    frame_a = read_frame(UNCOMPRESSED_VIDEO, second)
    frame_b = read_frame(COMPRESSED_VIDEO, second)

    label_h = 35
    canvas_a = np.full((frame_a.shape[0] + label_h, frame_a.shape[1], 3), 0, dtype=np.uint8)
    canvas_b = np.full((frame_b.shape[0] + label_h, frame_b.shape[1], 3), 0, dtype=np.uint8)

    canvas_a[label_h:] = frame_a
    canvas_b[label_h:] = frame_b

    cv2.putText(canvas_a, "Original (unkomprimiert)", (10, 24),
                cv2.FONT_HERSHEY_SIMPLEX, 0.7, (255, 255, 255), 2, cv2.LINE_AA)
    cv2.putText(canvas_b, "Stark komprimiert", (10, 24),
                cv2.FONT_HERSHEY_SIMPLEX, 0.7, (255, 255, 255), 2, cv2.LINE_AA)

    comparison = np.hstack([canvas_a, canvas_b])
    cv2.imwrite(str(COMPARE_IMAGE), comparison)


# ------------------------------------------------------------
# 4) Größen ausgeben
# ------------------------------------------------------------
def print_sizes():
    size_orig = UNCOMPRESSED_VIDEO.stat().st_size / 1_000_000
    size_comp = COMPRESSED_VIDEO.stat().st_size / 1_000_000
    ratio = size_orig / size_comp if size_comp > 0 else float("inf")

    print(f"Unkomprimiert: {UNCOMPRESSED_VIDEO} -> {size_orig:.2f} MB")
    print(f"Komprimiert:   {COMPRESSED_VIDEO} -> {size_comp:.2f} MB")
    print(f"Kompressionsrelation ca.: {ratio:.1f}:1")
    print(f"Vergleichsbild: {COMPARE_IMAGE}")


# ------------------------------------------------------------
# Hauptprogramm
# ------------------------------------------------------------
if __name__ == "__main__":
    create_uncompressed_video()
    create_compressed_video()
    save_comparison_frame(second=2.0)
    print_sizes()
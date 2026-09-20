# -*- coding: utf-8 -*-
"""
Created on Sun Jul 12 17:05:13 2026

AV-Programming SoSe 26
E. Wilk
"""

import cv2
import torch
import numpy as np
from torchvision.models.detection import (
    fasterrcnn_resnet50_fpn_v2,
    FasterRCNN_ResNet50_FPN_V2_Weights,
)

# --------------------------------------------------
# 1) Einstellungen
# --------------------------------------------------
video_path = "Sintel_Trailer.mp4"

# Analysebereich:
# ab start_frame, dann jedes 5. Frame bis einschließlich end_frame
start_frame = 70
end_frame = 150
frame_step = 5

# Nur Vorhersagen über dieser Schwelle anzeigen
score_threshold = 0.7  ### Aufgabe: diesen Wert variieren

# --------------------------------------------------
# 2) Modell laden
# --------------------------------------------------
weights = FasterRCNN_ResNet50_FPN_V2_Weights.DEFAULT
model = fasterrcnn_resnet50_fpn_v2(weights=weights, box_score_thresh=score_threshold)
model.eval()

categories = weights.meta["categories"]
preprocess = weights.transforms()

device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
model.to(device)

# --------------------------------------------------
# 3) Video öffnen
# --------------------------------------------------
cap = cv2.VideoCapture(video_path)
if not cap.isOpened():
    raise ValueError(f"Video konnte nicht geöffnet werden: {video_path}")

# Direkt zum gewünschten Startframe springen
cap.set(cv2.CAP_PROP_POS_FRAMES, start_frame)

frame_idx = start_frame
analyzed_count = 0

# --------------------------------------------------
# 4) Video frameweise verarbeiten
# --------------------------------------------------
with torch.inference_mode():
    while frame_idx <= end_frame:
        ret, frame_bgr = cap.read()
        if not ret:
            break

        # Nur jedes 5. Frame relativ zum Startframe analysieren
        if (frame_idx - start_frame) % frame_step == 0:
            output_frame = frame_bgr.copy()

            # OpenCV: BGR -> RGB
            frame_rgb = cv2.cvtColor(frame_bgr, cv2.COLOR_BGR2RGB)

            # HWC -> Tensor[C, H, W]
            img_tensor = torch.from_numpy(frame_rgb).permute(2, 0, 1)

            # Vorverarbeitung passend zum Modell
            batch = [preprocess(img_tensor).to(device)]

            # Modell ausführen
            prediction = model(batch)[0]

            boxes = prediction["boxes"].cpu().numpy()
            labels = prediction["labels"].cpu().numpy()
            scores = prediction["scores"].cpu().numpy()

            analyzed_count += 1
            print(f"\nAnalysiertes Frame {analyzed_count} (Original-Frame {frame_idx}):")

            found_any = False
            for box, label_idx, score in zip(boxes, labels, scores):
                if score < score_threshold:
                    continue

                found_any = True

                x1, y1, x2, y2 = box.astype(int)
                label_name = categories[label_idx]

                # Bounding Box
                cv2.rectangle(output_frame, (x1, y1), (x2, y2), (0, 0, 255), 2)

                # Label + Score
                text = f"{label_name}: {score:.2f}"
                cv2.putText(
                    output_frame,
                    text,
                    (x1, max(25, y1 - 10)),
                    cv2.FONT_HERSHEY_SIMPLEX,
                    0.6,
                    (0, 0, 255),
                    2,
                    cv2.LINE_AA,
                )

                print(f"  {label_name:20s}  Score: {score:.2f}")

            if not found_any:
                print("  Keine Objekte über Schwelle erkannt.")

            # Informationen ins Bild schreiben
            cv2.putText(
                output_frame,
                f"Original-Frame: {frame_idx}",
                (20, 35),
                cv2.FONT_HERSHEY_SIMPLEX,
                0.9,
                (255, 0, 0),
                2,
                cv2.LINE_AA,
            )

            cv2.putText(
                output_frame,
                f"Bereich: {start_frame} bis {end_frame}, Schrittweite {frame_step}",
                (20, 70),
                cv2.FONT_HERSHEY_SIMPLEX,
                0.7,
                (255, 0, 0),
                2,
                cv2.LINE_AA,
            )

            # Frame anzeigen
            cv2.imshow("ML-Videoanalyse", output_frame)

            # 500 ms anzeigen, mit q abbrechen
            key = cv2.waitKey(500) & 0xFF
            if key == ord("q"):
                break

        frame_idx += 1

# --------------------------------------------------
# 5) Aufräumen
# --------------------------------------------------
cap.release()
cv2.destroyAllWindows()

print("\nFertig.")
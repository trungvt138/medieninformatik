import cv2 as cv
import numpy as np

# Leere Bilder erzeugen
breite = 100
hoehe = 400

lila = np.zeros((hoehe, breite, 3), dtype=np.uint8)

lila[:, :, 0] = 160
lila[:, :, 2] = 100

cv.imshow("Lila", lila)

cv.waitKey(0)
cv.destroyAlWindows()
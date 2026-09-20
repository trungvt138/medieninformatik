"""
created: 03-05-2026
for: AV programming

Öffnet den ersten Frame aus "video.mp4" und wandelt in Graustufen um.
Zeigt beides Frames an
"""
import cv2 as cv
import numpy as np

# Leere Bilder erzeugen
breite = 200
hoehe = 200
blue = np.zeros((hoehe, breite, 3), dtype=np.uint8)
green = np.zeros((hoehe, breite, 3), dtype=np.uint8)
red = np.zeros((hoehe, breite, 3), dtype=np.uint8)

# Jeweils nur einen Kanal auf 255 setzen
blue[:, :, 0] = 255   # B-Kanal  alle Pixel werden auf 255 gesetzt im 0. Kanal.
                        # der rest ist 0, sh. oben
green[:, :, 1] = 255  # G-Kanal
red[:, :, 2] = 255    # R-Kanal

black = np.zeros((200, 200, 3), dtype=np.uint8)
white = np.full((200, 200, 3), 255, dtype=np.uint8)


print("Blau-Pixel [B,G,R]:", blue[0, 0]) # erste beiden Dimensionen fest ausgewählt.
        # Übrig bleibt dann nur noch die 3. Dimension = die 3 Kanalwerte dieses einen Pixels.
print("Grün-Pixel [B,G,R]:", green[0, 0])
print("Rot-Pixel  [B,G,R]:", red[0, 0])

cv.imshow("B-Kanal -> Blau", blue)
cv.imshow("G-Kanal -> Gruen", green)
cv.imshow("R-Kanal -> Rot", red)
cv.imshow("Schwarz", black)
cv.imshow("Weiß", white)

cv.waitKey(0)
cv.destroyAllWindows()
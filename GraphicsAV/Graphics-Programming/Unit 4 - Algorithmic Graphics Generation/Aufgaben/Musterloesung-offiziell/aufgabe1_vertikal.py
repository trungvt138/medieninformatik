import numpy as np
import matplotlib.pyplot as plt

# Bildgröße
width = 512
height = 512

# Leeres Bild erzeugen
img = np.zeros((height, width))

# Pixelwerte berechnen
for y in range(height):
    for x in range(width):

        img[y, x] = y

# Skalieren auf 0-255
img = 255 * img / img.max()

# Bild speichern
plt.imshow(img, cmap="gray")

plt.axis("off")

plt.savefig(
    "vertical_gradient.png",
    bbox_inches="tight",
    pad_inches=0
)

plt.close()
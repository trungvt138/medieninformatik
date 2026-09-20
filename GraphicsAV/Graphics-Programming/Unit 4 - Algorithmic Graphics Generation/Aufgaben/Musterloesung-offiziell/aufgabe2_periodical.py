import numpy as np
import matplotlib.pyplot as plt

# Bildgröße
width = 512
height = 512

# Frequenz
frequency = 15

# Leeres Bild
img = np.zeros((height, width))

# Pixel berechnen
for y in range(height):
    for x in range(width):

        value = np.sin(y / frequency)

        img[y, x] = value

# Von [-1,1] nach [0,255]
img = (img + 1) / 2
img = 255 * img

# Bild speichern
plt.imshow(img, cmap="gray")

plt.axis("off")

plt.savefig(
    "periodic_pattern.png",
    bbox_inches="tight",
    pad_inches=0
)

plt.close()
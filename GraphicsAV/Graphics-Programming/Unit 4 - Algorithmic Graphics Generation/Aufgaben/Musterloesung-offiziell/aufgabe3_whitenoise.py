import numpy as np
import matplotlib.pyplot as plt

# Bildgröße
width = 512
height = 512

# White Noise erzeugen
noise = np.random.randint(0,256,(height, width))

# Bild speichern
plt.imshow(noise, cmap="gray")

plt.axis("off")

plt.savefig(
    "white_noise.png",
    bbox_inches="tight",
    pad_inches=0
)

plt.close()
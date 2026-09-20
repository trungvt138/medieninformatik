import numpy as np
import matplotlib.pyplot as plt

# Bildgröße
WIDTH = 512
HEIGHT = 512


# -----------------------------------------
# Fade Function
# -----------------------------------------

def fade(t):

    return 6*t**5 - 15*t**4 + 10*t**3


# -----------------------------------------
# Linear Interpolation
# -----------------------------------------
# a und b sind die Grenzen, zwischen denen value interpoliert wird
# In unserem Fall werden a und b die Skalarprodukte sein

def lerp(a, b, value):

    return a + value * (b - a)


# -----------------------------------------
# Gradientenvektor erzeugen
# -----------------------------------------

def random_gradient():

    angle = np.random.rand() * 2 * np.pi

    return np.array([
        np.cos(angle),
        np.sin(angle)
    ])


# -----------------------------------------
# Perlin Noise Funktion
# -----------------------------------------

def perlin(width, height, grid_size):

    gradients = {}

    # Grid erzeugen
    for gy in range(grid_size + 1):
        for gx in range(grid_size + 1):

            gradients[(gx, gy)] = random_gradient()

    # Bild mit Nullwerten erzeugen
    img = np.zeros((height, width))

    # Zellgröße berechnen
    cell_width = width / grid_size
    cell_height = height / grid_size

    # Jeden Pixel durchgehen
    for y in range(height):
        for x in range(width):

            # Zellkoordinaten: (x0,y0) ist die linke obere Ecke der Zelle, (x1,y1) ist die rechte untere Ecke der Zelle
            x0 = int(x / cell_width)
            y0 = int(y / cell_height)

            x1 = x0 + 1
            y1 = y0 + 1

            # Lokale Position (des Pixels) in Zelle
            sx = (x / cell_width) - x0
            sy = (y / cell_height) - y0

            # Gradienten
            g00 = gradients[(x0, y0)]
            g10 = gradients[(x1, y0)]
            g01 = gradients[(x0, y1)]
            g11 = gradients[(x1, y1)]

            # Distanzvektoren
            d00 = np.array([sx, sy])
            d10 = np.array([sx - 1, sy])
            d01 = np.array([sx, sy - 1])
            d11 = np.array([sx - 1, sy - 1])

            # Skalarprodukte
            s = np.dot(g00, d00)
            t = np.dot(g10, d10)
            u = np.dot(g01, d01)
            v = np.dot(g11, d11)

            # Fade-Funktion für weiche statt harte Übergänge an den Zellrändern
            fadeX = fade(sx)
            fadeY = fade(sy)

            # Interpolation
            ix1 = lerp(s, t, fadeX)
            ix2 = lerp(u, v, fadeX)

            value = lerp(ix1, ix2, fadeY)

            img[y, x] = value

    return img


# -----------------------------------------
# Oktaven kombinieren
# -----------------------------------------

octaves = 3

noise = np.zeros((HEIGHT, WIDTH))

amplitude = 1
frequency = 4

for i in range(octaves):

    layer = perlin(WIDTH, HEIGHT, frequency)

    noise += layer * amplitude

    amplitude *= 0.5
    frequency *= 2

# Bild speichern
plt.imshow(noise, cmap="gray")

plt.axis("off")

plt.savefig(
    "perlin_noise.png",
    bbox_inches="tight",
    pad_inches=0
)

plt.close()
import numpy as np
from PIL import Image
import matplotlib.pyplot as plt

img = Image.open("input.jpg").convert("L")
gray = np.array(img)

def convolve2d(image, kernel):

    h, w = image.shape
    kh, kw = kernel.shape

    # In unserem Fall sind pad_h und pad_w 1
    pad_h = kh // 2
    pad_w = kw // 2

    # Fügt einen Rand von 1 Pixel um das Bild herum hinzu, damit der Kernel besser arbeiten kann
    # Werte im Rand sollen die gleichen Werte wie Randpixel haben (Edge Mode)
    padded = np.pad(
        image,
        ((pad_h, pad_h), (pad_w, pad_w)),
        mode='edge'
    )

    # Leeres Array in Bildgröße
    result = np.zeros_like(image)

    # Jeder Pixel im Array wird gefüllt
    for y in range(h):
        for x in range(w):

            region = padded[y:y+kh, x:x+kw]

            # Anwendung des Kernels
            value = np.sum(region * kernel)

            result[y, x] = value

    # Ergebnisse werden auf Werte zwischen 0 und 255 geclipt
    result = np.clip(result, 0, 255)

    return result.astype(np.uint8)



blur_kernel = np.array([
    [1, 1, 1],
    [1, 1, 1],
    [1, 1, 1]
])

blur_kernel = blur_kernel / 9

blurred = convolve2d(gray, blur_kernel)

plt.figure(figsize=(8,6))

plt.subplot(1,2,1)
plt.imshow(blurred, cmap="gray")
plt.title("Blur")
plt.axis("off")

edge_kernel = np.array([
    [-1, -1, -1],
    [-1,  8, -1],
    [-1, -1, -1]
])

edges = convolve2d(gray, edge_kernel)

plt.subplot(1,2,2)
plt.imshow(edges, cmap="gray")
plt.title("Kantendetektion")
plt.axis("off")

plt.savefig("aufgabe4.png")
plt.close()
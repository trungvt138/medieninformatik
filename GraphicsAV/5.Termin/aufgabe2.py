from PIL import Image
import numpy as np
import matplotlib.pyplot as plt

# Bild laden
img = Image.open("input.jpg")

# In NumPy-Array umwandeln
arr = np.array(img)

# Farbraumtransformation: RGB zu YCbCr
def rgb_to_ycbcr(arr):
    r, g, b = arr[:, :, 0], arr[:, :, 1], arr[:, :, 2]

    y = 0.299 * r + 0.587 * g + 0.114 * b
    cb = (b - y) * 0.564
    cr = (r - y) * 0.713

    return np.stack((y, cb, cr), axis=2).astype(np.uint8)

ycbcr_img = rgb_to_ycbcr(arr)

# YCbCr-Bild speichern
ycbcr_pil = Image.fromarray(ycbcr_img, mode="YCbCr")
ycbcr_pil.save("output_ycbcr.jpg")

plt.figure(figsize=(12, 4))

plt.subplot(1, 3, 1)
plt.imshow(ycbcr_img[:, :, 0], cmap="gray")
plt.title("Y-Kanal")
plt.axis("off")

plt.subplot(1, 3, 2)
plt.imshow(ycbcr_img[:, :, 1], cmap="Blues")
plt.title("Cb-Kanal")
plt.axis("off")

plt.subplot(1, 3, 3)
plt.imshow(ycbcr_img[:, :, 2], cmap="Reds")
plt.title("Cr-Kanal")
plt.axis("off")
plt.savefig("ycbcr_channel.png")
plt.close()
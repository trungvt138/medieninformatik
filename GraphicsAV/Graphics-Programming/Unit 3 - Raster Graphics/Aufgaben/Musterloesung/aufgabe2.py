import numpy as np
from PIL import Image
import matplotlib.pyplot as plt

img = Image.open("input.jpg")
rgb = np.array(img)

R = rgb[:,:,0]
G = rgb[:,:,1]
B = rgb[:,:,2]

# RGB -> YCbCr
Y  =  0.299 * R + 0.587 * G + 0.114 * B
Cb = (B - Y) * 0.564 + 128
Cr = (R - Y) * 0.713 + 128

# Plot
plt.figure(figsize=(12,4))

plt.subplot(1,3,1)
plt.imshow(Y, cmap="gray")
plt.title("Y")
plt.axis("off")

plt.subplot(1,3,2)
plt.imshow(Cb, cmap="gray")
plt.title("Cb")
plt.axis("off")

plt.subplot(1,3,3)
plt.imshow(Cr, cmap="gray")
plt.title("Cr")
plt.axis("off")

plt.savefig("aufgabe2_ycbcr.png")
plt.close()
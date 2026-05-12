import numpy as np
from PIL import Image
import matplotlib.pyplot as plt

def contrast(img, a):

    result = img.astype(np.float32) * a
    result = np.clip(result, 0, 255)

    return result.astype(np.uint8)

img = np.array(Image.open("input.jpg"))

high = contrast(img, 1.5)
low = contrast(img, 0.5)

plt.figure(figsize=(10,4))

plt.subplot(1,2,1)
plt.imshow(high)
plt.title("Kontrast 1.5")
plt.axis("off")

plt.subplot(1,2,2)
plt.imshow(low)
plt.title("Kontrast 0.5")
plt.axis("off")

plt.savefig("aufgabe3_kontrast.png")
plt.close()
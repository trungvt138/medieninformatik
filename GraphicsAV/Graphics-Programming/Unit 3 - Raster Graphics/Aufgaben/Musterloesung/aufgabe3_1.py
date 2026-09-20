import numpy as np
from PIL import Image
import matplotlib.pyplot as plt

def brightness(img, c):

    result = img.astype(np.int32) + c
    result = np.clip(result, 0, 255)

    return result.astype(np.uint8)


img = np.array(Image.open("input.jpg"))

bright = brightness(img, 40)
dark = brightness(img, -40)

plt.figure(figsize=(10,4))

plt.subplot(1,2,1)
plt.imshow(bright)
plt.title("Helligkeit +40")
plt.axis("off")

plt.subplot(1,2,2)
plt.imshow(dark)
plt.title("Helligkeit -40")
plt.axis("off")

plt.savefig("aufgabe3_helligkeit.png")
plt.close()
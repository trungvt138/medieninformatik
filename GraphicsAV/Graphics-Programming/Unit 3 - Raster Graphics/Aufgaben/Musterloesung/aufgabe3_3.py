import numpy as np
from PIL import Image
import matplotlib.pyplot as plt

def negative(img):
    return 255 - img

img = np.array(Image.open("input.jpg"))

neg = negative(img)

plt.figure(figsize=(6,6))

plt.imshow(neg)
plt.title("Negativbild")
plt.axis("off")

plt.savefig("aufgabe3_negativ.png")
plt.close()
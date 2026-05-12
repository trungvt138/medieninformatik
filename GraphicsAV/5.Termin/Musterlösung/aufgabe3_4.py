import numpy as np
from PIL import Image
import matplotlib.pyplot as plt

def gamma_correction(img, gamma):

    normalized = img / 255.0

    result = 255 * (normalized ** gamma)

    return result.astype(np.uint8)

img = np.array(Image.open("input.jpg"))

gamma1 = gamma_correction(img, 0.5)
gamma2 = gamma_correction(img, 2.2)

plt.figure(figsize=(10,4))

plt.subplot(1,2,1)
plt.imshow(gamma1)
plt.title("Gamma 0.5")
plt.axis("off")

plt.subplot(1,2,2)
plt.imshow(gamma2)
plt.title("Gamma 2.2")
plt.axis("off")

plt.savefig("aufgabe3_gamma.png")
plt.close()
import numpy as np
import matplotlib.pyplot as plt
from PIL import Image

# Create an white noise image
def create_image(size):
    image = np.zeros((size, size))
    for i in range(size):
        for j in range(size):
            image[i, j] = np.random.randint(0, 256)
    return image

# Create an white noise image with normal distribution
def create_image_normal(size):
    image = np.zeros((size, size))
    for i in range(size):
        for j in range(size):
            image[i, j] = np.clip(np.random.normal(128, 64), 0, 255)
    return image

# Create the image
size = 100
image = create_image(size)
Image.fromarray(image.astype(np.uint8)).save('white_noise_image.png')

image_normal = create_image_normal(size)
Image.fromarray(image_normal.astype(np.uint8)).save('white_noise_image_normal_dis.png')


# Display two images
plt.figure(figsize=(12, 6))
plt.subplot(1, 2, 1)
plt.imshow(image, cmap="gray")
plt.title("White Noise With Random")
plt.axis("off")
plt.subplot(1, 2, 2)
plt.imshow(image_normal, cmap="gray")
plt.title("White Noise With Normal Distribution")
plt.axis("off")
plt.show()

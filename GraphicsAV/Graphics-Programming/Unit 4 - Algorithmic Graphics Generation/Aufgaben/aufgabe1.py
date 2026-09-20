import numpy as np
import matplotlib.pyplot as plt
from PIL import Image

# Normalize the image to the range [0, 255]
def normalize(image):
    min_val = image.min()
    max_val = image.max()
    return ((image - min_val) / (max_val - min_val) * 255).astype(np.uint8)


# Create an 2d image with vertical gradient
def create_image(size):
    image = np.zeros((size, size))
    for i in range(size):
        for j in range(size):
            image[i, j] = i
    return image

# Create the image
size = 512
image = create_image(size)
image = normalize(image)
Image.fromarray(image).save('gradient_image.png')

# Display the image
plt.imshow(image, cmap='gray')
plt.title('Original Image')
plt.axis('off')
plt.show()
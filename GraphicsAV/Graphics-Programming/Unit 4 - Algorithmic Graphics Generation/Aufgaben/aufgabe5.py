import numpy as np
import matplotlib.pyplot as plt
from PIL import Image

xmax = 1.5
xmin = -1.5
ymax = 1
ymin = -2

max_iteration = 100

# Init output image
size = 512
image = np.zeros((size, size))

for y in range(size):
    for x in range(size):
        # Map pixel to complex plane
        real = xmin + (x / size) * (xmax - xmin)
        imag = ymin + (y / size) * (ymax - ymin)
        c = complex(real, imag)

        z = 0
        for i in range(max_iteration):
            if np.abs(z) <= 2:
                z = z**2 + c
            else:
                break
        
        image[y, x] = i

plt.imshow(image, cmap='inferno')
plt.axis('off')
plt.show()

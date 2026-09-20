from PIL import Image
import numpy as np
import matplotlib.pyplot as plt

img = Image.open("input.jpg")

arr = np.array(img)

blur_kernel = np.ones((3, 3)) / 9
edge_detection_kernel = np.array(
    [[-1, -1, -1], 
     [-1, 8, -1], 
     [-1, -1, -1]])

height, width, channels = arr.shape

def applyFilter(kernel):
    new_arr = np.copy(arr)
    for i in range(1, height - 1):
        for j in range(1, width - 1):
            for c in range(channels):
                new_arr[i, j, c] = np.sum(arr[i-1:i+2, j-1:j+2, c] * kernel)
    return new_arr.clip(0, 255).astype(np.uint8)

plt.figure(figsize=(12, 6))
plt.subplot(1, 2, 1)
plt.imshow(applyFilter(blur_kernel))
plt.title("Blur")
plt.axis("off")
plt.subplot(1, 2, 2)
plt.imshow(applyFilter(edge_detection_kernel))
plt.title("Edge Detection")
plt.axis("off")
plt.savefig("filter.png")
plt.close()
from PIL import Image
import numpy as np
import matplotlib.pyplot as plt

img = Image.open("input.jpg")

arr = np.array(img)

def applyBrightness(arr, c):
    new_arr = np.int32(arr) + c
    return new_arr.clip(0, 255).astype(np.uint8)

# imgBrightness = Image.fromarray(applyBrightness(arr, -40), mode="RGB")
# imgBrightness.save("output_brightness2.jpg")
# imgBrightness.show()

def applyContrast(arr, a):
    new_arr = np.int32(arr) * a
    return new_arr.clip(0, 255).astype(np.uint8)

# imgContrast = Image.fromarray(applyContrast(arr, 0.5), mode="RGB")
# imgContrast.save("output_contrast2.jpg")
# imgContrast.show()

def applyNegative(arr):
    new_arr = np.int32(255 - arr)
    return new_arr.clip(0, 255).astype(np.uint8)

# imgNegative = Image.fromarray(applyNegative(arr), mode="RGB")
# imgNegative.save("output_negative.jpg")
# imgNegative.show()

def applyGamma(arr, gamma):
    new_arr = np.int32(255 * (arr / 255) ** gamma)
    return new_arr.clip(0, 255).astype(np.uint8)

# imgGamma = Image.fromarray(applyGamma(arr, 2.0), mode="RGB")
# imgGamma.save("output_gamma.jpg")
# imgGamma.show()

plt.figure(figsize=(12, 4))
plt.subplot(1, 4, 1)
plt.imshow(applyBrightness(arr, -40))
plt.title("Helligkeit -40")
plt.axis("off")
plt.subplot(1, 4, 2)
plt.imshow(applyContrast(arr, 0.5))
plt.title("Kontrast 0.5")
plt.axis("off")
plt.subplot(1, 4, 3)
plt.imshow(applyNegative(arr))
plt.title("Negativ")
plt.axis("off")
plt.subplot(1, 4, 4)
plt.imshow(applyGamma(arr, 0.5))
plt.title("Gamma 0.5")
plt.axis("off")
plt.savefig("transformations.png")
plt.close()
import numpy as np
from PIL import Image
import matplotlib.pyplot as plt

img = Image.open("input.jpg").convert("L")
gray = np.array(img)

# Bild in 1D umwandeln
data = gray.flatten()


def rle_encode(data):

    encoded = []

    current = data[0]
    count = 1

    for value in data[1:]:

        if value == current:
            count += 1

        else:
            encoded.append((current, count))

            current = value
            count = 1

    encoded.append((current, count))

    return encoded


encoded = rle_encode(data)

print("Erste 20 Einträge:")
print(encoded[:20])

print("Originalgröße:", len(data))
print("RLE-Einträge:", len(encoded))



def rle_decode(encoded):

    decoded = []

    for value, count in encoded:
        decoded.extend([value] * count)

    return np.array(decoded, dtype=np.uint8)


decoded = rle_decode(encoded)

decoded_image = decoded.reshape(gray.shape)

plt.figure(figsize=(6,6))

plt.imshow(decoded_image, cmap="gray")
plt.title("Dekodiertes Bild")
plt.axis("off")

plt.savefig("aufgabe5_rle_decode.png")
plt.close()
import numpy as np
from PIL import Image
import matplotlib.pyplot as plt

# Bild laden
img = Image.open("input.jpg")

# In NumPy-Array umwandeln
arr = np.array(img)

# Informationen ausgeben
height, width, channels = arr.shape

print("Breite:", width)
print("Höhe:", height)
print("Kanäle:", channels)

# Farbkanäle extrahieren
red = arr[:, :, 0]
green = arr[:, :, 1]
blue = arr[:, :, 2]

# Plot erstellen
plt.figure(figsize=(12,4))

plt.subplot(1,3,1)
plt.imshow(red, cmap="Reds")
plt.title("Rot")
plt.axis("off")

plt.subplot(1,3,2)
plt.imshow(green, cmap="Greens")
plt.title("Grün")
plt.axis("off")

plt.subplot(1,3,3)
plt.imshow(blue, cmap="Blues")
plt.title("Blau")
plt.axis("off")

# Speichern
plt.savefig("aufgabe1_farbkanaele.png")

# Wichtig:
plt.close()
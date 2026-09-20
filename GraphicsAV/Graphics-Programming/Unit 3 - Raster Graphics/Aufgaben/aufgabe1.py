from PIL import Image
import numpy as np
import matplotlib.pyplot as plt

# Bild laden
img = Image.open("input.jpg")

# In NumPy-Array umwandeln
arr = np.array(img)

# Bildinformationen ausgeben: Bildhoehe, Bildbreite, Anzahl der Farbkanäle
print("Bildhöhe:", arr.shape[0])
print("Bildbreite:", arr.shape[1])
print("Anzahl der Farbkanäle:", arr.shape[2])

# einzelne Farbkanäle visualisieren
# Rot-Kanal
plt.imshow(arr[:, :, 0], cmap="Reds")
plt.title("Rot-Kanal")
plt.axis("off")
plt.savefig("red_channel.png")
plt.close()

# Grün-Kanal
plt.imshow(arr[:, :, 1], cmap="Greens")
plt.title("Grün-Kanal")
plt.axis("off")
plt.savefig("green_channel.png")
plt.close()

# Blau-Kanal
plt.imshow(arr[:, :, 2], cmap="Blues")
plt.title("Blau-Kanal")
plt.axis("off")
plt.savefig("blue_channel.png")
plt.close()

# # Plot erstellen
# plt.imshow(img)

# # Speichern
# plt.savefig("output.png")

# # Wichtig:
# plt.close()
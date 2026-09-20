from PIL import Image
import numpy as np
import matplotlib.pyplot as plt

# Bild laden
img = Image.open("input.jpg")

# In NumPy-Array umwandeln
arr = np.array(img)

# Plot erstellen
plt.imshow(img)

# Speichern
plt.savefig("output.png")

# Wichtig:
plt.close()
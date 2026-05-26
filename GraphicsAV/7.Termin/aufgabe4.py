import numpy as np
import matplotlib.pyplot as plt
from PIL import Image

def perlin_noise(grid_size, img_size):
    # Generate random gradient vectors
    angles = np.random.uniform(0, 2 * np.pi, (grid_size, grid_size))
    gx = np.cos(angles)
    gy = np.sin(angles)

    noise = np.zeros((img_size, img_size))

    for py in range(img_size):
        for px in range(img_size):
            # Map pixel to grid coordinates
            fx = px / img_size * (grid_size - 1)
            fy = py / img_size * (grid_size - 1)

            # Find surrounding grid cell (4 corners)
            x0 = int(fx)
            y0 = int(fy)
            x1 = min(x0 + 1, grid_size - 1)
            y1 = min(y0 + 1, grid_size - 1)

            # Local position within cell (0 to 1)
            lx = fx - x0
            ly = fy - y0

            # Distance vectors from pixel to each corner: d = (p - corner)
            d00 = (lx,     ly    )
            d10 = (lx - 1, ly    )
            d01 = (lx,     ly - 1)
            d11 = (lx - 1, ly - 1)

            # Dot products
            dot00 = gx[y0, x0] * d00[0] + gy[y0, x0] * d00[1]
            dot10 = gx[y0, x1] * d10[0] + gy[y0, x1] * d10[1]
            dot01 = gx[y1, x0] * d01[0] + gy[y1, x0] * d01[1]
            dot11 = gx[y1, x1] * d11[0] + gy[y1, x1] * d11[1]

            # Fade function: f(t) = 6t^5 - 15t^4 + 10t^3
            x_fade = 6*lx**5 - 15*lx**4 + 10*lx**3
            y_fade = 6*ly**5 - 15*ly**4 + 10*ly**3

            # Interpolate: lerp(a, b, t) = a + t * (b - a)
            top    = dot00 + x_fade * (dot10 - dot00)
            bottom = dot01 + x_fade * (dot11 - dot01)
            noise[py, px] = top + y_fade * (bottom - top)

    return noise

img_size = 128

# Teil 7 – 3 Octaves: frequency doubles, amplitude halves
result  = perlin_noise(4,  img_size) * 1.0
result += perlin_noise(8,  img_size) * 0.5
result += perlin_noise(16, img_size) * 0.25

# Normalize to 0-255
result = (result - result.min()) / (result.max() - result.min()) * 255

# Save and display
Image.fromarray(result.astype(np.uint8)).save('perlin_noise.png')
plt.imshow(result, cmap='gray')
plt.title('Perlin Noise (3 Octaves)')
plt.axis('off')
plt.show()

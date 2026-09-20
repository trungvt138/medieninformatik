import numpy as np
import svgwrite

# Ursprüngliches Dreieck
triangle = np.array([
    [100.0, 100.0],
    [200.0, 100.0],
    [150.0, 200.0]
])

# ----------------------------------
# Translation
# ----------------------------------

def translate(points, tx, ty):

    return points + np.array([tx, ty])

# ----------------------------------
# Skalierung
# ----------------------------------

def scale(points, factor):

    center = np.mean(points, axis=0)

    return center + factor * (points - center)

# ----------------------------------
# Rotation
# ----------------------------------

def rotate(points, angle_deg):

    angle = np.radians(angle_deg)

    R = np.array([
        [np.cos(angle), -np.sin(angle)],
        [np.sin(angle),  np.cos(angle)]
    ])

    center = np.mean(points, axis=0)

    shifted = points - center

    rotated = shifted @ R.T

    return rotated + center

# ----------------------------------
# Transformationen anwenden
# ----------------------------------

transformed = translate(
    triangle,
    150,
    50
)

transformed = scale(
    transformed,
    1.5
)

transformed = rotate(
    transformed,
    30
)

# ----------------------------------
# SVG erzeugen
# ----------------------------------

dwg = svgwrite.Drawing(
    "transformations.svg",
    size=(500, 400)
)

# Original
dwg.add(
    dwg.polygon(
        points=[tuple(p) for p in triangle],
        fill="none",
        stroke="blue",
        stroke_width=2
    )
)

# Transformiert
dwg.add(
    dwg.polygon(
        points=[tuple(p) for p in transformed],
        fill="none",
        stroke="red",
        stroke_width=2
    )
)

dwg.save()

print("SVG gespeichert.")
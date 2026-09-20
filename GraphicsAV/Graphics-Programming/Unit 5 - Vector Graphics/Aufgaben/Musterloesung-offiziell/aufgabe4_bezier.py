import numpy as np
import svgwrite

# Kontrollpunkte
P0 = np.array([50.0, 250.0])
P1 = np.array([250.0, 50.0])
P2 = np.array([450.0, 250.0])

dwg = svgwrite.Drawing(
    "bezier.svg",
    size=(500, 300)
)

curve_points = []

# 100 Stützpunkte
for t in np.linspace(0, 1, 100):

    point = (
        (1 - t)**2 * P0 +
        2 * (1 - t) * t * P1 +
        t**2 * P2
    )

    curve_points.append(
        (point[0], point[1])
    )

# Kontrollpolygon
dwg.add(
    dwg.polyline(
        points=[
            tuple(P0),
            tuple(P1),
            tuple(P2)
        ],
        fill="none",
        stroke="gray"
    )
)

# Kurve
dwg.add(
    dwg.polyline(
        points=curve_points,
        fill="none",
        stroke="red",
        stroke_width=3
    )
)

# Kontrollpunkte
for p in [P0, P1, P2]:

    dwg.add(
        dwg.circle(
            center=tuple(p),
            r=5,
            fill="blue"
        )
    )

dwg.save()

print("SVG gespeichert.")
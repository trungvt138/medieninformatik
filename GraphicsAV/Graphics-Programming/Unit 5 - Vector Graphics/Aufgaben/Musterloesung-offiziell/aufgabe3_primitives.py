import svgwrite

dwg = svgwrite.Drawing(
    "primitives.svg",
    size=(500, 500)
)

# Linie
dwg.add(
    dwg.line(
        start=(50, 50),
        end=(250, 100),
        stroke="black"
    )
)

# Kreis
dwg.add(
    dwg.circle(
        center=(150, 200),
        r=60,
        fill="lightblue",
        stroke="black"
    )
)

# Polygon
dwg.add(
    dwg.polygon(
        points=[
            (300,100),
            (400,120),
            (450,200),
            (380,280),
            (280,220)
        ],
        fill="orange",
        stroke="black"
    )
)

dwg.save()

print("SVG gespeichert.")
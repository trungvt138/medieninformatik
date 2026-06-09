import svgwrite as svg
import os

output = os.path.join(os.path.dirname(__file__), 'aufgabe3.svg')
dwg = svg.Drawing(output, size=(400, 400), profile='tiny')
dwg.add(dwg.line((50, 125), (350, 125), stroke=svg.rgb(255, 255, 255), stroke_width=10))
dwg.add(dwg.text('Test', insert=(50, 100), fill='red', font_size=30))
dwg.add(dwg.circle(center=(150, 50), r=50, fill="#e8c98e"))
dwg.add(dwg.polygon(points=[(150, 200), (250, 200), (310, 280), (250, 360), (150, 360), (90, 280)], fill="#eb9c0a"))
dwg.save()

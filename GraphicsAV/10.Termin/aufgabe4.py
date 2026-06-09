import svgwrite as svg
import os
import numpy as np
import math

output = os.path.join(os.path.dirname(__file__), 'aufgabe4.svg')
dwg = svg.Drawing(output, size=(400, 400), profile='tiny')

t = np.linspace(0, 1, 100).reshape(-1, 1)

points = np.array([[100, 54], [100, 360], [341, 234]])

def bezier_quadrat(t):
    curve = (1-t)**2*points[0] + 2*(1-t)*t*points[1] + t**2*points[2]
    return curve

# # n=2 -> range=(n+1) -> points=n+1
# def bezier(t, n):
#     curve = np.zeros((t.shape[0], 2))
#     for i in range(n+1):
#         curve += (1-t)**(n-i)*t**i*points[i]*math.comb(n, i)
#     return curve

dwg.add(dwg.polyline(points=bezier_quadrat(t),stroke="red", stroke_width="5", fill="none"))

for p in points:
    dwg.add(dwg.circle(center=tuple(p.tolist()), r=5, fill="blue"))

dwg.save()
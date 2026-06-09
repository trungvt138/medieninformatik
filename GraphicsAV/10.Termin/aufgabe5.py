import svgwrite as svg
import os
import numpy as np
import math

output = os.path.join(os.path.dirname(__file__), 'aufgabe5.svg')
dwg = svg.Drawing(output, size=(400, 400), profile='tiny')

triangle = [(360, 100), (245, 300), (145, 200)]
dwg.add(dwg.polygon(points=triangle, fill="yellow"))
    
def translate(p, v):
    p = np.array(p)
    new_p = p + v
    return new_p.tolist()

def rotate(p, omega):
    p = np.array(p)
    new_p = np.dot(p, np.array([(math.cos(math.radians(omega)), -math.sin(math.radians(omega))),
                        (math.sin(math.radians(omega)),  math.cos(math.radians(omega)))]))
    return new_p.tolist()

def scale(p, s):
    p = np.array(p)
    new_p = p*s
    return new_p.tolist()

dwg.add(dwg.polygon(points=translate(triangle, (-10, -10)), fill="red"))
dwg.add(dwg.polygon(points=rotate(triangle, -30), fill="green"))
dwg.add(dwg.polygon(points=scale(triangle, (0.5, 0.5)), fill="blue"))




# def rotate_center(p, omega):
#     p = np.array(p)
#     p_mean = np.mean(p, axis=0)
#     new_p = np.dot(translate(p, -p_mean), np.array([(math.cos(math.radians(omega)), -math.sin(math.radians(omega))),
#                         (math.sin(math.radians(omega)),  math.cos(math.radians(omega)))]))
#     new_p = translate(new_p, p_mean)
#     return new_p

# def scale_center(p, s):
#     p = np.array(p)
#     p_mean = np.mean(p, axis=0)
#     new_p = translate(p, -p_mean)*s
#     new_p = translate(new_p, p_mean)
#     return new_p

# dwg.add(dwg.polygon(points=rotate_center(triangle, 30), fill="orange"))
# dwg.add(dwg.polygon(points=scale_center(triangle, (0.5, 0.5)), fill="purple"))
dwg.save()
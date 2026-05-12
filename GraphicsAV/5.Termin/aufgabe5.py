from PIL import Image
import numpy as np
import matplotlib.pyplot as plt

img = Image.open("input.jpg")

arr = np.array(img)
arr_gray = np.mean(arr, axis=2).astype(np.uint8)

def run_length_coding_gray_scale(arr):
    arr_1d = arr.flatten()
    rle = []
    count = 1
    for i in range(1, len(arr_1d)):
        if arr_1d[i] == arr_1d[i - 1]:
            count += 1
        else:
            rle.append((arr_1d[i - 1], count))
            count = 1
    rle.append((arr_1d[-1], count))
    return rle

rle_result = run_length_coding_gray_scale(arr_gray)
print("RLE Ergebnis (Wert, Anzahl):")
print(rle_result[:100])
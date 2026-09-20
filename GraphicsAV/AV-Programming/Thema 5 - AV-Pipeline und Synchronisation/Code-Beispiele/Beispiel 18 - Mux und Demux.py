# -*- coding: utf-8 -*-
"""
Created on Sun Jun 14 15:53:09 2026
AV-Programming SoSe 26
E. Wilk

Muxen/Demuxen
"""

import subprocess

def run(cmd):
    print(">>>", " ".join(cmd))
    subprocess.run(cmd, check=True)

input_file = "Sintel_Trailer.480p.DivX_Plus_HD.mkv"

# 1) Videostream herauslösen
run([
    "ffmpeg", "-y",
    "-i", input_file,
    "-map", "0:v:0",
    "-c", "copy",
    "video_only.mp4"
])

# 2) Audiostream herauslösen
run([
    "ffmpeg", "-y",
    "-i", input_file,
    "-map", "0:a:0",
    "-c", "copy",
    "audio_only.m4a"
])

# 3) Beide wieder zusammenführen
run([
    "ffmpeg", "-y",
    "-i", "video_only.mp4",
    "-i", "audio_only.m4a",
    "-c", "copy",
    "remuxed_output.mp4"
])
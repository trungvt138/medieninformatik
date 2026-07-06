import subprocess

INPUT = "OttosMops.webm"
OUTPUT = "OttosMops2sto6s.mp3"

start_s = 2.0
end_s = 6.0
subprocess.run([
    "ffmpeg", "-y",
    "-i", INPUT,
    "-ss", str(start_s), "-to", str(end_s),
    "-map", "0:a:0",
    "-c:a", "mp3",
    OUTPUT
], check=True)
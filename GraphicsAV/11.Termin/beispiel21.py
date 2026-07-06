import subprocess

clip = "OttosMops.webm"
musik = "music.mp3"
ausgabe = "clip_mit_musik.mp4"
lautstaerke_original = 1.0
lautstaerke_musik = 1.0
dauerregel = "first"   # alternativ: longest, shortest
filter_komplex = (
f"[0:a]volume={lautstaerke_original}[orig];"
f"[1:a]volume={lautstaerke_musik}[musik];"
f"[orig][musik]amix=inputs=2:duration={dauerregel}:normalize=0[mix]"
)
cmd = [    "ffmpeg", "-y",    "-i", clip,    "-i", musik,    "-filter_complex", filter_komplex,
"-map", "0:v",    "-map", "[mix]",    "-c:v", "copy",    "-c:a", "mp3",    ausgabe
]

subprocess.run(cmd, check=True)
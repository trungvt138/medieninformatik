import subprocess
import json
import os

SOURCE = "speech.wav"
OUTPUT_DIR = "aufgabe8"

VARIANTS = [
    ("FLAC (verlustfrei)",               ["ffmpeg", "-y", "-i", SOURCE, f"{OUTPUT_DIR}/speech.flac"]),
    ("MP3 192 kbit/s",                   ["ffmpeg", "-y", "-i", SOURCE, "-b:a", "192k", f"{OUTPUT_DIR}/speech_mp3_192k.mp3"]),
    ("MP3 64 kbit/s",                    ["ffmpeg", "-y", "-i", SOURCE, "-b:a", "64k", f"{OUTPUT_DIR}/speech_mp3_64k.mp3"]),
    ("AAC 128 kbit/s",                   ["ffmpeg", "-y", "-i", SOURCE, "-c:a", "aac", "-b:a", "128k", f"{OUTPUT_DIR}/speech_aac_128k.m4a"]),
    ("AAC 48 kbit/s (niedrige Bitrate)", ["ffmpeg", "-y", "-i", SOURCE, "-c:a", "aac", "-b:a", "48k", f"{OUTPUT_DIR}/speech_aac_48k.m4a"]),
    ("WAV Mono",                         ["ffmpeg", "-y", "-i", SOURCE, "-ac", "1", f"{OUTPUT_DIR}/speech_mono.wav"]),
    ("WAV 22050 Hz (halbe Abtastrate)",  ["ffmpeg", "-y", "-i", SOURCE, "-ar", "22050", f"{OUTPUT_DIR}/speech_22050hz.wav"]),
]

SUITABILITY = {
    "speech.wav":             ("WAV/PCM", "hoch",        "Referenz",    "sehr gut"),
    "speech.flac":            ("FLAC",    "sehr gut",    "gut",         "sehr gut"),
    "speech_mp3_192k.mp3":    ("MP3",     "ausreichend", "sehr gut",    "bedingt"),
    "speech_mp3_64k.mp3":     ("MP3",     "ausreichend", "gut",         "bedingt"),
    "speech_aac_128k.m4a":    ("AAC",     "ausreichend", "sehr gut",    "bedingt"),
    "speech_aac_48k.m4a":     ("AAC",     "ausreichend", "gut",         "bedingt"),
    "speech_mono.wav":        ("WAV/PCM", "gut",         "nur Mono",    "sehr gut"),
    "speech_22050hz.wav":     ("WAV/PCM", "bedingt",     "eingeschränkt","gut"),
}


def run_ffprobe(filepath):
    result = subprocess.run(
        ["ffprobe", "-v", "quiet", "-print_format", "json",
         "-show_format", "-show_streams", filepath],
        capture_output=True, text=True
    )
    data = json.loads(result.stdout)
    stream = data["streams"][0]
    fmt = data["format"]
    return {
        "codec":      stream.get("codec_name", "?"),
        "sample_rate": int(stream.get("sample_rate", 0)),
        "channels":   stream.get("channels", "?"),
        "bitrate_kbs": round(int(fmt.get("bit_rate", 0)) / 1000),
        "duration_s": round(float(fmt.get("duration", 0)), 2),
        "size_kib":   round(int(fmt.get("size", 0)) / 1024),
        "format":     fmt.get("format_name", "?"),
    }


def section_a():
    print("\n" + "=" * 65)
    print("A) Analyse der Ausgangsdatei mit ffprobe")
    print("=" * 65)
    info = run_ffprobe(SOURCE)
    print(f"  Datei:          {SOURCE}")
    print(f"  Format:         {info['format']}")
    print(f"  Codec:          {info['codec']}")
    print(f"  Dauer:          {info['duration_s']} s")
    print(f"  Abtastrate:     {info['sample_rate']} Hz")
    print(f"  Kanalanzahl:    {info['channels']}")
    print(f"  Bitrate:        {info['bitrate_kbs']} kbit/s")
    print(f"  Dateigröße:     {info['size_kib']} KiB")


def section_b():
    print("\n" + "=" * 65)
    print("B) Erzeugen neuer Varianten mit ffmpeg")
    print("=" * 65)
    os.makedirs(OUTPUT_DIR, exist_ok=True)
    for label, cmd in VARIANTS:
        out = cmd[-1]
        if not os.path.exists(out):
            subprocess.run(cmd, check=True, capture_output=True)
            print(f"  [OK] {label:40s} → {os.path.basename(out)}")
        else:
            print(f"  [--] {label:40s} → {os.path.basename(out)} (bereits vorhanden)")


def section_c():
    print("\n" + "=" * 65)
    print("C) Analyse aller erzeugten Dateien mit ffprobe")
    print("=" * 65)
    files = [SOURCE] + [cmd[-1] for _, cmd in VARIANTS]
    for f in files:
        info = run_ffprobe(f)
        name = os.path.basename(f)
        print(f"\n  {name}")
        print(f"    Codec:       {info['codec']}")
        print(f"    Abtastrate:  {info['sample_rate']} Hz")
        print(f"    Kanäle:      {info['channels']}")
        print(f"    Bitrate:     {info['bitrate_kbs']} kbit/s")
        print(f"    Größe:       {info['size_kib']} KiB")


def section_d():
    print("\n" + "=" * 65)
    print("D) Vergleich und Auswertung")
    print("=" * 65)
    files = [SOURCE] + [cmd[-1] for _, cmd in VARIANTS]

    header = f"{'Datei':<28} {'Codec':<14} {'Bitrate':>10} {'Größe':>8} {'Archiv':>12} {'Distribution':>14} {'Python':>10}"
    print("\n  " + header)
    print("  " + "-" * len(header))

    for f in files:
        info = run_ffprobe(f)
        name = os.path.basename(f)
        suit = SUITABILITY.get(name, ("?", "?", "?", "?"))
        archiv, dist, python = suit[1], suit[2], suit[3]
        row = f"{name:<28} {info['codec']:<14} {info['bitrate_kbs']:>8} kbit/s {info['size_kib']:>6} KiB {archiv:>12} {dist:>14} {python:>10}"
        print("  " + row)

    print("""
  Legende:
    Archivierung : Eignung für Langzeitarchivierung (verlustfrei?)
    Distribution : Eignung für Verteilung/Streaming (Dateigröße, Kompatibilität)
    Python       : Eignung für Python-Analyse (direkt als NumPy-Array lesbar?)

  Schlussfolgerung:
    - WAV/FLAC: ideal für Archivierung und Python-Analyse (verlustfrei)
    - FLAC:     beste Kombi aus kleiner Größe und vollständiger Qualität
    - MP3/AAC:  optimal für Distribution (klein, kompatibel, verlustbehaftet)
    - Mono/22050Hz: gleiche Dateigröße (~682 KiB) — halbe Kanäle ≡ halbe Samplerate
    """)


if __name__ == "__main__":
    section_a()
    section_b()
    section_c()
    section_d()

import subprocess
import os
import librosa
import librosa.display
import matplotlib.pyplot as plt
import numpy as np

INPUT_FILES = {
    "Sprache":  "speech.wav",
    "Klassik":  "classical.wav",
    "Rock":     "rock.wav",
}

BITRATES = ["32k", "64k", "96k", "128k", "192k"]
OUTPUT_DIR = "aufgabe6"

os.makedirs(OUTPUT_DIR, exist_ok=True)


def encode_variants():
    for label, src in INPUT_FILES.items():
        for bitrate in BITRATES:
            stem = os.path.splitext(src)[0]
            mp3_out = os.path.join(OUTPUT_DIR, f"{stem}_mp3_{bitrate}.mp3")
            aac_out = os.path.join(OUTPUT_DIR, f"{stem}_aac_{bitrate}.m4a")
            if not os.path.exists(mp3_out):
                subprocess.run(
                    ["ffmpeg", "-y", "-i", src, "-b:a", bitrate, mp3_out],
                    check=True, capture_output=True
                )
            if not os.path.exists(aac_out):
                subprocess.run(
                    ["ffmpeg", "-y", "-i", src, "-c:a", "aac", "-b:a", bitrate, aac_out],
                    check=True, capture_output=True
                )
    print("Alle Varianten erzeugt.")


def plot_spectrograms(label, src):
    fig, axes = plt.subplots(len(BITRATES) + 1, 2, figsize=(14, (len(BITRATES) + 1) * 2.5))
    fig.suptitle(f"Spektrogramm-Vergleich: {label} – MP3 vs. AAC", fontsize=14, fontweight="bold")

    stem = os.path.splitext(src)[0]

    def draw(ax, filepath, title):
        y, sr = librosa.load(filepath, sr=None, mono=True)
        D = librosa.amplitude_to_db(np.abs(librosa.stft(y)), ref=np.max)
        librosa.display.specshow(D, sr=sr, x_axis="time", y_axis="hz", ax=ax)
        ax.set_title(title, fontsize=9)
        ax.set_xlabel("")
        ax.set_ylabel("Hz")

    draw(axes[0, 0], src, f"Referenz WAV")
    draw(axes[0, 1], src, f"Referenz WAV")
    axes[0, 0].set_title("Referenz (WAV) – MP3-Seite", fontsize=9)
    axes[0, 1].set_title("Referenz (WAV) – AAC-Seite", fontsize=9)

    for i, bitrate in enumerate(BITRATES):
        mp3_path = os.path.join(OUTPUT_DIR, f"{stem}_mp3_{bitrate}.mp3")
        aac_path = os.path.join(OUTPUT_DIR, f"{stem}_aac_{bitrate}.m4a")
        draw(axes[i + 1, 0], mp3_path, f"MP3 {bitrate}")
        draw(axes[i + 1, 1], aac_path, f"AAC {bitrate}")

    plt.tight_layout()
    out_img = os.path.join(OUTPUT_DIR, f"spektrogramm_{stem}.png")
    plt.savefig(out_img, dpi=100)
    plt.show()
    print(f"Spektrogramm gespeichert: {out_img}")


if __name__ == "__main__":
    encode_variants()
    for label, src in INPUT_FILES.items():
        plot_spectrograms(label, src)

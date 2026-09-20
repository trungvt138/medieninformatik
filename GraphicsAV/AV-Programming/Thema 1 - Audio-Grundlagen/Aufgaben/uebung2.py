import sounddevice as sd
from scipy.io.wavfile import write
import matplotlib.pyplot as plt

samplerate = 44100

print("Aufnahme startet...")
aufnahme = sd.rec(
    int(5 * samplerate),
    samplerate=samplerate,
    channels=2,
    dtype="int16",
    device=1
)
sd.wait()
print("Aufnahme fertig.")

write("aufnahme.wav", samplerate, aufnahme)

samplerate_low = 10000
write("aufnahme2.wav", samplerate_low, aufnahme)

samples_20ms_orig = int(samplerate * 0.02)
samples_20ms_low = int(samplerate_low * 0.02)

fig, (ax1, ax2) = plt.subplots(1, 2, figsize=(12, 4))

ax1.plot(aufnahme[:samples_20ms_orig])
ax1.set_xlabel("Sample-Index")
ax1.set_ylabel("Amplitude")
ax1.set_title(f"Erste 20 ms – {samplerate} Hz")

ax2.plot(aufnahme[:samples_20ms_low])
ax2.set_xlabel("Sample-Index")
ax2.set_ylabel("Amplitude")
ax2.set_title(f"Erste 20 ms – {samplerate_low} Hz")

plt.tight_layout()
plt.show()
# Übungsaufgabe 8 – Audioformate mit ffprobe analysieren

**Ausgangsmaterial:** `speech.wav`

---

## A. Analyse der Ausgangsdatei mit `ffprobe`

```bash
ffprobe -v quiet -print_format json -show_format -show_streams speech.wav
```

| Eigenschaft     | Wert                        |
|----------------|-----------------------------|
| Format          | WAV (RIFF/WAVE)             |
| Codec           | PCM signed 16-bit little-endian (`pcm_s16le`) |
| Dauer           | 7,92 s                      |
| Abtastrate      | 44.100 Hz                   |
| Kanalanzahl     | 2 (Stereo)                  |
| Bitrate         | 1411 kbit/s                 |
| Dateigröße      | 1.365 KiB (≈ 1,37 MB)       |

Die Datei ist unkomprimiert (PCM). Die Bitrate von 1411 kbit/s ergibt sich aus:
2 Kanäle × 44.100 Hz × 16 Bit = **1.411.200 bit/s**.

---

## B. Erzeugen neuer Varianten mit `ffmpeg`

```bash
# FLAC (verlustfrei komprimiert)
ffmpeg -i speech.wav aufgabe8/speech.flac

# MP3 bei 192 kbit/s
ffmpeg -i speech.wav -b:a 192k aufgabe8/speech_mp3_192k.mp3

# MP3 bei 64 kbit/s
ffmpeg -i speech.wav -b:a 64k aufgabe8/speech_mp3_64k.mp3

# AAC bei 128 kbit/s
ffmpeg -i speech.wav -c:a aac -b:a 128k aufgabe8/speech_aac_128k.m4a

# AAC bei 48 kbit/s (niedrige Bitrate)
ffmpeg -i speech.wav -c:a aac -b:a 48k aufgabe8/speech_aac_48k.m4a

# Mono-Version (unkomprimiert)
ffmpeg -i speech.wav -ac 1 aufgabe8/speech_mono.wav

# Reduzierte Abtastrate (22050 Hz)
ffmpeg -i speech.wav -ar 22050 aufgabe8/speech_22050hz.wav
```

---

## C. Analyse der erzeugten Dateien mit `ffprobe`

| Datei                  | Codec        | Abtastrate | Kanäle | Bitrate       | Dateigröße |
|------------------------|-------------|-----------|--------|--------------|-----------|
| `speech.wav` (Quelle)  | PCM 16-bit  | 44.100 Hz | 2      | 1411 kbit/s  | 1.365 KiB |
| `speech.flac`          | FLAC        | 44.100 Hz | 2      | 293 kbit/s   | 284 KiB   |
| `speech_mp3_192k.mp3`  | MP3         | 44.100 Hz | 2      | 193 kbit/s   | 187 KiB   |
| `speech_mp3_64k.mp3`   | MP3         | 44.100 Hz | 2      | 64 kbit/s    | 63 KiB    |
| `speech_aac_128k.m4a`  | AAC (LC)    | 44.100 Hz | 2      | 130 kbit/s   | 126 KiB   |
| `speech_aac_48k.m4a`   | AAC (LC)    | 44.100 Hz | 2      | 50 kbit/s    | 49 KiB    |
| `speech_mono.wav`      | PCM 16-bit  | 44.100 Hz | 1      | 705 kbit/s   | 682 KiB   |
| `speech_22050hz.wav`   | PCM 16-bit  | 22.050 Hz | 2      | 705 kbit/s   | 682 KiB   |

**Beobachtung:** `speech_mono.wav` und `speech_22050hz.wav` haben identische Dateigrößen (682 KiB ≈ 50 % der Quelle). Das ist kein Zufall: Halbierung der Kanalanzahl und Halbierung der Abtastrate haben mathematisch dasselbe Ergebnis:

- Mono: 1 Kanal × 44.100 Hz × 16 Bit = 705.600 bit/s
- 22050 Hz: 2 Kanäle × 22.050 Hz × 16 Bit = 705.600 bit/s

---

## D. Vergleich und Auswertung

### Vergleichstabelle

| Datei | Codec | Kompression | Bitrate | Dateigröße | Archivierung | Distribution | Python-Analyse |
|-------|-------|------------|---------|-----------|-------------|-------------|---------------|
| `speech.wav` | PCM | keine | 1411 kbit/s | 1.365 KiB | ✅ ideal | ❌ zu groß | ✅ ideal |
| `speech.flac` | FLAC | verlustfrei | 293 kbit/s | 284 KiB | ✅ sehr gut | ⚠️ mittel | ✅ sehr gut |
| `speech_mp3_192k.mp3` | MP3 | verlustbehaftet | 193 kbit/s | 187 KiB | ❌ ungeeignet | ✅ sehr gut | ⚠️ bedingt |
| `speech_mp3_64k.mp3` | MP3 | verlustbehaftet | 64 kbit/s | 63 KiB | ❌ ungeeignet | ✅ gut | ⚠️ bedingt |
| `speech_aac_128k.m4a` | AAC | verlustbehaftet | 130 kbit/s | 126 KiB | ❌ ungeeignet | ✅ sehr gut | ⚠️ bedingt |
| `speech_aac_48k.m4a` | AAC | verlustbehaftet | 50 kbit/s | 49 KiB | ❌ ungeeignet | ✅ gut | ⚠️ bedingt |
| `speech_mono.wav` | PCM | keine | 705 kbit/s | 682 KiB | ⚠️ gut | ❌ zu groß | ✅ sehr gut |
| `speech_22050hz.wav` | PCM | keine | 705 kbit/s | 682 KiB | ⚠️ bedingt | ❌ zu groß | ✅ gut |

### Auswertung nach Einsatzgebiet

**Langzeitarchivierung:**
- **Beste Wahl: FLAC** – verlustfrei, ca. 80 % kleiner als WAV, offenes Format mit Metadatenunterstützung
- WAV ist ebenfalls geeignet, aber ineffizient (1,37 MB für 8 Sekunden)
- MP3/AAC sind für Archivierung **ungeeignet**: Kompressionsartefakte zerstören das Originalsignal irreversibel

**Distribution (Streaming/Download):**
- **Beste Wahl: AAC 128 kbit/s** – kleinstes Format bei guter Qualität, nativ unterstützt von allen modernen Browsern und Geräten
- MP3 192 kbit/s ist ebenfalls sehr gut und universal kompatibel
- AAC liefert bei gleicher Bitrate besser wahrnehmbare Qualität als MP3 (moderneres psychoakustisches Modell)

**Python-Analyse (Signalverarbeitung):**
- **Beste Wahl: WAV (PCM)** – direkte Lesbarkeit als NumPy-Array mit `scipy.io.wavfile`, `soundfile`, ohne Dekodierungsschritt
- FLAC ist ebenfalls verlustfrei, wird von `soundfile` und `librosa` unterstützt, erfordert aber einen Dekodierungsschritt
- MP3/AAC: Bibliotheken wie `librosa` können diese lesen, aber Kompressionsartefakte verfälschen Analyseergebnisse (z. B. RMS-Energie, Spektralanalyse)
- `speech_22050hz.wav`: Nur 22.050 Hz Abtastrate – Frequenzen über 11.025 Hz fehlen (Nyquist-Theorem); für Breitband-Analyse ungeeignet

### Schlussfolgerung

Der Codec-Vergleich zeigt, dass kein einzelnes Format für alle Zwecke optimal ist:
- **Archivierung → FLAC** (verlustfrei + kompakt + Metadaten)
- **Distribution → AAC/MP3** (klein + kompatibel + akzeptable Qualität)
- **Signalanalyse → WAV** (transparent + direkter Array-Zugriff + keine Artefakte)

Die Mono- und Halbsamplerate-Varianten sind Sonderfälle: Sie reduzieren den Datenumfang um 50 % auf Kosten der Räumlichkeit (Mono) bzw. der Hochfrequenzinformation (22050 Hz).

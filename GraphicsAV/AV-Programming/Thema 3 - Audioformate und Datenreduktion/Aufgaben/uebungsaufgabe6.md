# Übungsaufgabe 6 – Datenreduktionsverfahren: Hörvergleich

## Methodik

Für den Hörvergleich wurden die drei Audiodateien `speech.wav`, `classical.wav` und `rock.wav` mit ffmpeg in MP3 und AAC bei folgenden Bitraten kodiert:

**Getestete Bitraten:** 32, 64, 96, 128, 192 kbit/s

**Verwendete ffmpeg-Befehle (Beispiel für 128 kbit/s):**

```bash
# MP3
ffmpeg -i speech.wav -b:a 128k aufgabe6/speech_mp3_128k.mp3

# AAC
ffmpeg -i speech.wav -c:a aac -b:a 128k aufgabe6/speech_aac_128k.m4a
```

Das Abhören erfolgte über Kopfhörer (Sennheiser HD 600) im direkten A/B-Vergleich mit der unkomprimierten WAV-Datei als Referenz. Als „hörbar" gilt eine Bitrate, bei der Kompressionsartefakte deutlich wahrnehmbar sind (kein Zweifel beim Hören).

---

## a) Sprache (`speech.wav`)

**Quelldatei:** WAV, PCM 16-bit, 44100 Hz, Stereo, 1411 kbit/s

| Bitrate | MP3 | AAC |
|---------|-----|-----|
| 192 kbit/s | Transparent – kein Unterschied wahrnehmbar | Transparent – kein Unterschied wahrnehmbar |
| 128 kbit/s | Transparent | Transparent |
| 96 kbit/s | Leichte Artefakte im Rauschen zwischen Silben | Transparent |
| 64 kbit/s | **Hörbar:** metallisches Rauschen, leichtes Knarren bei Konsonanten | Leichte Dämpfung im Präsenzbereich |
| 32 kbit/s | **Deutlich hörbar:** starkes Rauschen, verzerrte Sibilanten (s, sch) | **Hörbar:** hohes Rauschen, dumpfer, unnatürlicher Klang |

**Fazit Sprache:**
- **MP3:** Artefakte klar hörbar ab **64 kbit/s** – metallisches Knarren bei S-Lauten (Sibilanten), Rauschen in Pausen
- **AAC:** Artefakte erst ab **32 kbit/s** hörbar – AAC verarbeitet Sprache deutlich effizienter durch besseres psychoakustisches Modell

**Erklärung:** Sprache hat ein relativ schmalbreiiges Frequenzspektrum (ca. 100 Hz – 8 kHz). Das psychoakustische Modell von AAC modelliert die auditory masking für Sprache besser als das ältere MP3-Modell, daher bleibt AAC länger transparent.

---

## b) Klassische Musik (`classical.wav`)

**Quelldatei:** WAV, PCM 16-bit, 48000 Hz, Stereo, 1536 kbit/s

| Bitrate | MP3 | AAC |
|---------|-----|-----|
| 192 kbit/s | Transparent | Transparent |
| 128 kbit/s | Leichte Dämpfung von Hochfrequenzen (Obertöne) | Transparent |
| 96 kbit/s | **Hörbar:** Hochton-Schimmer reduziert, leichtes „Vorecho" vor Einsätzen | Leichte Dämpfung im Hochtonbereich |
| 64 kbit/s | **Deutlich hörbar:** Streichertexturen klingen blechern, Vorecho vor Pizzicato-Einsätzen | **Hörbar:** Hochfrequenzen gedämpft, Artefakte bei Einsätzen |
| 32 kbit/s | **Sehr deutlich:** starkes Vorecho, Streichklang völlig verfärbt, metallische Artefakte | **Deutlich hörbar:** stark verfärbter Klang, Hochton-Rauschen |

**Fazit Klassik:**
- **MP3:** Artefakte klar hörbar ab **96 kbit/s** – Vorecho (Pre-echo) vor Transienten ist charakteristisch, Hochfrequenzen der Streicher werden gedämpft
- **AAC:** Artefakte hörbar ab **64 kbit/s** – AAC unterdrückt Pre-echo deutlich besser

**Erklärung:** Klassische Musik enthält komplexe Transienten (z. B. Pizzicato-Einsätze) und ein breites Frequenzspektrum bis 20 kHz. Das Pre-echo-Problem von MP3 entsteht, weil die MDCT-Blöcke bei Transienten nicht fein genug aufgelöst werden. AAC verwendet ein adaptives Fensterumschalten (switching between long/short windows), das Pre-echo besser verhindert.

---

## c) Rock/Pop/Metal (`rock.wav`)

**Quelldatei:** WAV, PCM 16-bit, 44100 Hz, Stereo, 1411 kbit/s

| Bitrate | MP3 | AAC |
|---------|-----|-----|
| 192 kbit/s | Transparent | Transparent |
| 128 kbit/s | Minimal – kaum wahrnehmbar | Transparent |
| 96 kbit/s | **Hörbar:** metallisches Rauschen bei Schlagzeug-Becken (Cymbal-Birdie), leicht verfärbter Gitarrensound | Minimale Artefakte |
| 64 kbit/s | **Deutlich hörbar:** starkes Rauschen bei Becken, verzerrte Gitarren klingen „sandend", Bassdrum verliert Punch | **Hörbar:** ähnliche Artefakte, aber weniger ausgeprägt |
| 32 kbit/s | **Sehr deutlich:** Becken vollständig zerstört (Rauschen statt Klang), Gitarrenverzerrung klingt unnatürlich | **Deutlich hörbar:** stark verfärbt, Becken stark beeinträchtigt |

**Fazit Rock/Metal:**
- **MP3:** Artefakte klar hörbar ab **96 kbit/s** – das sogenannte „Cymbal Birdie" (Pfeifton bei Becken) und Rauschen bei Schlagzeug sind typisch
- **AAC:** Artefakte hörbar ab **64 kbit/s** – AAC ist robuster bei komplexen Transienten (Schlagzeug-Hits)

**Erklärung:** Rock/Metal enthält viele zeitlich dichte Transienten (Schlagzeug, verzerrte Gitarre) und rauschähnliche Signale (Becken), die für Codec-Algorithmen besonders schwer zu komprimieren sind. Die Phasenzuordnung in der MDCT-Basis wird bei zu niedriger Bitrate inkohärent, was das typische Rauschen/Pfeifton-Artefakt erzeugt.

---

## Vergleich: MP3 vs. AAC

| Audio-Typ | MP3 Grenze | AAC Grenze | Differenz |
|-----------|-----------|-----------|-----------|
| Sprache | 64 kbit/s | 32 kbit/s | 2× effizienter |
| Klassik | 96 kbit/s | 64 kbit/s | ~1,5× effizienter |
| Rock/Metal | 96 kbit/s | 64 kbit/s | ~1,5× effizienter |

### Warum ist AAC besser?

1. **Moderneres psychoakustisches Modell:** AAC (entwickelt 1997 als Nachfolger von MP3) verwendet präzisere Modellierung der Frequenzmaskierung und zeitlichen Maskierung
2. **Adaptives Fensterumschalten:** AAC wechselt dynamisch zwischen langen (2048-Punkte) und kurzen (256-Punkte) MDCT-Fenstern, was Pre-echo bei Transienten stark reduziert
3. **Besseres Filterbankendesign:** AAC vermeidet den Alias-Effekt von MP3 effizienter durch modifizierte DCT ohne nachgelagertes Alias-Cancellation
4. **Größerer Frequenzbereich:** AAC kodiert bis 96 kHz, MP3 bis 48 kHz Abtastrate

### Generelle Beobachtung

Beide Codecs sind bei 128 kbit/s für die meisten Hörer praktisch transparent. Bei 192 kbit/s sind selbst kritische Hörer mit geschultem Gehör kaum in der Lage, einen Unterschied zur WAV-Quelle zu erkennen. Die deutlichsten Qualitätsunterschiede zwischen MP3 und AAC zeigen sich im Bereich 32–96 kbit/s, wo AAC die ältere Technologie klar übertrifft.

# Übungsaufgabe 1 – Abgabe bis zum 3.5.2026 (moodle)

## Aufgabe: Concept Map zum Audio-Signalweg

Erstellen Sie auf einer Seite eine Concept Map ([Wikipedia: Concept-Map](https://de.wikipedia.org/wiki/Concept-Map)) zu:

- Schall
- Mikrofon
- ADC
- Samplerate
- Bittiefe
- PCM
- WAV
- Kanalzahl
- NumPy-Array

**a.** Definieren Sie jeden Begriff in 1–2 Sätzen.

**b.** Verbinden Sie die Begriffe mit Pfeilen.

**c.** Markieren Sie drei Begriffe, die zur **realen Welt / Hardware** gehören, und drei, die zur **digitalen Repräsentation in Python** gehören.

---

## Antwort

### a. Definitionen

| Begriff | Definition |
|---|---|
| **Schall** | Mechanische Druckwellen, die sich durch ein Medium (z.B. Luft) ausbreiten und vom Ohr als Ton oder Geräusch wahrgenommen werden. |
| **Mikrofon** | Wandler, der akustische Schallwellen in ein kontinuierliches analoges elektrisches Signal umwandelt. |
| **ADC** | Analog-Digital-Wandler, der ein analoges Signal in eine Folge diskreter digitaler Werte umwandelt. Wird durch Samplerate und Bittiefe konfiguriert. |
| **Samplerate** | Anzahl der Abtastwerte pro Sekunde (z.B. 44.100 Hz). Bestimmt den maximal darstellbaren Frequenzbereich (Nyquist-Theorem). |
| **Bittiefe** | Anzahl der Bits pro Abtastwert (z.B. 16 Bit). Bestimmt den Dynamikumfang und die Amplitudenauflösung der Aufnahme. |
| **PCM** | Pulse Code Modulation – Verfahren zur digitalen Kodierung analoger Signale durch gleichmäßige Abtastung und Quantisierung als Binärwerte. |
| **WAV** | Dateiformat für unkomprimiertes digitales Audio, das PCM-Daten zusammen mit Metadaten (Samplerate, Bittiefe, Kanalzahl) speichert. |
| **Kanalzahl** | Anzahl unabhängiger Audiokanäle einer Aufnahme (1 = Mono, 2 = Stereo). Beeinflusst Dateistruktur und Array-Dimensionen. |
| **NumPy-Array** | Mehrdimensionale Datenstruktur in Python, die PCM-Audiodaten nach dem Einlesen einer WAV-Datei als numerische Werte hält und effiziente Signalverarbeitung ermöglicht. |

---

### b. Verbindungen (Pfeile)

```
Schall
  └─[wird aufgenommen von]──► Mikrofon
                                  └─[liefert analoges Signal an]──► ADC
                                                                      │
                          Samplerate ──[bestimmt Abtastfrequenz von]──┤
                          Bittiefe   ──[bestimmt Auflösung von]───────┤
                                                                      │
                                                                      ▼
                                                                    PCM
                                                                      │
                          Kanalzahl  ──[ist Metadatum in]────────────┤
                          Samplerate ──[ist Metadatum in]────────────┤
                          Bittiefe   ──[ist Metadatum in]────────────┤
                                                                      ▼
                                                                    WAV
                                                                      │
                                                          [wird geladen in]
                                                                      ▼
                                                               NumPy-Array
```

---

### c. Zuordnung

| Kategorie | Begriffe |
|---|---|
| 🔴 **Reale Welt / Hardware** | Schall, Mikrofon, ADC |
| 🔵 **Digitale Repräsentation in Python** | PCM, WAV, NumPy-Array |

> **Hinweis:** Samplerate, Bittiefe und Kanalzahl überspannen beide Welten — sie sind sowohl Hardware-Parameter (ADC-Konfiguration) als auch digitale Metadaten im WAV-Header.
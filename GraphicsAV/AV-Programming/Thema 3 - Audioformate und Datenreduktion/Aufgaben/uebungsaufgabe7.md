# Übungsaufgabe 7 – Formatentscheidung für unterschiedliche Anwendungsszenarien

## Szenario A: Langzeitarchivierung eines Zeitzeugeninterviews (90 min)

**Gewähltes Format: FLAC (Free Lossless Audio Codec)**

### Begründung

1. **Verlustfreie Kompression**: FLAC speichert alle Audiodaten ohne Qualitätsverlust. Für eine wissenschaftliche Auswertung (Transkription, Segmentierung, Analyse) ist es entscheidend, dass das Originalsignal bit-genau erhalten bleibt – psychoakustische Komprimierungsartefakte (wie bei MP3/AAC) würden die Signalintegrität gefährden.

2. **Effiziente Dateigröße gegenüber WAV**: FLAC erzielt typischerweise 40–60 % Kompression gegenüber unkomprimiertem WAV. Eine 90-minütige Stereo-Aufnahme (44,1 kHz, 16 Bit) würde als WAV ca. 900 MB belegen, als FLAC hingegen nur ca. 400–550 MB – bei identischer Qualität. Dies ist bei Langzeitarchivierung (Speicherkosten, Backup) relevant.

3. **Offenes, lizenzfreies Format**: FLAC ist ein freier, offener Standard ohne Lizenzgebühren. Bei Langzeitarchivierung muss sichergestellt sein, dass Dateien auch in Jahrzehnten noch geöffnet werden können – proprietäre Formate mit Lizenzpflicht stellen ein Risiko für die Langzeitverfügbarkeit dar.

4. **Umfangreiche Metadaten**: FLAC unterstützt Vorbis-Comment-Metadaten, mit denen Interviewpartner, Datum, Ort, Sprecherinformationen und Segmentmarkierungen direkt in der Datei dokumentiert werden können.

### Alternative: WAV (PCM, unkomprimiert)

WAV ist ebenfalls verlustfrei und wird von praktisch jeder Software unterstützt. Jedoch fehlt die Kompression vollständig – die ca. 900 MB große Datei erfordert deutlich mehr Speicherplatz, was bei umfangreichen Archiven mit vielen Interviews erheblich ins Gewicht fällt. Außerdem bietet WAV kaum strukturierte Metadatenunterstützung. FLAC ist daher die bessere Wahl, da es dieselbe Qualität bei niedrigerem Speicherbedarf und besserer Metadatenverwaltung bietet.

---

## Szenario B: Studentischer Podcast auf einer Hochschulwebseite

**Gewähltes Format: AAC (Advanced Audio Coding) @ 128 kbit/s**

### Begründung

1. **Bessere Audioqualität bei gleicher Bitrate gegenüber MP3**: AAC verwendet ein moderneres psychoakustisches Modell und erzielt bei 128 kbit/s eine wahrnehmbar höhere Qualität als MP3 – besonders bei Sprache gemischt mit Musik (wie in einem Podcast). Artefakte wie Vorecho und Rauschen treten seltener auf.

2. **Kleine Dateigrößen für Web-Streaming**: Bei 128 kbit/s ist eine 30-minütige Episode nur ca. 28 MB groß – für eine Hochschulwebseite gut handhabbar. Die Dateien lassen sich effizient übertragen und auch bei schwacher Verbindung streamen.

3. **Breite Gerätekompatiblität**: AAC (im M4A- oder MP4-Container) wird nativ von allen modernen Browsern (Chrome, Firefox, Safari, Edge), iOS- und Android-Geräten sowie gängigen Mediaplayer unterstützt. Es gibt keine Inkompatibilitätsprobleme bei der Zielgruppe (Studierende mit verschiedenen Geräten).

4. **Geeignet für gemischte Inhalte**: AAC kodiert Sprache und Musik gleichermaßen gut und eignet sich damit besonders für Podcast-Formate mit Intro-Musik und gesprochenen Inhalten.

### Alternative: MP3

MP3 ist das verbreitetste Audioformat überhaupt und wird von noch mehr Geräten und Playern unterstützt. Jedoch ist MP3 technisch veraltet – bei gleicher Bitrate liefert AAC messbar bessere Qualität, vor allem bei niedrigen Bitraten (z. B. 96 kbit/s). Da eine Hochschulwebseite für ein breites Publikum zugänglich ist, ist AAC mit moderner Browserunterstützung heute die technisch überlegene und dennoch universell kompatible Wahl.

---

## Szenario C: Umweltaufnahmen zur Python-Analyse (Pegelverlauf, Dauer, Merkmale)

**Gewähltes Format: WAV (PCM, unkomprimiert)**

### Begründung

1. **Direkter Zugriff auf Rohsignal-Samples**: WAV/PCM speichert Audiodaten als unkomprimiertes Array von Abtastwerten. Python-Bibliotheken wie `scipy.io.wavfile`, `soundfile` oder `wave` können WAV-Dateien direkt als NumPy-Arrays einlesen – ohne Dekodierungsschritt. Dies ermöglicht einfachen, transparenten Zugriff auf jeden einzelnen Abtastwert.

2. **Keine Kompressionsartefakte bei der Merkmalsextraktion**: Verlustbehaftete Formate (MP3, AAC) entfernen durch psychoakustische Maskierung Signalanteile. Bei der Analyse von Pegelverlauf (RMS-Energie), spektralen Merkmalen oder Zero-Crossing-Rate würden diese Artefakte die Ergebnisse verfälschen. WAV garantiert, dass gemessene Werte dem Originalsignal entsprechen.

3. **Maximale Transparenz für die Lehre**: WAV hat eine einfache Struktur (RIFF-Header + lineare PCM-Daten), die Studierenden leicht erklärt werden kann. Der direkte Zusammenhang zwischen Dateiinhalt und physikalischem Signal (Amplitude als Integer-Wert pro Zeitschritt) ist pädagogisch wertvoll und unmittelbar verständlich.

4. **Universelle Python-Bibliotheksunterstützung**: `scipy`, `librosa`, `soundfile`, `torchaudio` – alle unterstützen WAV nativ ohne zusätzliche Abhängigkeiten oder Codec-Bibliotheken.

### Alternative: FLAC

FLAC ist ebenfalls verlustfrei und würde kleinere Dateien erzeugen. Für die Lehre ist jedoch WAV vorzuziehen: FLAC erfordert einen Dekodierungsschritt, der für Studierende eine zusätzliche Abstraktionsebene darstellt. Die einfachere Struktur von WAV macht es für Lehrszenarien transparenter und zugänglicher. Außerdem ist der Speichervorteil von FLAC bei einer Sammlung von Umweltaufnahmen (typischerweise moderate Datenmenge) weniger relevant als die Einfachheit des Formats.

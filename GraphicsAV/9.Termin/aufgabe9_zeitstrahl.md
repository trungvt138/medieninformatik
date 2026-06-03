# Aufgabe 9 – Zeitstrahl: Video-Containerformate und Video-Codecs

---

## Teil A – Video-Containerformate

```
1991        1992        1995        1995        2001/03     2002        2010
 |           |           |           |           |           |           |
MOV         AVI       MPEG-2 PS   MPEG-TS     MP4         MKV         WebM
(Apple)   (Microsoft) (DVD-Video) (Broadcast) (MPEG-4)  (Matroska) (Google)
```

---

### MOV (QuickTime Movie) — Apple, 1991
- **Einführung:** 1991 von Apple als Teil von QuickTime
- **Eigenschaften:** Track-basierte Struktur; unterstützt Video, Audio, Text, Kapitelmarken; Basis für den späteren MPEG-4-Standard (ISO/IEC 14496-12)
- **Einsatz:** Videoproduktion und -schnitt auf macOS, professionelle Postproduktion
- **Historisch:** Einer der ersten modernen Container; MOV und MPEG-4 Part 12 teilen heute dieselbe technische Grundstruktur

---

### AVI (Audio Video Interleave) — Microsoft, 1992
- **Einführung:** November 1992, Teil von Video for Windows
- **Eigenschaften:** Einfaches Interleave-Prinzip (Video- und Audiopakete abwechselnd); kaum Metadaten; kein natives Streaming; 4-GB-Dateibegrenzung (FAT32)
- **Einsatz:** Lokale Wiedergabe auf Windows-PCs in den 1990ern
- **Historisch:** Sehr weit verbreitet (DivX/Xvid-Ära), heute weitgehend durch MP4 und MKV abgelöst

---

### MPEG-2 Program Stream (PS) — ISO/IEC 13818-1, 1995
- **Einführung:** 1995 mit MPEG-2
- **Eigenschaften:** Für fehlerfreie Medien (DVD) ausgelegt; enthält Audio, Video, Untertitel; keine Fehlerkorrektur auf Containerebene
- **Einsatz:** DVD-Video
- **Historisch:** Definierte den Grundstein des digitalen Home-Cinema; MPEG-2 TS als Parallelformat für Broadcast

---

### MPEG-TS (MPEG-2 Transport Stream) — ISO/IEC 13818-1, 1995
- **Einführung:** 1995 zusammen mit MPEG-2
- **Eigenschaften:** Fehlerrobust durch feste 188-Byte-Pakete; mehrere Programme in einem Multiplex; für verlustbehaftete Übertragungskanäle konzipiert
- **Einsatz:** DVB-T/S/C-Rundfunk, ATSC (USA), Live-Streaming (HLS), IPTV
- **Historisch:** Bis heute Standard für Broadcast und HLS-Streaming im Internet

---

### MP4 / MPEG-4 Part 14 — ISO/IEC 14496-14, 2001 (formal 2003)
- **Einführung:** 2001 mit MPEG-4, formale ISO-Standardisierung 2003
- **Eigenschaften:** Basiert auf MOV/QuickTime; unterstützt H.264, H.265, AV1, AAC, Untertitel, Kapitel; breite Hardware-Unterstützung; kein praktisches Dateigrößenlimit
- **Einsatz:** Streaming (YouTube, Netflix), mobile Endgeräte, Blu-ray, Web
- **Historisch:** Heute meistgenutztes Containerformat weltweit; Nachfolger von AVI und MPEG-2 PS

---

### MKV (Matroska) — open-source, 2002
- **Einführung:** 2002 als offenes Containerformat ohne Lizenzgebühren
- **Eigenschaften:** Unterstützt praktisch jeden Codec; mehrere Audiospuren und Untertitelspuren; robustes Seek/Indexing; kein Proprietäranspruch
- **Einsatz:** Lokale Wiedergabe, Archivierung, Blu-ray-Rips
- **Historisch:** Konkurrenzformat zu MP4 für Offline-/Archivnutzung; technische Basis für WebM

---

### WebM — Google, 2010
- **Einführung:** Mai 2010 von Google und der WebM-Initiative
- **Eigenschaften:** Basiert auf Matroska; royaltyfrei; unterstützt VP8, VP9, AV1 (Video) und Vorbis, Opus (Audio); für HTML5-Video konzipiert
- **Einsatz:** HTML5 `<video>`, Web-Streaming, WebRTC
- **Historisch:** Gegenentwurf zu MP4/H.264 für das offene Web; von allen großen Browsern nativ unterstützt

---

## Teil B – Video-Codecs

```
1988       1993      1995       1996      1999      2003      2013     2013     2018
 |          |         |          |         |         |         |        |        |
H.261    MPEG-1V   MPEG-2V    H.263    MPEG-4P2  H.264/AVC VP9    H.265/HEVC AV1
(ITU-T)  (ISO)    (ISO/ITU)  (ITU-T)   (ISO)    (ISO/ITU) (Google)(ISO/ITU) (AOM)
```

---

### H.261 — ITU-T, 1988
- **Einführung:** 1988 (ratifiziert)
- **Eigenschaften:** DCT-basierte Blockkodierung (8×8 Blöcke); I- und P-Frames; CIF (352×288) und QCIF (176×144); für niedrige Bitraten auf ISDN-Leitungen
- **Einsatz:** Videokonferenz über ISDN
- **Historisch:** Wegweisend; führte DCT + Bewegungskompensation ein — das Grundprinzip aller Nachfolger

---

### MPEG-1 Video — ISO/IEC 11172-2, 1993
- **Einführung:** 1993
- **Eigenschaften:** Erster Mainstream-Codec für digitale Medien; I/P/B-Frames; bis ~1,5 Mbit/s bei 352×240 px
- **Einsatz:** Video-CD (VCD)
- **Historisch:** Etablierte das I/P/B-Frame-Konzept für die gesamte Branche

---

### MPEG-2 Video (H.262) — ISO/IEC 13818-2 / ITU-T H.262, 1995
- **Einführung:** 1995
- **Eigenschaften:** Unterstützt höhere Auflösungen (SD, HD) und höhere Bitraten; Profile (Main, High); intra-kodierte Slices für Broadcast-Sendeeignung
- **Einsatz:** DVD-Video, digitales Fernsehen (DVB, ATSC)
- **Historisch:** Über 25 Jahre Standardcodec für Broadcast und DVD; noch heute in der Übertragungsinfrastruktur aktiv

---

### H.263 — ITU-T, 1995/1996
- **Einführung:** 1995, breite Nutzung ab 1996
- **Eigenschaften:** Verbesserter Nachfolger von H.261; bessere Kompression bei gleicher Qualität; unterstützt mehr Bildformate; Grundlage für MPEG-4 Part 2
- **Einsatz:** 3G-Videotelefonie, frühe mobile Videodienste (3GP)
- **Historisch:** Übergangscodec zwischen H.261 und H.264

---

### MPEG-4 Part 2 (DivX / Xvid) — ISO/IEC 14496-2, 1999
- **Einführung:** 1999
- **Eigenschaften:** Erweiterte Bewegungskompensation; verbesserte VOP-Struktur (Video Object Plane); DivX und Xvid als populäre Implementierungen
- **Einsatz:** Frühe Internet-Videodistribution (~2000–2006)
- **Historisch:** Erste breite Verbreitung von komprimiertem Internet-Video; von H.264 verdrängt

---

### H.264 / AVC (Advanced Video Coding) — ITU-T H.264 / ISO/IEC 14496-10, 2003
- **Einführung:** Mai 2003, breite Nutzung ab 2006
- **Eigenschaften:** ~2× bessere Effizienz als MPEG-2; CABAC-Entropiecodierung; variable Blockgröße (4×4 bis 16×16); multiple Referenzframes; In-loop Deblocking Filter; Profile (Baseline, Main, High)
- **Einsatz:** Blu-ray, YouTube, Netflix, mobile Endgeräte, Videokonferenz
- **Historisch:** Dominanter Codec der letzten 20 Jahre; Grundlage für praktisch alle modernen Videodienste

---

### VP9 — Google, 2013
- **Einführung:** Juni 2013
- **Eigenschaften:** Offener, lizenzfreier Codec; vergleichbar mit H.265 in Effizienz; Superblöcke bis 64×64; Tile-basiertes Dekodieren für Parallelisierung
- **Einsatz:** YouTube (4K-Streaming), WebRTC, Chromium-Browser
- **Historisch:** Erster ernsthafter lizenzfreier Konkurrent zu H.265; ebnet den Weg für AV1

---

### H.265 / HEVC (High Efficiency Video Coding) — ITU-T H.265 / ISO/IEC 23008-2, 2013
- **Einführung:** April 2013, Verbreitung ab ca. 2016–2020
- **Eigenschaften:** ~2× bessere Effizienz als H.264; Coding Tree Units (CTU) bis 64×64; flexiblere Quadtree-Partitionierung; verbesserter In-loop-Filter; 10-Bit- und HDR-Unterstützung nativ
- **Einsatz:** 4K Blu-ray, HDR-Streaming (Netflix, Apple TV+), HEIF-Bilder (iPhone)
- **Historisch:** Nachfolger von H.264; Adoptionsprobleme wegen zersplitterter Lizenzsituation (drei getrennte Patentkonsortien)

---

### AV1 — Alliance for Open Media (AOM), 2018
- **Einführung:** März 2018
- **Eigenschaften:** Open-Source, royaltyfrei; ca. 30–50 % effizienter als H.264; Superblöcke bis 128×128; Film Grain Synthesis; noch hoher Rechenaufwand beim Encoding
- **Einsatz:** Netflix, YouTube, Discord, WebRTC (Chrome/Firefox)
- **Historisch:** Konsolidierung von VP9 (Google), Daala (Mozilla) und Thor (Cisco); zukünftiger Standardcodec für offenes Web und Streaming

---

## Quellen

- ITU-T H.261, H.263, H.264, H.265 Spezifikationen: https://www.itu.int/rec/T-REC-H/en
- ISO/IEC 14496 (MPEG-4): https://www.iso.org/standard/83209.html
- Alliance for Open Media – AV1 Bitstream Specification: https://aomedia.org/av1/specification/
- Matroska-Spezifikation: https://www.matroska.org/technical/specs/index.html
- WebM-Projektseite: https://www.webmproject.org/
- FFmpeg-Dokumentation zu Containern und Codecs: https://ffmpeg.org/ffmpeg-formats.html

---

*LLM-Nutzung: Claude Sonnet 4.6 wurde verwendet, um historische Einordnungen und technische Eigenschaften zu strukturieren. Jahreszahlen und ISO-Nummern wurden gegen offizielle Quellen geprüft. Eigene Korrekturen: Jahreszahl MPEG-TS (1995, nicht 1994), Einführungsjahr VP9 präzisiert auf Juni 2013, AV1-Größenangabe Superblöcke korrigiert auf 128×128.*

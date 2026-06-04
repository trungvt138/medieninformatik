# Aufgabe 10 – Vergleich H.264 und H.265

## Grundprinzip: Intra-Codierung, Inter-Codierung und Bewegungskompensation

Beide Standards basieren auf demselben hybriden Kodierungsprinzip, das seit H.261 (1988) die Basis aller Videocodecs bildet.

**Intra-Codierung** (I-Frames) komprimiert ein Bild ohne Bezug auf andere Frames – ähnlich einem JPEG. Dabei wird das Bild in Blöcke zerlegt, eine Diskrete-Kosinus-Transformation (DCT) in jedem Block berechnet, die Koeffizienten quantisiert und anschließend entropiekodiert. I-Frames sind deutlich größer als andere Frametypen, aber unabhängig decodierbar und damit wichtig für Schnitt- und Seek-Operationen.

**Inter-Codierung** (P- und B-Frames) nutzt zeitliche Redundanz: Statt das gesamte Bild zu speichern, werden nur die Differenzen zu einem oder mehreren Referenzframes kodiert. B-Frames können dabei vorwärts und rückwärts auf Referenzframes verweisen.

**Bewegungskompensation** ist das Kernwerkzeug der Inter-Codierung. Der Encoder sucht für jeden Block im aktuellen Frame den ähnlichsten Block in einem Referenzframe (Motion Estimation) und speichert nur den Versatzvektor (Motion Vector) sowie die Residualdifferenz. Je besser die Vorhersage, desto kleiner das Residual – und damit die Datenmenge.

## Welches Grundprinzip verfolgen H.264 und H.265?

H.264/AVC und H.265/HEVC verfolgen beide dieses hybride Prinzip: Prädiktionskodierung (räumlich und zeitlich) + Transformation + Quantisierung + Entropiekodierung. Der wesentliche Unterschied liegt in der **Granularität und Flexibilität** der Werkzeuge.

H.264 arbeitet mit Makroblöcken fixer Größe von 16×16 Pixeln, die intern in kleinere Partitionen (bis 4×4) unterteilt werden können. Die Entropiekodierung erfolgt wahlweise mit CAVLC oder dem rechenintensiveren, aber effizienteren CABAC (Context-Adaptive Binary Arithmetic Coding).

H.265 führt **Coding Tree Units (CTU)** ein, die bis zu 64×64 Pixel groß sein können. Die Partitionierung erfolgt rekursiv als Quadtree (CTU → CU → PU/TU), wodurch der Codec große homogene Flächen effizient als einen einzigen Block kodiert, während er für komplexe Bereiche tief unterteilt. Dazu kommen mehr Intra-Prädiktionsmodi (35 statt 9 in H.264), verbesserter In-loop-Filter (SAO – Sample Adaptive Offset) und Wavefront Parallel Processing für die Parallelisierung.

## Wesentlicher Fortschritt von H.265 gegenüber H.264

H.265 erreicht bei gleicher subjektiver Qualität etwa **50 % geringere Bitrate** als H.264. Dieser Gewinn ergibt sich aus mehreren zusammenwirkenden Faktoren: größere und flexiblere Kodiereinheiten reduzieren den Overhead bei gleichförmigen Bildbereichen; mehr Prädiktionsmodi erlauben eine präzisere Vorhersage; der verbesserte SAO-Filter reduziert Banding-Artefakte; und die breitere Nutzung von CABAC über alle Profile hinweg verbessert die Entropiecodierung.

Besonders relevant ist H.265 für hochauflösende Inhalte (4K, HDR), da hier die Einsparung absolut betrachtet besonders groß ist.

## H.265 als Nachfolger von H.264

H.265 ist der direkte Nachfolger von H.264 und wurde 2013 von ITU-T (als H.265) und ISO/IEC (als ISO/IEC 23008-2) standardisiert. Er ist abwärtskompatibel in dem Sinne, dass dieselben Anwendungsszenarien (Streaming, Archivierung, Broadcast) abgedeckt werden, aber nicht auf Bitstromebene – ein H.265-Decoder kann keine H.264-Streams dekodieren und umgekehrt.

Die Adoption von H.265 verlief langsamer als die von H.264, da die Lizenzsituation komplexer ist: Drei verschiedene Patentkonsortien (MPEG LA, HEVC Advance, Velos Media) erheben Gebühren, was viele Anbieter dazu bewegte, stattdessen das lizenzfreie AV1 (2018) zu favorisieren.

## Warum H.264 für diesen Kurs die vertiefte Behandlung verdient

H.264 bleibt aus didaktischer Sicht der geeignetere Einstiegscodec: Er ist in seinen Werkzeugen vollständig, aber noch überschaubar strukturiert – Makroblöcke, Profile, CABAC lassen sich klar erklären. H.265 fügt Komplexität (CTU-Quadtree, SAO, 35 Intra-Modi) hinzu, ohne neue Prinzipien einzuführen; es sind Verfeinerungen bekannter Konzepte. Wer H.264 versteht, versteht damit auch das Grundprinzip von H.265 und AV1. Umgekehrt würde ein Direkteinstieg mit H.265 die Lernkurve unnötig steiler machen. Zudem ist H.264 in der Praxis – bei Werkzeugen, APIs, Hardware-Decodern und verfügbaren Beispieldaten – noch immer allgegenwärtig.

---

## Quellen

- ITU-T H.264 Spezifikation: https://www.itu.int/rec/T-REC-H.264/en
- ITU-T H.265 Spezifikation: https://www.itu.int/rec/T-REC-H.265/en
- Richardson, I.E.G. (2010): *The H.264 Advanced Video Compression Standard*, Wiley
- Sullivan et al. (2012): Overview of the High Efficiency Video Coding (HEVC) Standard, IEEE Trans. Circuits Syst. Video Technol. 22(12)

# CI_05_00 — Daten & Terminologie (detailliert)

---

## 1. Warum sind Daten so wichtig?

Die eigentliche Revolution ist **nicht** die Technik, sondern die Daten selbst und wie wir sie nutzen. Früher hatte man wenig Daten — heute entstehen jede Minute Millionen neue Einträge (YouTube-Videos, WhatsApp-Nachrichten, Käufe im Online-Shop...).

**Zahl zum Staunen:** Allein 2012 gab es `2.800.000.000.000.000.000.000 Bytes` — als Papierstapel wäre das **2000× die Entfernung Erde–Sonne**.

---

## 2. Big Data — die 3+1 "V"s (nach Gartner, 2011)

**Velocity — Geschwindigkeit**
Daten entstehen im Sekundentakt oder noch schneller. Ein System muss in der Lage sein, diese Daten sofort zu verarbeiten, nicht erst nach Stunden.

**Volume — Umfang**
Die Datenmenge ist riesig. Man möchte möglichst nichts davon wegwerfen, weil jeder Datenpunkt nützlich sein könnte.

**Variety — Vielfalt**
Daten kommen in vielen Formen: strukturierte Tabellen, aber auch Bilder, Videos, Texte, Sensordaten. Klassische relationale Datenbanken kommen damit kaum zurecht.

**Veracity — Zuverlässigkeit** *(4. V, nach Mills u.a., 2012)*
Daten können unvollständig, widersprüchlich oder mehrdeutig sein — je nach Herkunft. Man muss immer die Qualität der Daten im Blick behalten.

Es gibt noch weitere "V"s (Variability, Validity, Vulnerability, Volatility...) — das zeigt, wie komplex das Thema ist.

---

## 3. Big Data ist nicht wirklich neu

Der "Mythos" lautet: Big Data ist etwas völlig Neues. Tatsächlich hat man schon immer große Datenmengen analysiert. **Neu** ist:
- die Geschwindigkeit, mit der Daten wachsen
- die Technologien, die das möglich machen
- die Kombination vieler Fachbereiche: IT, Datenbanken, KI, Mathematik, Statistik, Linguistik, Interface-Design

Es gibt **keine fertigen Lösungspakete** — für jeden Anwendungsfall muss man selbst eine Lösung aus vielen Bausteinen zusammensetzen.

---

## 4. Das "Rezept" für Big Data

**Infrastruktur:**
Große Firmen (Google, Amazon, Facebook) stießen an technische Grenzen und entwickelten neue Technologien:
- **Cloud** — Rechenleistung und Speicher über das Internet mieten
- **Hadoop** — verteilte Verarbeitung riesiger Datenmengen auf vielen Rechnern gleichzeitig
- Ziel: Kosten steigen *linear* mit der Datenmenge, nicht exponentiell

**Software:**
- **Parallelverarbeitung / MapReduce** — Aufgaben auf viele Rechner verteilen und Ergebnisse zusammenführen
- **NoSQL** — Datenbanken, die nicht nur klassische Tabellen kennen (z.B. Column Stores, InMemory-Datenbanken)
- **Realtime Stream Processing** — Daten sofort auswerten, noch während sie ankommen

**KI & Mathematik:**
- Maschinelles Lernen, Neuronale Netze
- Wahrscheinlichkeiten, Bayes'sche Netze
- Klassifikation, Clustering
- Sprachverarbeitung (NLP), Semantic Web

**Interfaces:**
- Datenvisualisierung, Storytelling
- APIs, REST, JSON, HTML5/JavaScript — damit Menschen und Systeme die Ergebnisse sehen und nutzen können

---

## 5. Wissensgewinnung — wie kommt man zu Erkenntnissen?

**Weg 1 — Daten zuerst, dann schauen:**
Man hat bereits Daten (z.B. Kaufhistorie) und analysiert sie blind, ob sich Muster ergeben.
- Beispiel: *"Kunden, die dieses Produkt kauften, kauften auch..."*

**Weg 2 — Hypothese zuerst, dann Daten suchen:**
Man stellt eine These auf und sucht gezielt nach Daten, die sie bestätigen oder widerlegen.
- **Vorsicht:** Man findet fast immer Daten, die eine These "bestätigen" — das ist trügerisch! (Erst sieht ein Trend eindeutig aus, mit mehr Daten dreht er sich komplett um.)

**Weg 3 — Daten, die bisher nicht betrachtet wurden:**
Journalisten oder Forscher haben ein Thema und suchen erst dann nach passenden Datensätzen.

---

## 6. Verteilte Datenbanken

Statt alles auf einem zentralen Server zu speichern, verteilt man die Daten auf mehrere Rechner:

| Art | Bedeutung |
|-----|-----------|
| **Horizontale Verteilung** | Verschiedene Zeilen einer Tabelle liegen auf verschiedenen Rechnern (z.B. Bestellungen 1–500 hier, 501–1000 dort) |
| **Vertikale Verteilung** | Verschiedene Spalten einer Tabelle liegen auf verschiedenen Rechnern |
| **Replikation** | Dieselben Daten liegen auf mehreren Rechnern gleichzeitig (für Ausfallsicherheit & Schnelligkeit) |

---

## 7. Business Intelligence (BI)

BI ist der **Oberbegriff** für alle Systeme, die Daten sammeln, aufbereiten und analysieren, damit Firmen bessere Entscheidungen treffen können.

**Aufbau:**
```
Rohdaten (Kundendaten, Produktionsdaten, externe Daten)
        ↓  Auswahl & Aggregation
   Data Warehouse  (zentraler, aufbereiteter Datenpool)
        ↓
   Analyse-Tools: OLAP, Data Mining, Berichte
        ↓
   Entscheidung
```

**Data Warehouse** = große, aufbereitete Datenbank mit historischen und aktuellen Daten aus vielen Quellen.

**Data Mart** = kleinere Variante des Data Warehouse — nur für eine bestimmte Abteilung oder Funktion.

---

## 8. OLAP — mehrdimensionale Analyse

**OLAP** (OnLine Analytical Processing) erlaubt es, Daten aus **mehreren Perspektiven gleichzeitig** zu betrachten — vorgestellt als 3D-Würfel:
- Dimension 1: Zeit (Monat, Quartal, Jahr)
- Dimension 2: Produkt (Chips, Cola, Wasser...)
- Dimension 3: Region (Bayern, NRW, Berlin...)

Man kann den Würfel "drehen" und z.B. fragen: *"Wie haben sich die Verkäufe von Chips in Bayern im Q3 entwickelt?"* — schnell und ohne aufwändige SQL-Abfragen.

---

## 9. Knowledge Discovery & Data Mining

Der Prozess, aus rohen Daten nützliches Wissen zu gewinnen, läuft in mehreren Schritten ab:

1. **Defining Goal** — Was will ich wissen?
2. **Data Selection** — Welche Daten brauche ich?
3. **Data Cleaning** — Fehler, Duplikate, fehlende Werte bereinigen
4. **Data Reduction** — Daten auf das Wesentliche reduzieren
5. **Mining Function & Algorithm** — Welche Methode passt? (Clustering, Klassifikation...)
6. **Interpretation** — Was bedeuten die Ergebnisse?
7. **Performance** — Wie gut funktioniert das Modell?

**Beispiel:** *"Wer Chips kauft, kauft zu 85% auch Cola"* → Chips und Cola werden im Regal nebeneinander platziert.

---

## 10. Predictive Analytics — die 4 Stufen

Nach Gartner gibt es eine Stufenleiter von einfach (wenig Wert) bis schwer (großer Wert):

| Stufe | Frage | Beispiel |
|-------|-------|---------|
| **Descriptive** | Was ist passiert? | Umsatz letzten Monat: 50.000 € |
| **Diagnostic** | Warum ist es passiert? | Wegen Rabattaktion in Woche 3 |
| **Predictive** | Was wird passieren? | Nächsten Monat ~55.000 € erwartet |
| **Prescriptive** | Wie können wir es beeinflussen? | Rabatt auf Produkt X erhöhen → +10% Umsatz |

Predictive Analytics nach Gartner zeichnet sich aus durch:
1. Fokus auf **Vorhersage** (nicht nur Beschreibung)
2. **Schnelle** Analysen (Stunden/Tage, nicht Monate)
3. Klarer **Business-Bezug** (keine reinen Forschungsanalysen)
4. **Einfache Bedienung** — auch für Nicht-Techniker nutzbar

---

## Kernbotschaft

> Daten sind Rohstoff. Erst durch saubere Aufbereitung, clevere Analyse und die richtigen Werkzeuge (BI, Data Mining, OLAP, Predictive Analytics) werden daraus Erkenntnisse — und aus Erkenntnissen Entscheidungen.

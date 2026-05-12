# README für Lehrende – Python 101 Selbstlernkurs Medieninformatik 🐍🎧💻

Dieses Dokument richtet sich an **Lehrende**, die den Python‑Selbstlernkurs im Studiengang
**Medieninformatik** begleiten oder einsetzen möchten.  
Es erklärt Aufbau, Ziele, bewährte Unterrichtsstrategien und Hinweise zur Betreuung.

---

# 🎯 1. Zielsetzung des Kurses

Der Kurs wurde entwickelt, um Studierende im **3. Semester Medieninformatik** auf einen
einheitlichen Python‑Kompetenzstand zu bringen – unabhängig von sehr heterogenen Vorkenntnissen:

- einige hatten **mehrjährige Python-Erfahrung** (Schule, Projekte),
- andere **ringen noch mit Java**,
- wieder andere haben **Quereinsteigerhintergrund**.

Der Selbstlernkurs ermöglicht:

- individuelles Einstiegstempo  
- adaptive Lernpfade (über den **Einstufungstest**)  
- kontinuierliche Übungsmöglichkeiten  
- klare Struktur (Module + Übungssets)  
- Einsatz in Vorlesungen, Praktika oder Selbstlernphasen  


## 🎓 Einsatz des KI-Tutors im Python-Selbstlernkurs

Dieser Kurs nutzt ein projektspezifisches KI-Tutorsystem, das Studierende beim selbstständigen Arbeiten unterstützt.  
Der Tutor ist auf „Hinweise statt Lösungen“ ausgerichtet und hilft vor allem beim Debugging, Verständnis und Reflektieren von Lösungswegen.

Die genauen Regeln, Verhaltenserwartungen und Nutzungshinweise für Studierende findest du hier:

👉 **Studenten-Anleitung: USING_AI_TUTOR.md**  
[docs/USING_AI_TUTOR.md](USING_AI_TUTOR.md)

Der Tutor liest automatisch:
- `.github/copilot-instructions.md` → pädagogische Regeln  
- `.vscode/settings.json` → Workspace-Konfiguration  

Bitte weise Studierende besonders in der Startphase darauf hin, den Tutor reflektiert einzusetzen und nicht als Lösungsgenerator zu verwenden.


---

# 🧭 2. Struktur und Materialien

Das Repository enthält:

```
INDEX_overview.ipynb        ← Zentrales Navigations-Notebook
notebooks/                  ← Module 00–9
exercises/                  ← Übungssets A/B/C
placement/                  ← Einstufungstest
docs/                       ← Guides für Lernende & Codespaces-Anleitung
.github/ & .vscode/         ← Vorkonfigurierte Umgebung
```

## Module (00–07)
Didaktisch aufeinander aufbauend, praxisnah, mit Medieninformatik‑Bezug:
- Basics, Kontrollstrukturen, Funktionen  
- Datenstrukturen (Listen, Dicts), Dateien/CSV  
- Fehlerbehandlung, Debugging  
- Module/Projektstruktur

## Module (08–08)
Weiterführende Medieninformatik‑Skills in Python:
- Daten laden, analysieren, visualisieren
- Überblick über objektorientierte Programmierung

## Übungssets (Level A, B, C)
Zur Vertiefung oder Differenzierung:

- **A** = Einsteiger  
- **B** = Mittelstufe  
- **C** = Fortgeschritten  

Alle Sets haben klar formulierte Aufgaben inkl. Startercode.

## Einstufungstest
Empfiehlt einen Lernpfad (A/B/C).  
Kann zu Beginn eines Semesters genutzt werden, um Studierende zu sortieren.

---

# 🚀 3. Nutzung im Kurs / mögliche Szenarien

## Szenario A – Selbstlernphase vor Semesterbeginn
- Studierende erhalten Link zum Repo  
- bearbeiten Einstufungstest  
- absolvieren mindestens Modul 00–03  
- Start ins Semester gelingt deutlich homogenisiert

## Szenario B – Flipped Classroom
1. Studierende arbeiten zuhause Module + Übungen durch  
2. Präsenzzeit = Fragen klären, Projekte, Anwendung, Pair‑Programming  
3. Lehrende agieren als Coach statt Inhaltsvermittler

## Szenario C – Integrierter Teil eines Praktikums
- Module 00–07 = Pflicht  
- Übungen B/C = benotete Übungsblätter  
- Codespaces eliminiert Installationsprobleme

## Szenario D – Freies Lernen während Projekten
- Studierende nutzen das Repo als Referenz  
- ideal in Projektkursen, wenn Python nur „Werkzeug“ ist

---

# 🛠️ 4. Hinweise zur Betreuung

## 4.1 Umgang mit heterogenen Gruppen
- Studierende nicht unter Druck setzen, „alle das Gleiche zur gleichen Zeit“ zu leisten  
- Level A/B/C konsequent kommunizieren  
- Fortgeschrittene früh in **Projektrollen** bringen (z. B. Mentoring)

## 4.2 Nutzung von GitHub Codespaces
Empfehlenswert, weil:
- keine Installation nötig  
- einheitliche Umgebung  
- Jupyter läuft stabil  
- Lehrende können Studierenden bei identischen Umgebungen besser helfen  

Hinweis: Einige Studierende brauchen Hilfe bei der Einrichtung → `using_codespaces.md`.

## 4.3 Typische Stolpersteine Studierender
- Python‑Indentation („Warum sagt er 'IndentationError'?“)  
- Mischung Java <→ Python (Typdenken, Semikolons, CamelCase)  
- Verständnis von Listen vs. Dictionaries  
- Schleifenlogik (insbesondere while + Abbruchbedingungen)  
- Umgang mit Dateien auf relativen Pfaden  

Die Module adressieren diese Probleme explizit.

## 4.4 KI‑Unterstützung
Studierende arbeiten im Repo häufig mit integrierten KI‑Assistenten (VS Code Copilot / ChatGPT).
Aus Sicht der Lehre empfehlenswert:

- KI für Fehlersuche einsetzen lassen  
- aber **nicht** zur Generierung ganzer Lösungen → Studierende sollen Code nachvollziehen können  
- Erklärbär‑Funktion ausdrücklich erlauben  
- im Unterricht über Chancen & Risiken reflektieren

---


# 📦 5. Anpassung & Erweiterbarkeit

Der Kurs ist modular und lässt sich leicht erweitern:

- zusätzliche Übungen (z. B. API‑Requests, JSON, Reguläre Ausdrücke)
- ein Projekt „Medienanalyse“ für das Semester  
- automatische Tests mit `pytest`  
- Gamification (Badges für absolvierte Module)  

Die Struktur ist absichtlich offen gehalten.

---

# 🧑‍🏫 6. Tipps für neue Lehrende

- erst **INDEX_overview.ipynb** durchgehen  
- dann Module 00–02 selbst testen  
- anhand von 3–5 Studierenden prüfen, ob Level A/B/C Empfehlungen passen  
- bewusst kurze Theorieeinheiten planen (Python ist Doing-first!)  
- bei Fragen gerne ergänzen: Dieses Repo ist dafür gebaut, mitzuwachsen

---

# 🙌 Viel Erfolg beim Einsatz!

Bei Bedarf kann dieses README erweitert werden, z. B. um Musterklausuren,
Projektaufgaben oder FAQ für Lehrende.


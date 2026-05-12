# Leitfaden für Studierende – Python 101 Selbstlernkurs Medieninformatik 🐍🎧💻

Dieser Leitfaden erklärt dir, wie du mit diesem Selbstlernkurs arbeitest, wie die Jupyter-Notebooks funktionieren, wie du den Einstufungstest nutzt und wie du KI als Erklärbär sinnvoll einsetzt.

---

## 1. Ziel dieses Kurses

Der Kurs soll dir helfen, Python eigenständig und in deinem eigenen Tempo zu lernen.  
Dabei wirst du:

- deinen Kenntnisstand einschätzen,
- einen zu dir passenden Lernpfad (Level A/B/C) wählen,
- Übungen in Jupyter-Notebooks bearbeiten,
- ein kleines Abschlussprojekt durchführen.

Der Kurs setzt **keine** Vorkenntnisse in Python voraus, nur grundlegende Programmiererfahrung (z. B. aus Java).

---

## 2. Wie du mit Jupyter-Notebooks arbeitest

Jupyter-Notebooks bestehen aus zwei Arten von Zellen:

- **Codezellen** — ausführbarer Python-Code  
- **Markdownzellen** — Text, Erklärungen, Aufgabenstellungen

### So führst du Code aus

1. Markiere eine Codezelle.
2. Drücke **Shift + Enter** oder benutze den ► Run-Button.
3. Die Ausgabe erscheint direkt unter der Zelle.

### Kernel auswählen

Falls das Notebook meldet, dass kein Kernel ausgewählt ist:

1. Klicke oben rechts auf „Select Kernel“.  
2. Wähle dein virtuelles Environment (`.venv`) oder den Cloud-Container-Kernel.

### Hinweise zu Fehlern

Fehler („Exceptions“) gehören dazu.  
Sie bedeuten nicht, dass du gescheitert bist, sondern dass der Code dir mitteilt, wo etwas nicht passt.

Nutze sie als **Lernhilfe**!

---

## 3. Der Einstufungstest

Im Ordner `placement/` findest du:

`placement_test.ipynb`

Dieses Notebook:

1. stellt dir mehrere kleine Fragen (Multiple Choice, kleine Codeaufgaben),
2. berechnet am Ende automatisch deine Punktzahl,
3. ordnet dich einem der folgenden Levels zu:

- **Level A — Anfänger:innen / Wiedereinsteiger:innen**
- **Level B — Fortgeschrittene Grundkenntnisse**
- **Level C — Erfahrener Umgang mit Python**

### Warum gibt es Levels?

Damit niemand unterfordert oder überfordert wird.  
Jede Gruppe bekommt eigene Übungen und Schwerpunkte.

---

## 4. Dein Lernpfad

Nach dem Einstufungstest bekommst du eine Empfehlung.  
Allgemein gilt:

### **Level A**

- Beginne bei `00_java_to_python.ipynb`  
- arbeite jedes Basis-Notebook durch  
- nutze die Übungen in `exercises/level_A/`

### **Level B**

- Beginne bei `01_basics.ipynb`
- arbeite alle Kernmodule systematisch durch
- nutze `exercises/level_B/`

### **Level C**

- du kannst Module überspringen oder nur überfliegen
- Fokus liegt auf Challenges und Medienprojekten
- nutze `exercises/level_C/`

---

## 5. Wie du KI als „Erklärbär“ sinnvoll nutzt

Du darfst KI in diesem Kurs verwenden — aber **richtig**.

### KI ist gedacht für:

- Erklärungen („Was bedeutet diese Fehlermeldung?“)
- Verständnis („Erklär mir meinen Code Schritt für Schritt.“)
- Debugging („Wo könnte der Fehler liegen?“)
- Varianten („Gibt es einen eleganteren Ansatz?“)

### KI ist **nicht** gedacht für:

- komplette Lösungen kopieren
- Aufgaben abgeben, die du nicht verstehst
- Projektlösungen generieren lassen

### Beispiel-Prompts

- „Erklär mir bitte den Fehler `TypeError` aus meiner letzten Zelle. Nur einen Hinweis, keine fertige Lösung.“
- „Kannst du meinen Code so umformulieren, dass er einfacher zu lesen ist?“
- „Ich verstehe folgenden Codeabschnitt nicht – bitte erklär ihn in einfachen Worten.“

### Goldene Regel

> **Benutze die KI nur so, dass du *mehr* verstehst, nicht weniger.**

Wenn du die Lösung nicht nachvollziehen kannst, hast du sie nicht gelöst.

---

## 6. Tipps für erfolgreiches Lernen

- Arbeite in kleinen Häppchen (20–40 Minuten Sessions).
- Führe **jede Codezeile selbst aus** und experimentiere.
- Versuche erst selbst eine Lösung, bevor du Hinweise suchst.
- Baue dir eigene kleine Beispiele — das vertieft das Verständnis enorm.
- Nutze Fehler als Chance, nicht als Störung.

---

## 7. Technische Probleme?

Typische Lösungen:

### Notebook startet nicht?
→ Prüfe, ob der richtige Kernel gewählt ist.

### Module können nicht importiert werden?
→ Installiere erneut:
```
pip install -r requirements.txt
```

### Bei Cloud-Varianten hängt etwas?
→ Browser neu laden oder Container neu starten.

Wenn nichts hilft, notiere die Fehlermeldung und frag nach Hilfe — oder lass sie dir von der KI erklären.

---

## Viel Erfolg – und vor allem: Hab Spaß beim Lernen! 🐍🎧

# Arbeiten mit Codespaces & Notebook-Kernel auswählen  
## Anleitung für den Python-Selbstlernkurs Medieninformatik

Diese Anleitung zeigt dir, wie du den Kurs in **GitHub Codespaces** nutzt und wie du im Notebook den **richtigen Python-Kernel** auswählst.  
Das ist wichtig, damit die Jupyter-Notebooks korrekt ausgeführt werden und alle Bibliotheken verfügbar sind.

---

# 1. Codespace öffnen

1. Öffne das Repository des Kurses auf GitHub.
2. Klicke auf den grünen **Code**-Button.
3. Wähle den Reiter **Codespaces**.
4. Klicke auf **„Create codespace on main“**.

Nach wenigen Sekunden startet ein **VS Code**-Fenster im Browser.  
Das ist deine gesamte Entwicklungsumgebung – du musst lokal nichts installieren.

---

# 2. Prüfen, ob die Python-Umgebung bereit ist

Im Codespace steht dir normalerweise bereits Python zur Verfügung.

Falls der Kurs ein virtuelles Environment (`.venv`) verwendet, musst du es einmalig anlegen:

1. Öffne das Terminal (unten in VS Code).
2. Tippe:

```bash
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
```

Danach ist alles eingerichtet.

---

# 3. Notebook öffnen

1. Öffne links im Explorer das Verzeichnis `placement/`.
2. Klicke auf `placement_test.ipynb`.
3. Das Notebook erscheint im Editor.

Bevor du es ausführen kannst, musst du **den richtigen Python-Kernel auswählen**.

---

# 4. Wichtigen Schritt: Den Python-Kernel auswählen

Wenn oben rechts im Notebook der Hinweis **„Select Kernel“** steht, musst du eine Python-Umgebung auswählen.

Du bekommst dann zwei Kategorien angezeigt:

---

## **A. Python-Umgebungen**

Hier findest du:

- dein virtuelles Environment (meist angezeigt als  
  **„Python 3.x ('.venv': venv)“**)  
- weitere lokale Python-Versionen

👉 **Wähle immer eine Umgebung aus dieser Liste!**  
Am besten:  
**`Python 3.x (.venv)`**

Damit verwendest du die speziell für den Kurs vorgesehenen Pakete.

---

## **B. Vorhandene Jupyter-Server bitte nicht verwenden**

Diese Umgebung darfst du **nicht** auswählen.  
Sie ist:

- nicht mit deinem `.venv` verbunden  
- oft unvollständig eingerichtet  
- bei jedem Codespace-Neustart anders  
- unzuverlässig für Kursmaterial

👉 **Diese Option bitte ignorieren.**

---

# 5. Warum ist der richtige Kernel wichtig?

Nur wenn du eine **Python-Umgebung** auswählst:

- stehen alle benötigten Python-Pakete zur Verfügung  
- funktionieren Jupyter-Notebooks fehlerfrei  
- findest du Dateien und Module des Kursprojekts  
- laufen Fehlermeldungen wie „IPython not found“ nicht auf

Der falsche Kernel führt fast immer zu:

- `ModuleNotFoundError`
- „Kernel busy / dead“
- fehlenden Imports
- fehlerhaften Auswertungen

---

# 6. Notebook ausführen

Wenn du den richtigen Kernel ausgewählt hast:

- klicke in eine Zelle  
- drücke **Shift + Enter**

Die Ausgabe erscheint direkt unter der Zelle.  
Geh so Zelle für Zelle durch das Notebook.

Am Ende erhältst du deine persönliche Einstufung (Level A/B/C).

---

# 7. Probleme? Hier sind schnelle Lösungen

### **Fehler: Kein Kernel gefunden**
→ Interpreter manuell auswählen (siehe Schritt 4)

### **Fehler: Module fehlen (z. B. IPython)**
→ Terminal öffnen und:

```bash
pip install -r requirements.txt
```

### **Notebook lädt nicht**
→ Seite neu laden oder Codespace neu starten  
(über das Menü oben links)

---

# 🎉 Fertig!

Damit kannst du alle Kurs-Notebooks in der Cloud bearbeiten — ohne lokale Installation, ohne Python-Setup-Probleme und ohne Konfigurationsstress.

## Zusatz: Wenn du das Repository lokal in VS Code auf deinem Rechner bearbeiten willst, gehst du so vor: 
### 1. Repository klonen
1. Öffne VS Code.
2. Öffne die Befehlspalette mit Ctrl + Shift + P.
3. Suche nach Git: Clone.
4. Füge die URL des GitHub-Repositories ein.
5. Wähle einen lokalen Ordner, in den das Repository geklont werden soll.
6. Öffne danach den geklonten Projektordner in VS Code.

### 2. Virtuelle Umgebung (venv) einmalig einrichten
Öffne ein Terminal in VS Code.

Dann:

```bash
python -m venv .venv
```

Jetzt aktivieren:

Windows (cmd):
```bash
.venv\Scripts\activate.bat
```

macOS/Linux:
```bash
source .venv/bin/activate
``` 
Dann die benötigten Pakete installieren:
```bash
pip install -r requirements.txt
``` 

### Beim nächsten Öffnen des Projekts in VC Code
Die Umgebung muss nicht neu erstellt werden.
Windows (cmd):
```bash
.venv\Scripts\activate.bat
```

macOS/Linux:
```bash
source .venv/bin/activate
``` 
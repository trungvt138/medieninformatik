# using_ai_tutor.md

# 🎓 Nutzung des KI-Tutors im Self-Study Python Kurs

Dieser Kurs enthält einen **integrierten KI-Tutor**, der dich beim
Lernen unterstützt.\
Der Tutor hilft dir beim Verstehen, Debuggen und Strukturieren deiner
Lösungswege -- **aber er löst die Aufgaben nicht für dich**.

Der KI-Tutor ist direkt in **VS Code / GitHub Codespaces** verfügbar und
wird automatisch mit den Regeln aus dem Projekt geladen.

------------------------------------------------------------------------

# 🧑‍🏫 1. Was kann der KI-Tutor?

Der Tutor unterstützt dich bei:

-   Erklärungen zu Python-Konzepten\
-   Verständnisfragen\
-   Debugging von Code\
-   Vorschlägen für bessere Testfälle\
-   Hinweisen zu Fehlersuche und Struktur\
-   Verweisen auf die passenden Module (00--09)

**Der Tutor ist kein Lösungsautomat.**\
Er gibt dir **Hinweise**, keine vollständigen Lösungen.

------------------------------------------------------------------------

# 🧭 2. Wie öffne ich den KI-Tutor?

### In **GitHub Codespaces** oder **VS Code**:

1.  Öffne das Kurs-Repository in VS Code.\
2.  Klicke links auf das Symbol **„Chat"** (Copilot / KI-Assistent).\
3.  Schreibe deine Frage in das Chat-Fenster.

Die Tutor-Regeln werden **automatisch geladen** -- du musst keinen
Prompt einfügen.

------------------------------------------------------------------------

# 💬 3. Wie stelle ich eine gute Frage?

Eine gute Frage enthält:

1.  **Modul**\
2.  **Aufgabe**\
3.  **Dein Code**\
4.  **Was du erreichen möchtest**\
5.  **Was nicht funktioniert**

### Beispiel

> Ich bin in *Modul 03 -- Functions*, Aufgabe 3.2.\
> Meine Funktion soll prüfen, ob ein String ein Palindrom ist.\
> Hier ist mein Code:
>
> ``` python
> def is_pal(s):
>     for i in range(len(s)):
>         if s[i] != s[-i]:
>             return False
>     return True
> ```
>
> Für "anna" bekomme ich False zurück. Wo liegt mein Denkfehler?\
> Bitte gib mir Hinweise, keine fertige Lösung.

------------------------------------------------------------------------

# 🧠 4. Was du vom Tutor erwarten kannst

Der Tutor wird:

-   Erklären, **warum** ein Fehler entsteht\
-   Kleine Hinweise geben\
-   Verständnisfragen stellen\
-   Vorschlagen, wie du selbst testen kannst\
-   Python-Konzepte aufgreifen und wiederholen\
-   Beispiele zeigen, aber **nicht die komplette Musterlösung**

Der Tutor wird **nicht**:

-   eine vollständige Lösung für deine Aufgabe schreiben\
-   komplette Übungssets für dich lösen\
-   Code für dich entwickeln, ohne dass du mitarbeitest\
-   Hausaufgaben oder Projektaufgaben fertig programmieren

------------------------------------------------------------------------

# 🛠️ 5. Wie nutzt du den Tutor beim Debuggen?

1.  Schreibe deinen Code selbst.\
2.  Teste ihn mit mehreren Eingaben.\
3.  Wenn etwas nicht stimmt, sende nur den relevanten Teil.

Beispiel:

> „Ich glaube, mein Fehler ist in der Schleife, aber ich weiß nicht
> genau wo."

------------------------------------------------------------------------

# 🚫 6. Was solltest du vermeiden?

-   „Schreib mir die Lösung."
-   „Löse Aufgabe 3.2 für mich."
-   „Mach das für mich richtig."

------------------------------------------------------------------------

# 📚 7. Woher kennt der Tutor die Kursregeln?

Der Tutor liest automatisch:

-   `.github/copilot-instructions.md`\
-   `.vscode/settings.json`

------------------------------------------------------------------------

# 🧪 8. Beispielhafte gute Tutor-Fragen

### Verständnisfrage

> Erklär mir nochmal kurz, was der Unterschied zwischen einer Liste und
> einem Tuple ist.

### Debugging

> In Modul 02 bricht meine Schleife nicht ab.
>
> ``` python
> i = 0
> while i < 10:
>     print(i)
> ```
>
> Ich denke, es hat etwas mit dem Zähler zu tun.

------------------------------------------------------------------------

# 🎯 9. Ziel des KI-Tutors

Der Tutor soll dir helfen, Probleme zu verstehen und selbstständig zu
lösen.

------------------------------------------------------------------------

# ✔️ 10. Noch Fragen?

Frage einfach den Tutor selbst oder wende dich an die Lehrenden.

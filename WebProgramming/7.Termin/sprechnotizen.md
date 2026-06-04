# Sprechnotizen – State Management in React (20 Min)

## 1 – Was ist State? (3 Min)
"Hi zusammen, heute geht's um State Management in React. Aber erstmal ganz von vorne – was ist State überhaupt?"

"Stellt euch eine ganz normale Webseite vor. Die hat HTML, CSS, sieht hübsch aus – aber sie ist statisch. Nichts verändert sich. State ist das, was eine App dynamisch macht. State sind Daten, die sich verändern während der User die App benutzt."

"Ein paar Beispiele: Ihr klickt auf einen Button und ein Zähler geht hoch – die Zahl ist State. Ihr tippt etwas in ein Suchfeld – der Text ist State. Ihr loggt euch ein – ob ihr eingeloggt seid oder nicht, ist State. Ein Modal das auf- und zugeht – ob es offen ist, ist State."

"Also kurz gesagt: Alles was sich zur Laufzeit ändern kann, ist State. Und State Management heißt einfach: Wie organisieren wir diese Daten in unserer App?"

"Es gibt verschiedene Level. Manchmal braucht nur eine einzige Komponente den State – das ist lokaler State. Manchmal brauchen viele Komponenten denselben State – das ist globaler State. Und je nachdem wie komplex das wird, gibt es verschiedene Werkzeuge. Wir fangen heute ganz einfach an und arbeiten uns hoch."

---

## 2 – useState: Counter + Custom Hook – Live Coding (5 Min)
"Das erste Werkzeug ist useState – ein sogenannter Hook, den React uns gibt. Damit können wir einer Komponente einen State geben. Ich zeig euch das jetzt live mit einem einfachen Counter."

*[Counter coden / zeigen]*

"Schauen wir uns das Zeile für Zeile an. Oben importieren wir useState aus React. Dann in der Komponente: `const [count, setCount] = useState(0)`. Das gibt uns zwei Sachen zurück – den aktuellen Wert, hier `count`, und eine Funktion um den Wert zu ändern, `setCount`. Die 0 in den Klammern ist der Startwert."

"Dann haben wir drei Funktionen: increment ruft setCount auf mit count + 1, decrement mit count - 1, und reset setzt auf 0 zurück. Unten im Return rendern wir den Wert und drei Buttons."

"Das Wichtige: Wir ändern count nie direkt. Wir rufen immer setCount auf. Warum? Weil React dann weiß, dass sich was geändert hat, und die Komponente neu rendert – also die Anzeige aktualisiert."

"Und noch was: Dieser State ist lokal. Nur diese eine Counter-Komponente kennt den Wert. Keine andere Komponente weiß was count gerade ist."

"Das beweis ich euch jetzt. Ich pack einfach drei Counter-Komponenten in die App."

*[In App.jsx: drei mal <Counter /> rendern]*

"Seht ihr? Jeder Counter zählt unabhängig. Einer auf 5, einer auf -2, einer auf 0. Die wissen nichts voneinander. Jeder Aufruf von useState erzeugt einen eigenen, separaten State. Das ist lokaler State."

"Jetzt schaut euch die Komponente nochmal an. Der State, die drei Funktionen und das JSX – alles in einer Komponente. Das geht hier noch, aber stellt euch vor die Logik wird komplexer. Dann wird das schnell unübersichtlich."

"Dafür gibt es Custom Hooks. Das Prinzip: Wir nehmen die State-Logik – also useState und die Funktionen – und packen sie in eine eigene Funktion. Die muss mit 'use' anfangen, zum Beispiel useCounter. Die Komponente selber hat dann nur noch das JSX."

*[useCounter Hook coden / zeigen]*

"Seht ihr den Unterschied? Die Counter-Komponente ruft jetzt nur noch useCounter() auf und bekommt count, increment, decrement und reset zurück. Die ganze Logik steckt im Hook. Das hat zwei Vorteile: Erstens ist der Code übersichtlicher. Zweitens können wir den Hook wiederverwenden – wenn eine andere Komponente auch einen Counter braucht, importiert sie einfach useCounter."

---

## 3 – useState: TodoList – Live Coding (4 Min)
"Okay, das war ein einzelner Zahlenwert. Jetzt wird's spannender. Wir bauen eine TodoList, auch mit useState, aber mit komplexeren Daten."

*[TodoList coden / zeigen]*

"Hier haben wir jetzt zwei States. `todos` ist ein Array von Objekten – jedes Todo hat eine id, einen text und ein done-Flag. `input` ist der Text im Eingabefeld. Zwei States, weil es zwei verschiedene Sachen sind."

"addTodo: Wir nehmen das todos-Array und erstellen ein neues mit dem Spread-Operator – alles was vorher drin war, plus ein neues Objekt am Ende. Dann leeren wir das Input-Feld."

"toggleTodo: Wir mappen über alle Todos – wenn die id passt, erstellen wir eine Kopie mit dem Spread-Operator und flippen das done-Flag. Alle anderen bleiben unverändert. Wichtig: Wir verändern nie das Original, sondern erstellen ein neues Array – das braucht React um zu wissen dass sich was geändert hat."

"deleteTodo: Wir filtern das Todo mit der passenden id raus. Neues Array, Original bleibt unberührt."

"Jetzt machen wir das Ganze noch sauberer. Wir lagern jedes Todo-Item in eine eigene Todo-Komponente aus und übergeben die Daten als Props – das kennt ihr ja schon."

*[Todo.jsx erstellen mit props.text, props.done, props.onChange, props.onClick]*

"Gleiche Idee wie immer: Eltern-Komponente hat die Daten, Kind-Komponente bekommt sie über Props. Funktioniert hier super – eine Ebene, klar und übersichtlich."

"Aber jetzt stellt euch mal folgendes vor: Wir wollen eine Navbar die die Anzahl der offenen Todos anzeigt. Die Navbar ist eine ganz andere Komponente. Wie kommt die an die todos ran? Selbst wenn wir einen Custom Hook useTodos bauen – wenn die Navbar useTodos() aufruft, bekommt sie ihren eigenen, separaten State. Nicht denselben! Jeder Aufruf von useState erzeugt einen neuen, unabhängigen State. Wir müssten den State also nach oben schieben und als Props durch jede Ebene weiterreichen. Das nennt man Prop Drilling – und genau das wird bei größeren Apps zum Problem."

---

## 4 – Context API: Level Up (4 Min)
"Okay, Level Up. Wir haben gesehen: useState ist super für lokalen State, aber wenn mehrere Komponenten denselben State brauchen, wird's mit Props schnell unübersichtlich."

"React hat dafür eine eingebaute Lösung: die Context API. Die funktioniert in drei Schritten."

"Erstens: Wir erstellen einen Context mit createContext(). Das ist wie ein Container den wir später befüllen."

"Zweitens: Wir wrappen unsere App – oder einen Teil davon – mit einem sogenannten Provider. Der Provider bekommt einen value – das sind die Daten die wir teilen wollen. Alle Komponenten innerhalb des Providers haben Zugriff darauf."

"Drittens: In jeder Komponente, egal wie tief verschachtelt, können wir mit useContext() direkt auf den Wert zugreifen. Kein Props weiterreichen, kein Prop Drilling."

"Praktisches Beispiel: Wir bauen einen Theme-Switcher. Dark Mode und Light Mode – das braucht fast jede Komponente. Mit Context erstellen wir einen ThemeProvider, wrappen die App, und sowohl die Navbar als auch die TodoList können mit useTheme() direkt das aktuelle Theme lesen. Toggle drücken – beide ändern sofort die Farben. Kein Prop Drilling."

"Und wir können auch die Todos in einen Context packen. Statt useTodos als Custom Hook bauen wir einen TodoProvider. Jetzt teilen sich Navbar und TodoList denselben State – Todo hinzufügen, und die Navbar zählt sofort mit."

"Context ist perfekt für Dinge die sich selten ändern: Theme, Auth, Sprache. Für State der sich ständig und schnell ändert – Slider, Drag and Drop – ist Context nicht ideal, weil es Performance-Probleme geben kann."

---

## 5 – useReducer: Strukturierte Logik (3 Min)
"Jetzt haben wir noch ein Problem. Schaut euch unsere Todos nochmal an – addTodo, toggleTodo, deleteTodo. Drei separate Funktionen die alle setTodos aufrufen. Das geht hier noch, aber stellt euch vor da kommen noch editTodo, sortTodos, filterTodos dazu. Dann wird das schnell chaotisch."

"Dafür gibt es useReducer. Das Konzept: Statt viele einzelne Setter-Aufrufe zu machen, schicken wir eine Action – einen Zettel der sagt was passieren soll. Und eine Reducer-Funktion entscheidet nach festen Regeln, wie der State sich ändert."

"useReducer hat drei Teile: State – ein Objekt mit allem Zustand. Action – ein Objekt mit einem type, zum Beispiel `{ type: 'add', text: 'Einkaufen' }`. Und der Reducer – eine Funktion mit einem switch-case die für jeden Action-Type den neuen State berechnet."

*[useReducer Counter zeigen]*

"Sieht erstmal nach mehr Code aus. Aber der Vorteil: Alle Regeln stehen an einer Stelle im Reducer. Wenn ihr wissen wollt was mit dem State passieren kann, schaut ihr in den switch-case – da steht alles. Und der Reducer ist eine reine Funktion – die könnt ihr separat testen ohne React."

"useReducer lohnt sich wenn: Der State komplex ist, mehrere Werte zusammengehören, oder viele verschiedene Aktionen den State ändern können."

---

## 6 – Redux: useReducer auf App-Ebene (3 Min)
"Und jetzt kommt der letzte Schritt. useReducer ist lokal – nur in einer Komponente. Redux nimmt genau dasselbe Konzept – State, Actions, Reducer – und macht es global."

"Redux Toolkit macht das einfach: Mit createSlice definiert ihr State, Actions und Reducer in einem Block. Der Slice für unsere Todos hat addTodo und toggleTodo als Reducer-Funktionen – sieht fast aus wie die Funktionen die wir vorher hatten, nur in einer anderen Struktur."

"Den Store erstellen wir mit configureStore – das ist der globale Container. Dann wrappen wir die App mit dem Provider und übergeben den Store. Ab da haben alle Komponenten Zugriff."

"In der Komponente: useSelector liest einen Wert aus dem Store – wie useContext. dispatch schickt eine Action – genau wie bei useReducer. Zum Beispiel: dispatch(addTodo(newTodo)) oder dispatch(toggleTodo(id)). Das Konzept ist identisch zu dem was ihr schon kennt, nur der Scope ist global."

"Redux lohnt sich wenn die App richtig groß wird – viele Features, viele Teams, komplexe Datenflüsse. Für die meisten Apps reichen useState und Context völlig aus."

---

## 7 – Zusammenfassung & Fragen (1 Min)
"Zusammengefasst: State sind Daten die sich zur Laufzeit ändern. useState für lokalen State – Counter, Formulare. Custom Hooks um die Logik sauber auszulagern. Context API wenn mehrere Komponenten denselben State brauchen. useReducer wenn die State-Logik komplex wird. Und Redux als useReducer auf App-Ebene für große Projekte."

"Die Faustregel: useState → Custom Hook → Context → useReducer → Redux. Starte immer einfach und skaliere bei Bedarf."

"Danke fürs Zuhören! Gerne Fragen."

---

## Tipps
- **Live Coding:** Counter und TodoList vorher einmal durchüben, damit's flüssig läuft
- **Begriffe erklären:** Hook, State, Render – nicht voraussetzen, kurz einordnen wenn du sie benutzt. Props, map, filter kennen sie schon.
- **Tempo:** Bei Code-Teilen langsam, Zeile für Zeile erklären – die Leute sehen das zum ersten Mal
- **Interaktion:** Nach der TodoList fragen: "Was wenn die Navbar die Anzahl offener Todos zeigen soll? Wie machen wir das?" → Überleitung zu Context
- **React DevTools:** Chrome Extension "React Developer Tools" vorher installieren. Nach dem Counter oder der TodoList kurz DevTools (F12) öffnen → Tab "Components" → auf die Komponente klicken → zeigt Props, State und Hooks live. Auf einen Button klicken und zeigen wie sich `count` oder `todos` in Echtzeit ändert. Macht State super greifbar.
- **useReducer Brücke:** Betonen: "Der switch-case den ihr hier seht – genau das gleiche Konzept benutzt Redux, nur global." Das macht Redux sofort verständlich.
- **Redux:** Noch nicht zu tief reingehen, nur Ausblick – "damit beschäftigen wir uns später ausführlicher" ist völlig okay
- **Falls die Zeit knapp wird:** useReducer und Redux zusammen kürzen, die sind am wenigsten kritisch

---

## Falls jemand fragt...

**"Warum steht key auf `<Todo>` und nicht auf dem `<li>`?"**
→ "`key` ist ein spezielles React-Attribut. Es wird nur auf dem Element gebraucht, das in einer map() erzeugt wird – also auf `<Todo>`. React benutzt key intern um zu tracken welches Element welches ist. Aber key kommt nicht als prop an – wenn du in der Todo-Komponente `props.key` liest, ist das undefined. Deshalb übergeben wir die id nochmal separat als eigene Prop, falls wir sie in der Kind-Komponente brauchen."

**"Was ist der Unterschied zwischen useReducer und Redux?"**
→ "Gleiche Konzepte – State, Action, Reducer, switch-case. Der Unterschied ist der Scope. useReducer ist lokal, lebt in einer Komponente. Redux ist global, der Store ist für die ganze App – alle Komponenten die den Provider wrappen können darauf zugreifen. Redux Toolkit gibt euch zusätzlich noch DevTools, Middleware und configureStore."

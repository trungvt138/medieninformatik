# State Management in React – Spickzettel

## Was ist State?
Daten, die sich zur Laufzeit ändern: User-Eingaben, API-Daten, UI-Zustand (Modal offen/zu), Auth-Status.

### 3 Level
1. **Lokal** – eine Komponente (useState)
2. **Global** – mehrere Komponenten (Context API)
3. **App-weit** – externe Libraries (Redux)

---

## 1. useState

```jsx
const [count, setCount] = useState(0);
//      ↑         ↑                ↑
//    Wert     Setter         Startwert
```

- Lokaler State in einer Komponente
- Wert nie direkt ändern, immer über Setter
- React rendert die Komponente neu wenn sich der State ändert

---

## 2. Custom Hook

State-Logik aus der Komponente rausziehen → sauberer, wiederverwendbar.

### useCounter
```jsx
import { useState } from "react";

function useCounter(startValue = 0) {
  const [count, setCount] = useState(startValue);

  function increment() { setCount(count + 1); }
  function decrement() { setCount(count - 1); }
  function reset() { setCount(startValue); }

  return { count, increment, decrement, reset };
}

export default useCounter
```

### Komponente
```jsx
import useCounter from "./useCounter";

function Counter() {
  const { count, increment, decrement, reset } = useCounter(0);

  return (
    <div>
      <h2>Zähler: {count}</h2>
      <button onClick={increment}>+1</button>
      <button onClick={decrement}>-1</button>
      <button onClick={reset}>Reset</button>
    </div>
  );
}
```

### useTodos
```jsx
import { useState } from "react";

function useTodos() {
  const [todos, setTodos] = useState([]);
  const [input, setInput] = useState("");

  function addTodo() {
    if (input.trim() === "") return;
    setTodos([...todos, { id: Date.now(), text: input, done: false }]);
    setInput("");
  }

  function toggleTodo(id) {
    setTodos(todos.map(todo =>
      todo.id === id ? { ...todo, done: !todo.done } : todo
    ));
  }

  function deleteTodo(id) {
    setTodos(todos.filter(todo => todo.id !== id));
  }

  return { todos, input, setInput, addTodo, toggleTodo, deleteTodo };
}

export default useTodos
```

**Wichtig:** Jeder Aufruf eines Custom Hooks erzeugt eigenen, separaten State – nicht denselben!

---

## 3. Props & Komponenten

Kind-Komponente bekommt Daten über Props:

```jsx
// Todo.jsx
function Todo(props) {
  return (
    <li>
      <span
        onClick={props.onChange}
        style={{
          textDecoration: props.done ? "line-through" : "none",
          cursor: "pointer"
        }}
      >
        {props.text}
      </span>
      <button onClick={props.onClick}>Löschen</button>
    </li>
  );
}
```

```jsx
// In TodoList.jsx
{todos.map(todo => (
  <Todo
    key={todo.id}
    id={todo.id}
    text={todo.text}
    done={todo.done}
    onChange={() => toggleTodo(todo.id)}
    onClick={() => deleteTodo(todo.id)}
  />
))}
```

**key vs props:** `key` ist React-intern (Tracking in Listen). `key` kommt NICHT als `props.key` an – deshalb `id` separat übergeben wenn nötig.

---

## 4. Prop Drilling (das Problem)

= Props durch jede Ebene weiterreichen, auch wenn Zwischenkomponenten sie nicht brauchen.

```
App          ← State (theme) lebt hier
  └ Layout   ← braucht theme nicht, reicht nur weiter
    └ Sidebar ← braucht theme nicht, reicht nur weiter
      └ TodoList ← braucht theme!
```

---

## 5. Context API (die Lösung)

### Ohne Context (Prop Drilling)
```jsx
// App.jsx – alles über Props
function App() {
  const [theme, setTheme] = useState("light");

  return (
    <div>
      <Navbar theme={theme} toggle={toggle} />
      <TodoList theme={theme} />
    </div>
  );
}
```

### Mit Context – 3 Schritte

```jsx
// ThemeContext.jsx
import { createContext, useContext, useState } from "react";

// 1. Context erstellen
const ThemeContext = createContext();

// 2. Provider mit State
function ThemeProvider({ children }) {
  const [theme, setTheme] = useState("light");

  function toggle() {
    setTheme(theme === "light" ? "dark" : "light");
  }

  return (
    <ThemeContext.Provider value={{ theme, toggle }}>
      {children}
    </ThemeContext.Provider>
  );
}

// 3. Custom Hook zum Konsumieren
function useTheme() {
  return useContext(ThemeContext);
}

export { ThemeProvider, useTheme }
```

### Styles auslagern
```jsx
// styles.js
const styles = {
  light: {
    nav: { background: "#f0f0f0", color: "#333", padding: "1rem" },
    container: { background: "#ffffff", color: "#333", padding: "1rem" },
    input: { background: "#fff", color: "#333", border: "1px solid #ccc" },
  },
  dark: {
    nav: { background: "#1a1a2e", color: "#eee", padding: "1rem" },
    container: { background: "#16213e", color: "#eee", padding: "1rem" },
    input: { background: "#1a1a2e", color: "#eee", border: "1px solid #444" },
  },
};

export default styles
```

### Komponenten mit Context
```jsx
// Navbar.jsx
import { useTheme } from "./ThemeContext";
import styles from "./styles";

function Navbar() {
  const { theme, toggle } = useTheme();

  return (
    <nav style={styles[theme].nav}>
      <span>Meine App</span>
      <button onClick={toggle}>
        {theme === "dark" ? "☀️ Light" : "🌙 Dark"}
      </button>
    </nav>
  );
}
```

```jsx
// App.jsx
import { ThemeProvider } from "./ThemeContext";
import Navbar from "./Navbar";
import TodoList from "./TodoList";

function App() {
  return (
    <ThemeProvider>
      <Navbar />
      <TodoList />
    </ThemeProvider>
  );
}
```

**Kein Prop Drilling:** `useTheme()` – fertig, egal wie tief verschachtelt.

### Context – Wann passt es?
- ✅ Theme, Auth, Sprache – Dinge die sich selten ändern
- ❌ High-Frequency Updates (Slider, Drag & Drop) – zu langsam
- ❌ Viele Contexts → "Provider Hell"

---

## 6. Redux (Ausblick)

Für große Apps mit komplexem State:
- **Store** – eine zentrale Quelle für den gesamten App-State
- **Actions** – beschreiben was passieren soll: `{ type: 'add_todo' }`
- **Reducers** – Regeln die den neuen State berechnen

Heute: Redux Toolkit (weniger Boilerplate). Lohnt sich erst bei großen Projekten.

---

## Faustregel

```
useState → Custom Hook → Context → Redux
 einfach    sauberer     global    große App
```

Starte immer einfach – skaliere bei Bedarf!

---

## React DevTools

Chrome Extension "React Developer Tools" → F12 → Tab "Components"
→ Komponente anklicken → Props, State, Hooks live sehen und ändern

# State Management – Code-Beispiele

---

## 1. useState – Counter

Einfacher lokaler State: ein Wert, ein Setter.

```jsx
import { useState } from "react";

function Counter() {
  const [count, setCount] = useState(0);

  return (
    <div>
      <h2>Zähler: {count}</h2>
      <button onClick={() => setCount(count + 1)}>+1</button>
      <button onClick={() => setCount(count - 1)}>-1</button>
      <button onClick={() => setCount(0)}>Reset</button>
    </div>
  );
}
```

**Warum useState hier passt:**
- Ein einzelner Wert (Zahl)
- Nur diese Komponente braucht den State
- Einfache Updates: +1, -1, Reset

---

## 2. useReducer – Daten laden (Fetch)

Mehrere zusammenhängende States: items, loading, error.
Eine Action → alles wird konsistent aktualisiert.

```jsx
import { useReducer, useEffect } from "react";

// Initial State – alles in einem Objekt
const initialState = {
  items: [],
  loading: false,
  error: null,
};

// Reducer – die Regeln
function reducer(state, action) {
  switch (action.type) {
    case "fetch_start":
      return { ...state, loading: true, error: null };
    case "fetch_success":
      return { ...state, loading: false, items: action.data };
    case "fetch_error":
      return { ...state, loading: false, error: action.message };
    default:
      return state;
  }
}

function UserList() {
  const [state, dispatch] = useReducer(reducer, initialState);

  useEffect(() => {
    dispatch({ type: "fetch_start" });

    fetch("https://jsonplaceholder.typicode.com/users")
      .then((res) => res.json())
      .then((data) => dispatch({ type: "fetch_success", data }))
      .catch((err) => dispatch({ type: "fetch_error", message: err.message }));
  }, []);

  if (state.loading) return <p>Laden...</p>;
  if (state.error) return <p>Fehler: {state.error}</p>;

  return (
    <ul>
      {state.items.map((user) => (
        <li key={user.id}>{user.name}</li>
      ))}
    </ul>
  );
}
```

**Warum useReducer hier passt:**
- 3 States (items, loading, error) gehören zusammen
- `fetch_start` setzt loading UND error in einem Schritt
- Kein Risiko, einen setState-Aufruf zu vergessen
- Reducer ist separat testbar

---

## 3. Context API – Theme-Switcher

Globaler State: Das Theme (dark/light) wird überall gebraucht,
ändert sich aber selten → perfekt für Context.

```jsx
import { createContext, useContext, useState } from "react";

// 1. Context erstellen
const ThemeContext = createContext();

// 2. Provider-Komponente
function ThemeProvider({ children }) {
  const [theme, setTheme] = useState("light");

  const toggle = () =>
    setTheme((prev) => (prev === "light" ? "dark" : "light"));

  return (
    <ThemeContext.Provider value={{ theme, toggle }}>
      {children}
    </ThemeContext.Provider>
  );
}

// 3. Custom Hook (optional, aber sauberer)
function useTheme() {
  return useContext(ThemeContext);
}

// Irgendwo tief verschachtelt – kein Prop Drilling nötig!
function Navbar() {
  const { theme, toggle } = useTheme();

  return (
    <nav style={{
      background: theme === "dark" ? "#1a1a2e" : "#ffffff",
      color: theme === "dark" ? "#eee" : "#333",
      padding: "1rem",
    }}>
      <span>Meine App</span>
      <button onClick={toggle}>
        {theme === "dark" ? "☀️ Light" : "🌙 Dark"}
      </button>
    </nav>
  );
}

// App – Provider einmal wrappen, fertig
function App() {
  return (
    <ThemeProvider>
      <Navbar />
      {/* Alle Kinder können useTheme() nutzen */}
    </ThemeProvider>
  );
}
```

**Warum Context hier passt:**
- Theme wird in vielen Komponenten gebraucht (Navbar, Sidebar, Cards...)
- Ändert sich selten (nur bei Toggle) → kein Re-Render-Problem
- Ohne Context müsste man `theme` und `toggle` durch jede Ebene reichen

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

export default App;
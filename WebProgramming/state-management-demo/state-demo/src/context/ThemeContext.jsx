import { createContext, useContext, useState } from "react";

// Folie 11: 1. Context erstellen
const ThemeContext = createContext(null);

// 2. Provider: hält den State und stellt ihn bereit
export function ThemeProvider({ children }) {
  const [theme, setTheme] = useState("dark");
  const toggleTheme = () => setTheme(t => (t === "dark" ? "light" : "dark"));

  return (
    <ThemeContext.Provider value={{ theme, toggleTheme }}>
      {children}
    </ThemeContext.Provider>
  );
}

// 3. Überall konsumieren
export function useTheme() {
  return useContext(ThemeContext);
}

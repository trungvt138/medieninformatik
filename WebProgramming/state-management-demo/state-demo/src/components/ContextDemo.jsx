import { useTheme } from "../context/ThemeContext";

// Folie 11–12: ThemeButton liegt tief im Baum – trotzdem keine Props nötig
export default function ContextDemo() {
  return (
    <section>
      <h2>Context: Theme</h2>
      <p className="muted">ContextDemo → Toolbar → ThemeButton (keine Props!)</p>
      <Toolbar />
    </section>
  );
}

function Toolbar() {
  return (
    <div>
      <ThemeButton />
    </div>
  );
}

function ThemeButton() {
  const { theme, toggleTheme } = useTheme();
  return (
    <button type="button" onClick={toggleTheme}>
      Theme: {theme === "dark" ? "Dark 🌙" : "Light ☀️"}
    </button>
  );
}

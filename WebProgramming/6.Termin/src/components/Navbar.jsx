import { useTheme } from "../hooks/ThemeContext";
import styles from "./styles"

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

export default Navbar
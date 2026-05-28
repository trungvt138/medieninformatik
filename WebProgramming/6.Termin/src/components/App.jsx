// App.jsx
import { ThemeProvider } from "../hooks/ThemeContext";
import Navbar from "./Navbar";
import TodoList from "./TodoList";

function App() {
  return (
    <ThemeProvider>
      {/* <Navbar /> */}
      <TodoList />
    </ThemeProvider>
  );
}

export default App
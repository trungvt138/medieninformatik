// App.jsx
import { ThemeProvider } from "../hooks/ThemeContext";
import Navbar from "./Navbar";
import TodoList from "./TodoList";
import Greeting from "./etc/Greeting";

function App() {
  return (
    // <ThemeProvider>
    //   {/* <Navbar /> */}
    //   <TodoList />
    // </ThemeProvider>

    <Greeting />
  );
}

export default App
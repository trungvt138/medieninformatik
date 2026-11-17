import { ThemeProvider, useTheme } from "./context/ThemeContext";
import SimpleCounter from "./components/SimpleCounter";
import SnapshotDemo from "./components/SnapshotDemo";
import Counter from "./components/Counter";
import TodoList from "./components/TodoList";
import ContextDemo from "./components/ContextDemo";
import ReducerCounter from "./components/ReducerCounter";

// Der Provider umschließt die ganze App
export default function App() {
  return (
    <ThemeProvider>
      <Page />
    </ThemeProvider>
  );
}

function Page() {
  const { theme } = useTheme();
  return (
    <main className={theme}>
      <h1>State Management in React</h1>
      <SimpleCounter />
      <SnapshotDemo />
      <Counter />
      <TodoList />
      <ContextDemo />
      <ReducerCounter />
    </main>
  );
}

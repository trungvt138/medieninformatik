import useTodos from "../hooks/useTodos";
import { useTheme } from "../hooks/ThemeContext";
import Todo from "./Todo";
import styles from "./styles"

function TodoList() {
  const { todos, input, setInput, addTodo, toggleTodo, deleteTodo } = useTodos();
  const { theme } = useTheme();

  return (
    <div>
      <h2>Meine Todos</h2>
      <input
        value={input}
        onChange={(e) => setInput(e.target.value)}
        placeholder="Neue Aufgabe..."
        style={styles[theme].input}
      />
      <button onClick={addTodo}>Hinzufügen</button>

      <ul>
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
      </ul>
    </div>
  );
}

export default TodoList
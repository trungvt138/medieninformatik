import Todo from "./Todo"
import { useTodo } from "../hooks/useTodo"

export default function TodoCont() {
    const { visibleTodos, toggleTodo } = useTodo()
    return (
        <div id="task_cont">
            {visibleTodos.map(todo => (
                <Todo
                    key={todo.id}
                    text={todo.text}
                    done={todo.done}
                    onToggle={() => toggleTodo(todo.id)}
                />
            ))}
        </div>
    )
}
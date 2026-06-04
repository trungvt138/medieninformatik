import Todo from "./Todo"
// import { useTodo } from "../hooks/useTodo"
import { useTodoRedux } from "../hooks/TodoRedux"

export default function TodoCont() {
    // const { visibleTodos, toggleTodo } = useTodo()
    const { visibleTodos, toggleTodo } = useTodoRedux()
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
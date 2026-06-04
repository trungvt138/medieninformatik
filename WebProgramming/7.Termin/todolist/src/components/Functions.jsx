// import { useTodo } from "../hooks/useTodo"
import { useTodoRedux } from "../hooks/TodoRedux"

export default function Functions() {
    // const { todos, filter, setFilter, clearCompleted, clearAll } = useTodo()
    const { todos, filter, setFilter, clearCompleted, clearAll } = useTodoRedux()

    return (
        <div className="function">
            <h4>{todos.length} Todos</h4>
            <button id="all-btn" onClick={() => setFilter("all")}>
                All
            </button>
            <button id="active-btn" onClick={() => setFilter("active")}>
                Active
            </button>
            <button id="completed-btn" onClick={() => setFilter("completed")}>
                Completed
            </button>
            <button id="clear-completed-btn" onClick={() => clearCompleted()}>
                Clear Completed
            </button>
            <button id="clear-all-btn" onClick={() => clearAll()}>
                Clear All
            </button>
        </div>
    )
}
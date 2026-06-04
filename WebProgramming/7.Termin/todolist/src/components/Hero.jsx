import { useTodo } from "../hooks/useTodo"

export default function Hero() {
    const { text, setText, addTodo } = useTodo()

    return (
        <div className="hero">
            <div className="hero-content">
                <h1>To-Do List</h1>
                <input 
                    placeholder="What to do?" 
                    value={text}
                    onChange={(e) => setText(e.target.value)}
                    onKeyDown={(e) => {
                        if (e.key === "Enter") addTodo()
                    }}
                />
            </div>
        </div>
    )
}
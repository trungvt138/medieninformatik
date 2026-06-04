import Hero from "./Hero"
import Content from "./Content"
import { TodoProvider } from "../hooks/useTodo"

export default function App() {
    return (
        <TodoProvider>
            <Hero />
            <Content />
        </TodoProvider>
    )
}
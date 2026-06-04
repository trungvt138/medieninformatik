import Hero from "./Hero"
import Content from "./Content"
// import { TodoProvider } from "../hooks/useTodo"
import { TodoReduxProvider } from "../hooks/TodoRedux"

export default function App() {
    return (
        // <TodoProvider>
        //     <Hero />
        //     <Content />
        // </TodoProvider>
        <TodoReduxProvider>
            <Hero />
            <Content />
        </TodoReduxProvider>
    )
}
import { configureStore } from "@reduxjs/toolkit";
import { Provider, useSelector, useDispatch } from "react-redux";

// Action Types
const SET_TEXT = "SET_TEXT";
const ADD_TODO = "ADD_TODO";
const TOGGLE_TODO = "TOGGLE_TODO";
const DELETE_TODO = "DELETE_TODO";
const CLEAR_COMPLETED = "CLEAR_COMPLETED";
const CLEAR_ALL = "CLEAR_ALL";
const SET_FILTER = "SET_FILTER";

// Action Creators
function setText(text) {
    return { type: SET_TEXT, payload: text };
}

function addTodo() {
    return { type: ADD_TODO };
}

function toggleTodo(id) {
    return { type: TOGGLE_TODO, payload: id };
}

function deleteTodo(id) {
    return { type: DELETE_TODO, payload: id };
}

function clearCompleted() {
    return { type: CLEAR_COMPLETED };
}

function clearAll() {
    return { type: CLEAR_ALL };
}

function setFilter(filter) {
    return { type: SET_FILTER, payload: filter };
}

// Reducer
function todoReducer(state = { text: "", todos: [], filter: "all" }, action) {
    switch (action.type) {
        case SET_TEXT:
            return { ...state, text: action.payload };
        case ADD_TODO:
            if (state.text.trim() === "") return state;
            return { ...state, todos: [...state.todos, { id: Date.now(), text: state.text, done: false }], text: "" };
        case TOGGLE_TODO:
            return { ...state, todos: state.todos.map(todo => todo.id === action.payload ? { ...todo, done: !todo.done } : todo) };
        case DELETE_TODO:
            return { ...state, todos: state.todos.filter(todo => todo.id !== action.payload) };
        case CLEAR_COMPLETED:
            return { ...state, todos: state.todos.filter(todo => !todo.done) };
        case CLEAR_ALL:
            return { ...state, todos: [] };
        case SET_FILTER:
            return { ...state, filter: action.payload };
        default:
            return state;
    }
}

function selectVisibleTodos(state) {
    if (state.filter === "active") return state.todos.filter(t => !t.done);
    if (state.filter === "completed") return state.todos.filter(t => t.done);
    return state.todos;
}


const store = configureStore({ reducer: todoReducer });

function TodoReduxProvider({ children }) {
    return <Provider store={store}>{children}</Provider>;
}

function useTodoRedux() {
    const dispatch = useDispatch();
    const text = useSelector(state => state.text);
    const todos = useSelector(state => state.todos);
    const filter = useSelector(state => state.filter);
    const visibleTodos = useSelector(selectVisibleTodos);

    return {
        text,
        todos,
        visibleTodos,
        filter,
        setText: (t) => dispatch(setText(t)),
        addTodo: () => dispatch(addTodo()),
        toggleTodo: (id) => dispatch(toggleTodo(id)),
        deleteTodo: (id) => dispatch(deleteTodo(id)),
        clearCompleted: () => dispatch(clearCompleted()),
        clearAll: () => dispatch(clearAll()),
        setFilter: (f) => dispatch(setFilter(f)),
    };
}

export { TodoReduxProvider, useTodoRedux };

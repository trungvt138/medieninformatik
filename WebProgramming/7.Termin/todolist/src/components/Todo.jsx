export default function Todo(props) {
    return (
        <div className={`task${props.done ? ' completed' : ''}`}>
            <input type="checkbox" checked={props.done} onChange={props.onToggle} />
            <p>{props.text}</p>
        </div>
    )
}

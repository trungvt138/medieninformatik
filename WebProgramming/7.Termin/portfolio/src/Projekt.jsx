export default function Projekt(props) {
    return (
        <div>
            <h1>{props.titel}</h1>
            <img src={props.bild} alt={props.titel} />
            <p>{props.beschreibung}</p>
        </div>
    )
}
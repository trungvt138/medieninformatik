function Greeting() {
    const currentTime = new Date().getHours();
    console.log(currentTime)

    let obj = {
        "text": "Good morning",
        "color": "red"
    }

    if (currentTime > 17) {
        obj.text = "Good evening"
        obj.color = "blue"
    } else if (currentTime > 12) {
        obj.text = "Good afternoon"
        obj.color = "green"
    }

    return (
        <h1 style={{ color: obj.color }}> {obj.text} </h1>
    )
}

export default Greeting
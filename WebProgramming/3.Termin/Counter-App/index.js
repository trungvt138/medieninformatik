const counterText = document.getElementById("counter");
const incrementBtn = document.getElementById("increment");
const decrementBtn = document.getElementById("decrement");
const resetBtn = document.getElementById("reset");

let counter = 0;

function render() {
    counterText.textContent = counter;
}

function increment() {
    counter++;
    render();
}

function decrement() {
    counter--;
    render();
}

function reset() {
    counter = 0;
    render();
}

incrementBtn.addEventListener("click", increment);
decrementBtn.addEventListener("click", decrement)
resetBtn.addEventListener("click", reset);

render();
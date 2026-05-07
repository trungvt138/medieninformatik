const taskInput = document.getElementById("task_input");
const taskCont = document.getElementById("task_cont");

const allBtn = document.getElementById("all");
const activeBtn = document.getElementById("active");
const completedBtn = document.getElementById("completed");
const clearCompletedBtn = document.getElementById("clear_completed");

const countOutput = document.getElementById("count");

class Task {
    constructor(name) {
        this.name = name;
        this.isCompleted = false;
    }
}

let taskList = []
let count = 0

function createComponent(task) {
    const taskComp = document.createElement("div");
    const taskTitle = document.createElement("p");
    const checkBox = document.createElement("input");

    taskComp.setAttribute('class', 'task');
    // taskComp.setAttribute("draggable", "true");
    // taskComp.setAttribute("ondragstart", "dragStart(event)");
    // taskComp.setAttribute("ondragend", "dragEnd(event)");
    taskTitle.textContent = task.name;

    checkBox.type = "checkbox";
    checkBox.checked = task.isCompleted;

    checkBox.addEventListener("change", () => {
        if (!task.isCompleted) {
            task.isCompleted = checkBox.checked;
            count--;
        }
        else {
            task.isCompleted = false
            count++;
        }
        renderCount();
    });

    taskComp.appendChild(checkBox);
    taskComp.appendChild(taskTitle);

    return taskComp;
}

function addTask() {
    const title = taskInput.value.trim();
    if (title === "") return;

    const task = new Task(title);
    taskList.push(task);
    count++;

    taskInput.value = "";
    taskCont.appendChild(createComponent(task));
    renderCount();
}

// function for ToDoView
function render(list = taskList) {
    taskCont.innerHTML = "";
    for (const task of list) {
        let t = createComponent(task);
        taskCont.appendChild(t);
    }
}

function renderClearCompleted() {
    taskList = taskList.filter(t => t.isCompleted == false);
    count = taskList.length;
    renderCount();
    render();
}

function renderCount() {
    countOutput.textContent = `${count} tasks left`;
}
// function dragStart(event) {
//     event.dataTransfer.setData("taskdiv", event.target.id);

// }

// function dragEnd(event) {

// }

// function allowDrop(event) {
//     event.preventDefault();
// }

// function drop(event) {
//     const data = event.dataTransfer.getData("taskdiv");
//     console.log(document.getElementById(data))
//     event.target.appendChild(document.getElementById(data))
// }

// functions for ToDoController
taskInput.addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        addTask();
    }
})

allBtn.addEventListener("click", () => render());

activeBtn.addEventListener("click", () => render(taskList.filter(t => t.isCompleted == false)))

completedBtn.addEventListener("click", () => render(taskList.filter(t => t.isCompleted == true)));

clearCompletedBtn.addEventListener("click", renderClearCompleted)

renderCount();

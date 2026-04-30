const taskInput = document.getElementById("task_input");
const taskCont = document.getElementById("task_cont");

const allBtn = document.getElementById("all");
const activeBtn = document.getElementById("active");
const completedBtn = document.getElementById("completed");
const clearCompletedBtn = document.getElementById("clear_completed")

class Task {
    constructor(name) {
        this.name = name;
        this.isCompleted = false;
    }

    greet() {
        console.log("Hello I'm " + this.name)
    }

    createComponent() {
        const taskComp = document.createElement("div");
        const taskTitle = document.createElement("p");
        const checkBox = document.createElement("input");

        taskTitle.textContent = this.name;

        checkBox.type = "checkbox";
        checkBox.checked = this.isCompleted;

        checkBox.addEventListener("change", () => {
            this.isCompleted = checkBox.checked; // updates the task object
            console.log(this.name, "completed:", this.isCompleted);
        });

        taskComp.appendChild(checkBox);
        taskComp.appendChild(taskTitle);

        return taskComp;
    }
}

let taskList = []

function addTask() {
    const title = taskInput.value.trim();
    if (title === "") return;

    const task = new Task(title);
    taskList.push(task);
    taskInput.value = "";
    taskCont.appendChild(task.createComponent());
}

function render(list = taskList) {
    //create a div contains input type checkbox and the task
    taskCont.innerHTML = "";
    for (const task of list) {
        let t = task.createComponent();
        taskCont.appendChild(t);

    }
}

taskInput.addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        addTask();
    }
})

allBtn.addEventListener("click", render);

activeBtn.addEventListener("click",
    render(taskList.filter(task => task.isCompleted == false)))

completedBtn.addEventListener("click",
    render(taskList.filter(task => task.isCompleted == true)));

clearCompletedBtn.addEventListener("click", function (taskList) {
    taskList = taskList.filter(taskList.filter(task => task.isCompleted == false));
    render();
})

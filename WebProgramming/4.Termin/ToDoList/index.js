import { renderTaskList, renderCount } from "./todoView.js";
import { addTask, toggleTask, getTaskList, getCount, deleteCompleted } from "./todoStore.js";

const taskInput = document.getElementById("task_input");
const taskCont = document.getElementById("task_cont");

const allBtn = document.getElementById("all");
const activeBtn = document.getElementById("active");
const completedBtn = document.getElementById("completed");
const clearCompletedBtn = document.getElementById("clear_completed");

const countOutput = document.getElementById("count");

function render(list = getTaskList()) {
    const items = renderTaskList(taskCont, list);
    for (const { task, comp, checkBox } of items) {
        checkBox.addEventListener("change", () => {
            toggleTask(task);
            comp.classList.toggle("completed");
            renderCount(countOutput, getCount());
        });
    }
}

taskInput.addEventListener("keydown", (event) => {
    if (event.key === "Enter") {
        const title = taskInput.value.trim();
        if (!title) return;
        addTask(title);
        taskInput.value = "";
        render();
        renderCount(countOutput, getCount());
    }
});

allBtn.addEventListener("click", () => render());

activeBtn.addEventListener("click", () => render(getTaskList().filter(t => !t.isCompleted)));

completedBtn.addEventListener("click", () => render(getTaskList().filter(t => t.isCompleted)));

clearCompletedBtn.addEventListener("click", () => {
    deleteCompleted();
    render();
    renderCount(countOutput, getCount());
});

renderCount(countOutput, getCount());

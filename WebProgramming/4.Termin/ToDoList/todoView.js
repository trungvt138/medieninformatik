const taskCont = document.getElementById("task_cont")
const 
// const allBtn = document.getElementById("all");
// const activeBtn = document.getElementById("active");
// const completedBtn = document.getElementById("completed");
// const clearCompletedBtn = document.getElementById("clear_completed");

// const countOutput = document.getElementById("count");

export function createTaskComp(title) {
    const taskComp = document.createElement("div")
    const taskTitle = document.createElement("p")
    const checkBox = document.createElement("input")

    taskComp.setAttribute("class", "task")
    taskTitle.textContent = title
    checkBox.type = "checkbox"
    
    taskComp.appendChild(checkBox)
    taskComp.appendChild(taskTitle)
    return taskComp
}

export function renderTaskList(taskCont, list) {
    taskCont.innerHTML = ""
    for (const task of list) {
        let t = createComponent(task)
        taskCont.appendChild(t)
    }
}

export function renderCount(countEl, count) {
    countEl.textContent = `${count} tasks left`;
}
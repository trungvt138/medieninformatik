export function createTaskComp(task) {
    const taskComp = document.createElement("div")
    const taskTitle = document.createElement("p")
    const checkBox = document.createElement("input")

    taskComp.setAttribute("class", "task")
    if (task.isCompleted) taskComp.classList.add("completed")
    taskTitle.textContent = task.name
    checkBox.type = "checkbox"
    checkBox.checked = task.isCompleted

    taskComp.appendChild(checkBox)
    taskComp.appendChild(taskTitle)
    return { comp: taskComp, checkBox: checkBox }
}

export function renderTaskList(taskCont, list) {
    taskCont.innerHTML = ""
    return list.map(task => {
        const { comp, checkBox } = createTaskComp(task)
        taskCont.appendChild(comp)
        return { task, comp, checkBox }
    })
}

export function renderCount(countEl, count) {
    countEl.textContent = `${count} tasks left`;
}


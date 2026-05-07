class Task {
    constructor(name) {
        this.name = name;
        this.isCompleted = false;
    }
}

let taskList = []
let count = 0

export function addTask(title) {
    const task = new Task(title);
    taskList.push(task);
    count++;
    return taskList;
}

export function toggleTask(task) {
    task.isCompleted = !task.isCompleted
    count += task.isCompleted ? -1 : 1
    return taskList;
}

export function getTaskList() {
    return taskList;
}

export function getCount() {
    return count;
}

export function deleteCompleted() {
    taskList = taskList.filter(t => t.isCompleted == false);
    count = taskList.length;
    return taskList;
}


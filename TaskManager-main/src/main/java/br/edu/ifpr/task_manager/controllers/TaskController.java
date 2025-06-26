package br.edu.ifpr.task_manager.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpr.task_manager.models.Task;
import br.edu.ifpr.task_manager.models.TaskStatus;

@Controller
@RequestMapping({ "", "/tasks" })
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        Task t1 = new Task(1L, "Task1", "T1 descrição", LocalDate.now(), TaskStatus.EM_ANDAMENTO);
        Task t2 = new Task(2L, "Task2", "T2 descrição", LocalDate.now(), TaskStatus.EM_ANDAMENTO);
        Task t3 = new Task(3L, "Task3", "T3 descrição", LocalDate.now(), TaskStatus.EM_ANDAMENTO);

        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);
    }

    @GetMapping({ "", "/", "/tasks" })
    public String listTask(Model model) {

        model.addAttribute("tasksList", tasks);

        return "task-list";

    }

    @GetMapping("/create")
    public String createTask() {

        return "task-create";
    }

    @PostMapping("/create")
    public String saveTask(Task task) {

        task.setId(Long.valueOf(tasks.size() + 1));
        task.setTaskStatus(TaskStatus.EM_ANDAMENTO);

        tasks.add(task);

        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        for (Task task : tasks) {

            if (task.getId() == id) {
                model.addAttribute("task", task);
                model.addAttribute("statusOptions", TaskStatus.values());
                return "task-edit";
            }
        }
        return "redirect:/tasks";
    }

    @PostMapping("/edit")
    public String editTask(Task taskForm) {

        for (Task task : tasks) {
            if (taskForm.getId() == task.getId()) {
                tasks.set(tasks.indexOf(task), taskForm);
            }
        }

        return "redirect:/tasks";
    }

    @GetMapping("/info/{id}")
    public String infoTask(@PathVariable Long id, Model model) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                model.addAttribute("task", task);
                return "task-info";
            }
        }
        return "redirect:/tasks";

    }

    @GetMapping("/in-progress")
    public String listInProgressTasks(Model model) {
        List<Task> inProgressTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getTaskStatus() == TaskStatus.EM_ANDAMENTO) {
                inProgressTasks.add(task);
            }
        }

        model.addAttribute("tasksList", inProgressTasks);
        model.addAttribute("filterTitle", "Tarefas em Andamento");

        return "task-list"; 
    }

    @GetMapping("/remove/{id}")
    public String removeTask(@PathVariable Long id) {

        tasks.removeIf(task -> task.getId().equals(id));

        return "redirect:/tasks";
    }

}

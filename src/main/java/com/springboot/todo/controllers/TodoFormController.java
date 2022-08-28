package com.springboot.todo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.todo.models.TodoItem;
import com.springboot.todo.repositories.TodoItemRepository;

@Controller
public class TodoFormController {
    private final Logger logger = LoggerFactory.getLogger(TodoFormController.class);

    @Autowired
    private TodoItemRepository todoItemRepository;

    @GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem){
        return "add-todo-form";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){
        TodoItem item = todoItemRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Todo Item id "+id+" not found."));
        model.addAttribute("todo", item);
        return "update-todo-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") long id, Model model){
        TodoItem item = todoItemRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Todo Item id "+id+" not found."));
        todoItemRepository.delete(item);
        return "redirect:/";
    }


}

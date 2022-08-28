package com.springboot.todo.controllers;

import java.time.Instant;
import java.time.ZoneId;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.todo.models.TodoItem;
import com.springboot.todo.repositories.TodoItemRepository;


@Controller
public class TodoItemController {

    private final Logger logger = LoggerFactory.getLogger(TodoItemController.class);

    @Autowired
    private TodoItemRepository todoItemRepository;
    
    @GetMapping("/")
    public ModelAndView index(){
        logger.debug("Request to GET index.");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", todoItemRepository.findAll());
        modelAndView.addObject("today", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        return modelAndView;
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") long id, @Valid TodoItem todoItem, BindingResult result, Model model){
        if (result.hasErrors()) {
            todoItem.setId(id);
            return "update-todo-form";
        }

        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);
        return "redirect:/";
    }
    
    @PostMapping("/add-todo")
    public String addToItem(@Valid TodoItem todoItem, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "create-todo-form";
        }

        todoItem.setModifiedDate(Instant.now());
        todoItem.setCreatedDate(Instant.now());
        todoItemRepository.save(todoItem);
        return "redirect:/";
        
    }

}

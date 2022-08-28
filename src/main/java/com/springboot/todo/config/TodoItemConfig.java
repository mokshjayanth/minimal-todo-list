package com.springboot.todo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springboot.todo.models.TodoItem;
import com.springboot.todo.repositories.TodoItemRepository;

@Component
public class TodoItemConfig implements CommandLineRunner{

    @Autowired
    TodoItemRepository todoItemRepository;

    private final Logger logger = LoggerFactory.getLogger(TodoItemConfig.class);
    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    private void loadSeedData() {
        todoItemRepository.deleteAll();
        if (todoItemRepository.count() == 0) {
            TodoItem todoItem1 = new TodoItem("Go To Grocery Store.");
            TodoItem todoItem2 = new TodoItem("Clean My Room. Organize Shelves.");
            TodoItem todoItem3 = new TodoItem("Call Agnes.");

            todoItemRepository.save(todoItem1);
            todoItemRepository.save(todoItem2); 
            todoItemRepository.save(todoItem3); 
        }

        logger.info("Number of TodoItems: {}", todoItemRepository.count());
    }
    
}

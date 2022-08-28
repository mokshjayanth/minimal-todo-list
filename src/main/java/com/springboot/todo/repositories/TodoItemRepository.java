package com.springboot.todo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.todo.models.TodoItem;

public interface TodoItemRepository extends CrudRepository<TodoItem, Long>{
    
}

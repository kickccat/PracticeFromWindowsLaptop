package io.sample.service;

import io.sample.entity.TodoData;

import java.util.List;

public interface TodoDataService {
    
    TodoData createTodoData(TodoData todoData);
    
    TodoData updateTodoData(TodoData todoData);
    
    TodoData findTodoDataById(Long id);
    
    List<TodoData> getTodoDatas();
}

package io.sample.service;

import io.sample.model.TodoData;
import io.sample.model.TodoItem;

public interface TodoItemService {
    
    void addItem(TodoItem toAddItem);
    
    void removeItem(int id);
    
    TodoItem getItem(int id);
    
    void updateItem(TodoItem toUpdateItem);
    
    TodoData getData();
}

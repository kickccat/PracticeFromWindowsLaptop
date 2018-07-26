package io.sample.service;

import io.sample.model.TodoData;
import io.sample.model.TodoItem;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class TodoItemServiceImpl implements TodoItemService {
    
    // == fields ==
    @Getter
    private final TodoData data = new TodoData();
    
    @Override
    public void addItem(TodoItem toAddItem) {
        data.addItem(toAddItem);
    }
    
    @Override
    public void removeItem(int id) {
        data.removeItem(id);
    }
    
    @Override
    public TodoItem getItem(int id) {
        return data.getItem(id);
    }
    
    @Override
    public void updateItem(TodoItem toUpdateItem) {
        data.updateItem(toUpdateItem);
    }
}

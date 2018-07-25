package io.sample.controller;

import io.sample.model.TodoData;
import io.sample.util.Mappings;
import io.sample.util.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TodoItemController {
    
    // Model attributes
    @ModelAttribute
    public TodoData todoData() {
        return new TodoData();
    }
    
    // Handler methods
    
    // http://localhost:8080/todo-list/items
    @GetMapping(Mappings.ITEMS)
    public String items() {
        return ViewNames.ITEMS_LIST;
    }
}

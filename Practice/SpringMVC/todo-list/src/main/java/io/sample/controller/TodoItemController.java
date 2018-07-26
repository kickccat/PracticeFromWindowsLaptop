package io.sample.controller;

import io.sample.model.TodoData;
import io.sample.model.TodoItem;
import io.sample.service.TodoItemService;
import io.sample.util.AttributeNames;
import io.sample.util.Mappings;
import io.sample.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@Controller
public class TodoItemController {
    
    private final TodoItemService itemService;
    
    @Autowired
    public TodoItemController(TodoItemService itemService) {
        this.itemService = itemService;
    }
    
    // Model attributes
    @ModelAttribute
    public TodoData todoData() {
        return itemService.getData();
    }
    
    // Handler methods
    
    // http://localhost:8080/todo-list/items
    @GetMapping(Mappings.ITEMS)
    public String items() {
        return ViewNames.ITEMS_LIST;
    }
    
    @GetMapping(Mappings.ADD_ITEM)
    public String addEditItem(Model model) {
        TodoItem todoItem = new TodoItem("", "", LocalDate.now());
        model.addAttribute(AttributeNames.TODO_ITEM, todoItem);
        return ViewNames.ADD_ITEM;
    }
    
    @PostMapping(Mappings.ADD_ITEM)
    public String processItem(@ModelAttribute(AttributeNames.TODO_ITEM) TodoItem todoItem) {
        log.info("todoItem from form = {}", todoItem);
        itemService.addItem(todoItem);
        return "redirect:/" + Mappings.ITEMS;
    }
    
    @GetMapping(Mappings.DELETE_ITEM)
    public String deleteItem(@RequestParam int id) {
        log.info("Deleting item with id = {}", id);
        itemService.removeItem(id);
        return "redirect:/" + Mappings.ITEMS;
    }
}

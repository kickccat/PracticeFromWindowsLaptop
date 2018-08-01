package io.sample.entity;

import lombok.Data;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
public class TodoData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotEmpty(message = "Task must be set")
    @Size(min = 10, message = "Task should not be less than 10 characters")
    private String task;
    
    @NotNull(message = "Due must be set")
    @FutureOrPresent(message = "Due date must be in the present or future")
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private LocalDate dueDate;
    
    private boolean isCompleted;
    private LocalDate dateCompleted;
    private LocalDate dateCreated;
    
    @PrePersist
    private void init() {
//        log.info("init method called");
//        log.info("LocalDate = {}", LocalDate.now());
        setDateCreated(LocalDate.now());
    }
}

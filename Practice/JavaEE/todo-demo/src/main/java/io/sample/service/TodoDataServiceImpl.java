package io.sample.service;

import io.sample.entity.TodoData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TodoDataServiceImpl implements TodoDataService {
    
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    public TodoData createTodoData(TodoData todoData) {
        entityManager.persist(todoData);
        return todoData;
    }
    
    @Override
    public TodoData updateTodoData(TodoData todoData) {
        entityManager.merge(todoData);
        return todoData;
    }
    
    @Override
    public TodoData findTodoDataById(Long id) {
        return entityManager.find(TodoData.class, id);
    }
    
    @Override
    public List<TodoData> getTodoDatas() {
        return entityManager.createQuery("SELECT t from TodoData t", TodoData.class).getResultList();
    }
}

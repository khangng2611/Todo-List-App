package com.example.todoListAPI.dao;

import com.example.todoListAPI.entities.Task;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDAO {
    @Autowired
    private EntityManager entityManager;

    public List<Task> getAllTask() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Task> query = currentSession.createQuery("FROM Task", Task.class);
        List<Task> list = query.getResultList();
        return list;
    }
    public Task getTask(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Task> query = currentSession.createQuery("FROM Task WHERE id=:id", Task.class);
        query.setParameter("id", id);
        Task result = query.uniqueResult();
        return result;
    }
    public void addTask(Task newTask){
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("INSERT INTO Task(name, state, user_id) VALUES (:name, :state, :user_id)");
        query.setParameter("name", newTask.getName());
        query.setParameter("state", newTask.getState());
        query.setParameter("user_id", newTask.getUser_id());
        int affectedRows = query.executeUpdate();
    }

    public void updateTask(Task newTask) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("UPDATE Task SET name=:name, state=:state WHERE id=:id");
        query.setParameter("id", newTask.getId());
        query.setParameter("name", newTask.getName());
        query.setParameter("state", newTask.getState());
        int affectedRows = query.executeUpdate();
    }

    public void deleteTask(int deleteId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("DELETE Task WHERE id=:id");
        query.setParameter("id",deleteId);
        int affectedRows = query.executeUpdate();
    }
}

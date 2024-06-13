package com.nzangi.todolist_docker.repository;

import com.nzangi.todolist_docker.model.TodoListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends JpaRepository<TodoListModel,Long> {
}

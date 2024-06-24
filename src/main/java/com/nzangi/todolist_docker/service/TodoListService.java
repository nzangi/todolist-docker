package com.nzangi.todolist_docker.service;

import com.nzangi.todolist_docker.model.TodoListModel;
import com.nzangi.todolist_docker.repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoListService {

    private final TodoListRepository todoListRepository;

    // create a todolist
    public TodoListModel createTodoList(TodoListModel todoListModel) {
        log.info("Creating a new todolist: {}, {}",todoListModel.getTodoListTitle(),todoListModel.getTodoListDescription());
        TodoListModel todoListModel1 =  todoListRepository.save(todoListModel) ;

        return todoListModel1;

    }

    // get all the todolist
    public List<TodoListModel> getAllTodoList() {
        List<TodoListModel> todoListModels = todoListRepository.findAll();
        log.info("Getting all the todolist: <{}>",todoListModels);
        return todoListModels;
    }

    // get a specific todolist with given id
    public TodoListModel getTodoListById(Long todolistId) {
        log.info("Get a todolist with id {}",todolistId);
        return todoListRepository.findById(todolistId).orElseThrow(
                () -> new RuntimeException("Could not find todolist with id "+todolistId));
    }

    // update a specific todolist
    public TodoListModel updateTodoList(Long todolistId, TodoListModel todoListModel) {
        log.info("Updating todolist with id {}",todolistId);

        TodoListModel todoList = todoListRepository.findById(todolistId).orElseThrow(
                () -> new RuntimeException("Could not find todolist with id "+todolistId));

        todoList.setTodoListTitle(todoListModel.getTodoListTitle());
        todoList.setTodoListDescription(todoListModel.getTodoListDescription());

        return todoListRepository.save(todoList);

    }

    //delete a todolist
    public ResponseEntity<String> deleteTodoList(Long todolistId) {
        log.warn("Deleting todolist with id {}",todolistId);
        todoListRepository.deleteById(todolistId);
        return new ResponseEntity<>("TodoList was deleted successfully", HttpStatus.OK);
    }
}

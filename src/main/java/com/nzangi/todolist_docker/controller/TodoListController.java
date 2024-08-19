package com.nzangi.todolist_docker.controller;

import com.nzangi.todolist_docker.model.TodoListModel;
import com.nzangi.todolist_docker.service.TodoListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolist")
@RequiredArgsConstructor
@Slf4j
public class TodoListController {
    private final TodoListService todoListService;

    // post a todolist
    @PostMapping
    public ResponseEntity<TodoListModel> createTodoList(@RequestBody TodoListModel todoListModel){
        log.info("A post request was made");
//        TodoListModel todoListModel1 = todoListService.createTodoList(todoListModel);
//        return ResponseEntity.ok(todoListModel1);
        return ResponseEntity.ok(todoListService.createTodoList(todoListModel));

    }



    // get all the todolist
    @GetMapping
    public ResponseEntity<List<TodoListModel>> getAllTodoList(){
        log.info("A get request was made");
        return ResponseEntity.ok(todoListService.getAllTodoList());
    }

    // get on todolist
    @GetMapping("/{todolist-id}")
    public ResponseEntity<TodoListModel> getTodoListById(@PathVariable("todolist-id") Long todolistId){
        return ResponseEntity.ok(todoListService.getTodoListById(todolistId));
    }

    // update a todolist
    @PutMapping("/{todolist-id}")
    public ResponseEntity<TodoListModel> updateTodoList(@PathVariable("todolist-id") Long todolistId,@RequestBody TodoListModel todoListModel) {

        return ResponseEntity.ok(todoListService.updateTodoList(todolistId,todoListModel));

    }

    // delete a todolist
    @DeleteMapping("/{todolist-id}")
    public ResponseEntity<String> deleteTodoList(@PathVariable("todolist-id") Long todolistId){
        return todoListService.deleteTodoList(todolistId);
    }

    }

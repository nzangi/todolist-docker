package com.nzangi.todolist_docker.controller;

import com.nzangi.todolist_docker.model.TodoListModel;
import com.nzangi.todolist_docker.service.TodoListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TodoListControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TodoListService todoListService;

    @InjectMocks
    private TodoListController todoListController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(todoListController).build();
    }

    @Test
    void testCreateTodoList() throws Exception {
        TodoListModel todoListModel = new TodoListModel(1L, "Title", "Description");
        when(todoListService.createTodoList(any(TodoListModel.class))).thenReturn(todoListModel);


        mockMvc.perform(post("/todolist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"todoListTitle\": \"Title\", \"todoListDescription\": \"Description\"}"))
                .andDo(print())  // Print the response body
                .andExpect(status().isOk())
//
                .andExpect(jsonPath("$.todoListDescription").value("Description"))
                ;

    }

    @Test
    void testGetAllTodoList() throws Exception {
        TodoListModel todoListModel1 = new TodoListModel(1L, "Title 1", "Description 1");
        TodoListModel todoListModel2 = new TodoListModel(2L, "Title 2", "Description 2");
        List<TodoListModel> todoList = Arrays.asList(todoListModel1, todoListModel2);

        when(todoListService.getAllTodoList()).thenReturn(todoList);

        mockMvc.perform(get("/todolist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetTodoListById() throws Exception {
        TodoListModel todoListModel = new TodoListModel(1L, "Title", "Description");
        when(todoListService.getTodoListById(1L)).thenReturn(todoListModel);

        mockMvc.perform(get("/todolist/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.todoListTitle").value("Title"));
    }

    @Test
    void testUpdateTodoList() throws Exception {
        TodoListModel todoListModel = new TodoListModel(1L, "Updated Title", "Updated Description");
        when(todoListService.updateTodoList(eq(1L),any(TodoListModel.class))).thenReturn(todoListModel);

        mockMvc.perform(put("/todolist/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"todoListTitle\": \"Updated Title\", \"todoListDescription\": \"Updated Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.todoListTitle").value("Updated Title"))
                .andExpect(jsonPath("$.todoListDescription").value("Updated Description"))

        ;
    }

    @Test
    void testDeleteTodoList() throws Exception {
        when(todoListService.deleteTodoList(1L)).thenReturn(ResponseEntity.ok("TodoList was deleted successfully"));

        mockMvc.perform(delete("/todolist/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("TodoList was deleted successfully"));
    }
}


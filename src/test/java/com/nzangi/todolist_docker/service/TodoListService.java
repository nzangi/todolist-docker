package com.nzangi.todolist_docker.service;

import com.nzangi.todolist_docker.model.TodoListModel;
import com.nzangi.todolist_docker.repository.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TodoListServiceTest {

    @Mock
    private TodoListRepository todoListRepository;

    @InjectMocks
    private TodoListService todoListService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTodoList() {
        TodoListModel todoListModel = new TodoListModel(1L, "Title", "Description");
        when(todoListRepository.save(todoListModel)).thenReturn(todoListModel);

        TodoListModel result = todoListService.createTodoList(todoListModel);
        assertEquals(todoListModel, result);
    }

    @Test
    void testGetAllTodoList() {
        TodoListModel todoListModel1 = new TodoListModel(1L, "Title 1", "Description 1");
        TodoListModel todoListModel2 = new TodoListModel(2L, "Title 2", "Description 2");

        when(todoListRepository.findAll()).thenReturn(Arrays.asList(todoListModel1, todoListModel2));

        assertEquals(2, todoListService.getAllTodoList().size());
    }

    @Test
    void testGetTodoListById() {
        TodoListModel todoListModel = new TodoListModel(1L, "Title", "Description");
        when(todoListRepository.findById(1L)).thenReturn(Optional.of(todoListModel));

        TodoListModel result = todoListService.getTodoListById(1L);
        assertEquals(todoListModel, result);
    }

    @Test
    void testUpdateTodoList() {
        TodoListModel existingTodoList = new TodoListModel(1L, "Title", "Description");
        TodoListModel updatedTodoList = new TodoListModel(1L, "Updated Title", "Updated Description");

        when(todoListRepository.findById(1L)).thenReturn(Optional.of(existingTodoList));
        when(todoListRepository.save(existingTodoList)).thenReturn(updatedTodoList);

        TodoListModel result = todoListService.updateTodoList(1L, updatedTodoList);
        assertEquals(updatedTodoList, result);
    }

    @Test
    void testDeleteTodoList() {
        doNothing().when(todoListRepository).deleteById(1L);

        todoListService.deleteTodoList(1L);

        verify(todoListRepository, times(1)).deleteById(1L);
    }
}

package com.nzangi.todolist_docker.model;

import com.nzangi.todolist_docker.repository.TodoListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TodoListModelTest {

    @Autowired
    private TodoListRepository todoListRepository;

    @Test
    void testCreateTodoListModel() {
        TodoListModel todoListModel = new TodoListModel();
        todoListModel.setTodoListTitle("Sample Title");
        todoListModel.setTodoListDescription("Sample Description");

        TodoListModel savedTodoList = todoListRepository.save(todoListModel);

        assertThat(savedTodoList).isNotNull();
        assertThat(savedTodoList.getTodoListId()).isNotNull();
        assertThat(savedTodoList.getTodoListTitle()).isEqualTo("Sample Title");
        assertThat(savedTodoList.getTodoListDescription()).isEqualTo("Sample Description");
    }

    @Test
    void testUpdateTodoListModel() {
        TodoListModel todoListModel = new TodoListModel();
        todoListModel.setTodoListTitle("Initial Title");
        todoListModel.setTodoListDescription("Initial Description");

        TodoListModel savedTodoList = todoListRepository.save(todoListModel);

        savedTodoList.setTodoListTitle("Updated Title");
        savedTodoList.setTodoListDescription("Updated Description");

        TodoListModel updatedTodoList = todoListRepository.save(savedTodoList);

        assertThat(updatedTodoList.getTodoListTitle()).isEqualTo("Updated Title");
        assertThat(updatedTodoList.getTodoListDescription()).isEqualTo("Updated Description");
    }

    @Test
    void testDeleteTodoListModel() {
        TodoListModel todoListModel = new TodoListModel();
        todoListModel.setTodoListTitle("Title to Delete");
        todoListModel.setTodoListDescription("Description to Delete");

        TodoListModel savedTodoList = todoListRepository.save(todoListModel);

        todoListRepository.delete(savedTodoList);

        assertThat(todoListRepository.findById(savedTodoList.getTodoListId())).isEmpty();
    }

    @Test
    void testFindTodoListById() {
        TodoListModel todoListModel = new TodoListModel();
        todoListModel.setTodoListTitle("Title to Find");
        todoListModel.setTodoListDescription("Description to Find");

        TodoListModel savedTodoList = todoListRepository.save(todoListModel);

        TodoListModel foundTodoList = todoListRepository.findById(savedTodoList.getTodoListId()).orElse(null);

        assertThat(foundTodoList).isNotNull();
        assertThat(foundTodoList.getTodoListTitle()).isEqualTo("Title to Find");
        assertThat(foundTodoList.getTodoListDescription()).isEqualTo("Description to Find");
    }
}

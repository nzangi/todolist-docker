package com.nzangi.todolist_docker.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "todolist_table")
public class TodoListModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoListId;
    private String todoListTitle;
    private String todoListDescription;

}

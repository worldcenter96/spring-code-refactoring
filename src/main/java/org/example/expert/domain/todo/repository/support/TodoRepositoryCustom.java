package org.example.expert.domain.todo.repository.support;

import org.example.expert.domain.todo.entity.Todo;

import java.util.Optional;

public interface TodoRepositoryCustom {

    Optional<Todo> findByIdWithUser(long todoId);
}

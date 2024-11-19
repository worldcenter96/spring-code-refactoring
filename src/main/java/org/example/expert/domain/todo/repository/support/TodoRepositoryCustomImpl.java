package org.example.expert.domain.todo.repository.support;

import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TodoRepositoryCustomImpl extends QuerydslRepositorySupport implements TodoRepositoryCustom {

    // 도메인 클래스를 QuerydslRepositorySupport(부모 클래스)에 전달
    public TodoRepositoryCustomImpl() {
        super(Todo.class);
    }

    @Override
    public Optional<Todo> findByIdWithUser(long todoId) {
        QTodo todo = QTodo.todo;

        return Optional.ofNullable(from(todo)
                .join(todo.user).fetchJoin()
                .where(todo.id.eq(todoId))
                .fetchOne());

    }
}

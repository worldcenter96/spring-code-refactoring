package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.repository.support.TodoRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user u " +
            "WHERE t.modifiedAt >= :startDate AND t.modifiedAt <= :endDate " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByModifiedAtOrderByModifiedAtDesc(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user u " +
            "WHERE t.weather = :weather AND t.modifiedAt >= :startDate AND t.modifiedAt <= :endDate " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findByWeatherOrderByModifiedAtDesc(LocalDateTime startDate, LocalDateTime endDate, String weather, Pageable pageable);

}

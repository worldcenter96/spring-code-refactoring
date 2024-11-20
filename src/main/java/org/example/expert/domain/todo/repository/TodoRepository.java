package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.repository.support.TodoRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user u " +
            "WHERE t.modifiedAt >= :fromDate AND t.modifiedAt <= :toDate " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByModifiedAtOrderByModifiedAtDesc(LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user u " +
            "WHERE t.weather = :weather AND t.modifiedAt >= :fromDate AND t.modifiedAt <= :toDate " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findByWeatherOrderByModifiedAtDesc(LocalDateTime fromDate, LocalDateTime toDate, String weather, Pageable pageable);

}

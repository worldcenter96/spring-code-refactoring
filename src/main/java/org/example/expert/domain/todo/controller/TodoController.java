package org.example.expert.domain.todo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.dto.request.TodoSaveRequest;
import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.dto.response.TodoSaveResponse;
import org.example.expert.domain.todo.service.TodoService;
import org.example.expert.domain.user.entity.User;
import org.example.expert.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoSaveResponse> saveTodo(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody TodoSaveRequest todoSaveRequest
    ) {
        User user = userDetails.getUser();
        return ResponseEntity.ok(todoService.saveTodo(user, todoSaveRequest));
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<TodoResponse>> getTodos(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String weather,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate
    ) {

        if (fromDate == null) {
            fromDate = "00010101";
        }

        if (toDate == null) {
            toDate = "99991231";
        }

        LocalDateTime startDate = LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("yyyyMMdd")).atTime(0,0,0);
        LocalDateTime endDate = LocalDate.parse(toDate, DateTimeFormatter.ofPattern("yyyyMMdd")).atTime(23,59,59);

        return ResponseEntity.ok(todoService.getTodos(page, size, weather, startDate, endDate));
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoResponse> getTodo(@PathVariable long todoId) {
        return ResponseEntity.ok(todoService.getTodo(todoId));
    }
}

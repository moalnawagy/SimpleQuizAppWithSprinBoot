package com.moalnawagy.quizapp.controllers;

import com.moalnawagy.quizapp.models.Question;
import com.moalnawagy.quizapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Questions")
public class QuestionsController
{
    @Autowired
    QuestionService questionService;
    @GetMapping("GetAll")
    public ResponseEntity<List<Question>> getAll() {
        return questionService.getAll();
    }
    @GetMapping("GetById/{id}")
    public ResponseEntity<Question> GetById(@PathVariable Integer id) {
        return questionService.getById(id);
    }
    @GetMapping("GetByCategory/{category}")
    public ResponseEntity<List<Question>> getAll(@PathVariable String category) {
        return questionService.getByCategory(category);
    }
    @PostMapping("Add")
    public ResponseEntity<Question> add(@RequestBody Question question) {
        return questionService.add(question);
    }
    @PutMapping("Update")
    public ResponseEntity<Question> update(@RequestBody Question question) {
        return questionService.update(question);
    }

    @DeleteMapping("Delete")
    public ResponseEntity<Question> delete(@RequestBody Question question) {
        return questionService.delete(question);
    }
}

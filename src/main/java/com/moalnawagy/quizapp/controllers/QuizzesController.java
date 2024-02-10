package com.moalnawagy.quizapp.controllers;

import com.moalnawagy.quizapp.models.Question;
import com.moalnawagy.quizapp.models.Quiz;
import com.moalnawagy.quizapp.services.QuestionService;
import com.moalnawagy.quizapp.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Quizzes")
public class QuizzesController
{
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<Quiz> create(@RequestParam String category, String title, int numOfQuestions) {
        return quizService.create(title, category, numOfQuestions);
    }
    @GetMapping("getById/{id}")
    public ResponseEntity<Quiz> getById(@PathVariable Integer id) {
        return quizService.getById(id);
    }
    @PostMapping("submit")
    public ResponseEntity<Integer> submit(@RequestBody Quiz quiz) {
        return quizService.submit(quiz);
    }
}

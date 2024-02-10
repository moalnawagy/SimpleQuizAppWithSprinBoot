package com.moalnawagy.quizapp.services;

import com.moalnawagy.quizapp.models.Question;
import com.moalnawagy.quizapp.repository.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService
{
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAll() {
        try
        {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Question> getById(Integer id) {
        try
        {
            return new ResponseEntity<>(questionDao.findById(id).orElse(null), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Question>> getByCategory(String category) {
        return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
    }

    public ResponseEntity<Question> add(Question question) {
        return new ResponseEntity<>( questionDao.save(question), HttpStatus.CREATED);
    }

    public ResponseEntity<Question> update(Question question) {
        try {
            Question questionFromDb = questionDao.findById(question.getId()).orElseThrow();
            questionDao.save(question);
            return new ResponseEntity<>( questionFromDb, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>( new Question(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<Question> delete(Question question) {
        try {
            Question questionFromDb = questionDao.findById(question.getId()).orElseThrow();
            questionDao.delete(question);
            return new ResponseEntity<>( questionFromDb, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>( new Question(), HttpStatus.BAD_REQUEST);
        }
    }
}

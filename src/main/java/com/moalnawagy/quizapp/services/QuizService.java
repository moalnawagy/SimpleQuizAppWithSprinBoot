package com.moalnawagy.quizapp.services;

import com.moalnawagy.quizapp.repository.QuestionDao;
import com.moalnawagy.quizapp.repository.QuizDao;
import com.moalnawagy.quizapp.models.Question;
import com.moalnawagy.quizapp.models.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuizService
{
    @Autowired
    QuestionDao questionDao;

    @Autowired
    QuizDao quizDao;
    public ResponseEntity<Quiz> create(String title,String category, int numOfQuestions) {
        try
        {
            List<Question> questions = questionDao.getRandomQuestionsByCategory(category, numOfQuestions);
            Quiz quiz = new Quiz();
            quiz.setCategory(category);
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quiz = quizDao.save(quiz);
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(new Quiz(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<Quiz> getById(Integer id) {
        try
        {
            Optional<Quiz> quiz = quizDao.findById(id);
            if(quiz.isPresent()){
                for (Question question: quiz.get().getQuestions()){
                    question.setRightAnswer(null);
                    question.setAnswer(null);
                }
                return new ResponseEntity<>(quiz.get(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new Quiz(), HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>(new Quiz(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<Integer> submit(Quiz submittedQuiz) {
        try
        {
            Integer degree = quizDao.getQuestionsCountById(submittedQuiz.getId());

            if(degree> 0){
                if(degree != submittedQuiz.getQuestions().size())
                {
                    return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
                }
                for(Question submittedQuestion :submittedQuiz.getQuestions()){
                    Question questionFromDb = questionDao.getById(submittedQuestion.getId());
                    if(!Objects.equals(questionFromDb.getRightAnswer(), submittedQuestion.getAnswer()))
                    {
                        degree--;
                    }
                }
                return new ResponseEntity<>(degree, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(0, HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

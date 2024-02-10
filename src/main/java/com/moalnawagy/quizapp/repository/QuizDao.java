package com.moalnawagy.quizapp.repository;

import com.moalnawagy.quizapp.models.Question;
import com.moalnawagy.quizapp.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer>
{
    @Query(value = "SELECT count(questions_id) from quiz_questions where quiz_id = :quizId", nativeQuery = true)
    Integer getQuestionsCountById( int quizId);
}

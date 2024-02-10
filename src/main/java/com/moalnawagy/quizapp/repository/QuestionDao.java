package com.moalnawagy.quizapp.repository;

import com.moalnawagy.quizapp.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>
{
    List<Question> findByCategory(String category);
    @Query(value = "SELECT * FROM Question q WHERE q.category= :category ORDER BY Random() Limit :numOfQuestions", nativeQuery = true)
    List<Question> getRandomQuestionsByCategory(String category, int numOfQuestions);

}

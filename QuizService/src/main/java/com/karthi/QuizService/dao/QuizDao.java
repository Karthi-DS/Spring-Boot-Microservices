package com.karthi.QuizService.dao;


import com.karthi.QuizService.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
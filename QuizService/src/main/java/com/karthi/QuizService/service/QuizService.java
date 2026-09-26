package com.karthi.QuizService.service;


import com.karthi.QuizService.dao.QuizDao;
import com.karthi.QuizService.feign.QuizInterface;
import com.karthi.QuizService.model.QuestionWrapper;
import com.karthi.QuizService.model.Quiz;
import com.karthi.QuizService.model.QuizDto;
import com.karthi.QuizService.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(QuizDto quizDto) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(quizDto.getCategoryName(), quizDto.getNumOfQuestions()).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(quizDto.getTitle());
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Optional<Quiz> quiz = quizDao.findById(id);
        List<Integer> questionsIds = quiz.get().getQuestionIds();
        List<QuestionWrapper> questionsForUser = quizInterface.getQuestionsFromId(questionsIds).getBody();

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(List<Response> responses) {

        Integer right = quizInterface.getScores(responses).getBody();
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
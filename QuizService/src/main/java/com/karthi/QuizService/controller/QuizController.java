package com.karthi.QuizService.controller;



import com.karthi.QuizService.model.QuestionWrapper;
import com.karthi.QuizService.model.QuizDto;
import com.karthi.QuizService.model.Response;
import com.karthi.QuizService.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
// controller for quiz
@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit")
    public ResponseEntity<Integer> submitQuiz( @RequestBody List<Response> responses){
        return quizService.calculateResult(responses);
    }


}
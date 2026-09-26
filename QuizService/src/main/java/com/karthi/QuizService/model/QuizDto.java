package com.karthi.QuizService.model;

import lombok.Data;

@Data
public class QuizDto {
    Integer numOfQuestions;
    String categoryName;
    String title;
}

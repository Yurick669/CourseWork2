package com.example.CourseWork2.Service;

import com.example.CourseWork2.Model.Question;

import java.util.Collection;

public interface QuestionService {

    boolean add(String question, String answer);

    boolean remove(String question, String answer);

    Collection<Question> getAll();

    Question getRandomQuestion();

    int getSize();

    boolean add(Question question);

    boolean remove(Question question);
}

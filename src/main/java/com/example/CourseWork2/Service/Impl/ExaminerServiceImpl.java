package com.example.CourseWork2.Service.Impl;

import com.example.CourseWork2.Model.Question;
import com.example.CourseWork2.Service.ExaminerService;
import com.example.CourseWork2.Exception.InvalidExamGenerateException;
import com.example.CourseWork2.Service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Service
public class ExaminerServiceImpl implements ExaminerService {


    private static final Logger log = LoggerFactory.getLogger(ExaminerServiceImpl.class);

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }


    @Override
    public Collection<Question> getQuestion(int amount) {
        int size = questionService.getSize();
        if (amount <= 0 || size < amount) {
            log.error("The amount must be positive and must be more than" + size);
            try {
                throw new InvalidExamGenerateException(amount, size);
            } catch (InvalidExamGenerateException e) {
                e.printStackTrace();
            }
        }

        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }

        log.info("The following random questions were found", amount, size);

        return questions;
    }


}

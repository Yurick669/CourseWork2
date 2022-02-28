package com.example.CourseWork2.Service.Impl;

import com.example.CourseWork2.Model.Question;
import com.example.CourseWork2.Service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.example.CourseWork2.Service.Impl.QuestionsContains.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceImplTest {

    @Mock
    private QuestionService service;

    @InjectMocks
    private JavaQuestionServiceImpl out;



    @Test
    public void testAdd() {
        Question question = new Question(QUESTION_1, ANSWER_1);

        assertTrue(out.add(question));
        assertFalse(out.add(question));
    }

    @Test
    public void testRemove() {
        String questionTest = QUESTION_1;
        String answerTest = ANSWER_1;
        Question question = new Question(questionTest, answerTest);
        when(service.remove(question)).thenReturn(true, false);

        assertTrue(out.remove(questionTest, answerTest));
        assertFalse(out.remove(questionTest, answerTest));
    }

    @Test
    public void testAll() {
        Set<Question> questions = Set.of(
                new Question(QUESTION_1, ANSWER_1),
                new Question(QUESTION_2, ANSWER_2)
        );
        when(service.getAll()).thenReturn(questions);

        assertEquals(out.getAll().size(), questions.size());
        assertTrue(out.getAll().containsAll(questions));
    }

    @Test
    public void testRandom() {
        when(service.getAll()).thenReturn(List.of(
                new Question(QUESTION_1, ANSWER_1),
                new Question(QUESTION_2, ANSWER_2),
                new Question(QUESTION_3, ANSWER_3)
        ));

        Random randomMock = mock(Random.class);
        when(randomMock.nextInt(anyInt())).thenReturn(0, 2);
        out.setRandom(randomMock);

        assertEquals(new Question(QUESTION_1, ANSWER_1), out.getRandomQuestion());
        assertEquals(new Question(QUESTION_2, ANSWER_2), out.getRandomQuestion());


    }


}
package com.tejas.QuestionService.controller;


import com.tejas.QuestionService.model.Question;
import com.tejas.QuestionService.model.QuestionWrapper;
import com.tejas.QuestionService.model.Response;
import com.tejas.QuestionService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("getAllQuestions")
    public ResponseEntity<List<Question>> getAllQuestion(){

        return questionService.getAllQuestion();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question que){
        return questionService.addQuestion(que);
    }

    @PutMapping("updateQuestion")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question que){
        return questionService.updateQuestion(que);
    }

    @DeleteMapping("deleteQuestion/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int questionId){
        return questionService.deleteQuestion(questionId);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQues){
        return  questionService.generateQuiz(category,numQues);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForIds(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port"));

        return questionService.getQuestionsForIds(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}

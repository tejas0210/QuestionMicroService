package com.tejas.QuestionService.service;



import com.tejas.QuestionService.model.Question;
import com.tejas.QuestionService.model.QuestionWrapper;
import com.tejas.QuestionService.model.Response;
import com.tejas.QuestionService.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;


    public ResponseEntity<List<Question>> getAllQuestion() {
        return new ResponseEntity<>(questionRepo.findAll(),HttpStatus.OK);
    }



    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        return new ResponseEntity<>(questionRepo.findByCategory(category),HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> addQuestion(Question que) {
        questionRepo.save(que);
        return new ResponseEntity<>("Success",HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Question> updateQuestion(Question que) {
        Question updatedQue = new Question();
        updatedQue.setId(que.getId());
        updatedQue.setQuestionTitle(que.getQuestionTitle());
        updatedQue.setOption1(que.getOption1());
        updatedQue.setOption2(que.getOption2());
        updatedQue.setOption3(que.getOption3());
        updatedQue.setOption4(que.getOption4());
        updatedQue.setRightAnswer(que.getRightAnswer());
        updatedQue.setDifficultylevel(que.getDifficultylevel());
        updatedQue.setCategory(que.getCategory());
        questionRepo.save(updatedQue);
        return new ResponseEntity<>(updatedQue,HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteQuestion(int questionId) {
        questionRepo.deleteById(questionId);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);

    }

    public ResponseEntity<List<Integer>> generateQuiz(String category, Integer numQues) {
        List<Integer> quizQuestions = questionRepo.findRandomQuestionByCategory(category,numQues);

        return new ResponseEntity<>(quizQuestions,HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsForIds(List<Integer> questionIds) {
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id: questionIds){
            questions.add(questionRepo.findById(id).get());
        }

        for(Question question : questions){
            QuestionWrapper questionWrapper1 = new QuestionWrapper();
            questionWrapper1.setId(question.getId());
            questionWrapper1.setQuestionTitle(question.getQuestionTitle());
            questionWrapper1.setOption1(question.getOption1());
            questionWrapper1.setOption2(question.getOption2());
            questionWrapper1.setOption3(question.getOption3());
            questionWrapper1.setOption4(question.getOption4());

            questionWrappers.add(questionWrapper1);

        }

        return  new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right=0;

        for(Response res : responses){
            Question question = questionRepo.findById(res.getId()).get();
            if(res.getResponse().equals(question.getRightAnswer())){
                right++;
            }

        }

        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}

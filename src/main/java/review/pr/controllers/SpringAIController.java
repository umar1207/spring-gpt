package review.pr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import review.pr.services.AIServiceHelper;

@RestController
@RequestMapping("/api/v1")
public class SpringAIController {
    @Autowired
    AIServiceHelper aiServiceHelper;

    @GetMapping("/qa")
    public String chat(@RequestParam String question){
        return aiServiceHelper.chat(question);
    }
}

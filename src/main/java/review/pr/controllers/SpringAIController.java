package review.pr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import review.pr.services.AIServiceHelper;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class SpringAIController {
    @Autowired
    AIServiceHelper aiServiceHelper;

    @GetMapping("/qa")
    public String chat(@RequestBody String question){
        return aiServiceHelper.chat(question);
    }

    @PostMapping("/trigger/pr")
    public ResponseEntity<String> triggerPR(
            @RequestBody Map<String, Object> payload,
            @RequestHeader Map<String, String> headers) {

        String event = headers.get("x-github-event");
        if ("push".equals(event)) {
            return ResponseEntity.ok("push");
        } else if ("pull_request".equals(event)) {
            if (payload.get("action").equals("opened")){
                // fetch code at this part and refer to reviewing
                return ResponseEntity.ok("pull request opened");
            } else {
                return ResponseEntity.ok("pull request closed");
            }
        } else {
            return ResponseEntity.ok("unknown");
        }

    }

}

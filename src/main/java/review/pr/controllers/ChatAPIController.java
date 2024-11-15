package review.pr.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import review.pr.models.ChatRequest.ChatRequestBuilder;
import review.pr.models.ChatRequest;
import review.pr.models.ChatResponse;
import review.pr.models.Message;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vi")
@Slf4j
public class ChatAPIController {

        @Autowired
        private RestTemplate restTemplate;

        @Value("${openai.model}")
        private String model;

        @Value("${openai.max-completions}")
        private int maxCompletions;

        @Value("${openai.temperature}")
        private double temperature;

        @Value("${openai.api.url}")
        private String apiUrl;

        /**
         * Handles chat requests by sending a prompt to a chat API and returning the response.
         *
         * @param prompt The prompt message provided by the user.
         * @return The response generated by the chat API.
         */
        @PostMapping("/chat")
        public ChatResponse chat(@RequestParam("prompt") String prompt) {
                /* Initialize variables */
                ChatResponse chatResponse=null;
                List<Message> ChatMessages = new ArrayList<>();
                ChatRequestBuilder request = ChatRequest.builder();
                try {
                        /* Add user prompt to chat messages */
                        ChatMessages.add(new Message("user", prompt));
                        /* Build chat request */
                        request
                                .model(model)
                                .messages(ChatMessages)
                                .n(maxCompletions)
                                .temperature(temperature);

                        /* Send chat request and obtain response */
                        chatResponse = restTemplate.postForObject(apiUrl, request.build(), ChatResponse.class);
                }catch(Exception e) {
                        log.error("error : "+e.getMessage());
                }
                return chatResponse;
        }

}
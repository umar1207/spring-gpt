package review.pr.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AIServiceHelper {
    @Autowired
    private final ChatClient chatClient;

    public AIServiceHelper(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String question){
        return this.chatClient.prompt()
                .user(question)
                .call()
                .content();
    }

}
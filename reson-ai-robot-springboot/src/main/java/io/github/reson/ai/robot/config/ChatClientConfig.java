package io.github.reson.ai.robot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ResonXu
 * @Date: 2026/1/9 15:24
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Configuration
@RequiredArgsConstructor
public class ChatClientConfig {

    private final ChatMemory chatMemory;
    /**
     * 初始化 ChatClient 客户端
     * @param chatModel
     * @return
     */
    @Bean
    public ChatClient chatClient(DeepSeekChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .defaultSystem("请你扮演一名犬小哈 Java 项目实战专栏的客服人员")
                .defaultAdvisors(new MyLoggerAdvisor(),
                                new SimpleLoggerAdvisor(),
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                        ) // 添加 Spring AI 内置的日志记录功能
                .build();
    }
}

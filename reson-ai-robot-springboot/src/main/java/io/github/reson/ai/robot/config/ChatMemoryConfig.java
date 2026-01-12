package io.github.reson.ai.robot.config;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ResonXu
 * @Date: 2026/1/9 17:24
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Configuration
@RequiredArgsConstructor
public class ChatMemoryConfig {

    /**
     * 记忆存储
     */
    private final ChatMemoryRepository chatMemoryRepository;

    /**
     * 初始化一个 ChatMemory 实例，并注入到 Spring 容器中
     * @return
     */
    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .maxMessages(50) // 最大消息窗口为 50，默认值为 20
                .chatMemoryRepository(chatMemoryRepository) // 记忆存储
                .build();
    }
}


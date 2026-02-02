package io.github.reson.ai.robot.controller;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.util.Map;

/**
 * @Author: ResonXu
 * @Date: 2026/1/12 17:21
 * @Version: v1.0.0
 * @Description: TODO
 **/
@RestController
@RequestMapping("/v8/ai")
@RequiredArgsConstructor
public class StructuredOutputController {

    private final ChatClient chatClient;

    /**
     * 使用低级 API 的 BeanOutputConverter - 获取书籍信息
     * @param bookTitle
     * @return
     */
    @GetMapping("/book-info")
    public Book getBookInfo(@RequestParam(value = "name") String bookTitle) {

        // 使用 BeanOutputConverter 定义输出格式
        BeanOutputConverter<Book> converter = new BeanOutputConverter<>(Book.class);

        // 提示词模板
        String template = """
                请提供关于书籍《{bookTitle}》的详细信息：
                1. 作者姓名
                2. 出版年份
                3. 主要类型（数组）
                4. 书籍描述（不少于50字）
                
                不要包含任何解释说明，直接按指定格式输出。
                {format}
                """;

        // 创建 Prompt
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create(Map.of(
                "bookTitle", bookTitle,
                "format", converter.getFormat()
        ));

        // 调用模型并转换结果
        String result = chatClient.prompt(prompt)
                .call()
                .content();


        // 结构化转换
        return converter.convert(result);
    }


}

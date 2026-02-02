package io.github.reson.ai.robot.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;

import java.time.LocalDateTime;

/**
 * @Author: ResonXu
 * @Date: 2026/1/13 20:18
 * @Version: v1.0.0
 * @Description: TODO
 **/
@Slf4j
public class DateTimeTools {

    @Tool(description = "获取当前日期和时间")
    String getCurrentDateTime() {
        return LocalDateTime.now().toString();
    }

}
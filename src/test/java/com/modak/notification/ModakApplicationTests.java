package com.modak.notification;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;

@SpringBootTest
class ModakApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        LocalDateTime plus = LocalDateTime.now().plus(Duration.ofDays(1));

        LocalDateTime date1 = LocalDateTime.now().plusMinutes(1);
        LocalDateTime date2 = LocalDateTime.now().plusMinutes(2);

    }

}

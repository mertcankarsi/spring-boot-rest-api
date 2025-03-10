package com.example.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationTest {

    @Test
    void contextLoads() {
        // Spring context'in başarıyla yüklendiğini test eder
    }

    @Test
    void applicationStarts() {
        // Uygulamanın başlatılabildiğini test eder
        Application.main(new String[]{});
    }
} 
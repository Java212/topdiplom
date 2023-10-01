package ru.top.java212;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexViewTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void viewSubjects() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/index", String.class);
        assertThat(response.getBody()).contains("<h2>Преподаватели:</h2>");
    }
}

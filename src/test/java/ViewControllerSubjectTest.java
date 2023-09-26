import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.top.java212.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest (classes = Application.class)
@AutoConfigureMockMvc
public class ViewControllerSubjectTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void test_GetMapping () throws Exception{
        String url = "/";

        this.mockMvc.perform(get(url))
                .andExpect(status().isOk());

    }
}

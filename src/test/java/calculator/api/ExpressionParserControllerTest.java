package calculator.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpressionParserController.class)
class ExpressionParserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void expressionParserTest() throws Exception {
        mockMvc.perform(post("/api/parser/evaluate")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("1*1"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }
}

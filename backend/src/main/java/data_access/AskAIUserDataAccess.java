package data_access;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import use_case.ask_ai.AskAIUserDataAccessInterface;

@Repository
public class AskAIUserDataAccess implements AskAIUserDataAccessInterface {

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/completions";
    private static final String API_KEY = System.getenv("OPENAI_API_KEY");
    @Override
    public String getResponseFromAI(String inputMessage) {
        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Content-Type", "application/json");

        // Create the request body
        String requestBody = """
            {
                "model": "gpt-4o-mini",
                "messages": [
                    {
                        "role": "system",
                        "content": "You are a helpful financial advisor."
                    },
                    {
                        "role": "user",
                        "content": "%s"
                    }
                ],
                "max_tokens": 150,
                "temperature": 0.7
            }
        """.formatted(inputMessage);

        // Create HTTP entity
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            // Send the request
            ResponseEntity<String> response = restTemplate.exchange(
                    OPENAI_API_URL,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            // Parse the response JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            return root.get("choices").get(0).get("message").get("content").asText();
        } catch (Exception e) {
            throw new RuntimeException("Error during OpenAI API request", e);
        }
    }
}
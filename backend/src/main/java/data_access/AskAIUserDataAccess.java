package data_access;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import use_case.ask_ai.AskAIUserDataAccessInterface;

import java.util.List;

@Repository
public class AskAIUserDataAccess implements AskAIUserDataAccessInterface {

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    @Override
    public String getResponseFromAI(String inputMessage) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Content-Type", "application/json");


        // Build the JSON request body using Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode systemMessage = objectMapper.createObjectNode();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are a helpful financial advisor.");

        ObjectNode userMessage = objectMapper.createObjectNode();
        userMessage.put("role", "user");
        userMessage.put("content", inputMessage);

        ObjectNode requestBody = objectMapper.createObjectNode();
        requestBody.put("model", "gpt-4o-mini");
        requestBody.put("max_tokens", 350);
        requestBody.put("temperature", 0.7);
        requestBody.set("messages", objectMapper.valueToTree(List.of(systemMessage, userMessage)));

        // Convert the request body to a JSON string
        String jsonBody;
        try {
            jsonBody = objectMapper.writeValueAsString(requestBody);
        } catch (Exception e) {
            throw new RuntimeException("Error constructing JSON request body", e);
        }

        // Log the request body
        System.out.println("Request Body: " + jsonBody);

        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    OPENAI_API_URL,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            // Log successful response
            System.out.println("Response Status: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());

            // Parse and return the response
            JsonNode root = objectMapper.readTree(response.getBody());
            return root.get("choices").get(0).get("message").get("content").asText();

        } catch (HttpClientErrorException e) {
            System.err.println("HTTP Client Error: " + e.getResponseBodyAsString());
            throw new RuntimeException("API request error: " + e.getMessage(), e);

        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
            throw new RuntimeException("Unexpected error during API request", e);
        }
    }


}
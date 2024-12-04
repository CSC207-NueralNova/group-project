package use_case.AskAI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.ask_ai.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AskAIInteractorTest {

    private AskAIUserDataAccessInterface userDataAccessMock;
    private AskAIOutputBoundary aiOutputBoundaryMock;
    private AskAIInteractor askAIInteractor;

    @BeforeEach
    void setUp() {
        userDataAccessMock = mock(AskAIUserDataAccessInterface.class);
        aiOutputBoundaryMock = mock(AskAIOutputBoundary.class);

        askAIInteractor = new AskAIInteractor(userDataAccessMock, aiOutputBoundaryMock);
    }

    @Test
    void testExecuteNormalFlow() {
        AskAIInputData inputData = new AskAIInputData("Jamie Han", "Hello, AI!");
        String expectedResponse = "Hi, Jamie! How can I assist you?";
        when(userDataAccessMock.getResponseFromAI("Hello, AI!")).thenReturn(expectedResponse);

        AskAIOutputData actualOutput = askAIInteractor.execute(inputData);

        assertEquals("AI Bot", actualOutput.getSender(), "Sender should be 'AI Bot'");
        assertEquals(expectedResponse, actualOutput.getResponse(), "Response should match the mocked response");

        verify(userDataAccessMock, times(1)).getResponseFromAI("Hello, AI!");
        verify(aiOutputBoundaryMock, times(1)).presentAIResponse(actualOutput);
    }

    @Test
    void testExecuteWithExceptionHandling() {
        AskAIInputData inputData = new AskAIInputData("Jamie Han", "Trigger error");
        String fallbackResponse = "Sorry, there was an error processing your request. Please try again.";
        when(userDataAccessMock.getResponseFromAI("Trigger error"))
                .thenThrow(new RuntimeException("Simulated exception"));

        AskAIOutputData actualOutput = askAIInteractor.execute(inputData);

        assertEquals("AI Bot", actualOutput.getSender(), "Sender should be 'AI Bot'");
        assertEquals(fallbackResponse, actualOutput.getResponse(), "Response should match the fallback response");

        verify(userDataAccessMock, times(1)).getResponseFromAI("Trigger error");
        verify(aiOutputBoundaryMock, times(1)).presentAIResponse(actualOutput);
    }
}

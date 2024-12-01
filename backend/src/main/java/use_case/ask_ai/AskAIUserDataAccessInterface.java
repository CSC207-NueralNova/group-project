package use_case.ask_ai;

import entity.user.User;
import org.springframework.stereotype.Repository;

public interface AskAIUserDataAccessInterface {

    String getResponseFromAI(String inputMessage);
}

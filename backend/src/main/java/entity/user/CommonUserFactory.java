package entity.user;
import org.springframework.stereotype.Component;

/**
 * Factory for creating CommonUser objects.
 */

@Component
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String password) {
        return new CommonUser(name, password);
    }
}

package johan;

import johan.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserRepository {

  private ConcurrentHashMap<String, User> users;

  public UserRepository() {
    this.users = new ConcurrentHashMap<>();
  }

  public void put(User user) {
    users.put(user.getUsername(), user);
  }

  public User getUser(String username) {
    return users.get(username);
  }

  public User findByUsername(String username) {
    return users.get(username);
  }
}

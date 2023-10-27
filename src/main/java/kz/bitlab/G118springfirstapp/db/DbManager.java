package kz.bitlab.G118springfirstapp.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kz.bitlab.G118springfirstapp.model.User;
import lombok.Getter;

public class DbManager {

  @Getter
  private static List<User> users = new ArrayList<>();


  private static Long id = 4L;

  static {
    users.add(new User(1L, "marat@gmail.com", "qwe", "Marat"));
    users.add(new User(2L, "saniya@gmail.com", "qwe", "Saniya"));
    users.add(new User(3L, "vlad@gmail.com", "qwe", "Vlad"));
  }

  public static void addUser(User user) {
    user.setId(id);
    users.add(user);
    id++;
  }

  public static User getUserById(Long id) {
    return users.stream().filter(user -> Objects.equals(user.getId(), id))
        .findFirst()
        .orElse(null);
  }
}

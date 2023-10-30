package kz.bitlab.G118springfirstapp.db;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import kz.bitlab.G118springfirstapp.model.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DbManager {

  @Getter
  private static List<User> users = new ArrayList<>();


  private static Long id = 4L;

  static {
    users.add(new User(1L, "marat@gmail.com", "qwe", "Марат"));
    users.add(new User(2L, "saniya@gmail.com", "qwe", "Сания"));
    users.add(new User(3L, "vlad@gmail.com", "qwe", "Влад"));
  }

  public static void addUser(User user) {
    log.info("add user method is started");
    user.setId(id);
    users.add(user);
    id++;
    log.info("User added successfully");
    log.info("user data: {}", user);
  }

  public static User getUserById(Long id) {
    return users.stream().filter(user -> Objects.equals(user.getId(), id))
        .findFirst()
        .orElse(null);
  }

  public static void editUser(Long id, String email, String fullName) {
    User user = getUserById(id);
    if (user == null) {
      return;
    }
    user.setEmail(email);
    user.setFullName(fullName);
  }

  public static void deleteUserById(Long userId) {
    users.removeIf(user -> user.getId().equals(userId));
  }

  public static List<User> findUsers(String search) {
    return users.stream()
        .filter(user -> user.getEmail().toLowerCase().contains(search.toLowerCase())
            || user.getFullName().toLowerCase().contains(search.toLowerCase()))
        .sorted(Comparator.comparing(User::getFullName)
            .thenComparing(User::getId).reversed())
        .collect(Collectors.toList());
  }

  public static String findUsersAlt(String search) {
    if (!search.isEmpty()) {

    }
    return users.stream()
        .filter(user -> user.getEmail().toLowerCase().contains(search.toLowerCase())
            || user.getFullName().toLowerCase().contains(search.toLowerCase()))
        .sorted(Comparator.comparing(User::getFullName)
            .thenComparing(User::getId).reversed())
        .map(User::getFullName)
        .collect(Collectors.joining(" – "));
  }
}

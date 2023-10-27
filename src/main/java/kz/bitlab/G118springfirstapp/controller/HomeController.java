package kz.bitlab.G118springfirstapp.controller;

import java.util.List;
import kz.bitlab.G118springfirstapp.db.DbManager;
import kz.bitlab.G118springfirstapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

  @GetMapping("/") //@WebServlet("/") + doGet()
  public String homePage(Model model) {
    List<User> users = DbManager.getUsers();
    model.addAttribute("users", users); // req.setAttribute("users", users)
    return "home";
  }

  /**
   * String email = req.getParameter("email");
   * String fullName = req.getParameter("fullName");
   * User user = new User();
   * user.setEmail(email);
   * user.setFullName(fullName);.
   *
   * @param user новый пользователь
   * @return главная страница.
   */
  @PostMapping("/add-user")
  public String addUser(User user) {
    DbManager.addUser(user);
    return "redirect:/"; //resp.sendRedirect("/")
  }

  @GetMapping("/user-details")
  public String getUser(@RequestParam(name = "userId") Long id, Model model) {
    User user = DbManager.getUserById(id);
    model.addAttribute("user", user);
    return "userDetails";
  }
}

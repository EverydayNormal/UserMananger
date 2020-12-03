package my.java.test.accountmanager.controller;

import my.java.test.accountmanager.dao.UserDaoImpl;
import my.java.test.accountmanager.model.UserAccount;
import my.java.test.accountmanager.security.SecurityBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserAccountController {

    @Autowired
    public UserDaoImpl userDao;

    @Autowired
    public SecurityBean securityBean;

    @GetMapping("/")
    public String startPage() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("userAccount", new UserAccount());
        model.addAttribute("securityBean", securityBean);
        return "login";
    }

    @PostMapping("/login")
    public String registration(@ModelAttribute("userAccount") UserAccount userAccount, Model model) {
        securityBean.search(userAccount);
        model.addAttribute("securityBean", securityBean);
        if (!securityBean.isUserValid()) {
            model.addAttribute("userAccount", userAccount);
            return "login";
        }
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String showAllUsersAccounts(Model model) {
        if (!securityBean.isUserValid()) {
            return "redirect:/logOut";
        }
        model.addAttribute("securityBean", securityBean);
        model.addAttribute("userAccounts", userDao.findAll());
        return "showUserAccounts";
    }

    @GetMapping("/user/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        if (!securityBean.isUserValid()) {
            return "redirect:/logOut";
        }
        UserAccount userAccount = userDao.getById(id);
        model.addAttribute("securityBean", securityBean);
        model.addAttribute("userAccount", userAccount);
        model.addAttribute("status", userAccount.isStatus());
        return "showUserAccount";
    }

    @GetMapping("/user/new")
    public String createAccount(@ModelAttribute("userAccount") UserAccount userAccount) {
        if (!securityBean.isUserAdmin()) {
            return "redirect:/logOut";
        }
        return "createNewUserAccount";
    }

    @PostMapping("/user")
    public String createNewUserAccount(@ModelAttribute("userAccount") @Valid UserAccount userAccount,
                                       BindingResult result) {
        if (!securityBean.isUserAdmin()) {
            return "redirect:/logOut";
        }
        if (result.hasErrors()) {
            return "createNewUserAccount";
        }
        userDao.save(userAccount);
        return "redirect:/user";
    }

    @GetMapping("/user/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        if (!securityBean.isUserAdmin()) {
            return "redirect:/logOut";
        }
        model.addAttribute("userAccount", userDao.getById(id));
        return "editPage";
    }

    @PostMapping("/user/{id}")
    public String edit(@ModelAttribute("userAccount") @Valid UserAccount userAccount, BindingResult result) {
        if (!securityBean.isUserAdmin()) {
            return "redirect:/logOut";
        }
        if (result.hasErrors()) {
            return "editPage";
        }
        userDao.update(userAccount);
        return "redirect:/user";
    }

    @PostMapping("/user/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        if (!securityBean.isUserAdmin()) {
            return "redirect:/logOut";
        }
        userDao.delete(id);
        return "redirect:/user";
    }

    @PostMapping("/user/{id}/statusChange")
    public String statusChange(@PathVariable("id") int id, @ModelAttribute("status") boolean status) {
        if (!securityBean.isUserAdmin()) {
            return "redirect:/logOut";
        }
        UserAccount userAccount = userDao.getById(id);
        userAccount.setStatus(status);
        userDao.update(userAccount);
        return "redirect:/user/" + userAccount.getId();
    }

    @GetMapping("/logOut")
    public String logOut() {
        securityBean.logOut();
        return "redirect:/login";
    }
}

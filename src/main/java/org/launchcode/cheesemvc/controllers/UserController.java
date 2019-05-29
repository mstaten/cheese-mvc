package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.launchcode.cheesemvc.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @RequestMapping(value = "")
    public String userIndex(Model model, @ModelAttribute @Valid User user,
                            Errors errors) {
        model.addAttribute("title", "Welcome!");
        model.addAttribute("users", UserData.getAll());
        return "user/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddUserForm(Model model) {
        model.addAttribute("title", "Add User");
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddUserForm(@ModelAttribute @Valid User user,
                                     Errors errors, Model model,
                                     RedirectAttributes redirectAttrs) {

        // annotation-configured validation
        if (errors.hasErrors()) {
            if (errors.hasFieldErrors("email")) {
                user.setEmail("");
            }
            if (errors.hasFieldErrors("username")) {
                user.setUsername("");
            }

            //model.addAttribute(user);
            model.addAttribute("title", "Add User");
            return "user/add";
        }

        UserData.add(user);
        redirectAttrs.addAttribute("userId", user.getUserId());
        return "redirect:";
    }
}

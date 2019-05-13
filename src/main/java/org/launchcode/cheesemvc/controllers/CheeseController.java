package org.launchcode.cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/** 05/09/19
 * */
@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    static HashMap<String, String> cheeses = new HashMap<>();

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    public String validateAddForm(@RequestParam String cheeseName) {
        if (cheeseName.equals("")) {
            return "Must enter cheese name";
        }

        // check that each char is alphabetic
        for (Character c : cheeseName.toCharArray()) {
            if (!Character.isAlphabetic(c) && !Character.isWhitespace(c)) {
                return "Only alphabetic characters and spaces allowed";
            }
        }
        return "";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(Model model, @RequestParam String cheeseName, @RequestParam String cheeseDesc) {

        String nameError = validateAddForm(cheeseName);

        if (!nameError.equals("")) {
            model.addAttribute("cheeses", cheeses);
            model.addAttribute("title", "Add Cheeses");
            model.addAttribute("cheeseDesc", cheeseDesc);
            model.addAttribute("nameError", nameError);
            return "cheese/add";
        }
        cheeses.put(cheeseName, cheeseDesc);

        // Redirect to /cheese
        return "redirect:";
    }

    // Request path: cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }


    // Request path: cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam ArrayList<String> cheeseList) {

        for (String aCheese : cheeseList ) {
            cheeses.remove(aCheese);
        }

        // Redirect to cheese/
        return "redirect:";
    }

}

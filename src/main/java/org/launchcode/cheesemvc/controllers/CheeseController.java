package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/** 05/09/19
 * */
@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    static ArrayList<Cheese> myCheeses = new ArrayList<>();

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        // display current cheeses
        model.addAttribute("myCheeses", myCheeses);
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

        // if there's an error
        if (!nameError.equals("")) {
            model.addAttribute("myCheeses", myCheeses);
            model.addAttribute("title", "Add Cheeses");
            model.addAttribute("cheeseDesc", cheeseDesc);
            model.addAttribute("nameError", nameError);
            return "cheese/add";
        }

        Cheese newCheese = new Cheese(cheeseName, cheeseDesc);
        myCheeses.add(newCheese);

        // Redirect to /cheese
        return "redirect:";
    }

    // Request path: cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("myCheeses", myCheeses);
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }


    // Request path: cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam ArrayList<String> cheeseList) {

        // save size of myCheeses to use as iterator
        // (myCheeses will change size as we remove appropriate cheeses)
        int myCheesesSize = myCheeses.size();

        // loop enough times to check each cheese in myCheeses
        for (int i = myCheesesSize - 1; i >= 0 ; i--) {

            // get cheese at index i
            Cheese someCheese = myCheeses.get(i);

            // if cheese name is in cheeseList (aka needs to be removed)
            if (cheeseList.contains(someCheese.getName())) {

                // remove cheese
                myCheeses.remove(someCheese);
            }
        }

        // Redirect to cheese/
        return "redirect:";
    }

}

package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/** 05/09/19, 05/25/19
 **/
@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("myCheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute Cheese newCheese) {
        CheeseData.add(newCheese);
        return "redirect:";
    }

    // Request path: cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("myCheeses", CheeseData.getAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }


    // Request path: cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }

        // Redirect to cheese/
        return "redirect:";
    }

}

package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.launchcode.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        Cheese editCheese = CheeseData.getById(cheeseId);
        model.addAttribute(editCheese);
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/edit";
    }

    @RequestMapping(value = {"edit/{cheeseId}"}, method = RequestMethod.POST)
    public String processEditForm(@ModelAttribute @Valid Cheese cheeseToEdit, Errors errors,
                                  Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("cheese",cheeseToEdit);
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/edit";
        }

        Cheese editCheese2 = CheeseData.getById(cheeseToEdit.getCheeseId());
        editCheese2.setName(cheeseToEdit.getName());
        editCheese2.setDescription(cheeseToEdit.getDescription());
        editCheese2.setType(cheeseToEdit.getType());
        editCheese2.setRating(cheeseToEdit.getRating());

        return "redirect:/cheese";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/add";
        }

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

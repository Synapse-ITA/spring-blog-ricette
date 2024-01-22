package org.learning.blogricette.controller;

import jakarta.validation.Valid;
import org.learning.blogricette.model.Ricetta;
import org.learning.blogricette.repository.RicettaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ricetta")
public class RicettaController {
    @Autowired
    private RicettaRepository ricettaRepository;
    @GetMapping
    public String index(Model model) {
        List<Ricetta> ricettaList = ricettaRepository.findAll();
        model.addAttribute("ricettaList", ricettaList);
        return "ricetta/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Ricetta ricetta = new Ricetta();
        model.addAttribute("ricetta", ricetta);
        return "ricetta/create";
    }
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ricetta") Ricetta formRicetta, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ricetta/create";
        } else {
            Ricetta savedRicetta = ricettaRepository.save(formRicetta);
            return "redirect:/ricetta";
        }
    }


}

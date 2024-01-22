package org.learning.blogricette.controller;

import org.learning.blogricette.model.Categoria;
import org.learning.blogricette.model.Ricetta;
import org.learning.blogricette.repository.CategoriaRepository;
import org.learning.blogricette.repository.RicettaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private RicettaRepository ricettaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public String index(Model model) {
        List<Ricetta> ricettaList = ricettaRepository.findAll();
        List<Categoria> categoriaList = categoriaRepository.findAll();
        model.addAttribute("ricettaList", ricettaList);
        model.addAttribute("categoriaList", categoriaList);
        return "blog/list";
    }

    @GetMapping("/ricetta/{id}")
    public String detailsRicetta(@PathVariable Integer id, Model model) {
        Optional<Ricetta> ricetta = ricettaRepository.findById(id);
        if (ricetta.isPresent()) {
            model.addAttribute("ricetta", ricetta.get());
            return "ricetta/details";
        } else {
            return "redirect:/blog";
        }
    }
}

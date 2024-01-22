package org.learning.blogricette.controller;

import jakarta.validation.Valid;
import org.learning.blogricette.model.Categoria;
import org.learning.blogricette.model.Ricetta;
import org.learning.blogricette.repository.CategoriaRepository;
import org.learning.blogricette.repository.RicettaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ricetta")
public class RicettaController {
    @Autowired
    private RicettaRepository ricettaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
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
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Ricetta> result = ricettaRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("ricetta", result.get());
            return "ricetta/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ricetta with id " + id + " not found");
        }
    }
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("ricetta") Ricetta formRicetta, BindingResult bindingResult) {
        Optional<Ricetta> result = ricettaRepository.findById(id);
        if (result.isPresent()) {
            Ricetta ricettaToEdit = result.get();
            if (bindingResult.hasErrors()) {
                return "ricetta/edit";
            }
            Ricetta savedRicetta = ricettaRepository.save(formRicetta);
            return "redirect:/ricetta";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricetta with id " + id + " not found");
        }
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Ricetta> result = ricettaRepository.findById(id);
        if (result.isPresent()) {
            ricettaRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("redirectMessage",
                    "Ricetta " + result.get().getName() + " deleted!");
            return "redirect:/ricetta";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricetta with id " + id + " not found");
        }
    }

    @GetMapping("/assignCategoria/{id}")
    public String showAssignCategoriaForm(@PathVariable Integer id, Model model) {
        Optional<Ricetta> result = ricettaRepository.findById(id);
        if (result.isPresent()) {
            Ricetta ricetta = result.get();
            List<Categoria> categorie = categoriaRepository.findAll();
            model.addAttribute("ricetta", ricetta);
            model.addAttribute("categorie", categorie);
            return "ricetta/assignCategoria";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricetta with id " + id + " not found");
        }
    }
    @PostMapping("/assignCategoria/{id}")
    public String handleAssignCategoriaForm(@PathVariable Integer id, @RequestParam Integer categoriaId) {
        Optional<Ricetta> result = ricettaRepository.findById(id);
        if (result.isPresent()) {
            Ricetta ricetta = result.get();
            Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
            if (categoria.isPresent()) {
                ricetta.setCategoria(categoria.get());
                ricettaRepository.save(ricetta);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria with id " + categoriaId + " not found");
            }
            return "redirect:/ricetta";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricetta with id " + id + " not found");
        }
    }
}

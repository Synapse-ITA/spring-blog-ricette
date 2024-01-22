package org.learning.blogricette.controller;

import jakarta.validation.Valid;
import org.learning.blogricette.model.Categoria;
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
@RequestMapping("/categorie")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoryRepository;
    @Autowired
    private RicettaRepository ricettaRepository;

    @GetMapping("/list")
    public String list(Model model) {
        List<Categoria> categoryList = categoryRepository.findAll();
        for (Categoria categoria : categoryList) {
            categoria.setRicette(ricettaRepository.findByCategoria(categoria));
        }
        model.addAttribute("categoryList", categoryList);
        return "categoria/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Categoria categoria = new Categoria();
        model.addAttribute("categoria", categoria);
        return "categoria/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("categoria") Categoria categoriaForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "categorie/list";
        }
        Categoria savedCategoria = categoryRepository.save(categoriaForm);
        return "redirect:/categorie/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Categoria> result = categoryRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("categoria", result.get());
            return "categoria/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category with id " + id + " not found");
        }

    }
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("categoria") Categoria categoriaForm, BindingResult bindingResult, Model model) {
        Optional<Categoria> result = categoryRepository.findById(id);
        if (result.isPresent()) {

            if (bindingResult.hasErrors()) {
                return "categoria/edit";
            }

            Categoria updatedCategory = categoryRepository.save(categoriaForm);
            return "redirect:/categorie/list";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category with id " + id + " not found");

        }
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Categoria> result = categoryRepository.findById(id);
        if (result.isPresent()) {
            categoryRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("redirectMessage", "Categoria " + result.get().getName() + " deleted!");
            return "redirect:/categorie/list";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricetta with id " + id + " not found");

        }
    }


    }
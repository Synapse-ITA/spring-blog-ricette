package org.learning.blogricette.repository;

import org.learning.blogricette.model.Categoria;
import org.learning.blogricette.model.Ricetta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RicettaRepository extends JpaRepository<Ricetta, Integer> {
    List<Ricetta> findByCategoria(Categoria categoria);
}

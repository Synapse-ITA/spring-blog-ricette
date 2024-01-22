package org.learning.blogricette.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ricette")
public class Ricetta {
    // ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String ingredients;
    private String image;
    private int preparationTime;
    private int numOfPortions;
    private String description;

    // COSTRUTTORI
    public Ricetta() {
    }

    // GETTER AND SETTER

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getNumOfPortions() {
        return numOfPortions;
    }

    public void setNumOfPortions(int numOfPortions) {
        this.numOfPortions = numOfPortions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

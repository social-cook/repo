package com.socialcooking.domain;

import javax.persistence.*;

@Entity
@Table(name = "ingredient")
public class Ingredient extends DomainObject {

    @Column(name = "name_ingredient")
    private String name;

    @Column(name = "comment")
    private String comment;

    @Column(name = "quantity")
    private String quantity;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recipe_fk")
    private Recipe recipe;

    public Ingredient() {

    }

    public Ingredient(Long id, String name, String comment, String quantity) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.quantity = quantity;
    }

    public Ingredient(String name, String comment, String quantity) {
        this.name = name;
        this.comment = comment;
        this.quantity = quantity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}

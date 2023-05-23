package com.example.foodvault.Model;

public class Ingredient {
    private String ingredientName, quantity, unit, preparation;
    public Ingredient(){

        ingredientName ="";
        quantity = "";
        unit = "";
        preparation = "";
    }
    public Ingredient(String quantity, String unit, String ingredientName, String preparation){

        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
        this.preparation = preparation;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String toString() {
        return String.format("%s-%s-%s-%s", quantity, unit, ingredientName, preparation);
    }

    public boolean equals(Ingredient i) {
        if (this.getIngredientName().equalsIgnoreCase(i.getIngredientName())) {
            return true;
        }
        else {
            return false;
        }
    }
}

package org.acme.quickstart;

public class FruityVice {

    public static FruityVice EMPTY_FRUIT = new FruityVice();

    private String name;
    private Nutritions nutritions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Nutritions getNutritions() {
        return nutritions;
    }

    public void setNutritions(Nutritions nutritions) {
        this.nutritions = nutritions;
    }

    public static class Nutritions {
        private double fat;
        private int calories;

        public double getFat() {
            return fat;
        }

        public void setFat(double fat) {
            this.fat = fat;
        }

        public int getCalories() {
            return calories;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }

    }
}
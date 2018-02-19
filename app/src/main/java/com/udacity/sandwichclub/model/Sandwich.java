package com.udacity.sandwichclub.model;

import java.util.List;

public class Sandwich {

    private String mainName;
    private List<String> alsoKnownAs = null;
    private String placeOfOrigin;
    private String description;
    private String image;
    private List<String> ingredients = null;

    /**
     * No args constructor for use in serialization
     */
    public Sandwich() {
    }

    public Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients) {
        this.mainName = mainName;
        this.alsoKnownAs = alsoKnownAs;
        this.placeOfOrigin = placeOfOrigin;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getMainName() {
        return nvl(mainName);
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public String getAlsoKnownAsString() {
        return nvl(listToString(alsoKnownAs));
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getPlaceOfOrigin() {
        return nvl(placeOfOrigin);
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getDescription() {
        return nvl(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getIngredientsString() {
        return nvl(listToString(ingredients));
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    private String listToString(List<String> list) {
        StringBuilder result = new StringBuilder();

        if (list != null) {
            for (String s : list) {
                result.append("\t* ").append(s).append("\n");
            }
        }
        if (result.toString().endsWith("\n")) {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }

    private String nvl(String s) {
        return s == null || s.trim().isEmpty() ? "-" : s.replace("\\", "");
    }
}

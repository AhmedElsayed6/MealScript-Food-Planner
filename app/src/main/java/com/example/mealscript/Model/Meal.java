package com.example.mealscript.Model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Meal implements  Serializable {

    private String strMeal;
    private String idMeal;
    private String strIngredient;
    private String strDrinkAlternate;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strTags;
    private String strYoutube;
    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;
    private String strIngredient6;
    private String strIngredient7;
    private String strIngredient8;
    private String strIngredient9;
    private String strIngredient10;
    private String strIngredient11;
    private String strIngredient12;
    private String strIngredient13;
    private String strIngredient14;
    private String strIngredient15;
    private String strIngredient16;
    private String strIngredient17;
    private String strIngredient18;
    private String strIngredient19;
    private String strIngredient20;
    private String strMeasure1;
    private String strMeasure2;
    private String strMeasure3;
    private String strMeasure4;
    private String strMeasure5;
    private String strMeasure6;
    private String strMeasure7;
    private String strMeasure8;
    private String strMeasure9;
    private String strMeasure10;
    private String strMeasure11;
    private String strMeasure12;
    private String strMeasure13;
    private String strMeasure14;
    private String strMeasure15;
    private String strMeasure16;
    private String strMeasure17;
    private String strMeasure18;
    private String strMeasure19;
    private String strMeasure20;
    private String strSource;
    private String strImageSource;
    private String strCreativeCommonsConfirmed;
    private Object dateModified;
    @Expose
    private boolean isFavorite = false;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }




    public String getStrIngredient() {
        return strIngredient;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrDrinkAlternate() {
        return strDrinkAlternate;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public String getStrTags() {
        return strTags;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public String getStrIngredient16() {
        return strIngredient16;
    }

    public String getStrIngredient17() {
        return strIngredient17;
    }

    public String getStrIngredient18() {
        return strIngredient18;
    }

    public String getStrIngredient19() {
        return strIngredient19;
    }

    public String getStrIngredient20() {
        return strIngredient20;
    }

    public String getStrMeasure1() {
        return strMeasure1;
    }

    public String getStrMeasure2() {
        return strMeasure2;
    }

    public String getStrMeasure3() {
        return strMeasure3;
    }

    public String getStrMeasure4() {
        return strMeasure4;
    }

    public String getStrMeasure5() {
        return strMeasure5;
    }

    public String getStrMeasure6() {
        return strMeasure6;
    }

    public String getStrMeasure7() {
        return strMeasure7;
    }

    public String getStrMeasure8() {
        return strMeasure8;
    }

    public String getStrMeasure9() {
        return strMeasure9;
    }

    public String getStrMeasure10() {
        return strMeasure10;
    }

    public String getStrMeasure11() {
        return strMeasure11;
    }

    public String getStrMeasure12() {
        return strMeasure12;
    }

    public String getStrMeasure13() {
        return strMeasure13;
    }

    public String getStrMeasure14() {
        return strMeasure14;
    }

    public String getStrMeasure15() {
        return strMeasure15;
    }

    public String getStrMeasure16() {
        return strMeasure16;
    }

    public String getStrMeasure17() {
        return strMeasure17;
    }

    public String getStrMeasure18() {
        return strMeasure18;
    }

    public String getStrMeasure19() {
        return strMeasure19;
    }

    public String getStrMeasure20() {
        return strMeasure20;
    }

    public String getStrSource() {
        return strSource;
    }

    public String getStrImageSource() {
        return strImageSource;
    }

    public String getStrCreativeCommonsConfirmed() {
        return strCreativeCommonsConfirmed;
    }

    public Object getDateModified() {
        return dateModified;
    }




    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        if (strIngredient1 != null && !strIngredient1.isEmpty()) ingredients.add(strIngredient1);
        if (strIngredient2 != null && !strIngredient2.isEmpty()) ingredients.add(strIngredient2);
        if (strIngredient3 != null && !strIngredient3.isEmpty()) ingredients.add(strIngredient3);
        if (strIngredient4 != null && !strIngredient4.isEmpty()) ingredients.add(strIngredient4);
        if (strIngredient5 != null && !strIngredient5.isEmpty()) ingredients.add(strIngredient5);
        if (strIngredient6 != null && !strIngredient6.isEmpty()) ingredients.add(strIngredient6);
        if (strIngredient7 != null && !strIngredient7.isEmpty()) ingredients.add(strIngredient7);
        if (strIngredient8 != null && !strIngredient8.isEmpty()) ingredients.add(strIngredient8);
        if (strIngredient9 != null && !strIngredient9.isEmpty()) ingredients.add(strIngredient9);
        if (strIngredient10 != null && !strIngredient10.isEmpty()) ingredients.add(strIngredient10);
        if (strIngredient11 != null && !strIngredient11.isEmpty()) ingredients.add(strIngredient11);
        if (strIngredient12 != null && !strIngredient12.isEmpty()) ingredients.add(strIngredient12);
        if (strIngredient13 != null && !strIngredient13.isEmpty()) ingredients.add(strIngredient13);
        if (strIngredient14 != null && !strIngredient14.isEmpty()) ingredients.add(strIngredient14);
        if (strIngredient15 != null && !strIngredient15.isEmpty()) ingredients.add(strIngredient15);
        if (strIngredient16 != null && !strIngredient16.isEmpty()) ingredients.add(strIngredient16);
        if (strIngredient17 != null && !strIngredient17.isEmpty()) ingredients.add(strIngredient17);
        if (strIngredient18 != null && !strIngredient18.isEmpty()) ingredients.add(strIngredient18);
        if (strIngredient19 != null && !strIngredient19.isEmpty()) ingredients.add(strIngredient19);
        if (strIngredient20 != null && !strIngredient20.isEmpty()) ingredients.add(strIngredient20);
        return ingredients;
    }

    public List<String> getMeasures() {
        List<String> measures = new ArrayList<>();
        if (strMeasure1 != null && !strMeasure1.isEmpty()) measures.add(strMeasure1);
        if (strMeasure2 != null && !strMeasure2.isEmpty()) measures.add(strMeasure2);
        if (strMeasure3 != null && !strMeasure3.isEmpty()) measures.add(strMeasure3);
        if (strMeasure4 != null && !strMeasure4.isEmpty()) measures.add(strMeasure4);
        if (strMeasure5 != null && !strMeasure5.isEmpty()) measures.add(strMeasure5);
        if (strMeasure6 != null && !strMeasure6.isEmpty()) measures.add(strMeasure6);
        if (strMeasure7 != null && !strMeasure7.isEmpty()) measures.add(strMeasure7);
        if (strMeasure8 != null && !strMeasure8.isEmpty()) measures.add(strMeasure8);
        if (strMeasure9 != null && !strMeasure9.isEmpty()) measures.add(strMeasure9);
        if (strMeasure10 != null && !strMeasure10.isEmpty()) measures.add(strMeasure10);
        if (strMeasure11 != null && !strMeasure11.isEmpty()) measures.add(strMeasure11);
        if (strMeasure12 != null && !strMeasure12.isEmpty()) measures.add(strMeasure12);
        if (strMeasure13 != null && !strMeasure13.isEmpty()) measures.add(strMeasure13);
        if (strMeasure14 != null && !strMeasure14.isEmpty()) measures.add(strMeasure14);
        if (strMeasure15 != null && !strMeasure15.isEmpty()) measures.add(strMeasure15);
        if (strMeasure16 != null && !strMeasure16.isEmpty()) measures.add(strMeasure16);
        if (strMeasure17 != null && !strMeasure17.isEmpty()) measures.add(strMeasure17);
        if (strMeasure18 != null && !strMeasure18.isEmpty()) measures.add(strMeasure18);
        if (strMeasure19 != null && !strMeasure19.isEmpty()) measures.add(strMeasure19);
        if (strMeasure20 != null && !strMeasure20.isEmpty()) measures.add(strMeasure20);
        return measures;
    }



}
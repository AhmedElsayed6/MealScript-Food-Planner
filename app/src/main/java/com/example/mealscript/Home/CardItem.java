package com.example.mealscript.Home;
public class CardItem {
    private String text;
    private int imageResId;
    private String buttonText;
    private int imageButtonResId;

    public CardItem(String text, int imageResId, String buttonText, int imageButtonResId) {
        this.text = text;
        this.imageResId = imageResId;
        this.buttonText = buttonText;
        this.imageButtonResId = imageButtonResId;
    }

    public String getText() {
        return text;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getButtonText() {
        return buttonText;
    }

    public int getImageButtonResId() {
        return imageButtonResId;
    }
}
package com.example.petstore;

public class AnimalInfo {
    private String animal_id;
    private String animal_name;
    private String animal_price;
    private String animal_breed;
    private String animal_color;
    private String animal_description;
    private byte[] animal_image;

    public String getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(String animal_id) {
        this.animal_id = animal_id;
    }

    public String getAnimal_name() {
        return animal_name;
    }

    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }

    public String getAnimal_price() {
        return animal_price;
    }

    public void setAnimal_price(String animal_price) {
        this.animal_price = animal_price;
    }

    public String getAnimal_breed() {
        return animal_breed;
    }

    public void setAnimal_breed(String animal_breed) {
        this.animal_breed = animal_breed;
    }

    public String getAnimal_color() {
        return animal_color;
    }

    public void setAnimal_color(String animal_color) {
        this.animal_color = animal_color;
    }

    public String getAnimal_description() {
        return animal_description;
    }

    public void setAnimal_description(String animal_description) {
        this.animal_description = animal_description;
    }

    public byte[] getAnimal_image() {
        return animal_image;
    }

    public void setAnimal_image(byte[] animal_image) {
        this.animal_image = animal_image;
    }
}

package com.sarvika.dto;

public class UserResponseDTO {
    private String username;
    private String address;
    private int age;
    private String gender;
    private String occupation;

    // --- Constructors ---
    public UserResponseDTO() {}

    public UserResponseDTO(String username, String address, int age, String gender, String occupation) {
        this.username = username;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
    }

    // --- Getters & Setters ---
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { this.occupation = occupation; }

}

package org.example.websiteenglish.entity;

public class User {

    private Integer id;
    private String email;
    private String password;
    private String name;
    private String role;

    // Конструктор для чтения из БД
    public User(Integer id, String email, String password, String name, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role != null ? role : "USER";
    }

    // Конструктор для создания нового пользователя
    public User(String email, String password, String name) {
        this(null, email, password, name, "USER");
    }

    // Геттеры
    public Integer getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getRole() { return role; }

    // Сеттеры
    public void setId(Integer id) { this.id = id; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

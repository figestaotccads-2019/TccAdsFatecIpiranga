package com.figestaotcc;



public class Usuario {
    private String chave;
    private String name, email, password, confirm_password;

    public Usuario() {

    }
    public Usuario (String chave, String name, String email, String password, String confirm_password){
        this (name, email, password, confirm_password);
        setChave(chave);
    }
    public Usuario (String name, String email, String password, String confirm_password){
        setName(name);
        setEmail(email);
        setPassword(password);
        setConfirmPassword(confirm_password);
    }


    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) { this.password = password;}

    public String getConfirmPassword() {
        return confirm_password;
    }

    public void setConfirmPassword(String confirm_password) { this.confirm_password = confirm_password;}

    @Override
    public String toString() {
        return "Usuario{" +
                "chave='" + chave + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirm_password='" + confirm_password + '\'' +
                '}';
    }

    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String CONFIRMPASSWORD = "confirm_password";

}

/*
public class Usuario {
    private String chave;
    private String name, email, password, confirm_password;

    public Usuario() {

    }
    public Usuario (String chave, String name, String email, String password, String confirm_password){
        this (name, email, password, confirm_password);
        setChave(chave);
    }
    public Usuario (String name, String email, String password, String confirm_password){
        setName(name);
        setEmail(email);
        setPassword(password);
        setConfirmPassword(confirm_password);
    }


    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) { this.password = password;}

    public String getConfirmPassword() {
        return confirm_password;
    }

    public void setConfirmPassword(String confirm_password) { this.confirm_password = confirm_password;}

    @Override
    public String toString() {
        return "Usuario{" +
                "chave='" + chave + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirm_password='" + confirm_password + '\'' +
                '}';
    }

    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String CONFIRMPASSWORD = "confirm_password";

}
 */
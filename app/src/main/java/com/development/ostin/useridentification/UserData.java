package com.development.ostin.useridentification;

public class UserData {
    //Clase que contiene el modelo basico de un usuario
    String matricula;
    String contrasenia;
    String nombre;
    String apellidoP;
    String apellidoM;

    public UserData(String matricula, String contrasenia, String nombre, String apellidoP, String apellidoM) {
        this.matricula = matricula;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
    }

    public UserData(String matricula, String contrasenia) {
        this.matricula = matricula;
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}

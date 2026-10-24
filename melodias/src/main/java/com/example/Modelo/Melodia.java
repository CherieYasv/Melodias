package com.example.Modelo;

import java.util.Objects;

public class Melodia {
    private int id;
    private String nombre;
    private String compositor;
    private int ano;
    private String genero;
    private String cantante;
    private double precio;
    private String formato;

    //Constructores

    public Melodia(String nombre, String compositor, int ano, String genero, String cantante, double precio, String formato){
        this.nombre = nombre;
        this.compositor = compositor;
        this.ano = ano;
        this.genero = genero;
        this.cantante = cantante;
        this.precio = precio;
        this.formato = formato;
        this.id = this.hashCode(); // al final, para que ya existan nombre, cantante y ano
    }
    public Melodia(){
        this.nombre = "Raro";
        this.compositor = "Cuarteto de nos";
        this.ano = 2006;
        this.genero = "Rock";
        this.cantante = "Roberto Musso";
        this.precio = 67;
        this.formato = ".flac";
        this.id = this.hashCode();
    }

    //setters y getters
    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getCompositor(){
        return compositor;
    }
    public void setCompositor(String compositor){
        this.compositor = compositor;
    }

    public int getAno(){
        return ano;
    }
    public void setAno(int ano){
        this.ano = ano;
    }

    public String getGenero(){
        return genero;
    }
    public void setGenero(String genero){
        this.genero = genero;
    }

    public String getCantante(){
        return cantante;
    }
    public void setCantante(String cantante){
        this.cantante = cantante;
    }

    public double getPrecio(){
        return precio;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }

    public String getFormato(){
        return formato;
    }
    public void setFormato(String formato){
        this.formato = formato;
    }

    @Override
    public String toString(){
        return "Nombre: " + nombre
            + " | Compositor: " + compositor
            + " | Cantante: " + cantante
            + " | Año: " + ano
            + " | Género: " + genero
            + " | Precio: $" + String.format("%.2f", precio)
            + " | Formato: " + formato;
    }

    // Dos melodias son la misma version si tienen el mismo nombre, el mismo cantante
    // y el mismo año de grabacion (sin importar mayusculas o minusculas)
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        if(!(obj instanceof Melodia)){
            return false;
        }

        Melodia mel = (Melodia) obj;
        return nombre.equalsIgnoreCase(mel.getNombre())
            && cantante.equalsIgnoreCase(mel.getCantante())
            && ano == mel.getAno();
    }

    // Tiene que usar los mismos campos que equals
    @Override
    public int hashCode(){
        return Objects.hash(nombre.toLowerCase(), cantante.toLowerCase(), ano);
    }
}
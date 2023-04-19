package Semana1;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private int id;
    private String nom;
    private String ape;
    private int edad;

    public void setId(int ID){
        ID = id;
    }
    public int getId(){
        return id;
    }

    public void setName(String nombre){
        nombre = nom;
    }
    public String getName(){
        return nom;
    }

    public void setApe(String apellido){
        apellido = ape;
    }
    public String getApe(){
        return ape;
    }

    public void setEdad(int ed){
        ed = edad;
    }
    public int getEdad(){
        return edad;
    }

    public Person(int ID, String nombre, String apellido, int ed){
        ID = id;
        nombre = nom;
        apellido = ape;
        ed = edad;
    }

}

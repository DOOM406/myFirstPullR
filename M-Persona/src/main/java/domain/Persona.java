package domain;

import exceptions.*;

import java.time.LocalDateTime;

public class Persona {

    private String name;
    private String lastName;
    private LocalDateTime birthDate;
    private int heigth; //cm
    private int weight; //kg
    private String DNI;

    private Persona(String name, String lastName, LocalDateTime birthDate, int heigth, int weight, String DNI) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.heigth = heigth;
        this.weight = weight;
        this.DNI = DNI;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public int getHeigth() {
        return heigth;
    }

    public int getWeight() {
        return weight;
    }

    public String getDNI() {
        return DNI;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public static Persona create(String name, String lastName, LocalDateTime fechaNato , int heigth, int weight, String DNI) throws PersonaException {
        if(name==null || name.isEmpty()) throw new NameNotEmpty("El nombre no puede estar vacio ni ser nulo"); //importa el orden de validación
        if((DNI == null) || DNI.isEmpty() || (DNI.length() < 7 || DNI.length() > 8)) throw new DNIException("El Dni, no pueder nulo, no puede estar vacio, y debe ser un DNI valido");
        if((weight <= 0) || (weight >= 500)) throw new WeightNotValidException("Ingrese un peso valido");
        if((fechaNato.getYear()<1)||(fechaNato.isAfter(LocalDateTime.now()))) throw new DateDataException("Ingrese una fecha valida");
        if((heigth<=0)||(heigth>=250)) throw new HeightNotValidException("Ingrese una altura valida");
        return new Persona(name, lastName, fechaNato, heigth, weight, DNI);
    }
}


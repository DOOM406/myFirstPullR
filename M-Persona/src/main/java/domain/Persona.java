package domain;

import exceptions.*;

import java.time.LocalDateTime;
import java.util.Objects;

public class Persona {

    private String name;
    private String lastName;
    private LocalDateTime fechaNato;
    private int heigth; //cm
    private int weight; //kg
    private String DNI;

    private Persona(String name, String lastName, LocalDateTime fechaNato , int heigth, int weight, String DNI) {
        this.name = name;
        this.lastName = lastName;
        this.fechaNato = fechaNato;
        this.heigth = heigth;
        this.weight = weight;
        this.DNI = DNI;
    }


    public static Persona created(String name, String lastName, LocalDateTime fechaNato , int heigth, int weight, String DNI) throws PersonaException {
        if(name==null || name.isEmpty()) throw new NameNotEmpty("El nombre no puede estar vacio ni ser nulo"); //importa el orden de validación
        if((DNI == null) || DNI.isEmpty() || (DNI.length() < 7 || DNI.length() > 8)) throw new DNIException("El Dni, no pueder nulo, no puede estar vacio, y debe ser un DNI valido");
        if((weight <= 0) || (weight >= 500)) throw new WeightNotValidException("Ingrese un peso valido");
        if((fechaNato.getYear()<1)||(fechaNato.isAfter(LocalDateTime.now()))) throw new DateDataException("Ingrese una fecha valida");
        if((heigth<=0)||(heigth>=250)) throw new HeightNotValidException("Ingrese una altura valida");
        return new Persona(name, lastName, fechaNato, heigth, weight, DNI);
    }
}


package output;

import domain.Persona;

import java.util.List;

public interface InterfacePersona {

    public abstract Persona save(Persona persona);
    public abstract Persona findByDNI(String DNI);
    public abstract List<Persona> findAllPersonas();

}

package input;

import domain.Persona;

import java.time.LocalDateTime;
import java.util.List;

public interface InterfaceUseCasePersona {
    Persona savePersona(String name, String lastName, LocalDateTime birthDate, int height, int weight, String DNI);
    Persona findByDNI(String DNI);
    List<Persona> findAllPersonas();
}

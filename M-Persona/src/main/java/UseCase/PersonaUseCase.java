package UseCase;

import domain.Persona;
import input.InterfaceUseCasePersona;
import output.InterfacePersona;

import java.time.LocalDateTime;
import java.util.List;

public class PersonaUseCase implements InterfaceUseCasePersona {
    private InterfacePersona interfacePersona;

    public PersonaUseCase(InterfacePersona interfacePersona) {
        this.interfacePersona = interfacePersona;
    }

    @Override
    public Persona savePersona(String name, String lastName, LocalDateTime birthDate, int height, int weight, String DNI) {
        Persona p = Persona.create(name, lastName, birthDate, height, weight, DNI);
        return interfacePersona.save(p);
    }

    @Override
    public Persona findByDNI(String DNI) {
        return interfacePersona.findByDNI(DNI);
    }

    @Override
    public List<Persona> findAllPersonas() {
        return interfacePersona.findAllPersonas();
    }
}

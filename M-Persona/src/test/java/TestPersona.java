import domain.Persona;
import exceptions.DNIException;
import exceptions.HeightNotValidException;
import exceptions.NameNotEmpty;
import exceptions.WeightNotValidException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;

import java.time.LocalDateTime;

public class TestPersona {

    @Test
    @DisplayName("Constructor")
    @Order(1)
    public void Test01(){
        LocalDateTime fecha = LocalDateTime.of(2003,8,5,0,0);
        Persona persona = Persona.created("Ramiro",
                "Tobares",
                fecha,
                174,
                80,
                "24555321");
        Assertions.assertNotNull(persona);

    }

    @Test
    @DisplayName("Exception -> name")
    @Order(2)
    public void Test02(){
        LocalDateTime fecha = LocalDateTime.of(2003,8,5,0,0);

        Assertions.assertThrows(NameNotEmpty.class, () ->{
            Persona persona = Persona.created("",
                    "Tobares",
                    fecha,
                    174,
                    80,
                    "24555321");
        });

        Assertions.assertThrows(NameNotEmpty.class, () ->{
            Persona persona = Persona.created(null,
                    "Tobares",
                    fecha,
                    174,
                    80,
                    "45110029");
        });
    }

    @Test
    @DisplayName("Exception -> Dni")
    @Order(3)
    public void Test03(){
        LocalDateTime fecha = LocalDateTime.of(2003,8,5,0,0);

        Assertions.assertThrows(DNIException.class, () ->{
            Persona persona = Persona.created("Ramiro",
                    "Tobares",
                    fecha,
                    174,
                    80,
                    ""); //caso vacio
        });
        Assertions.assertThrows(DNIException.class, () ->{
            Persona persona = Persona.created("Ramiro",
                    "Tobares",
                    fecha,
                    174,
                    80,
                    null); // caso nulo
        });
        Assertions.assertThrows(DNIException.class, () ->{
            Persona persona = Persona.created("Ramiro",
                    "Tobares",
                    fecha,
                    174,
                    80,
                    "24555"); //dni validos desde 1.000.000 a 99.999.999
        });
    }

    @Test
    @DisplayName("Exception -> weight")
    @Order(4)
    public void Test04(){
        LocalDateTime fecha = LocalDateTime.of(2003,8,5,0,0);
        Assertions.assertThrows(WeightNotValidException.class, () ->{
            Persona persona = Persona.created("Ramiro",
                    "Tobares",
                    fecha,
                    174,
                    -12,
                    "24555321");
        });
        Assertions.assertThrows(WeightNotValidException.class, () ->{
            Persona persona = Persona.created("Ramiro",
                    "Tobares",
                    fecha,
                    174,
                    0,
                    "24555321");
        });
    }

    @Test
    @DisplayName("Exception -> height ")
    @Order(5)
    public void Test05(){
        LocalDateTime fecha = LocalDateTime.of(2003,8,5,0,0);
        Assertions.assertThrows(HeightNotValidException.class, () ->{
            Persona persona = Persona.created("Ramiro",
                    "Tobares",
                    fecha,
                    0,
                    80,
                    "24555321");
        });
    }
}

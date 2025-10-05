import UseCase.PersonaUseCase;
import domain.Persona;
import exceptions.DNIException;
import exceptions.HeightNotValidException;
import exceptions.NameNotEmpty;
import exceptions.WeightNotValidException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import output.InterfacePersona;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestPersona {

    @Mock
    private InterfacePersona repositorio;
    @InjectMocks
    private PersonaUseCase personaUseCase;

    @Test
    @DisplayName("Constructor")
    public void Test01(){
        LocalDateTime fecha = LocalDateTime.of(2003,8,5,0,0);
        Persona persona = Persona.create("Ramiro",
                "Tobares",
                fecha,
                174,
                80,
                "24555321");
        Assertions.assertNotNull(persona);

    }

    @Test
    @DisplayName("Test: Nombre no puede ser nulo o vacio")
    public void Test02(){
        LocalDateTime fecha = LocalDateTime.of(2003,8,5,0,0);

        Assertions.assertThrows(NameNotEmpty.class, () ->{
            Persona persona = Persona.create("",
                    "Tobares",
                    fecha,
                    174,
                    80,
                    "24555321");
        });

        Assertions.assertThrows(NameNotEmpty.class, () ->{
            Persona persona = Persona.create(null,
                    "Tobares",
                    fecha,
                    174,
                    80,
                    "45110029");
        });
    }

    @Test
    @DisplayName("Test: DNI no puede ser nulo, vacio, o de longitud < 7 > 9 ")
    public void Test03(){
        LocalDateTime fecha = LocalDateTime.of(2003,8,5,0,0);

        Assertions.assertThrows(DNIException.class, () ->{
            Persona persona = Persona.create("Ramiro",
                    "Tobares",
                    fecha,
                    174,
                    80,
                    ""); //caso vacio
        });
        Assertions.assertThrows(DNIException.class, () ->{
            Persona persona = Persona.create("Ramiro",
                    "Tobares",
                    fecha,
                    174,
                    80,
                    null); // caso nulo
        });
        Assertions.assertThrows(DNIException.class, () ->{
            Persona persona = Persona.create("Ramiro",
                    "Tobares",
                    fecha,
                    174,
                    80,
                    "24555"); //dni validos desde 1.000.000 a 99.999.999
        });
    }

    @Test
    @DisplayName("Test: weight no puede ser negativa o 0")
    public void Test04(){
        LocalDateTime fecha = LocalDateTime.of(2003,8,5,0,0);
        Assertions.assertThrows(WeightNotValidException.class, () ->{
            Persona persona = Persona.create("Ramiro",
                    "Tobares",
                    fecha,
                    174,
                    -12,
                    "24555321");
        });
        Assertions.assertThrows(WeightNotValidException.class, () ->{
            Persona persona = Persona.create("Ramiro",
                    "Tobares",
                    fecha,
                    174,
                    0,
                    "24555321");
        });
    }

    @Test
    @DisplayName("Test: Height no puede ser 0 ni nula")
    public void Test05(){
        LocalDateTime fecha = LocalDateTime.of(2003,8,5,0,0);
        Assertions.assertThrows(HeightNotValidException.class, () ->{
            Persona persona = Persona.create("Ramiro",
                    "Tobares",
                    fecha,
                    0,
                    80,
                    "24555321");
        });
    }
    //test con mockito
    @Test
    @DisplayName("Test: guardardado de persona exitoso")
    public void Test06(){
        //arrange
        LocalDateTime fecha = LocalDateTime.of(2003,8,5,0,0);
        Persona p_esperada = Persona.create("Ramiro",
                "Tobares",
                fecha,
                174,
                79,
                "24555321");
        //cuando se guarde cualquier objeto de tipo persona -> devolver p_esperada
        when(repositorio.save(any(Persona.class))).thenReturn(p_esperada);

        //act
        Persona resultado = personaUseCase.savePersona("Ramiro",
                "Tobares",
                fecha,
                174,
                79,
                "24555321");
        //assert
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Ramiro", resultado.getName());
        //verifica si usamos el metodo save() del repositorio UNA vez
        verify(repositorio, times(1)).save(any(Persona.class));
    }
    //test: verifcicar si nunca se guardo
    //test: buscar persona por dNI
    //test: buscar todas las personas
}

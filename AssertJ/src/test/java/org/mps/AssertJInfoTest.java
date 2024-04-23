package org.mps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AssertJInfoTest {
    
    @Test
    public void testStringAssertJ(){
        String malaga = "Malaga";
        
        assertThat(malaga)       
        .withFailMessage("should be %s with letters", "Malaga")
        .isEqualTo("Malaga") 
        .contains("g")
        .containsPattern("[a-zA-Z]")
        .isGreaterThan("Almeria")
        .doesNotContainAnyWhitespaces();
    }

    @Test
    public void testNumbersAsserJ(){
        assertThat(0.2).isCloseTo(0.0, byLessThan(0.20001));
        assertThat(8.1f).isEqualTo(8.0f, offset(0.1f));
        assertThat(38)
            .isEqualTo(38)
            .isCloseTo(40, within(10))
            .isEven();
        assertThat(8.1f).isNotCloseTo(8.0f, byLessThan(0.01f));

        assertThat(-0).isZero();
    }

    @Test
    public void testExcepcionAssertJ(){
        assertThatThrownBy(() -> { throw new Exception("boom!"); }).hasMessage("boom!");
       
        Throwable thrown = catchThrowable(() -> { throw new Exception("boom!"); });
        assertThat(thrown).hasMessageContaining("boom");
        
        assertThatExceptionOfType(RuntimeException.class)
        .isThrownBy(() -> { throw new RuntimeException("Exception!");})
        .withMessage("Exception!");

    }

    @Test
    public void testListAssertJ(){
        List<String> userList= new ArrayList<>();
        String user = "Paco";
        String user2 = "Pepe";
        
        userList.add(user);
        userList.add(user2);

        Condition<String> startwithP = new Condition<>(value -> value.startsWith("P"), "Starts with P");

        assertThat(userList)
        // Contains
        .contains(user2)
        // Contains en posicion en concreto
        .contains(user, atIndex(0))
        // Contiene al menos dos entrada con P
        .areAtLeast(2, startwithP)
        // No Nulo
        .isNotNull()
        // Solo una aparicion
        .containsOnlyOnce(user, user2)
        // Comprueban exactamente, potente
        .containsExactly(user, user2);
    }

    @Test
    /*
     * Asertos:
     * 1. La lista no esta vacia
     * 2. Tiene el tamaño adecuado
     * 3. Tiene al menos 2 rojos y como mucho dos amarillos
     * 4. Contiene la secuencia rojo, rojo
     * 5. Empieza por amarillo
     * 6. Termina por amarillo
     * 7. No contiene azul, verde
     */
    public void testListColorsAssertJ(){
        List<String> colors = new ArrayList<>();

        colors.add("amarillo");
        colors.add("rojo");
        colors.add("rojo");
        colors.add("amarillo");
    }

    /*
     * Asertos:
     * 1. Comprueba que al menos hay 2 numeros pares
     * 2. Y tiene algo de impares sin poner ningun numero
     */
    public void testListNumbersAssertJ(){
        List<Integer> numbers = new ArrayList<>();

        numbers.add(2);
        numbers.add(4);
        numbers.add(6);
        numbers.add(6);
        numbers.add(9);

    }


    @Test
    public void testMapAssertJ(){
        Map<String,String> map = new HashMap<>();

        map.put("uma", "Universidad de Malaga");
        map.put("uco", "Universidad de Cordoba");
        map.put("ual", "Universidad de Almeria");
        map.put("ugr", "");
        
        // comprueba todas las claves a la vez
        assertThat(map).containsKeys("uma", "uco", "ual");

        // comprueba que no tiene una clave
        assertThat(map).doesNotContainKeys("us");

        // comprueba un valor
        assertThat(map).containsValue("Universidad de Malaga");
        
        // comprueba si una clave satisface una condicion
        assertThat(map).hasEntrySatisfying("ugr", String::isEmpty);
    }

    @Test
    public void testcitiesInSpainwithAssertJ() throws Exception{
        List<String> citiesInSpain =  new ArrayList<>(Arrays.asList("Malaga","Cordoba","Almeria"));
        
        assertThat(citiesInSpain)
               .contains("Malaga")
               .doesNotContain("Sevilla")

               .containsExactly("Malaga","Cordoba","Almeria")
               .doesNotHaveDuplicates();
   }

   @Test
    public void testcitiesInSpainwithJunit() throws Exception{
        List<String> citiesInSpain =  new ArrayList<>(Arrays.asList("Malaga","Cordoba","Almeria"));
        
        boolean malagaIN = citiesInSpain.contains("Malaga");
        boolean sevillaNotIN = citiesInSpain.contains("Sevilla");
        boolean almeriaIN = citiesInSpain.contains("Almeria");
        boolean cordobaIN = citiesInSpain.contains("Cordoba");
        
        assertTrue(malagaIN);
        assertFalse(sevillaNotIN);
        assertTrue(almeriaIN);
        assertTrue(cordobaIN);
        assertEquals(3, citiesInSpain.size());
   }

   @Test
    public void testContrasenya() {
        String password = "Password100#";


        assertThat(password).hasSizeBetween(10,15);
        //assertThat(password).containsAnyOf(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"});
        //assertThat(password).containsAnyOf(new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"});
        //assertThat(password).containsAnyOf(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"});
        assertThat(password).containsPattern("[a-z]");
       assertThat(password).containsPattern("[A-Z]");
       assertThat(password).containsPattern("[0-9]");
        assertThat(password).containsPattern("![a-zA-Z0-9]");

   }
}

package org.ClubDeportivo.clubdeportivo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ClubDeportivoAltoRendimientoTest {
    ClubDeportivoAltoRendimiento club;

    @BeforeEach
    void setup() throws ClubException {club = new ClubDeportivoAltoRendimiento("Club", 10, 20);}

    @ParameterizedTest
    @CsvSource({
            "CLUB, -1, 1",
            "CLUB, 1, -1"
    })
    void constructorClubDeportivoAltoRendimineto_DatosNegativos_ThrowsClubException(String nombre, int maximo, double incremento){
        assertThrows(ClubException.class, ()->{
            club = new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "CLUB, 0, 1",
            "CLUB, 1, 0"
    })
    void constructorClubDeportivoAltoRendimineto_DatosCero_ThrowsClubException(String nombre, int maximo, double incremento){
        assertThrows(ClubException.class, ()->{
            club = new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);
        });
    }

    @Test
    void constructorClubDeportivoAltoRendimineto_nombreNulo_ThrowsClubException(){
        assertThrows(ClubException.class, ()->{
            club = new ClubDeportivoAltoRendimiento(null, 10, 10);
        });
    }

    @Test
    void constructorClubDeportivoAltoRendimineto_DatosValidos_CreaClubCorrectamente() throws ClubException {
        //Arrange
        String nombre = "CLUB";
        int maximo = 10;
        int incremento = 10;
        String expected = "CLUB --> [  ]";
        //Act
        club = new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);
        //Assert
        assertEquals(expected, club.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "CLUB, -1, 1, 1",
            "CLUB, 1, -1, 1",
            "CLUB, 1, 1, -1"
    })
    void constructorClubDeportivoAltoRendiminetoConTamaño_DatosNegativos_ThrowsClubException(String nombre, int maximo, double incremento, int tamaño){
        assertThrows(ClubException.class, ()->{
            club = new ClubDeportivoAltoRendimiento(nombre, tamaño, maximo, incremento);
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "CLUB, 0, 1, 1",
            "CLUB, 1, 0, 1",
            "CLUB, 1, 1, 0"
    })
    void constructorClubDeportivoAltoRendiminetoConTamaño_DatosCero_ThrowsClubException(String nombre, int maximo, double incremento, int tamaño){
        assertThrows(ClubException.class, ()->{
            club = new ClubDeportivoAltoRendimiento(nombre, tamaño, maximo, incremento);
        });
    }

    @Test
    void constructorClubDeportivoAltoRendiminetoConTamaño_nombreNulo_ThrowsClubException(){
        assertThrows(ClubException.class, ()->{
            club = new ClubDeportivoAltoRendimiento(null, 10, 10, 10);
        });
    }

    @Test
    void constructorClubDeportivoAltoRendiminetoConTamaño_DatosValidos_CreaClubCorrectamente() throws ClubException {
        //Arrange
        String nombre = "CLUB";
        int maximo = 10;
        int incremento = 10;
        int tamaño = 10;
        String expected = "CLUB --> [  ]";
        //Act
        club = new ClubDeportivoAltoRendimiento(nombre, tamaño, maximo, incremento);
        //Assert
        assertEquals(expected, club.toString());
    }

    @Test
    void anyadirActividad_NumeroMenorDeDatos_ThrowsClubException(){
        //Arrange
        String[] datos = {"456B", "Pilates", "8", "5"};
        //Act
        //Assert
        assertThrows(ClubException.class, ()->{
            club.anyadirActividad(datos);
        });
    }

    @ParameterizedTest
    @CsvSource({
            ", Pilates, 8, 5, 50.0",
            "456B,, 8, 5, 50.0",
            "456B, Pilates,, 5, 50.0",
            "456B, Pilates, 8,, 50.0",
            "456B, Pilates, 8, 5,"
    })
    void anyadirActividad_DatosNulos_ThrowsClubException(String codigo, String actividad, String nplazas, String nmatriculados, String tarifa){
        //Arrange
        String[] datos = {codigo, actividad, nplazas, nmatriculados, tarifa};
        //Act
        //Assert
        assertThrows(ClubException.class, ()->{
            club.anyadirActividad(datos);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "456B, Pilates, STRING, 5, 50.0",
            "456B, Pilates, 2.5, 5, 50.0",
            "456B, Pilates, 8, STRING, 50.0",
            "456B, Pilates, 8, 2.5, 50.0",
            "456B, Pilates, 8, 5, STRING"
    })
    void anyadirActividad_FormatoDatosIncorrecto_ThrowsClubException(String codigo, String actividad, String nplazas, String nmatriculados, String tarifa){
        //Arrange
        String[] datos = {codigo, actividad, nplazas, nmatriculados, tarifa};
        //Act
        //Assert
        assertThrows(ClubException.class, ()->{
            club.anyadirActividad(datos);
        });
    }
    @Test
    void anyadirActividad_PlaazasNoSuperanMaximo_AnyadeGrupoAlClub() throws ClubException {
        //Arrange
        String[] datos = {"456B", "Pilates", "8", "5", "50.0"};
        String expected = "Club --> [ (456B - Pilates - 50.0 euros - P:8 - M:5) ]";
        //Act
        club.anyadirActividad(datos);
        //Assert
        assertEquals(expected, club.toString());
    }

    @Test
    void anyadirActividad_PlaazasSuperanMaximo_AnyadeGrupoAlClubConPlazasLimite() throws ClubException {
        //Arrange
        String[] datos = {"456B", "Pilates", "12", "5", "50.0"};
        String expected = "Club --> [ (456B - Pilates - 50.0 euros - P:10 - M:5) ]";
        //Act
        club.anyadirActividad(datos);
        //Assert
        assertEquals(expected, club.toString());
    }

    @Test
    void ingresos_SinGrupos_DevuelveCero(){
        assertEquals(0, club.ingresos());
    }

    @Test
    void ingresos_ConGrupos_DevuelveResultadoEsperado() throws ClubException {
        //Arrange
        int matriculados = 5;
        double tarifa = 50.0;
        Grupo grupo = new Grupo("111A", "Pilates", 8, matriculados, tarifa);
        club.anyadirActividad(grupo);
        //Act
        double ingresosExpected = 300;
        //Assert
        assertEquals(ingresosExpected, club.ingresos());
    }
}

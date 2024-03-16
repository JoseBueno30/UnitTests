package org.ClubDeportivo.clubdeportivo;

import org.ClubDeportivo.clubdeportivo.ClubDeportivo;
import org.ClubDeportivo.clubdeportivo.ClubException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ClubDeportivoTest {
    ClubDeportivo club;

    @BeforeEach
    void setup() throws ClubException {club = new ClubDeportivo("Club");}

    @ParameterizedTest
    @CsvSource({
            "CLUB, -1",
            "CLUB, 0",
            "CLUB, null",
            "null, 1"
    })
    void constructorClubDeportivo_DatosInvalidos_ThrowsClubException(String nombre, int ngrupos){
        assertThrows(ClubException.class, ()->{
            club = new ClubDeportivo(nombre, ngrupos);
        });
    }

    @Test
    void constructorClubDeportivo_NumeroDeGruposPositivo_CreaClubCorrectamente() throws ClubException {
        //Arrange
        String nombre = "CLUB";
        int ngrupos = 10;
        String expected = "CLUB --> [ ]";
        //Act
        club = new ClubDeportivo(nombre, ngrupos);
        //Assert
        assertEquals(expected, club.toString());
    }

    @Test
    void constructorClubDeportivo_StringValido_CreaClubCorrectamente() throws ClubException {
        //Arrange
        String nombre = "CLUB";
        String expected = "CLUB --> [ ]";
        //Act
        club = new ClubDeportivo(nombre);
        //Assert
        assertEquals(expected, club.toString());
    }

    @Test
    void toString_DevuelveResultadoEsperado() throws ClubException {
        //Arrange
        Grupo grupo = new Grupo("456B", "Pilates", 8, 5, 50.0);
        club.anyadirActividad(grupo);
        String expected = "Club --> [ (456B - Pilates - 50.0 euros - P:8 - M:5) ]";
        //Act
        String actual = club.toString();
        //Assert
        assertEquals(expected, actual);
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
            //valores nulos
            "null, Pilates, 8, 5, 50.0",
            "456B, null, 8, 5, 50.0",
            "456B, Pilates, null, 5, 50.0",
            "456B, Pilates, 8, null, 50.0",
            "456B, Pilates, 8, 5, null",
            //valores a 0
            "456B, Pilates, 0, 5, 50.0",
            "456B, Pilates, 8, 0, 50.0",
            "456B, Pilates, 8, 5, 0",
            //valores double
            "456B, Pilates, 2.5, 5, 50.0",
            "456B, Pilates, 8, 2.5, 50.0",
            "456B, Pilates, 8, 5, 2.5",
            //valores negativos
            "456B, Pilates, -8, 5, 50.0",
            "456B, Pilates, 8, -5, 50.0",
            "456B, Pilates, 8, 5, -50.0"
    })
    void anyadirActividad_StringDatosInvalidos_ThrowsClubException(String codigo, String actividad, String nplazas, String nmatriculados, String tarifa){
        //Arrange
        String[] datos = {codigo, actividad, nplazas, nmatriculados, tarifa};
        //Act
        //Assert
        assertThrows(ClubException.class, ()->{
            club.anyadirActividad(datos);
        });
    }

    @Test
    void anyadirActividad_GrupoNull_ThrowsClubException() throws ClubException {
        //Arrange
        Grupo grupo = null;
        //Act
        //Assert
        assertThrows(ClubException.class, ()->{
            club.anyadirActividad(grupo);
        });
    }

    @Test
    void anyadirActividad_GrupoNuevo_AnyadeGrupoAlClub() throws ClubException {
        //Arrange
        Grupo grupo = new Grupo("456B", "Pilates", 8, 5, 50.0);
        club.anyadirActividad(grupo);
        String expected = "Club --> [ (456B - Pilates - 50.0 euros - P:8 - M:5) ]";
        //Act
        //Assert
        assertEquals(expected, club.toString());
    }

    @Test
    void anyadirActividad_GrupoExistente_ActualizaNumeroDePlazas() throws ClubException {
        //Arrange
        int plazasExpected = 5;
        Grupo grupo1 = new Grupo("111A", "Pilates", 8, 5, 50.0);
        club.anyadirActividad(grupo1);
        Grupo grupo2 = new Grupo("111A", "Pilates", plazasExpected, 5, 50.0);
        club.anyadirActividad(grupo2);
        //Act
        int plazasActual = grupo1.getPlazas();
        //Assert
        assertEquals(plazasExpected, plazasActual
        );
    }

    @Test
    void plazasLibres_ActividadInexistente_DevuelveCero(){
        //Arrange
        int expected = 0;
        //Act
        int actual = club.plazasLibres("Pilates");
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void plazasLibres_ActividadExistente_DevuelveMayorOIgualACero() throws ClubException {
        //Arrange
        Grupo grupo1 = new Grupo("111A", "Pilates", 8, 5, 50.0);
        club.anyadirActividad(grupo1);
        Grupo grupo2 = new Grupo("111B", "Pilates", 8, 0, 50.0);
        club.anyadirActividad(grupo2);
        //Act
        int plazasLibresActual = club.plazasLibres("Pilates");
        //Assert
        assertTrue(0 <= plazasLibresActual);
    }
    @Test
    void plazasLibres_ActividadExistente_DevuelveResultadoEsperado() throws ClubException {//ESTE TEST SERIA CORRECTO O SE CONSIDERA QUE COMPRUEBA LA IMPLEMENTACIÓN
        //Arrange
        int nplazas = 8;
        int matriculados = 5;
        Grupo grupo = new Grupo("111A", "Pilates", nplazas, matriculados, 50.0);
        club.anyadirActividad(grupo);
        int plazasLibresExpected = nplazas - matriculados;
        //Act
        int plazasLibresActual = club.plazasLibres("Pilates");
        //Assert
        assertEquals(plazasLibresExpected, plazasLibresActual);
    }


    @Test
    void plazasLibres_ActividadNull_ThrowsClubException(){
        //Arrange
        String actividad = null;
        //Act
        //Assert
        assertThrows(ClubException.class, ()->{
            club.plazasLibres(actividad);
        });
    }

    @Test
    void matricular_PersonasNegativas_NoAlteraPlazasLibres() throws ClubException {
        //Arrange
        String actividad = "Pilates";
        Grupo grupo = new Grupo("111A", "Pilates", 8, 5, 50.0);
        club.anyadirActividad(grupo);
        int npersonas = -1;
        int plazasLibresExpected = club.plazasLibres("Pilates");
        //Act
        club.matricular("Pilates", npersonas);
        //Assert
        assertEquals(plazasLibresExpected, club.plazasLibres("Pilates"));
    }

    @Test
    void matricular_CeroPersonas_NoAlteraPlazasLibres() throws ClubException {
        //Arrange
        Grupo grupo = new Grupo("111A", "Pilates", 8, 5, 50.0);
        club.anyadirActividad(grupo);
        int npersonas = 0;
        int plazasLibresExpected = club.plazasLibres("Pilates");
        //Act
        club.matricular("Pilates", npersonas);
        //Assert
        assertEquals(plazasLibresExpected, club.plazasLibres("Pilates"));
    }

    @Test
    void matricular_NPersonasSinPlazasDisponibles_ThrowsClubException() throws ClubException {
        //Arrange
        Grupo grupo = new Grupo("111A", "Pilates", 8, 5, 50.0);
        club.anyadirActividad(grupo);
        int npersonas = club.plazasLibres("Pilates") + 1;
        //Act
        //Assert
        assertThrows(ClubException.class, ()->{
            club.matricular("Pilates", npersonas);
        });
    }

    @Test
    void matricular_NPersonasConPlazasDisponiblesEnUnGrupo_ReduceNPlazasDisponibles() throws ClubException {
        //Arrange
        Grupo grupo = new Grupo("111A", "Pilates", 8, 0, 50.0);
        club.anyadirActividad(grupo);
        int npersonas = club.plazasLibres("Pilates") - 1;
        int plazasLibresExpected = club.plazasLibres("Pilates") - npersonas;

        //Act
        club.matricular("Pilates", npersonas);
        //Assert
        assertEquals(plazasLibresExpected, club.plazasLibres("Pilates"));
    }

    @Test
    void matricular_NPersonasConPlazasDisponiblesEnVariosGrupos_ReduceNPlazasDisponibles() throws ClubException {//DIVIDIR EN TRES TESTS DIFERENTES?
        //Arrange
        int plazas1 = 8;
        int plazas2 = 8;
        int npersonas = 12;
        Grupo grupo1 = new Grupo("111A", "Pilates", plazas1, 0, 50.0);
        club.anyadirActividad(grupo1);
        Grupo grupo2 = new Grupo("111B", "Pilates", plazas2, 0, 50.0);
        club.anyadirActividad(grupo2);

        int plazasLibresClubExpected = club.plazasLibres("Pilates") - npersonas;
        int plazasLibresGrupo1Expected = 0;
        int plazasLibreGrupo2Expected = plazas2 - (npersonas - plazas1);
        //Act
        club.matricular("Pilates", npersonas);
        //Assert
        assertEquals(plazasLibresClubExpected, club.plazasLibres("Pilates"));
        assertEquals(plazasLibresGrupo1Expected, grupo1.plazasLibres());
        assertEquals(plazasLibreGrupo2Expected, grupo2.plazasLibres());
    }

    @ParameterizedTest
    @CsvSource({
          "null, 2",
          "Pilates, null"
    })
    void matricular_DatosNulos_ThrowsClubException(String actividad, int npersonas) throws ClubException {
        //Arrange
        Grupo grupo = new Grupo("111A", "Pilates", 8, 5, 50.0);
        club.anyadirActividad(grupo);
        //Act
        //Assert
        assertThrows(ClubException.class, ()->{
           club.matricular(actividad, npersonas);
        });
    }

    //como testear esto sin testear la implementacipon y solo el resultado??
    @Test
    void ingresos_SinGrupos_DevuelveCero(){
        //Arrange

        //Act

        //Assert
        assertEquals(0, club.ingresos());
    }

    @Test
    void ingresos_ConGrupos_DevuelveResultadoEsperado() throws ClubException {
        //Arrange
        Grupo grupo = new Grupo("111A", "Pilates", 8, 5, 50.0);
        club.anyadirActividad(grupo);
        //Act

        //Assert
        assertEquals(250, club.ingresos());
    }














}

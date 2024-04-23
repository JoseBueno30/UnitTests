package es.uma.informatica.mps;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarioTest {

    Calendario calendario;

    @BeforeEach
    void setup(){
        calendario = new Calendario();
    }

    @Nested
    class DiaSemana{
        @ParameterizedTest
        @DisplayName("Lanza IllegalArgumentException con un año invalido")
        @Tags({@Tag("12")})
        @CsvSource({
                "4, 5, -1",
                "4, 5, 3",
                "4, 4, 10000"
        })
        void diaSemana_anyoInvalido_lanzaIlleglArgumentException(int dia, int mes, int anyo){
            assertThrows(IllegalArgumentException.class, ()->{
                calendario.diaSemana(dia, mes, anyo);
            });
        }

        @ParameterizedTest
        @DisplayName("Lanza IllegalArgumentException con una fecha inexistente")
        @Tags({@Tag("16")})
        @CsvSource({
                "5, 10, 1582",
                "14, 10, 1582",
        })
        void diaSemana_fechaInexistente_lanzaIlleglArgumentException(int dia, int mes, int anyo){
            assertThrows(IllegalArgumentException.class, ()->{
                calendario.diaSemana(dia, mes, anyo);
            });
        }

        @ParameterizedTest
        @DisplayName("Lanza IllegalArgumentException con un mes invalido")
        @Tags({@Tag("2"), @Tag("14")})
        @CsvSource({
                "5, -1, 2024",
                "5, 0, 2024",
                "5, 13, 2024",
                "5, 2, 4"
        })
        void diaSemana_mesInvalido_lanzaIlleglArgumentException(int dia, int mes, int anyo){
            assertThrows(IllegalArgumentException.class, ()->{
                calendario.diaSemana(dia, mes, anyo);
            });
        }

        @ParameterizedTest
        @DisplayName("Lanza IllegalArgumentException con un día invalido en meses de 30 y 31 días")
        @Tags({@Tag("4"), @Tag("6")})
        @CsvSource({
                "-1, 1, 2024",
                "0, 1, 2024",
                "32, 1, 2024",
                "-1, 4, 2024",
                "0, 4, 2024",
                "31, 4, 2024"
        })
        void diaSemana_diaInvalidoMesTreintaOTreintayunDias_lanzaIlleglArgumentException(int dia, int mes, int anyo){
            assertThrows(IllegalArgumentException.class, ()->{
                calendario.diaSemana(dia, mes, anyo);
            });
        }

        @ParameterizedTest
        @DisplayName("Lanza IllegalArgumentException con un día invalido en Febrero en un anyo NO bisiesto")
        @Tags({@Tag("8")})
        @CsvSource({
                "-1, 2, 2023",
                "0, 2, 2023",
                "29, 2, 2023",
                "29, 2, 2100",
                "29, 2, 41"
        })
        void diaSemana_diaInvalidoFebreroAnyoNoBisiesto_lanzaIlleglArgumentException(int dia, int mes, int anyo){
            assertThrows(IllegalArgumentException.class, ()->{
                calendario.diaSemana(dia, mes, anyo);
            });
        }

        @ParameterizedTest
        @DisplayName("Lanza IllegalArgumentException con un día invalido en Febrero en un anyo bisiesto")
        @Tags({@Tag("10")})
        @CsvSource({
                "-1, 2, 2024",
                "0, 2, 2024",
                "30, 2, 2024",
                "30, 2, 4000",
                "30, 2, 40"
        })
        void diaSemana_diaInvalidoFebreroAnyoBisiesto_lanzaIlleglArgumentException(int dia, int mes, int anyo){
            assertThrows(IllegalArgumentException.class, ()->{
                calendario.diaSemana(dia, mes, anyo);
            });
        }

        @ParameterizedTest
        @DisplayName("Devuelve un día de la semana con un día valido en Febrero en un anyo NO bisiesto")
        @Tags({@Tag("7")})
        @CsvSource({
                "1, 2, 2023",
                "28, 2, 2023",
                "28, 2, 2023",
                "28, 2, 2100",
                "28, 2, 41"
        })
        void diaSemana_diaValidoFebreroAnyoNoBisiesto_devuelveUnDiaDeLaSemana(int dia, int mes, int anyo){
            DayOfWeek diaSemana = calendario.diaSemana(dia, mes, anyo);
            assertNotNull(diaSemana);
        }

        @ParameterizedTest
        @DisplayName("Devuelve un día de la semana con un día valido en Febrero en un anyo bisiesto")
        @Tags({@Tag("9")})
        @CsvSource({
                "1, 2, 2024",
                "29, 2, 2024",
                "29, 2, 2024",
                "29, 2, 4000",
                "29, 2, 40"
        })
        void diaSemana_diaValidoFebreroAnyoBisiesto_devuelveUnDiaDeLaSemana(int dia, int mes, int anyo){
            DayOfWeek diaSemana = calendario.diaSemana(dia, mes, anyo);
            assertNotNull(diaSemana);
        }

        @ParameterizedTest
        @DisplayName("Devuelve un día de la semana con un día valido")
        @Tags({@Tag("1"), @Tag("3"), @Tag("5"), @Tag("15")})
        @CsvSource({
                "1, 1, 2024",
                "31, 1, 2024",
                "30, 4, 2023",
                "4, 10, 1582",
                "15, 10, 1582"
        })
        void diaSemana_diaValido_devuelveUnDiaDeLaSemana(int dia, int mes, int anyo){
            DayOfWeek diaSemana = calendario.diaSemana(dia, mes, anyo);
            assertNotNull(diaSemana);
        }

        @ParameterizedTest
        @DisplayName("Devuelve un día de la semana con un mes valido")
        @Tags({@Tag("1"), @Tag("13")})
        @CsvSource({
                "1, 1, 2024",
                "1, 3, 4",
                "31, 12, 4"
        })
        void diaSemana_mesValido_devuelveUnDiaDeLaSemana(int dia, int mes, int anyo){
            DayOfWeek diaSemana = calendario.diaSemana(dia, mes, anyo);
            assertNotNull(diaSemana);
        }

        @ParameterizedTest
        @DisplayName("Devuelve un día de la semana con un anyo valido")
        @Tags({@Tag("11")})
        @CsvSource({
                "1, 3, 4",
                "31, 12, 9999"
        })
        void diaSemana_anyoValido_devuelveUnDiaDeLaSemana(int dia, int mes, int anyo){
            DayOfWeek diaSemana = calendario.diaSemana(dia, mes, anyo);
            assertNotNull(diaSemana);
        }
    }
}

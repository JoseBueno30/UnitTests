package org.ClubDeportivo.clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GrupoTest {
    Grupo grupo;

    @ParameterizedTest
    @CsvSource({
            " '456B', 'Pilates', 8, 5, 50.0"
    })
    public void testConstructorDatosValidos(String codigo, String actividad, int nplazas, int matriculados,
            double tarifa) {
        assertThrows(ClubException.class, () -> {
            grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        });
    }
}

package org;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.ClubDeportivo.clubdeportivo.ClubException;
import org.ClubDeportivo.clubdeportivo.Grupo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestGrupo {
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

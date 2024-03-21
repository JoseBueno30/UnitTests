package org.ClubDeportivo.clubdeportivo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class GrupoTest {
    Grupo grupo;

    @ParameterizedTest
    @CsvSource({
            ", Pilates, 8, 5, 50.0",
            "456B,, 8, 5, 50.0",

    })
    public void constructorGrupo_ValoresNulos_ThrowsClubException(String codigo, String actividad, int nplazas, int matriculados, double tarifa) {
        assertThrows(ClubException.class, () -> {
            grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "'456B', 'Pilates', -8, 5, 50.0",
            "'456B', 'Pilates', 8, -5, 50.0",
            "'456B', 'Pilates', 8, 5, -50.0"

    })
    public void constructorGrupo_ValoresNegativos_ThrowsClubException(String codigo, String actividad, int nplazas, int matriculados, double tarifa) {
        assertThrows(ClubException.class, () -> {
            grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "'456B', 'Pilates', 0, 5, 50.0",
            "'456B', 'Pilates', 8, 5, 0"

    })
    public void constructorGrupo_ValoresCero_ThrowsClubException(String codigo, String actividad, int nplazas, int matriculados, double tarifa) {
        assertThrows(ClubException.class, () -> {
            grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        });
    }

    @Test
    public void contructorGrupo_MasMatriculadosQuePlazas_ThrowsClubException(){
        String codigo = "456B" ;
        String actividad = "Pilates";
        int nplazas =8;
        int matriculados = 10;
        double tarifa = 50.0;
        assertThrows(ClubException.class, ()-> {
            grupo = new Grupo(codigo, actividad , nplazas, matriculados, tarifa);
        });
    }

    @Test
    public void constructorGrupo_CreaGrupoCorrectamente() throws ClubException {
        String codigo = "456B" ;
        String actividad = "Pilates";
        int nplazas =8;
        int matriculados = 0;
        double tarifa = 50.0;
        String expected = "(456B - Pilates - 50.0 euros - P:8 - M:0)";

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        assertEquals(expected, grupo.toString());
    }

    @Test
    public void gettersGrupo_DevuelvenValorCorrecto() throws ClubException {
        String codigo = "456B";
        String actividad = "Pilates";
        int nplazas = 20;
        int matriculados = 10;
        double tarifa = 50.0;

        grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

        assertEquals(codigo, grupo.getCodigo());
        assertEquals(actividad, grupo.getActividad());
        assertEquals(nplazas, grupo.getPlazas());
        assertEquals(matriculados, grupo.getMatriculados());
        assertEquals(tarifa, grupo.getTarifa());
    }

    @Test
    public void plazasLibres_DevuelveValorCorrecto() throws ClubException {
        grupo = new Grupo("456B", "Pilates", 20, 10, 50.0);
        int plazasLibresExpected = grupo.getPlazas()-grupo.getMatriculados();

        int plazasLibres = grupo.plazasLibres();

        assertEquals(plazasLibresExpected, plazasLibres);
    }

    @Test
    public void actualizarPlazas_CeroDePlazas_ThrowsClubException() throws ClubException {
        grupo = new Grupo("456B", "Pilates", 20, 10, 50.0);
        assertThrows(ClubException.class, ()-> {
            grupo.actualizarPlazas(0);
        });
    }

    @Test
    public void actualizarPlazas_PlazasNegativas_ThrowsClubException() throws ClubException {
        grupo = new Grupo("456B", "Pilates", 20, 10, 50.0);
        assertThrows(ClubException.class, ()-> {
            grupo.actualizarPlazas(-5);
        });
    }

    @Test
    public void actualizarPlazas_NumeroMenorQueMatriculados_ThrowsClubException() throws ClubException {
        grupo = new Grupo("456B", "Pilates", 20, 10, 50.0);

        assertThrows(ClubException.class, ()-> {
            grupo.actualizarPlazas(5);
        });
    }
    @Test
    public void actualizarPlazas_DevuelveValorCorrecto() throws ClubException {
        grupo = new Grupo("456B", "Pilates", 20, 10, 50.0);
        grupo.actualizarPlazas(25);

        assertEquals(25, grupo.getPlazas());
    }

    @Test
    public void matricular_CeroPersonas_ThrowsClubException() throws ClubException {
        grupo = new Grupo("456B", "Pilates", 20, 10, 50.0);
        assertThrows(ClubException.class, ()-> {
            grupo.matricular(0);
        });
    }

    @Test
    public void matricular_PersonasNegativas_ThrowsClubException() throws ClubException {
        grupo = new Grupo("456B", "Pilates", 20, 10, 50.0);

        assertThrows(ClubException.class, ()-> {
            grupo.matricular(-5);
        });
    }

    @Test
    public void matricular_NPersonasSinPlazasDisponibles_ThrowsClubException() throws ClubException {
        grupo = new Grupo("456B", "Pilates", 20, 18, 50.0);

        int plazasLibres = grupo.plazasLibres();

        assertThrows(ClubException.class, ()-> {
            grupo.matricular(5);
        });
    }

    @Test
    public void matricular_DevuelveValorCorrecto() throws ClubException {
        int nmatriculados = 10;
        Grupo grupo = new Grupo("456B", "Pilates", 20, nmatriculados, 50.0);

        grupo.matricular(5);
        assertEquals(15, grupo.getMatriculados());
    }

    @Test
    public void toString_DevuelveValorCorrecto() throws ClubException{
        grupo = new Grupo("456B", "Pilates", 20, 10, 50.0);

        String expected = "(456B - Pilates - 50.0 euros - P:20 - M:10)";

        assertEquals(expected, grupo.toString());
    }

    @Test
    public void equals_MismaInstancia_DevuelveValorCorrecto() throws ClubException {
        grupo = new Grupo("456B", "Pilates", 20, 10, 50.0);
        assertTrue(grupo.equals(grupo));
    }

    @Test
    public void equals_ObjetosIguales_DevuelveVerdadero() throws ClubException {
        Grupo grupo1 = new Grupo("456B", "Pilates", 20, 10, 50.0);
        Grupo grupo2 = new Grupo("456B", "Pilates", 20, 10, 50.0);

        assertTrue(grupo1.equals(grupo2));
    }

    @Test
    public void equals_ObjetosDiferentes_DevuelveFalso() throws ClubException {
        Grupo grupo1 = new Grupo("456B", "Pilates", 20, 10, 50.0);
        Grupo grupo2 = new Grupo("456A", "Yoga", 15, 5, 40.0);

        assertFalse(grupo1.equals(grupo2));
    }

    @Test
    public void hashCode_DevuelveValorCorrecto() throws ClubException {
        String codigo = "456B";
        String actividad = "Pilates";
        Grupo grupo = new Grupo(codigo, actividad, 20, 10, 50.0);

        int expectedHashCode = codigo.toUpperCase().hashCode() + actividad.toUpperCase().hashCode();
        int actualHashCode = grupo.hashCode();

        assertEquals(expectedHashCode, actualHashCode);
    }
}
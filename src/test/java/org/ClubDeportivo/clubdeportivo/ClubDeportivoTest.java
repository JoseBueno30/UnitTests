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

    @Test
    void ClubDeportivoConstructor_NegativeGroupNumber_ThrowsClubException(){
        assertThrows(ClubException.class, ()->{
            club = new ClubDeportivo("club", -1);
        });
    }
}

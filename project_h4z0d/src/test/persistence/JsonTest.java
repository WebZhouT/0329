package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkGame(String map, int place, String myLegend, String weapon1, String weapon2, int shieldLevel,
                               int myDamage, String squadLegend1, String squadLegend2, int myKill, int squadKill,
                               int squadDamage, int numberOfDeath, Game game) {
        assertEquals(map, game.getMap());
        assertEquals(place, game.getPlace());
        assertEquals(myLegend, game.getMyLegend());
        assertEquals(weapon1, game.getWeapon1());
        assertEquals(weapon2, game.getWeapon2());
        assertEquals(shieldLevel, game.getShieldLevel());
        assertEquals(myDamage, game.getMyDamage());
        assertEquals(squadLegend1, game.getSquadLegend1());
        assertEquals(squadLegend2, game.getSquadLegend2());
        assertEquals(myKill, game.getMyKill());
        assertEquals(squadKill, game.getSquadKill());
        assertEquals(squadDamage, game.getSquadDamage());
        assertEquals(numberOfDeath, game.getNumberOfDeath());
    }
}

package persistence;

import model.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
    JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
        GameList gl = reader.read();
    } catch (IOException e) {
        // pass
    }
}
    @Test
    void testReaderEmptyGameList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGameList.json");
        try {
            GameList gl = reader.read();
            assertEquals(0, gl.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGameList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGameList.json");
        try {
            GameList gl = reader.read();
            List<Game> games = gl.getGames();
            assertEquals(2, games.size());
            checkGame("World's Edge", 20, "Octane", "Flatline", "R99",
                    1, 0, "Ash", "Horizon", 0, 0,
                    0, 1, games.get(0));
            checkGame("Broken Moon", 9, "Wraith", "R-301","Nemesis",
                    3,1233,"Bloodhound","Lifeline",3,5,
                    3189,1, games.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}


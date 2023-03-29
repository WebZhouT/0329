package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameList implements Writable {
    private LinkedList<Game> games;

    public GameList() {
        games = new LinkedList<>();
    }

    //EFFECTS: print all the game in the gameList
    public String accessAll() {
        List<String> list = new ArrayList<>();
        for (Game game : games) {
            String item = "[Id: " + game.getGameId()
                    + ", Map: " + game.getMap()
                    + ", Place: " + game.getPlace()
                    + ", My Legend: " + game.getMyLegend()
                    + ", Weapon 1: " + game.getWeapon1()
                    + ", Weapon 2: " + game.getWeapon2()
                    + ", Shield Level: " + game.getShieldLevel()
                    + ", My Damage: " + game.getMyDamage()
                    + ", Squad Legend 1: " + game.getSquadLegend1()
                    + ", Squad Legend 2: " + game.getSquadLegend2()
                    + ", My Kill: " + game.getMyKill()
                    + ", Squad Kill: " + game.getSquadKill()
                    + ", Squad Damage: " + game.getSquadDamage()
                    + ", My Number of Death: " + game.getNumberOfDeath() + "]";
            list.add(item);
        }
        return list.toString();
    }

    /*
     *EFFECTS: returns an unmodifiable list of games from the gamelist
     */
    public List<Game> getGames() {
        return Collections.unmodifiableList(games);
    }

    /*
     * MODIFIES: this
     * EFFECTS: add a new game record object in the list
     */
    public void addGame(Game g) {
        games.add(g);
    }

    /*
     * REQUIRES: gameId >= 1
     * EFFECTS: return a string of the game record with the given gameId
     */
    public boolean accessGameRecord(int gameId) {
        for (Game game : games) {
            if (game.getGameId() == gameId) {
                return true;
            }
        }
        return false;
    }

    /*
     * REQUIRES: list of games is not empty
     * EFFECTS: remove the game record with the game id provided, if it can be found, print true, else false
     */
    public boolean deleteGame(int gameId) {
        for (Game game : games) {
            if (game.getGameId() == gameId) {
                games.remove(game);
                return true;
            }
        }
        return false;
    }

    /*
     * EFFECTS: create a list of game records with selected map
     */
    public LinkedList<Game> accessMapRecord(String map) {
        LinkedList<Game> temp = new LinkedList<>();
        for (Game game: games) {
            if (map.equals(game.getMap())) {
                temp.add(game);
            }
        }
        return temp;
    }

    /*
     * EFFECTS: create a list of game records with selected legend
     */

    public LinkedList<Game> accessLegendRecord(String myLegend) {
        LinkedList<Game> temp = new LinkedList<>();
        for (Game game: games) {
            if (myLegend.equals(game.getMyLegend())) {
                temp.add(game);
            }
        }
        return temp;
    }

    /*
     * EFFECTS: create a list of game records with selected weapon
     */
    public LinkedList accessWeaponRecord(String weapon) {
        LinkedList<Game> temp = new LinkedList<>();
        for (Game game: games) {
            if ((weapon.equals(game.getWeapon1())) || (weapon.equals(game.getWeapon2()))) {
                temp.add(game);
            }
        }
        return temp;
    }

    /*
     * EFFECTS: create a list of game records with filtered place
     */
    public LinkedList accessTopRecord(int idealPlace) {
        LinkedList<Game> temp = new LinkedList<>();
        for (Game game: games) {
            if (game.getPlace() <= idealPlace) {
                temp.add(game);
            }
        }
        return temp;
    }


    //EFFECT: return the number of games recorded in the history
    public int length() {
        return games.size();
    }

    //EFFECT: return a boolean: true if the list is empty, false otherwise
    public boolean isEmpty() {
        if (games.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("games", gamesToJson());
        return json;
    }

    // EFFECTS: returns things in this game list as a JSON array
    private JSONArray gamesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Game g : games) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }


}

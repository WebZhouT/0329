package model;


import org.json.JSONObject;
import persistence.Writable;

public class Game implements Writable {
    private static int nextGameId = 1;
    private int gameId;  // ID for a specific game
    private String map;  // the name of the game map
    private int place;   // the place user has reached in this game
    private String myLegend;      // the name of the legend the user chose
    private String weapon1;        // the name of the first weapon
    private String weapon2;      // the name of the second weapon
    private int shieldLevel;     // the level of the shield, from 1 to 5
    private int myDamage;        // the amount of damage user made in this game
    private String squadLegend1;  // the name of the legend squadmate1 chose
    private String squadLegend2;  // the name of the legend squadmate2 chose
    private int myKill;           // the number of kill in this game
    private int squadKill;      // the number of the total kill the squad made
    private int squadDamage;    // the amount of the total damage the squad made
    private int numberOfDeath;  // the number of death in this game

    public Game(String map, int place, String myLegend, String weapon1, String weapon2, int shieldLevel,
                int myDamage, String squadLegend1, String squadLegend2, int myKill, int squadKill,
                int squadDamage, int numberOfDeath) {
        gameId = nextGameId++;
        this.map = map;
        this.place = place;
        this.myLegend = myLegend;
        this.weapon1 = weapon1;
        this.weapon2 = weapon2;
        this.shieldLevel = shieldLevel;
        this.myDamage = myDamage;
        this.squadLegend1 = squadLegend1;
        this.squadLegend2 = squadLegend2;
        this.myKill = myKill;
        this.squadKill = squadKill;
        this.squadDamage = squadDamage;
        this.numberOfDeath = numberOfDeath;
    }

    public Game(String map, int place, String myLegend, String weapon1, String weapon2, int shieldLevel,
                int myDamage, String squadLegend1, String squadLegend2, int myKill, int squadKill,
                int squadDamage, int numberOfDeath, int id) {
        this(map, place, myLegend, weapon1, weapon2, shieldLevel, myDamage, squadLegend1, squadLegend2,
                myKill, squadKill, squadDamage, numberOfDeath);
        gameId = id;
        nextGameId = id + 1;
    }


    public int getGameId() {
        return gameId;
    }

    public String getMap() {
        return map;
    }

    public int getPlace() {
        return place;
    }

    public String getMyLegend() {
        return myLegend;
    }

    public String getWeapon1() {
        return weapon1;
    }

    public String getWeapon2() {
        return weapon2;
    }

    public int getShieldLevel() {
        return shieldLevel;
    }

    public int getMyDamage() {
        return myDamage;
    }

    public String getSquadLegend1() {
        return squadLegend1;
    }

    public String getSquadLegend2() {
        return squadLegend2;
    }

    public int getMyKill() {
        return myKill;
    }

    public int getSquadKill() {
        return squadKill;
    }

    public int getSquadDamage() {
        return squadDamage;
    }

    public int getNumberOfDeath() {
        return numberOfDeath;
    }

    @Override
    // EFFECTS: make the game record into the form of string
    public String toString() {
        return "Game{"
                + "gameId=" + gameId
                + ", map='" + map + '\''
                + ", place=" + place
                + ", myLegend='" + myLegend + '\''
                + ", weapon1='" + weapon1 + '\''
                + ", weapon2='" + weapon2 + '\''
                + ", shieldLevel=" + shieldLevel
                + ", myDamage=" + myDamage
                + ", squadLegend1='" + squadLegend1 + '\''
                + ", squadLegend2='" + squadLegend2 + '\''
                + ", myKill=" + myKill
                + ", squadKill=" + squadKill
                + ", squadDamage=" + squadDamage
                + ", numberOfDeath=" + numberOfDeath
                + '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("map", map);
        json.put("place", place);
        json.put("myLegend", myLegend);
        json.put("weapon1", weapon1);
        json.put("weapon2", weapon2);
        json.put("shieldLevel", shieldLevel);
        json.put("myDamage", myDamage);
        json.put("squadLegend1", squadLegend1);
        json.put("squadLegend2", squadLegend2);
        json.put("myKill", myKill);
        json.put("squadKill", squadKill);
        json.put("squadDamage", squadDamage);
        json.put("numberOfDeath", numberOfDeath);
        return json;
    }
}

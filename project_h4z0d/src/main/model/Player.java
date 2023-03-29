package model;


public class Player {
    private static int nextPlayerId = 1;
    private int playerId;                 // bot-user id
    private String name;                  // bot-user name
    private int totalDeath;               // total times of Death in Apex Legends
    private int totalKillMade;            // total number of kills the user has made in Apex Legends
    private double kd;                    // kill to death ratio

    /*
     * REQUIRES: name should be a string longer than 0, totalDeath >= 0, totalKillMade >= 0;
     * EFFECTS: name of the player is set to name; player account id is a unique positive integer
     *          between different accounts; if totalDeath > 0 then kd is the outcome of totalKillMade
     *          divided by totalDeath, otherwise kd is zero.
     */
    public Player(String name, int totalDeath, int totalKillMade) {
        playerId = nextPlayerId++;
        this.name = name;
        this.totalDeath = totalDeath;
        this.totalKillMade = totalKillMade;
        if (totalDeath > 0) {
            kd = totalKillMade / totalDeath;
        } else {
            kd = 0;
        }
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public int getTotalDeath() {
        return totalDeath;
    }

    public int getTotalKillMade() {
        return totalKillMade;
    }

    public double getKd() {
        return kd;
    }

    //EFFECTS: return all the variable in a player with the form of a string
    public String viewPlayer() {
        return "Id: " + getPlayerId() + ", Name: " + getName()
                + ", Total Death: " + getTotalDeath() + ", Total Kill: "
                + getTotalKillMade() + ", Kd: " + getKd();
    }

    /*
     * REQUIRES: numberOfDeath >= 0, totalKillMade >= 0,
     * MODIFIES: this
     * EFFECTS: total death and total kill made are updated, kd is adjusted after the calculation
     */
    public void updatePlayer(Game game) {
        totalDeath = totalDeath + game.getNumberOfDeath();
        totalKillMade = totalKillMade + game.getMyKill();
        if (game.getNumberOfDeath() == 0) {
            if (totalDeath == 0) {
                kd = 0;
            } else {
                kd = totalKillMade / totalDeath;
            }
        } else {
            this.kd = totalKillMade / totalDeath;
        }
    }


}

package Player;

import Player.Droid.Droid;

public class Player {


    private String name;
    private Droid[] droids;

    public Player(String name, Droid[] droids) {
        this.name = name;
        this.droids = droids;
    }

    public Droid[] getDroids() {
        return droids;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

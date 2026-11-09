package utils.goals;

import Essential.Player;

public class ConquerTilesGoal implements Goal{
    
    private final Player player;
    private final int numberOfTiles;

    public ConquerTilesGoal(Player player, int nbTiles) {
        this.player = player;
        this.numberOfTiles = nbTiles;
    }

    public boolean isAchieved() {
        return this.player.getBuildings().size() >= this.numberOfTiles;
    }

    public String toString() {
        return "Conquérir " + this.numberOfTiles + " tuiles";
    }

}

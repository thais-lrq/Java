package utils.goals;

import Essential.Player;
import utils.Island;

public class InvadeIslandGoal implements Goal{

    static final int MIN_BUILDING = 5;
    
    private final Player player;

    public InvadeIslandGoal(Player player) {
        this.player = player;
    }

    public boolean isAchieved() {
        for (Island island : this.player.getOccupiedIslands()) {
            if ((island.numberOfBuildings(this.player) >= MIN_BUILDING) && (island.getDominantPlayer() == this.player)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "Envahir une île (Avoir au moins " + MIN_BUILDING +" bâtiments sur une île et être le joueur dominant sur celle-ci)";
    }
}

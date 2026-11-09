package utils.goals;

import Buildings.Building;
import Buildings.ProducingBuilding;
import Essential.Player;

public class ScoreGoal implements Goal{
    
    private final Player player;
    private final int score;

    public ScoreGoal(Player player, int score) {
        this.player = player;
        this.score = score;
    }

    public boolean isAchieved() {
        int result = 0;
        for (Building building : this.player.getBuildings()) {
            if(building instanceof ProducingBuilding){
                result = result + building.getDimension();
            }
        }

        if (result >= this.score) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "Avoir un score minimum de " + this.score + " (ferme = 1 point, exploitation = 2 points)";
    }
}

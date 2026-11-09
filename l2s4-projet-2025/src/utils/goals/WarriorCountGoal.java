package utils.goals;

import Essential.Player;
import Buildings.*;

public class WarriorCountGoal implements Goal {
    
    private final Player player;
    private final int numberOfWarriors;

    public WarriorCountGoal(Player player, int nbWarriors) {
        this.player = player;
        this.numberOfWarriors = nbWarriors;
    }

    public boolean isAchieved() {
        int result = 0;
        for (Building building : this.player.getBuildings()) {
            if(building instanceof ProducingBuilding){
                result = result + building.getDimension();
            }
        }

        if (result >= this.numberOfWarriors) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "Avoir un nombre de guerrier placé dans ses armées et camps d'au moins " + this.numberOfWarriors ;
    }

}

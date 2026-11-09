package Actions;

import java.util.List;

import Essential.Player;
import Ressources.*;
import utils.listchooser.ListChooser;


/**
 * This class lets the player steal a resource from all other players.
 * 
 * The player needs at least one "Thief" to use this action.
 */
public class Steal extends Action{

    // List of all resources that can be stolen
    private static final List<Ressource> ALL_RESSOURCES = List.of(new Ore(), new Sheep(), new Wheat(), new Wood());

    /**
     * Creates a new Steal action
     *
     * @param player the player who will steal resources.
     * @param listChooser helps the player choose a resource.
     */
    public Steal(Player player, ListChooser<Object> listChooser) {
        super(player, listChooser);
    }

    /**
     * Checks if the player can steal
     * The player must have at least 1 thief
     *
     * @return true if the player can steal, false otherwise
     */
    public boolean canExecute() {
        return (this.player.getDataSheet().getOrDefault("Thief", 0) > 0);
    }

    /**
     * Runs the steal action
     * The player chooses a resource (wood, ore, sheep, or wheat)  then steals all of that resource from the other players.
     */
    public void execute() {
        System.out.println("Vous avez choisi de voler le stock d'une ressource au autres joueur.\n");
            Ressource ressourceChosen = (Ressource)this.lc.choose("Quelle ressource voulez-vous voler aux autres joueurs ? ", ALL_RESSOURCES);
            
            process(ressourceChosen);
        }

    
    /**
     * Steals the selected resource from all other players
     *
     * @param ressource The resource to steal (e.g. Wood, Ore)
     */
    public void process(Ressource ressource) {
        if ((ressource == null)) {
            System.out.println("Vous venez d'annuler votre action");
            return;
        }

        int numberRessource = 0;
        for (Player otherPlayer : this.player.getGame().getAllPlayers()) {
            if (this.player != otherPlayer) {
                numberRessource = numberRessource + otherPlayer.stealingRessources(ressource);
            }
        }
        this.player.addResource(ressource.getName(), numberRessource);
        System.out.println("Vous avez volé " + numberRessource + " " + ressource.getName() + " aux autres joueurs");

    }

    /** 
     * returns string to represent the action 
     * 
     * @return the representation of the action
     */
    public String toString() {
        return "Voler le stock d'une ressource à chaque joueurs (1 voleur)";
    }
}

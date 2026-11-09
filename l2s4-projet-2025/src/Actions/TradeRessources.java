package Actions;

import Essential.Player;
import Ressources.Ore;
import Ressources.Ressource;
import Ressources.Sheep;
import Ressources.Wheat;
import Ressources.Wood;
import java.util.List;
import utils.listchooser.ListChooser;

/**
 * Allows a player to trade 3 identical resources for another resource.
 * This action is free.
 */
public class TradeRessources extends CostAction {

    /**
     * Constructor for the TradeRessources action.
     * 
     * @param player The player who will perform the action.
     * @param listChooser The listChooser used for resource selection.
     */
    public TradeRessources(Player player, ListChooser<Object> listChooser) {
        super(player, listChooser);
    }

    /**
     * Checks if the player can execute the trade.
     * The player must have at least 3 of the same resource to trade.
     *
     * @return true if the player has at least 3 of a resource, false otherwise.
     */
    public boolean canExecute() {
        for (String ressourceName : Ressource.ALL_RESSOURCES) {
            if (player.hasResource(ressourceName, 3)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Executes the trade action.
     * Prompts the player to choose resources and performs the trade if possible.
     * 
     * @throws Exception If there is an issue during resource selection or trade.
     */
    public void execute() throws Exception {
        if (!canExecute()) {
            System.out.println("Vous n'avez pas assez de ressources pour échanger");
            return;
        }

        List<String> availableResources = Ressource.ALL_RESSOURCES.stream()
                .filter(res -> player.hasResource(res, 2))
                .toList();

        String resourceToGive = (String)this.lc.choose("Choisissez la ressource à échanger (vous devez en avoir au moins 3) :", availableResources);

        List<String> allResources = Ressource.ALL_RESSOURCES.stream()
                .filter(res -> !res.equals(resourceToGive))
                .toList();

        String resourceToReceive = (String)this.lc.choose("Choisissez la ressource que vous souhaitez recevoir :", allResources);

        Ressource ressourceDonnee = getResourceInstance(resourceToGive);
        Ressource ressourceRecue = getResourceInstance(resourceToReceive);

        process(ressourceDonnee, ressourceRecue);
    }
    
    /**
     * Executes the trade process: removes 3 of the given resource from the player's inventory
     * and adds 1 of the received resource to the player's inventory.
     * 
     * @param ressourceDonnee The resource being given away.
     * @param ressourceRecue The resource being received.
     */
    public void process(Ressource ressourceDonnee, Ressource ressourceRecue) {
        if (canExecute()) {
            player.removeResource(ressourceDonnee.getName(), 3);
            player.addResource(ressourceRecue.getName(), 1);
            System.out.println(player.getName() + " a échangé 3 " + ressourceDonnee.getName() +
                               " contre 1 " + ressourceRecue.getName() + " !");
        } else {
            System.out.println("Échange impossible : " + player.getName() + " ne possède pas assez de " +
                               ressourceDonnee.getName() + ".");
        }
    }

    /** 
     * @return A string representation of the trade.
     */
    public String toString() {
        return "TradeResources (Échange 3 ressources contre 1 autre ressource)";
    }

	@Override
	/**
     * Returns the cost of the trade (free).
     *
     * @return The cost (always 0 for this action).
     */
	public int cost() {
		return 0;
	}

    /**
     * Gets the instance of a given resource.
     * 
     * @param resourceName The name of the resource to create.
     * @return The corresponding resource instance.
     */
    public static Ressource getResourceInstance(String resourceName) {
        return switch (resourceName) {
            case "Wood" -> new Wood();
            case "Ore" -> new Ore();
            case "Sheep" -> new Sheep();
            case "Wheat" -> new Wheat();
            default -> null; 
        };
    }
}

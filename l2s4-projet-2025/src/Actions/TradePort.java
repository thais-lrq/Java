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
 * Allows a player to trade resources via a port.
 * This action is free.
 */
public class TradePort extends CostAction {
    
    /**
     * Constructor for trade with Port 
     * 
     * @param player the player
     * @param listChooser the listChooser
     */
	public TradePort(Player player, ListChooser<Object> listChooser) {
        super(player, listChooser);
    }

    /**
    * returns True if the player can trade on the port
    *
    * @return true if the player has the needed resource, false otherwise
    *
    */
    public boolean canExecute() {
        if ( player.hasPort()){
            for (String ressourceName : Ressource.ALL_RESSOURCES) {
                if (player.hasResource(ressourceName, 2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * execution in order to trade in a port
     * 
     * @throws Exception if not ressources 
     */
    public void execute() throws Exception {
        
        if (!canExecute()) {
            System.out.println("Vous n'avez pas assez de ressources pour échanger ou vous n'avez pas encore construit de port");
            return;
        }

        System.out.println("Choisissez la ressource à échanger (vous devez en avoir au moins 2) :");
        List<String> availableResources = Ressource.ALL_RESSOURCES.stream()
                .filter(res -> player.hasResource(res, 2))
                .toList();

        String resourceToGive = (String)this.lc.choose("Choisissez la ressource à échanger (vous devez en avoir au moins 2) :", availableResources);

        List<String> allResources = Ressource.ALL_RESSOURCES.stream()
                .filter(res -> !res.equals(resourceToGive))
                .toList();

        String resourceToReceive = (String)this.lc.choose("Choisissez la ressource que vous souhaitez recevoir :", allResources);

        Ressource ressourceDonnee = getResourceInstance(resourceToGive);
        Ressource ressourceRecue = getResourceInstance(resourceToReceive);

        process(ressourceDonnee, ressourceRecue);
    }

    
    /** 
     * execute the trade
     * 
     * @param ressourceDonnee the given resource we want to trade
     * @param ressourceRecue the resource the player is going to have instead of ressourceDonnee
     */
    public void process(Ressource ressourceDonnee, Ressource ressourceRecue) {
        if (canExecute()) {
            player.removeResource(ressourceDonnee.getName(), 2);
            player.addResource(ressourceRecue.getName(), 1);
            System.out.println(player.getName() + " a échangé 2 " + ressourceDonnee.getName() +
                               " contre 1 " + ressourceRecue.getName() + " via un port !");
        } else {
            System.out.println("Échange impossible : " + player.getName() + " ne possède pas assez de " +
                               ressourceDonnee.getName() + ".");
        }
    	player.removeResource(ressourceDonnee.getName(), 1);
        player.addResource(ressourceRecue.getName(), 1);
        System.out.println(player.getName() + " a échangé " + ressourceDonnee.getName() +
                           " contre " + ressourceRecue.getName() + " via un port !");
    }
    
    /** 
     * Returns the cost in order to do the trade (free)
     * @return cost the cost in order to do the trade 
     */
    public int cost() {
        return 0;
    }
    /** 
     * Returns the sumary of the trade
     * @return the trade 
     */
    public String toString() {
        return "TradePort (Échange 2 ressource contre 1 autre ressource)";
    }

    /**
     * Gets the instance of a resource
     * 
     * @param resourceName the resource 
     * @return Ressource the ressource
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

    
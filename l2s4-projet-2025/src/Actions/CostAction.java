package Actions;

import Essential.Player;
import utils.listchooser.ListChooser;

/**
 * Represents all of the action who need a cost
 */
public abstract class CostAction extends Action {

    /**
     * Constructor for an action which is worthy
     * 
     * @param player the player
     * @param listChooser the listChooser
     */
    public CostAction(Player player, ListChooser<Object> listChooser) {
        super(player, listChooser);
    }

    /** 
     * Returns the cost of the choosen action
     * 
     * @return int the cost of the action
     */
    public abstract int cost();

    @Override
    /**
     * returns true if the action can be executed
     * 
     * @return true if the action can be executed, false otherwise
     */
    public abstract boolean canExecute();

    /**
     * execute the program if the player has enough ressources and if the conditions in order to do the action are checked
     * 
     * @throws Exception if there is a problem
     */
    @Override
    public abstract void execute() throws Exception;
}


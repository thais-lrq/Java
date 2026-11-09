package Actions;

import Essential.Player;
import utils.listchooser.ListChooser;

/**
 * Represents an action in the game.
 */
public abstract class Action {

    /** the player */
	protected Player player;
    /** The listChooser */
    protected ListChooser<Object> lc;
	
	/**
     * Builds an Action with a given player
     * 
     * @param player the player
     * @param listChooser the listChooser
     */
	public Action(Player player, ListChooser<Object> listChooser) {
        this.player = player;
        this.lc = listChooser;
    }
	
    /**
     * Returns True if the action can be executed
     * 
     * @return True if the action can be executed, false otherwise
     */
    public abstract boolean canExecute();

    /** 
     * Execute an action 
     * @throws Exception if the action can't be executed
     */
    public abstract void execute() throws Exception;

    /**
     * Returns the cost for the action to be exected
     * 
     * @return cost the cost of the action
     */
    public abstract String toString() ;

}
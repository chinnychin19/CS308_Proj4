package game.controller.state;

import game.controller.Input;

public interface State {
	
	/**
	 * paint the screen for the input state
	 */
	public void paint ();
	
	/**
	 * 
	 * @param input the key entered
	 */
    public void act (Input input);
    

}

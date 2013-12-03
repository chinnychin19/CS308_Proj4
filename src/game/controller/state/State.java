package game.controller.state;

import game.controller.Input;

public interface State {
	//TODO: COMMENT
	
	public void paint ();
	
	
    public void act (Input input);
    

}

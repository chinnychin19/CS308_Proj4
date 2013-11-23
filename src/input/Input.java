package input;

import constants.Constants;
import game.model.GameModel;
import game.view.GameView;

/**
 * Organizes the statuses of all the directions and key presses so that the model can stay updated.  Calls can be made to see if 
 * a key is pressed or to set a key to being pressed
 * @author rtoussaint
 *
 */

public class Input {
	private boolean[] myInputs;
	private final int NUM_INPUTS = 7;
	
	
	public static final int INDEX_UP = 0,
            INDEX_LEFT = 1,
            INDEX_DOWN = 2,
            INDEX_RIGHT = 3,
            INDEX_INTERACT = 4,
            INDEX_MENU = 5,
			INDEX_KEY_PRESSED = 6;
	
	public Input() {
		 myInputs = new boolean[NUM_INPUTS];
    }
	/**
	 * Checks to see if the up key is pressed
	 * @return position of key
	 */
	public boolean isKeyUpPressed(){
		return myInputs[INDEX_UP];
	}
	/**
	 * Checks to see if the down key is pressed
	 * @return position of key
	 */
	public boolean isKeyDownPressed(){
		return myInputs[INDEX_DOWN];
	}
	/**
	 * Checks to see if the left key is pressed
	 * @return position of key
	 */
	public boolean isKeyLeftPressed(){
		return myInputs[INDEX_LEFT];
	}
	/**
	 * Checks to see if the right key is pressed
	 * @return position of key
	 */
	public boolean isKeyRightPressed(){
		return myInputs[INDEX_RIGHT];
	}
	/**
	 * Checks to see if the interact key is pressed
	 * @return position of key
	 */
	public boolean isKeyInteractPressed(){
		return myInputs[INDEX_INTERACT];
	}
	/**
	 * Checks to see if the menu key is pressed
	 * @return position of key
	 */
	public boolean isKeyMenuPressed(){
		return myInputs[INDEX_MENU];
	}
	/**
	 * Checks to see if any of the direction keys are pressed
	 * @return true if any of the directions are pressed
	 */
	public boolean isDirectionPressed(){
		for(int i=0; i < Constants.NUM_OF_DIRECTIONS; i++){
			if(myInputs[i]){
				return myInputs[i];
			}
		}
		return false;
	}
	/**
	 * Checks to see if any key is being pressed at the current moment
	 * @return position of key
	 */
	public boolean isKeyCurrentlyPressed(){
		return myInputs[INDEX_KEY_PRESSED];
	}
	/**
	 * Checks to see if the current key has been released
	 * @return position of key
	 */
	public boolean isKeyReleased(){
		return !isKeyCurrentlyPressed();
	}
	/**
	 * Set a specific key to the 'pressed state'
	 * @param val index of which key to set
	 */
	public void setKeyPressed(int val){
		myInputs[val] = true;
	}
	/**
	 * Set a specific key to the 'unpressed state'
	 * @param val index of which key to set
	 */
	public void setKeyUnpressed(int val){
		myInputs[val] = false;
	}
	/**
	 * Set the 'Current' key to the 'pressed state' -- unlike other set methods, which require a key index to be specified, this
	 * method makes a note that a key is currently pressed.  This key could be anything, all this method cares about is that some
	 * key is currently pressed.  As a result, it doesn't know which key is pressed, just that a key IS pressed
	 */
	public void setKeyCurrentlyPressed(){
		myInputs[INDEX_KEY_PRESSED] = true;
	}
	/**
	 * Set the 'Current' key to unreleased.  'Current' key is its own key, that keeps track if any key is pressed at the current 
	 * moment.  This method does not require an index like other set methods, rather it just keeps track if any key is pressed, 
	 * not a specific key.   
	 */
	public void setKeyCurrentlyReleased(){
		myInputs[INDEX_KEY_PRESSED] = false;
	}
	/**
	 * Reset all the inputs to the original states
	 */
	public void resetAllInputs(){
		for(int i = 0; i < myInputs.length; i++){
			myInputs[i] = false;
		}
	}
	
}

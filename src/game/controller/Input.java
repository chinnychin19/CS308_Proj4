package game.controller;

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
	private boolean[] interactionValues;
	
	public enum Index{
		UP(0), LEFT(1), DOWN(2), RIGHT(3), INTERACT(4), MENU(5), CURRENTLY_PRESSED(6);
		private int index;
		
		private Index(int val){
			index = val;
		}
		public int getValue(){
			return index;
		}
	}
          
	public Input() {
		 interactionValues = new boolean[Constants.NUM_INPUTS];
		 for(int i = 0; i < interactionValues.length; i++){
			 interactionValues[i] = false;
		 }
   }
	/**
	 * Checks to see if the up key is pressed
	 * @return position of key
	 */
	public boolean isKeyUpPressed(){
		return interactionValues[Index.UP.getValue()];
	}
	/**
	 * Checks to see if the down key is pressed
	 * @return position of key
	 */
	public boolean isKeyDownPressed(){
		return interactionValues[Index.DOWN.getValue()];
	}
	/**
	 * Checks to see if the left key is pressed
	 * @return position of key
	 */
	public boolean isKeyLeftPressed(){
		return interactionValues[Index.LEFT.getValue()];
	}
	/**
	 * Checks to see if the right key is pressed
	 * @return position of key
	 */
	public boolean isKeyRightPressed(){
		return interactionValues[Index.RIGHT.getValue()];
	}
	/**
	 * Checks to see if the interact key is pressed
	 * @return position of key
	 */
	public boolean isKeyInteractPressed(){
		return interactionValues[Index.INTERACT.getValue()];
	}
	/**
	 * Checks to see if the menu key is pressed
	 * @return position of key
	 */
	public boolean isKeyMenuPressed(){
		return interactionValues[Index.MENU.getValue()];
	}
	/**
	 * Checks to see if any of the direction keys are pressed
	 * @return true if any of the directions are pressed
	 */
	public boolean isDirectionPressed(){
		for(int i=0; i < Constants.NUM_OF_DIRECTIONS; i++){
			if(interactionValues[i]){
				return interactionValues[i];
			}
		}
		return false;
	}
	/**
	 * Checks to see if any key is being pressed at the current moment
	 * @return position of key
	 */
	public boolean isKeyCurrentlyPressed(){
		return interactionValues[Index.CURRENTLY_PRESSED.getValue()];
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
		interactionValues[val] = true;
	}
	/**
	 * Set a specific key to the 'unpressed state'
	 * @param val index of which key to set
	 */
	public void setKeyUnpressed(int val){
		interactionValues[val] = false;
	}
	/**
	 * Set the 'Current' key to the 'pressed state' -- unlike other set methods, which require a key index to be specified, this
	 * method makes a note that a key is currently pressed.  This key could be anything, all this method cares about is that some
	 * key is currently pressed.  As a result, it doesn't know which key is pressed, just that a key IS pressed
	 */
	public void setKeyCurrentlyPressed(){
		interactionValues[Index.CURRENTLY_PRESSED.getValue()] = true;
	}
	/**
	 * Set the 'Current' key to unreleased.  'Current' key is its own key, that keeps track if any key is pressed at the current 
	 * moment.  This method does not require an index like other set methods, rather it just keeps track if any key is pressed, 
	 * not a specific key.   
	 */
	public void setKeyCurrentlyReleased(){
		interactionValues[Index.CURRENTLY_PRESSED.getValue()] = false;
	}
	/**
	 * Reset all the inputs to the original states
	 */
	public void resetAllInputs(){
		for(int i = 0; i < interactionValues.length; i++){
			interactionValues[i] = false;
		}
	}
	
	public boolean[] getIndexValues(){
		boolean[] copyOfOriginal = interactionValues.clone();
		return copyOfOriginal;
	}
	
}

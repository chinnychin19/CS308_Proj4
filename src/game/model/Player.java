package game.model;

import java.util.Collection;

public abstract class Player extends AbstractViewableObject {
	Collection<Monster> myParty;
	Collection<Item> myItem;
	
	@Override
	void paint() {
	}
	
	public abstract Collection<Monster> getParty();
	
	public abstract void move();
	
	public abstract boolean canMove();

}
// view only cares about objects currently on screen
// model returns currently viewablable objects
// 
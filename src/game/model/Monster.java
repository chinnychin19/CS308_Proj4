package game.model;

import game.model.attack.AbstractAttack;

import java.util.Collection;

public abstract class Monster extends AbstractViewableObject {
	private Collection<AbstractAttack> myAttacks;
	private Stat myStat;
	public Collection<AbstractAttack> getAttacks() {
		return myAttacks;
	}
	
	public abstract Stat getStat();
	
}

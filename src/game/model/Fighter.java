package game.model;

import java.util.List;

/**
 * List of fighters associated with a Monster's party.
 * @author tylernisonoff
 *
 */
public interface Fighter {
    public List<Monster> getParty();
}

package game.controller;

import java.util.Collection;

import game.model.AbstractViewableObject;
import game.model.Model;

public abstract class WorldController extends AbstractController{
        public abstract Collection<AbstractViewableObject> getViewableObjects();
}

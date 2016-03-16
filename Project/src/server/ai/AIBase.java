package server.ai;

import java.util.HashSet;

import server.facade.*;
import server.util.ServerPlayer;
import shared.definitions.CatanColor;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;

public abstract class AIBase extends ServerPlayer {

	protected IAIFacade facade;
	
	
	
	protected AIBase(String name, CatanColor color, int playerID, int playerIndex, IAIFacade facade) {
		super(name, color, playerID, playerIndex);
		this.facade = facade;
	}
	
	
	public abstract HashSet<ResourceType> getPreferredResources();
	public abstract HashSet<AIActionPreference> getPreferredActions();
	
	public void doTurn() {
		facade.rollNumber();
		
	}
	
	
	public void doDiscarding() {
		ResourceList resources = this.getResources();
		HashSet<ResourceType> preferred = getPreferredResources();
		int numToDiscard = resources.total() >= 7 ?  resources.total() / 2 : 0;
		if (numToDiscard == 0) return;
		
		if (resources.getBrick() > 0 && !preferred.contains(ResourceType.BRICK)) {
			while (numToDiscard > 0 && resources.getBrick() > 0) {
				resources.setBrick(resources.getBrick() - 1);
				numToDiscard--;
			}	
		}
		if (resources.getWood() > 0 && !preferred.contains(ResourceType.WOOD)) {	
			while (numToDiscard > 0 && resources.getWood() > 0) {
				resources.setWood(resources.getWood() - 1);
				numToDiscard--;
			}	
		}
		if (resources.getSheep() > 0 && !preferred.contains(ResourceType.SHEEP)) {	
			while (numToDiscard > 0 && resources.getSheep() > 0) {
				resources.setSheep(resources.getSheep() - 1);
				numToDiscard--;
			}	
		}
		if (resources.getOre() > 0 && !preferred.contains(ResourceType.ORE)) {	
			while (numToDiscard > 0 && resources.getOre() > 0) {
				resources.setOre(resources.getOre() - 1);
				numToDiscard--;
			}	
		}
		if (resources.getWheat() > 0 && !preferred.contains(ResourceType.WHEAT)) {	
			while (numToDiscard > 0 && resources.getWheat() > 0) {
				resources.setWheat(resources.getWheat() - 1);
				numToDiscard--;
			}	
		}
		
		while (numToDiscard > 0 && resources.getBrick() > 0) {
			resources.setBrick(resources.getBrick() - 1);
			numToDiscard--;
		}
		while (numToDiscard > 0 && resources.getWood() > 0) {
			resources.setWood(resources.getWood() - 1);
			numToDiscard--;
		}
		while (numToDiscard > 0 && resources.getSheep() > 0) {
			resources.setSheep(resources.getSheep() - 1);
			numToDiscard--;
		}
		while (numToDiscard > 0 && resources.getOre() > 0) {
			resources.setOre(resources.getOre() - 1);
			numToDiscard--;
		}
		while (numToDiscard > 0 && resources.getWheat() > 0) {
			resources.setWheat(resources.getWheat() - 1);
			numToDiscard--;
		}
		
		
		
		
	}
	
}

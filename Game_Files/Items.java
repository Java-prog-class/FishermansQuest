package Game;

public class Items {
	
	String name;
	int health = 0;
	int attackModifier = 0;
	int defenseModifier = 0;	
	boolean isCarryable = true;
	boolean inInventory = false;
	//boolean isContainer = false;
	
	Items() {};  //empty constructor. Only used in loops
	Items(String name, int h, int am, int dm, boolean carry, boolean mine) {
		this.name = name;
		this.health = h;
		attackModifier = am;
		defenseModifier = dm;
		isCarryable = carry;
		inInventory = mine;
	}
}

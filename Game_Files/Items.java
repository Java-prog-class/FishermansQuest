package Game;

public class Items {
	
	String name;
	int health;
	int attackModifier;
	int defenseModifier;
	boolean isOpen;  //?????
	boolean isCarryable;
	
	Items(String name, int h, int am, int dm, boolean carry) {
		this.name = name;
		this.health = health;
		attackModifier = am;
		defenseModifier = dm;
		isCarryable = carry;
	}
	//???? activate
	/*
	 type: food, weapon, armour, keys, misc
	 Food
	 	name = Sandwich
	 	health = 10; increase health by 10
	 	
	 Weapons
	 	name = Damascus Sword
	 	attackModifier= 15
	 Armour
	  	name = chain mail
	  	defenseModifier = 30
	 Key
	 	name = crypt key
	 	open = which room or item it opens
	 Misc
	 	name = 
	 	carryable = true; //can be carried in inventory. 
	 				False means that it stays in the room
	 	activate = ???
	 */
}

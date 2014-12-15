package Game;

public class Items {
	
	String name;
	int health;
	int attackModifier;
	int defenseModifier;
	Object open;  //?????
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
		
       	//   2-d arrays with the items inside each item category 
	 	 
	    //main array ItemCat 
	 	//	-FoodAndDrink 
 	    //	-Weapons 
	 	//	-Armour 
	 	//	-Keys 
	 	//	-Misc 
	 	 
	String[][] food = new String[0][0];
	 
	String[][] weapons = new String [0][0];
	 	//Inside Weapons(Mostly Found Inside The Crypt) 
	 	//	-Kitchen Knife 
	 	//	-Harpoon Gun 
	 	//	-Damascus Sword 
	 	//	-Battle Axe 
	 	//	(Possible Bow and Arrows?) 
	 	 
	String[][] armour = new String [0][0];
	 	//Inside Armour 
	 	//	-Helmet 
	 	//	-ChainMail 
	 	//  -ChestPlate 
	 	//	-PlateLegs 
	 	//	-Shield 
	 	//	(Anti-Fire Shield?) 
	 	 
	String[][] keys = new String[0][0];
	 	//Inside Keys 
	 	//	-Crypt Key 
	 	//	-Tower Key 
	 	//   Inside Misc 
	 	//	-Books 
	 	//	-GasLamp(Inside Crypt To Burn Remains) 
	 	//	-Lantern 
	 	//	-Dragon Head 


}

package Game;
import java.util.*;

public class Main {

	//global (instance) variables
	ArrayList<Rooms> roomList = new ArrayList<Rooms>();
	ArrayList<Items> itemList = new ArrayList<Items>();

	int currRoomID = 1;
	Rooms currentRoom = new Rooms(0,"","");
	
	public static void main(String[]args){
		new Main();
	}

	Main() {

		boolean playing = true;
		String command = " ";
		Rooms.setupRooms(roomList);
		makeItems();
		updateCurrentRoom(currRoomID);

		//main game loop
		//set to false if the user quit
		while (playing) {
			command = getCommand();
			playing = parseCommand(command);

			//set playing to false if the user is dead
		}		

	}

	void updateCurrentRoom(int n) {
		//check each room for current room id
		for (Rooms r : roomList){
			if (r.getRoomID() == n) {
				currentRoom = r; //set currentRoom to this room
				break;
			}
		}
	}

	String getCommand() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nCurrent location: " + currentRoom.getTitle());
		String text = sc.nextLine();
		if (text.length() == 0) text = "qwerty";
		return text;
	}

	boolean parseCommand(String text) {

		/* What this section does:
		 * 1. remove extra spaces, capitalize the first letter of each word
		 * 2. put the first word into word1 and the second into word2
		 * 3. get rid of the word "Go" (e.g. "go north" becomes "north")
		 */
		text = text.toLowerCase().trim();

		//split text into words
		String words[] = text.split(" ");
		//capitalize first letter of each word now
		for (int i=0; i < words.length; i++) {
			words[i] = capitalizeFirstLetter(words[i]);
		}		
		String word1 = words[0];		
		String word2 = "";
		int numWords = words.length;
		if (numWords > 1) word2 = words[1];
		//pre-parsing: remove GO (also remove "THE")
		if (word1.equals("Go")) word1 = words[1];	//ie. GO NORTH --> NORTH

		
		/** This is the main switch statement that decides what command to do **/
		switch(word1) {
		case "Quit":
			System.out.print("Do you really want to quit the game?");
			return false;

		case "N":
		case "E":
		case "S":
		case "W":
		case "U":
		case "D":
		case "North":
		case "South":
		case "East": 
		case "West":
		case "Up":
		case "Down":
			move(word1.charAt(0));
			break;

		case "Look":
			if (word2.equals("At")) {
				//System.out.println("You are in the "+ currentRoom.getTitle() + ". " + currentRoom.getLongDesc());
				//listItemsinRoom();
				break;
			}else{
				System.out.println(currentRoom.getTitle() + currentRoom.getLongDesc());
				listItemsinRoom();				
			}
			
			break;
		case "Take":
		case "Grab":				
			if (word2.equals("The")) {
				takeItem(minusTwoWords(words));
			} else {
				takeItem(minusOneWord(words));
			}
			break;
			
		case "Check":           //checks your health and armour
			//if (word2.equals("STATS")){
			//	System.out.println(player.toString());
			//}
			break;
			
		case "I":
		case "Inventory":
			for (Items item : itemList){
				if (item.inInventory){
					System.out.println(item.name);
				}
			}
			break;
		case "Open":
		case "Use":
			//opens container
			openContainer(word2);
			break;
		default: 
			System.out.println("Sorry, I don't understand that command.");
		}
		return true;
	}			

	void move(char dir) {
		int newRoom = currentRoom.getExit(dir);
		if (newRoom == 0) {
			System.out.println("You can't go that way!");
			return;
		}
		currRoomID = newRoom;
		updateCurrentRoom(currRoomID);
		System.out.println("You are now at "+ currentRoom.getTitle() + ". " + currentRoom.getDesc());
		listItemsinRoom();
		//start combat here if there is a monster in the room???
		//new Combat(player);
	}

	void listItemsinRoom() {
		if (currentRoom.roomItems.size() <= 0) return; //nothing in the room
		System.out.print("You see a ");
		for (String s : currentRoom.roomItems) {
			System.out.print(s + ", ");
		}
	}

	//itemName can be multiple words
	void takeItem(String itemName){
		
		if (!(currentRoom.roomItems.contains(itemName))) {
			System.out.println("There is no " + itemName + " here.");
			return;
		}

		//the itemList has all of the items created along with their properties
		for (Items item : itemList){
			if (item.name.equals(itemName)){  //you've found the item
				//check the isCarryable property
				if (!(item.isCarryable)) {
					System.out.print("Item is not Carryable!");
					return;
				}
			}			
		}

		currentRoom.roomItems.remove(itemName);

		//add to inventory
		//find item in list, and set inInventory to TRUE
		for (Items item : itemList){
			if (item.name.equals(itemName)){
				item.inInventory = true;
			}
		}
		listItemsinRoom();
	}

	void isCarryable(boolean isCarryable){

	}

	void inInventory(boolean inInventory){

	}
	
	void openContainer(String cname) {		

		/*1. is the container in the room
		  2. is the cname a container?
		    	yes: continue on
		    	no: "you can't open that". Return
		  3. does it require a key? 
		  				no: open the container
						yes: do you have the key?
								yes: open the container
								no: print "You need the right key". return.
		  4. list what is inside the container
		 */

		if (! currentRoom.roomItems.contains(cname)) {			
			System.out.println("There is no " + cname + " here.");
			return;
		}
		//find the item/container in the list of items and store it in the variable "item"
		Items possibleContainer = new Items();
		for(Items z : itemList) {
			if (z.name.equals(cname)) {
				possibleContainer = z;
				break;
			}
		}
		//is the item NOT a container?
		if (!(possibleContainer instanceof Containers)) {
			System.out.println("You can't open that!");
			return;
		}
		Containers container = (Containers)possibleContainer; 

		if (container.requiresKey == "" ) {
			container.isOpen = true;
			System.out.println("You can see what's inside the " + cname + "!");
		} else {
			//see if the key is in the inventory
			for (Items item : itemList){
				if (item.name.equals(container.requiresKey) && item.inInventory){
					System.out.println("You have successfully opened the " + cname);
					System.out.println(container.items.toString());
					return;
				}
			}
			System.out.println("You need the " + container.requiresKey + " to open this.");
		}
		
	}
	
	String capitalizeFirstLetter(String original){
		if(original.length() == 0)
			return original;
		return original.substring(0, 1).toUpperCase() + original.substring(1);
	}
	
	String minusOneWord(String[] w) {
		String s = "";
		for (int i=1; i<w.length; i++) {
			s = s + w[i] + "";
		}
		return s.trim();
	}
	
	String minusTwoWords(String[] w) {
		String s = "";
		for (int i=2; i<w.length; i++) {
			s = s + w[i] + "";
		}
		return s.trim();
	}
	
	//Items(String name, int h, int am, int dm, boolean carry, boolean mine(is it in your inventory?)) {
	void makeItems() {
		//food
		Items z = new Items("Sandwich", 15, 0,0,true, false);		
		itemList.add(z);
		z = new Items("Banana", 5, 0,0,true, false);
		itemList.add(z);
		//potions
		z = new Items("Healing Potion", 50, 0, 0, true, false);
		itemList.add(z);
		z = new Items("Damage/Rage Potion", 0, 80, 0, true, false);
		itemList.add(z);
		// Weapons
		z = new Items("Kitchen knife", 0, 10, 0, true, false);
		itemList.add(z);
		z = new Items("Damascus Sword", 0, 60, 10, true, false);
		itemList.add(z);
		z = new Items("Long Sword", 0, 50, 20, true, false);
		itemList.add(z);
		z = new Items("Mace", 0, 30, 5, true, false);
		itemList.add(z);
		// Armour
		z = new Items("Iron Helmet", 0, 0, 40, true, false);
		itemList.add(z);
		z = new Items("Chest Plate", 0, 0, 40, true, false);
		itemList.add(z);
		z = new Items("Plate Leggings", 0, 0, 40, true, false);
		itemList.add(z);
		z = new Items("Shield", 0, 0, 100, true, false);
		itemList.add(z);
		//Keys
		z = new Items("Cabinet Key", 0, 0, 0, true, false);	    
		itemList.add(z);
		z = new Items("Chest Key", 0, 0, 0, true, false);
		itemList.add(z);
		//Misc
		z = new Items("Gas Lantern", 0, 0, 0, true, false);
		itemList.add(z);
		z = new Items("Stupid Award", 0, 0, 0, true, false);
		itemList.add(z);
		z = new Items("Flashlight", 0, 0, 0, true, false);
		itemList.add(z);
		z = new Items("Boat Part 1", 0, 0, 0, true, false);//in the bedroom
		itemList.add(z);
		z = new Items("Boat Part 2", 0, 0, 0, true, false);//in the attic
		itemList.add(z);
		z = new Items("Boat Part 3", 0, 0, 0, true, false);//in the top of the tower behind the three headed dog
		itemList.add(z);
		z = new Items("Boat Part 4", 0, 0, 0, true, false);
		itemList.add(z);
		z = new Items("Boat Part 5", 0, 0, 0, true, false);//in the cemetery
		itemList.add(z);
		z = new Items("Boat Part 6", 0, 0, 0, true, false);
		itemList.add(z);
		z = new Items("Boat Part 7", 0, 0, 0, true, false);
		itemList.add(z);
		z = new Items("Boat Part 8", 0, 0, 0, true, false);
		itemList.add(z);
		z = new Items("Boat Part 9", 0, 0, 0, true, false);// in  the cabinet
		itemList.add(z);
		z = new Items("Boat Part 10", 0, 0, 0, true, false);
		itemList.add(z);

		Containers c = new Containers("Cabinet");
		c.items.add("Long Sword");
		c.items.add("Gas Lantern");
		c.items.add("Boat Part 9");
		c.requiresKey = "Cabinet Key";
		itemList.add(c);

		c = new Containers("Chest");
		c.items.add("Damascus Sword");  
		c.requiresKey = "Chest Key";
		itemList.add(c);
		
		c = new Containers("Cave");
		c.items.add("Boat Part 4");
		c.requiresKey = "Flashlight";
		itemList.add(c);

		for (Items item : itemList){
			System.out.println(item.name);
		}
	}

	//TODO see if this crashes with a one letter word.
	private String capitalize(String line)
	{
		return Character.toUpperCase(line.charAt(0)) + line.toLowerCase().substring(1);
	}
}

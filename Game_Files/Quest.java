package Game;
import java.util.*;

public class Main {

	//global (instance) variables
	ArrayList<Room> roomList = new ArrayList<Room>();
	ArrayList<Items> itemList = new ArrayList<Items>();
	
	int currRoomID = 1;
	Room currentRoom = new Room(0,"","");
	
	public static void main(String[]args){
		new Main();
	}
	
	Main() {
		
		boolean playing = true;
		String command = " ";
		Room.setupRooms(roomList);
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
		for (Room r : roomList){
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
	
	/* Commands that work so far:
	GO direction, direction,
	LOOK, QUIT
	?, HELP  <--- not working yet
	*/
	boolean parseCommand(String text) {
		
			
		text = text.toLowerCase().trim();
		
		//split text into words
		String words[] = text.split(" ");
		String word1 = words[0].toUpperCase();  //first word is upper case
		String word2 = "";
		int numWords = words.length;
		if (numWords > 1) word2 = words[1];
		//pre-parsing: remove GO (also remove "THE")
		if (word1.equals("GO")) word1 = words[1];	//ie. GO NORTH --> NORTH
		
		//directions:
		switch(word1) {
		case "QUIT":
			System.out.print("Do you really want to quit the game?");
			//get answer
			return false;
			
		case "N":
		case "E":
		case "S":
	    case "W":
	    case "U":
	    case "D":
		case "NORTH":
		case "SOUTH":
		case "EAST": 
		case "WEST":
		case "UP":
		case "DOWN":
			move(word1.charAt(0));
			break;
			
		case "LOOK":
			if (word2.equals("AT")) {
			//	lookAtObject(words[]);
				break;
				
			}
			//display long descr. 
			System.out.println("You are in the "+ currentRoom.getTitle() + ". " + currentRoom.getLongDesc());
			listItemsinRoom();
			break;
		case "OPEN":
			//if door then open door
			
			//if container, then open container
			openContainer(capitalizeFirstLetter(word2));
			break;
		default: 
			System.out.println("Sorry, I don't understand that command");
		}
		return true;
				
		
	}			
	
	void move(char dir) {
		int newRoom = currentRoom.getExit(dir);
		if (newRoom == 0) {
			System.out.println("You can't go that way");
			return;
		}
		currRoomID = newRoom;
		updateCurrentRoom(currRoomID);
		System.out.println("You walk to the "+ currentRoom.getTitle() + ". " + currentRoom.getDesc());
		listItemsinRoom();
	}
	
	void listItemsinRoom() {
		if (currentRoom.roomItems.size() <= 0) return; //nothing in the room
		System.out.print("You see a ");
		for (String s : currentRoom.roomItems) {
			System.out.print(s + ", ");
		}
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
								no: print "You need the cabinet key". return.
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
		//
		if (container.requiresKey == "" ) {
			container.isOpen = true;
			System.out.println("You open the " + cname);
		} else {
			//see if the key is in the inventory
			//...
			System.out.println("You need the " + container.requiresKey);
		}
		//here list everything in the cabinet.
	}
	
	String capitalizeFirstLetter(String original){
	    if(original.length() == 0)
	        return original;
	    return original.substring(0, 1).toUpperCase() + original.substring(1);
	}
	/*String[] inventory = new String[20];
	ArrayList <String> inventory = new ArrayList<String>();*/
	
		//Items(String name, int h, int am, int dm, boolean carry) {
	void makeItems() {
		//food
		Items z = new Items("Sandwich", 15, 0,0,true);		
		itemList.add(z);
		z = new Items("Banana", 5, 0,0,true);
		itemList.add(z);
		//potions
		z = new Items("Healing Potion", 50, 0, 0, true);
		itemList.add(z);
		z = new Items("Damage/Rage Potion", 0, 80, 0, true);
	    itemList.add(z);
		// Weapons
		z = new Items("Kitchen Knife", 0, 10, 0, true);
		itemList.add(z);
		z = new Items("Damascus Sword", 0, 60, 10, true);
		itemList.add(z);
		z = new Items("Long Sword", 0, 50, 20, true);
		itemList.add(z);
		z = new Items("Mace", 0, 30, 5, true);
		itemList.add(z);
		// Armour
		z = new Items("Iron Helmet", 0, 0, 40, true);
		itemList.add(z);
	    z = new Items("Chest Plate", 0, 0, 40, true);
	    itemList.add(z);
	    z = new Items("Plate Leggings", 0, 0, 40, true);
	    itemList.add(z);
	    z = new Items("shield", 0, 0, 100, true);
	    itemList.add(z);
	    //Keys
	    z = new Items("Cabinet Key", 0, 0, 0, true);	    
	    itemList.add(z);
	    z = new Items("Tower Key", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Chest Key", 0, 0, 0, true);
	    itemList.add(z);
	    //Misc
	    z = new Items("Gas Lantern", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Boat Part 1", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Boat Part 2", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Boat Part 3", 0, 0, 0, true);
	    itemList.add(z);
	    //(activated by keys)
	    //z = new Items("Cabinet", 0, 0, 0, false);
	    //z.isContainer = true;
	    Containers c = new Containers("Cabinet");
	    c.items.add("Long Sword");
	    c.items.add("Gas Lantern");
	    c.requiresKey = "Cabinet Key";
	    itemList.add(c);
	    
	    c = new Containers("Chest");
	    c.items.add("Damascus Sword");  
	    c.requiresKey = "Chest Key";
	    itemList.add(c);
	    itemList.add(z);
	    
	    z = new Items("Tower", 0, 0, 0, false); //this should be a room. Need to figure out how doors work.
	    itemList.add(z);
	       
		for (Items item : itemList){
			System.out.println(item.name);
		}
	}
}

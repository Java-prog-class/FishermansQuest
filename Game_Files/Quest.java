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
		
			
		text = text.toUpperCase().trim();
		
		//split text into words
		String words[] = text.split(" ");
		String word1 = words[0];
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
	    z = new Items("Shield", 0, 0, 100, true);
	    itemList.add(z);
	    //Keys
	    z = new Items("Cabnet Key", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Tower Key", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Chest Key", 0, 0, 0, true);
	    itemList.add(z);
	    //Misc
	    z = new Items("Gas Lantern", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Rope", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Boat Part 1", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Boat Part 2", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Boat Part 3", 0, 0, 0, true);
	    itemList.add(z);
	       
		for (Items item : itemList){
			System.out.println(item.name);
		}
	}
}

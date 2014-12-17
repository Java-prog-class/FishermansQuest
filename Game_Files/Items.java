package Game;
import java.util.*;

public class Main {

	//global (instance) variables
	ArrayList<Room> roomList = new ArrayList<Room>();
	ArrayList<Items> itemList = new ArrayList<Items>();
	
	int currRoomID = 1;
	Room currentRoom = new Room(0,"","");;
	
	public static void main(String[]args){
		new Main();
	}
	
	Main() {
		
		boolean playing = true;
		String command = "";
		setupRooms();
		makeItems();
		updateCurrentRoom(currRoomID);
		
		//main game loop
		//set to false if the user quits
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
		case "N": case "E": case "S": case "W":
		case "NORTH": case "SOUTH": case "EAST": case "WEST":
			move(word1.charAt(0));
			break;
		case "LOOK":
			if (word2.equals("AT")) {
			//	lookAtObject(words[]);
				break;
			}
			System.out.println("You are in the "+ currentRoom.getTitle() + ". " + currentRoom.getDesc());
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
	}
	
	
	void setupRooms() {

		Room r = new Room(1, "Your Jail Cell", "It stinks.");
		//		   N E S W
		r.setExits(0,2,0,0);
		roomList.add(r);
		
		r = new Room(2, "Jailblock", "It's dark.");
		r.setExits(0,0,0,1); //fix these exits
		roomList.add(r);
		
		r = new Room(3, "long hallway", "It's dark.");
		r.setExits(0,0,0,1); 
		roomList.add(r);
	
	//	for (Room m : roomList){
	//		System.out.println(m.toString());
	//	}
	}
	//Items(String name, int h, int am, int dm, boolean carry) {
	void makeItems() {
		Items z = new Items("Sandwich", 15, 0,0,true);
		itemList.add(z);
		z = new Items("banana", 5, 0,0,true);
		itemList.add(z);
		z = new Items("Cooked Fish", 25, 0, 0, true);
		itemList.add(z);
		z = new Items("Healing Potion", 50, 0, 0, true);
		itemList.add(z);
		z = new Items("Damage Potion", 0, 80, 0, true);
	    itemList.add(z);
		// Weapons
		z = new Items("Kitch Knife", 0, 10, 0, true);
		itemList.add(z);
		z = new Items("Harpoon Gun", 0, 100, 0, true);
		itemList.add(z);
		z = new Items("Damascus Sword", 0, 60, 30, true);
		itemList.add(z);
		z = new Items("Battle Axe", 0, 80, 20, true);
		itemList.add(z);
		z = new Items("Long Sword", 0, 50, 30, true);
		itemList.add(z);
		z = new Items("Mace", 0, 30, 10, true);
		itemList.add(z);
		// Armour
		z = new Items("Iron Helmet", 0, 0, 40, true);
		itemList.add(z);
	    z = new Items("Chest Plate", 0, 0, 50, true);
	    itemList.add(z);
	    z = new Items("Plate Leggings", 0, 0, 30, true);
	    itemList.add(z);
	    z = new Items("Shield", 0, 0, 100, true);
	    itemList.add(z);
	    //Keys
	    z = new Items("Crypt Key", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Tower Key", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Chest Key", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Gas Lamp", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Rope", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Boat Part 1", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Boat Part 2", 0, 0, 0, true);
	    itemList.add(z);
	    z = new Items("Boat Part 3", 0, 0, 0, true);
	    itemList.add(z);
	    // Misc
	    
		for (Items item : itemList){
			System.out.println(item.name);
		}
	}
}

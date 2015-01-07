package Game;
import java.util.*;
public class Room{
	
	private int roomID;
	private String title;
	private String description;
	private String long_desc;
	private int N,E,S,W,U,D;
	ArrayList<String> roomItems = new ArrayList<String>();

	//constructor
	Room(int id, String title, String description){
		roomID = id;
		this.title = title;
		this.description = description;
	}
	
	void setExits(int N, int E, int S, int W, int U, int D) {
		this.N = N;
		this.E = E;
		this.S = S;
		this.W = W;	
		this.U = U;
		this.D = D;
	}
	
	int getExit(char c) {
		switch (c) {
		case 'N': return this.N;
		case 'E': return this.E;
		case 'S': return this.S;
		case 'W': return this.W;
		case 'U': return this.U;
		case 'D': return this.D;
		default: return 0;
		}
	}
	
	int getRoomID()	  { return roomID; }
	String getTitle() { return title; }
	String getDesc()  { return description; }
	String getLongDesc()  { return long_desc; }
	
	//most classes need to have this
	public String toString(){
		String s = String.format("Title=%-25s\tDescription=%s",title,description);		
		return s;
	}
	
	static void setupRooms(ArrayList<Room> roomList) {

		Room r = new Room(1, "--Beach--\n\n", "\nThick forest filled with tall trees. You cannot go around.");//Sandwich, banana, Kitchen Knife, Chest Key
		//		   N E S W U D
		r.setExits(2,0,0,0,0,0);
		r.long_desc = "The wind blows and the waves crash against your\n wrecked boat. It still needs parts. The crate that you stored your stuff is broken. Inside is a...";
		r.roomItems.add("Sandwich");	
		r.roomItems.add("Banana");
		r.roomItems.add("Kitchen Knife");
		r.roomItems.add("Chest Key");
		roomList.add(r);
		
		r = new Room(2, "--Front of House--\n\n", "\nIt's old, looks abandoned, boarded up windows.");
		r.setExits(3,0,1,0,0,0); 
		r.long_desc = " ";
		r.roomItems.add("Shattered windows with boards nailed to them, trees hug the\n sides of the house. Bird bath to the left of you.");
		roomList.add(r);
		
		r = new Room(3, "--Living Room--\n\n", "\nYou walk in. Candles are lit around the room.");//Cabnet(needs key): Long Sword, Rope, Gas Lantern
		r.setExits(0,4,2,5,0,0); 
		roomList.add(r);
		
		r = new Room(4, "--Bedroom--\n\n", "\nDoor swings open. Half unpacked boxes are scattered all over the room.");//Boat Part 1, Cabnet key
		r.setExits(0,0,0,3,0,0);
		roomList.add(r);
		
		r = new Room(5, "--Hallway--\n\n", "\nNothing but the dim lighting of the candles.");
		r.setExits(6,3,0,0,0,0);
		roomList.add(r);
		
		r = new Room(6, "--Hallway #2--\n\n", "\nGas lantern on the wall lighting the rest of the hallway, along\nwith some stairs leading down to darkness.");//Gas Lantern
		r.setExits(0,7,5,0,8,0);
		roomList.add(r);
		
		r = new Room(7, "--Basement Hallway--\n\n", "\nDarkness. Large webs surround the wall.");//Tower key
		r.setExits(0,0,9,6,0,0);
		roomList.add(r);
		
		r = new Room(8, "--Attic--\n\n", "\nDusty, webs, crates. ");//Mace, Boat Part 2
		r.setExits(0,0,0,0,0,6);
		roomList.add(r);
		
		r = new Room(9, "--Crypt--\n\n", "--ENTERING COMBAT--");// Giant Spiders, Armor: helmet, leggings, chest plate, shield (after battle)
		r.setExits(7,10,0,11,0,0);
		roomList.add(r);
		
		r = new Room(10, "--Laboratory--\n\n", "\nA table with stuff on a table. Smashed beakers on the floor.");// Health Potion, Damage Rage Potion
		r.setExits(0,0,0,9,0,0);
		roomList.add(r);
		
		r = new Room(11, "--Underground Pathway--\n\n", "\nLeads to an old door.");//need key to get in(tower key)
		r.setExits(0,9,0,12,0,0);
		roomList.add(r);
		
		r = new Room(12, "--Base of Tower--\n\n", "\nStairs are leading up to the top.");//Chest(needs key): Damascus Sword, Health potion
		r.setExits(0,11,0,0,13,0);
		roomList.add(r);
		
		r = new Room(13, "--Top of Tower--\n\n", "--ENTER COMBAT--");// 3 headed dog, Boat Part 3
		r.setExits(0,0,0,0,0,12);
		roomList.add(r);
		
	//	for (Room m : roomList){
	//		System.out.println(m.toString());
	//	}
	}
}

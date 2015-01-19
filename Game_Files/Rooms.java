package Game;
import java.util.*;

public class Rooms{
	
	private int roomID;
	private String title;
	private String description;
	private String long_desc;
	private int N,E,S,W,U,D;
	ArrayList<String> roomItems = new ArrayList<String>();

	//constructor
	Rooms(int id, String title, String description){
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
	
	static void setupRooms(ArrayList<Rooms> roomList) {

		Rooms r = new Rooms(1, "--Beach--\n\n", "\nThick forest filled with tall trees. You cannot go around.");
		//		   N E S W U D
		r.setExits(2,0,47,0,0,0);
		r.long_desc = "The wind blows and the waves crash against your\nwrecked boat. It still needs parts. The crate"
				+ " that you stored your stuff is broken. Almost all your things are washed away.w";
		r.roomItems.add("Sandwich");	
		r.roomItems.add("Banana");
		r.roomItems.add("Knife");
		r.roomItems.add("Chest Key");
		roomList.add(r);
		
		r = new Rooms(2, "--Front of House--\n\n", "\nIt's old, looks abandoned, boarded up windows.");
		r.setExits(3,0,1,0,0,0); 
		r.long_desc = "Old white house, paint looks chipped. There is no way around. Shattered windows \nwith boards "
				+ "nailed to them, trees hug the\nsides of the house. Bird bath to the left of you. You cannot\ngo around!";
		roomList.add(r);
		
		r = new Rooms(3, "--Living Room--\n\n", "\nYou walk in. Candles are lit\naround the room. There's"
				+ " an old cabinet, and a mess all over the room.");
		r.setExits(0,4,2,5,0,0); 
		r.long_desc = "Smokes are left in the ash tray. It'still lit. More garbage lays"
				+ " around the living room. Almost like\nsomeone was in a hurry to leave..";
		r.roomItems.add("Cabinet");
		roomList.add(r);
		
		r = new Rooms(4, "--Bedroom--\n\n", "\nDoor swings open. Half unpacked boxes are scattered all over the room.");
		r.setExits(0,0,0,3,0,0);
		r.long_desc = "Boxes all over the place. Like someone was packing to leave. Special boat "
				+ "part seems to be hanging\non thew wall in a show case. ";
		r.roomItems.add("Cabinet Key");
		r.roomItems.add("Steering Wheel");
		r.roomItems.add("Picklock Set");
		roomList.add(r);
		
		r = new Rooms(5, "--Hallway--\n\n", "\nNothing but the dim lighting of the candles.");
		r.setExits(6,3,0,0,0,0);
		r.long_desc = "Paint chipped walls, along with some cob webs.";
		roomList.add(r);
		
		r = new Rooms(6, "--Hallway #2--\n\n", "\nGas lantern on the wall lighting the "
				+ "rest of the hallway, along\nwith some stairs leading down to darkness.");
		r.setExits(0,7,5,0,8,0);
		r.long_desc = "Theres an attic above you.";
		roomList.add(r);
		
		r = new Rooms(7, "--Basement Hallway--\n\n", "\nDarkness. Large webs surround the wall.");
		r.setExits(0,0,9,6,0,0);
		r.long_desc = "Roachs roam the hallway.";
		
		roomList.add(r);
		
		r = new Rooms(8, "--Attic--\n\n", "\nDusty, webs, crates. ");
		r.setExits(0,0,0,0,0,6);
		r.long_desc = "Webs are all over the place! some dusty boating books, old clothes.";
		r.roomItems.add("Mace");
		r.roomItems.add("Metal Plate 1");
		roomList.add(r);
		
		r = new Rooms(9, "--Crypt--\n\n", "Bones lay on the ground. Maybe a beast of some sort has been threw here..");
		r.setExits(7,10,0,11,0,0);
		r.long_desc = "Skulls and ribs lay on the ground. More webs, and some slobber..interesting..";
		r.roomItems.add("Iron Helmet");
		r.roomItems.add("Chest Plate");
		r.roomItems.add("Plate Leggings");
		r.roomItems.add("Shield");
		roomList.add(r);
		
		r = new Rooms(10, "--Laboratory--\n\n", "\nA table with stuff on it. Smashed beakers on the floor and lab"
				+ " equipment. None of it is worth taking.");
		r.setExits(0,0,0,9,0,0);
		r.long_desc = "";
		r.roomItems.add("Health Potion");
		roomList.add(r);
		
		r = new Rooms(11, "--Underground Pathway--\n\n", "\nLeads to an old wooden door.");
		r.setExits(0,9,0,12,0,0);
		r.long_desc = "More slobber is on the floor, looks like a trail leading to what appears to be a door.";
		roomList.add(r);
		
		r = new Rooms(12, "--Base of Tower--\n\n", "\nStairs are leading up to the top. Watch your step!");
		r.setExits(0,11,0,0,13,0);
		r.long_desc = "Nothing special. Its just the base of a tower.";
		r.roomItems.add("Chest");
		roomList.add(r);
		
		r = new Rooms(13, "--Top of Tower--\n\n", " A monster is sleeping. SHHHHHH.");
		r.setExits(0,0,0,14,0,12);
		r.long_desc = "A Sleeping troll lays in front of a boat piece. Looks like part\nof the engine. And a "
				+ "window that you can jump out of. should you take the chance?";
		r.roomItems.add("Engine Part 1");
		roomList.add(r);
		
		r = new Rooms(14, "--Outside at the Bottom of Tower--\n\n", "luckly, you have landed in a bale of hay. That was close!");
		r.setExits(16,0,0,15,0,0);
		r.long_desc = "Thick forest monkey sit in trees watching you. A path leads more north, and a path to the west.";
		roomList.add(r);
		
		r = new Rooms(15, "--West Pathway--\n\n", "Starting to get gloomy, a dark presence roams the air.");
		r.setExits(0,14,0,17,0,0);
		r.long_desc = "Looks like the trees are dying. Again nothing too special.";
		roomList.add(r);
		
		r = new Rooms(16, "--North Pathway--\n\n", "Long pathway to some where. More trees with more staring monkeys.");
		r.setExits(19,0,14,0,0,0);
		r.long_desc = "The monkeys....all they do is stare... why? nobody knows.";
		roomList.add(r);
		
		r = new Rooms(17, "--Cemetery--\n\n"," Its Quiet, tooo quiet. Nothing but grave stones and the\n"
				+ "precense of death. And maybe something else..");
		r.setExits(0,15,18,0,0,0); 
		r.long_desc = "Rusty gate entrance. Dead trees and old tomb stones. OH and a boat piece.";
		r.roomItems.add("Engine Part 2");
		r.roomItems.add("Flashlight");
		roomList.add(r);
		
		r = new Rooms(18, "--Stupid Hole--\n\n","You have fallen in a hole. You have won the Stupid Award! please, take it!");
		r.setExits(0,0,0,0,17,0); 
		r.long_desc = "Did you seriously just look around in the hole? No get out! Theres nothing here!";
		r.roomItems.add("Stupid Award");
		roomList.add(r);
		
		r = new Rooms(19, "--Bridge--\n\n","Watch out, it could break.");
		r.setExits(20,0,16,0,0,21); 
		r.long_desc = "Fragile wood. Could break. Down below there's a river. Theres also a ladder going down.";
		roomList.add(r);
		
		r = new Rooms(20, "--Village Ruins--\n\n","The place looks burnt. Almost nothing is salvageable.");
		r.setExits(31,0,19,0,0,0); 
		r.long_desc = "Was this village just burnt down? looks recent. Ash falls from ruined huts. Some burning wood.";
		r.roomItems.add("Coins");
		roomList.add(r);
		
		r = new Rooms(21, "--River--\n\n","Strong current. Sea monsters lurk in the water i bet.");
		r.setExits(0,36,22,0,19,37); 
		r.long_desc = "Turns out there is sea monsters that you can see in the river. Its too dangerous. Also what looks\n"
				+ "to be a cave enterance beside the ladder.";
		roomList.add(r);
		
		r = new Rooms(22, "--Cave Entrance--\n\n","Its dark. You can't see unless you have a flashlight.");
		r.setExits(21,0,0,0,0,0);
		r.long_desc = "You cant see anything or enter unless you have a flashlight.";
		roomList.add(r);
		
		r = new Rooms(23, "--Cave Entrance--\n\n","Slime lines the walls, along with some bones and sharp rocks.");
		r.setExits(21,0,24,0,0,0); 
		r.long_desc = "A boat piece lays infront of you.";
		r.roomItems.add("Boat Part 4");
		roomList.add(r);
		
		r = new Rooms(24, "--Deep Cavern--\n\n","A dim purple light appears around the corner to the east.");
		r.setExits(23,25,26,0,0,0); 
		r.long_desc = "More sharp rocks. More nothing to be seen.";
		roomList.add(r);
		
		r = new Rooms(25, "--Portal Entrance--\n\n","Swirls of purple and blue blind your eyes.");
		r.setExits(0,27,0,24,0,0); 
		r.long_desc = "You see faces in the portal. They smile and whisper hello to you.";
		roomList.add(r);
		
		r = new Rooms(26, "--Dead End--\n\n","Human remains Splattered on the wall.");
		r.setExits(24,0,0,0,0,0); 
		r.long_desc = "A dusty old scuba mask.";
		r.roomItems.add("Scuba Mask");
		roomList.add(r);
		
		r = new Rooms(27, "--Infernum--\n\n","You must pay thenatos the toll to get across the river.");
		r.setExits(0,0,0,25,0,0); 
		r.long_desc = "Spirits roam around you. The heat is intense. You can barely stand it.";
		roomList.add(r);
		
		r = new Rooms(28, "--Infernum--\n\n","You pay the toll. You may get across now the the gates of hades.");
		r.setExits(0,29,0,25,0,0); 
		r.long_desc = "Flames are around you. looks like hell. How are you still breahting?";
		roomList.add(r);
		
		r = new Rooms(29, "--Gates Of Hades Palace--\n\n","You have successfully paid the fee. Stairs lead up.");
		r.setExits(0,0,0,28,30,0); 
		r.long_desc = "They look evil. Does look like anyones home. Must be doing things.";
		roomList.add(r);
		
		r = new Rooms(30, "--Hades Palace--\n\n","The door creeks open. Dark but very luxurious.");
		r.setExits(0,33,0,0,0,29); 
		r.long_desc = "Gold door handle, red welcome mat. You hear barking. Sounds like multiple dogs.";
		roomList.add(r);
		
		r = new Rooms(31, "--Burnt Down Tower--\n\n","Too much burnt stuff to even know what it is.");
		r.setExits(0,32,20,0,0,0); 
		r.long_desc = "Nothing to pick from the ashes.";
		roomList.add(r);
		
		r = new Rooms(32, "--Burnt house--\n\n","You See nothing special. Just the sun setting in the distance.");
		r.setExits(0,35,0,31,0,0); 
		r.long_desc = "A Boat piece is in the remains! And a dirty jewely box!";
		r.roomItems.add("Pipes");
		r.roomItems.add("Box");
		roomList.add(r);
		
		r = new Rooms(33, "--Hades Bedroom--\n\n","Bed isnt made. He's not home. Must be out doing things.");
		r.setExits(0,0,0,30,0,34); 
		r.long_desc = "Where could he be? who knows. Mess all over his room. Probably not the best idea to steal froma god..";
		r.roomItems.add("Metal Plate 2");
		roomList.add(r);
		
		r = new Rooms(34, "--Trap door--\n\n","Looks like his secret stash. Some of the pages are stuck together. Ew.");
		r.setExits(0,0,0,0,33,0); 
		r.long_desc = "I dont think you want to take those but you can...";
		r.roomItems.add("Hades Magazines");
		roomList.add(r);
		
		r = new Rooms(35, "--Cliff--\n\n","You can see sea monsters in the distance. You will need more renforcments.");
		r.setExits(0,0,0,32,0,0);
		r.long_desc = "You will need more boat part to get off this island and survive from those sea monsters.";
		r.roomItems.add("Rope");
		roomList.add(r);
		
		r = new Rooms(36, "--Water Fall--\n\n","Theres a watterfall in front of you coming off of the tall\ncliff.");
		r.setExits(0,0,0,21,0,0);
		r.long_desc = "Maybe I could use a rope and throw it up there to attach it to that branch.";
		r.roomItems.add("Metal Plate 4");
		roomList.add(r);
		
		r = new Rooms(37, "--Water Fall--\n\n","You throw the rope up and pull. Its snug to the thick branch. You can climb up now.");
		r.setExits(0,0,0,21,38,0);
		r.long_desc = "The sea monsters look hungry. Dont fall in. Those sharp rocks wont feel good.";
		roomList.add(r);
		
		r = new Rooms(38, "--Heavy Forest--\n\n","Thick forest. Its starting to get dark.");
		r.setExits(0,39,0,0,0,37);
		r.long_desc = "Luckily you have a flshlight. If not you couldnt see. Your gonna need to\nfind a spot where you can stay the night.";
		roomList.add(r);
		
		r = new Rooms(39, "--Scary Pond--\n\n", "Seems really quiet. You cant drink this water. Its too dirty.");
		r.setExits(0,0,40,38,0,41);
		r.long_desc = "The forest is too thick to see through. You gonna just have to trust the path leading east.";
		roomList.add(r);
		
		r = new Rooms(40, "--Cliff--\n\n","Smells like fresh water from the south.");
		r.setExits(39,0,1,0,0,0);
		r.long_desc = "You can see the beach and your wrecked boat in the distance. Looks like we can"
				+ " get\ndown without hurting ourselves.";
		r.roomItems.add("Engine Part 4");
		roomList.add(r);
		
		r = new Rooms(41, "--In the Pond--\n\n","You realize its deeper than you thought. Can you go deeper?");
		r.setExits(0,0,0,0,40,42);
		r.long_desc = "Little fish swim around you not bothering you. Still not touching the bottom.";
		roomList.add(r);
		
		r = new Rooms(42, "--Metal Hatch--\n\n","You cant go any father unless you have a scuba mask. Its getting hard to breath.");
		r.setExits(0,0,0,0,41,0);
		r.long_desc = "Your vision is blurred and your eyes hurt to look.";
		roomList.add(r);
		
		r = new Rooms(43, "--Metal Hatch--\n\n","You can open the hatch and go down now.");
		r.setExits(0,0,0,0,41,44);
		r.long_desc = "Your vision is blurred and your eyes hurt to look.";
		roomList.add(r);
		
		r = new Rooms(44, "--UnderGround Hideout--\n\n","You close the hatch. Looks like a hideout. Another room to the west.");
		r.setExits(0,0,0,45,43,0);
		r.long_desc = "Papers are all over the place. Rusty door.";
		r.roomItems.add("Engine Part 3");
		roomList.add(r);
		
		r = new Rooms(45, "--Long Hallway--\n\n","Theres a door at the end of the hallway.");
		r.setExits(0,44,0,46,0,0);
		r.long_desc = "The light flickers...";
		roomList.add(r);
		
		r = new Rooms(46, "--Storage Room--\n\n","Theres boat pieces in here. But you only need the special part.");
		r.setExits(0,45,0,0,0,0);
		r.long_desc = "This must be an old place.";
		r.roomItems.add("Metal Plate 3");
		roomList.add(r);
		
		r = new Rooms(47, "--Wrecked Boat--\n\n","The boat is wrecked. It needs parts for the engine and to replace the holes");
		r.setExits(1,0,0,0,0,0);
		r.long_desc = "The boat is in bad shape.";
		roomList.add(r);
		
		r = new Rooms(48, "--Repaired Boat--\n\n","Your boat is fixed! you can now go south and get off this island and go home!!!");
		r.setExits(0,0,0,0,0,0);
		r.long_desc = "You have one last look at the island before you sail away.";
		roomList.add(r);
	}
}

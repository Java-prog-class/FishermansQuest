
import java.util.*;

public class Map{
	
	//Global Variables
	//int Current Room=1;
	String currentRoom = "Boat";
	ArrayList<Room> roomList = new ArrayList<Room>();
	
	public static void main(String[] Args){
		new Map();
	}
	
	Map() {
		makeRooms();
		
		String command = getCommand() ; //put scanner in here
		parseCommand(command); // do whatever the command tells you to do
			
			/////////////////////////
			
			//Room room = //Find current room in list
					
					//look -> show desc
					
					//North
					//South
					//East
					//West
					
					
		//id //number or name
		//short Desc
		//lond d
		//N =
		//S = Refer to id (either String or int)
		//E =
		//W = 
		//(up or down?)
				
	}
	
	String getCommand() {
		//....
		//String s = scanner ....
		String s = "look";
		return s;				
	}
	
	void parseCommand(String text) {
		System.out.println("parsing " + text);
		//find all data for current room.
		Room theroom = new Room("","") ; 
		for(Room r : roomList) {
			if (r.name.equals(currentRoom)) {
				theroom = r;
			}
		}
		
		text = text.toUpperCase();
		text = text.trim();
		//split into words
		String words[] = text.split(" ");
		System.out.println(words[0]);
		if (words[0].equals("LOOK")) {
			System.out.println(theroom.description);
			return;
		}
		
		char dir = words[0].charAt(0); //take the first letter (of the first word)
		
		switch (dir){
		case 'N':
		case 'S': 
		case 'E':
		case 'W':
			//if (theroom)
		}
	}
	
	void makeRooms() {
		Room r = new Room("Boat", "You are in a large leaky boat. \n It smells of fish.");
		//         N, S, E, W, U, D
		r.setExits("Shore", "","","","","");
		roomList.add (r);
			
				//create each room
				//Add Rooms

	}
}


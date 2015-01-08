package Game;

import java.util.ArrayList;

public class Containers extends Items
{
	boolean isOpen = false;  //if it is a container, default to closed
	String requiresKey = "";	// if this is not empty, then it requires the key to open (and close it?) 
	ArrayList<String> items = new ArrayList<String>();
	
	Containers(String name) {
		super(name,0,0,0,false); //this sets the item property "isCarryable" to false.
	}

}



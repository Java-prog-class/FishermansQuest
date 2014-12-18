package Game;
import java.util.*;
public class Room{
	
	private int roomID;
	private String title;
	private String description;
	private String long_desc;
	private int N,E,S,W,U,D; 

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
	
	//most classes need to have this
	public String toString(){
		String s = String.format("Title=%-25s\tDescription=%s",title,description);		
		return s;
	}

}

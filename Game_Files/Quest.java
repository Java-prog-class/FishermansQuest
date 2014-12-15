//import java.util.Scanner;

public class Quest{

	public static void main(String[] Args){
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
		z = new Items("Kitch Knife", 0, 10, 0, true);
		itemList.add(z);
		z = new Items("Harpoon Gun", 0, 50, 0, true);
		itemList.add(z);
		
		
		for (Items item : itemList){
			System.out.println(item.name);
		}
	}
}










	
	}

}

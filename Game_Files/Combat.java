import java.util.ArrayList;
import java.util.Scanner;

public class CombatMain {

	//instance (global) variables
	Monster monster;
	Player player;
	
	ArrayList<Monster> monsterList = new ArrayList<Monster>();
	
	public static void main(String[]args){
		new CombatMain();
	}


	CombatMain(){
		boolean inCombat = false;
		boolean playing = true;
		boolean inShop = false;
		String command;
		Scanner sc = new Scanner(System.in);
		player = new Player(50, 50, 0, 10, 0, 0);
		
		makeMonsters("dwarf", 25, 25, 1, 10, 10, 2);
		
		
		generateMonster(1);

		while(playing){
			System.out.println("\nWhat do you wish to do?");
			System.out.println("Fight \nShop \nStats \nExit");
			command = sc.next();
			command = command.toUpperCase();

			if (command.equals("FIGHT")) inCombat = true;
			if (command.equals("EXIT")) return;
			//if (command.equals("HELP")) System.out.println(helpText);
			if (command.equals("SHOP")) inShop = true;
			if (command.equals("STATS")){
				System.out.println(player.toString());
			}


			while(inShop){
				System.out.println("Lets go shopping!!!!");
			}

			while(inCombat){
				//int damageTaken = monster.damage - player.armourRating;
				boolean playerTurn = true;


				if(playerTurn){
					System.out.println("Your HP: " + player.currentHealth);
					System.out.println("Monster's HP: " + monster.currentHealth);

					System.out.println("What do you want to do?");
					System.out.println("Attack \nBlock \nHeal");
					command = sc.next();
					command = command.toUpperCase();

					if(command.equals("ATTACK")) monster.currentHealth -= player.damage;
					//if(command.equals("HEAL")) player.currentHealth += Items.HealthPack();

					playerTurn = false;
				}

				if(playerTurn == false){
					player.currentHealth -= monster.damage;
					playerTurn = true;

				}				

				if (monster.currentHealth <= 0){
					monster.currentHealth = monster.health;
					System.out.println("you killed the " +  monster.monsterType );
					player.gold += monster.goldDrop;
					inCombat = false;
				}
			}
		}		 
	}
	
	void generateMonster(int level) {
		if (level == 1) {
			//makea random level 1 monster
			monster = new Dwarf("dwarf", 25, 25, 1, 10, 10, 2);
			
	}	
	//	monster = new Monster("dwarf", 25, 25, 1, 10 , 10, 2);
	}


	void makeMonsters(String mt, int health, int currentHealth, int armourRating, int damage, int xpGain, int goldDrop) {
		monsterList.add(new Dwarf(mt, health, currentHealth, armourRating, damage, xpGain, goldDrop));
		//monsterList.add(new Dwarf());
		//monsterList.add(new Goblin());
		//monsterList.add(new Dwarf());
		//monsterList.add(new Samurai());
		
		
	}

}


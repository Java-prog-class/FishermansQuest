public class Monster {
	
	//default values for all/any monster
	String monsterType;	
	int health=100;
	int currentHealth;
	int armourRating;
	int damage;
	int xpGain;
	int goldDrop;
	
	//empty constructor
	
	/*
	Monster(String mt, int health, int currentHealth, int armourRating, int damage, int xpGain, int goldDrop){
		monsterType = mt;
		this.health = health;
		this.currentHealth = currentHealth;
		this.armourRating = armourRating;
		this.damage = damage;
		this.xpGain = xpGain;
		this.goldDrop = goldDrop;		
	}
	*/
	
	//put common methods for monsters here
	void getDamaged(int hit) {
		health -= hit;
	}
}

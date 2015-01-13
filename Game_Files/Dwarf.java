public class Dwarf extends Monster {

	/*String monsterType = "dwarf";
	health = 25;
	int currentHealth = 25;
	int armourRating = 1;
	int damage = 10;
	int xpGain = 10;
	int goldDrop = 2;
	*/
	Dwarf(String mt, int health, int currentHealth, int armourRating, int damage, int xpGain, int goldDrop) {
		this.monsterType = "dwarf";
		this.currentHealth = 25;
		this.armourRating = 1;
		this.damage = 10;
		this.xpGain = 10;
		this.goldDrop = 2;
		//20 - 30
		this.health = (int)(Math.random() *10) +  20;
	}
	
	//special functions that only apply to dwarfs (e.g. dig)
	
}

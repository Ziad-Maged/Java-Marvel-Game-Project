package model.world;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import model.abilities.Ability;
import model.effects.Effect;

@SuppressWarnings("rawtypes")
public abstract class Champion implements Damageable,Comparable {
	private String name;
	private int maxHP;
	private int currentHP;
	private int mana;
	private int maxActionPointsPerTurn;
	private int currentActionPoints;
	private int attackRange;
	private int attackDamage;
	private int speed;
	private ArrayList<Ability> abilities;
	private ArrayList<Effect> appliedEffects;
	private Condition condition;
	private Point location;
	private ImageIcon championImage;
	private boolean leader;
	

	public Champion(String name, int maxHP, int mana, int actions, int speed, int attackRange, int attackDamage) {
		this.name = name;
		this.maxHP = maxHP;
		this.mana = mana;
		this.currentHP = this.maxHP;
		this.maxActionPointsPerTurn = actions;
		this.speed = speed;
		this.attackRange = attackRange;
		this.attackDamage = attackDamage;
		this.condition = Condition.ACTIVE;
		this.abilities = new ArrayList<Ability>();
		this.appliedEffects = new ArrayList<Effect>();
		this.currentActionPoints=maxActionPointsPerTurn;
		this.championImage = new ImageIcon(new ImageIcon(name + ".png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
	}
	
	public int isPunchAvailable() {
		for(int i = 0; i < abilities.size(); i++) {
			if(abilities.get(i).getName().equalsIgnoreCase("Punch") && abilities.get(i).getCurrentCooldown() == 0)
				return i;
		}
		return -1;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public String getName() {
		return name;
	}

	public void setCurrentHP(int hp) {

		if (hp <= 0) {
			currentHP = 0;
			condition=Condition.KNOCKEDOUT;
			
		} 
		else if (hp > maxHP)
			currentHP = maxHP;
		else
			currentHP = hp;

	}
	
	public void setLeader(boolean isLeader) {
		leader = isLeader;
	}
	
	public boolean isLeader() {
		return leader;
	}

	
	public int getCurrentHP() {

		return currentHP;
	}

	public ArrayList<Effect> getAppliedEffects() {
		return appliedEffects;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int currentSpeed) {
		if (currentSpeed < 0)
			this.speed = 0;
		else
			this.speed = currentSpeed;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point currentLocation) {
		this.location = currentLocation;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	public int getCurrentActionPoints() {
		return currentActionPoints;
	}

	public void setCurrentActionPoints(int currentActionPoints) {
		if(currentActionPoints>maxActionPointsPerTurn)
			currentActionPoints=maxActionPointsPerTurn;
		else 
			if(currentActionPoints<0)
			currentActionPoints=0;
		this.currentActionPoints = currentActionPoints;
	}

	public int getMaxActionPointsPerTurn() {
		return maxActionPointsPerTurn;
	}

	public void setMaxActionPointsPerTurn(int maxActionPointsPerTurn) {
		this.maxActionPointsPerTurn = maxActionPointsPerTurn;
	}
	
	public ImageIcon getChampionImage() {
		return championImage;
	}
	
	public void setChampionImage(ImageIcon championImage) {
		this.championImage = championImage;
	}

	public int compareTo(Object o)
	{
		Champion c = (Champion)o;
		if(speed==c.speed)
			return name.compareTo(c.name);
		return -1 * (speed-c.speed);
	}
	
	private String getAbilitiesString() {
		String abilitiesNames = "[ ";
		for(int i = 0; i < abilities.size() - 1; i++) {
			abilitiesNames += abilities.get(i).getName() + ", ";
		}
		return abilitiesNames + abilities.get(abilities.size() - 1).getName() + " ]";
	}
	
	public abstract void useLeaderAbility(ArrayList<Champion> targets);
	
	public String abilitiesToString2() {
		String resault = "[ ";
		for(int i = 0; i < abilities.size() - 1; i++) {
			resault += abilities.get(i).toString2();
		}
		resault += abilities.get(abilities.size() - 1).toString2() + "]";
		return resault;
	}
	
	public String effectsToString2() {
		String resault = "";
		for(int i = 0; i < appliedEffects.size() - 1; i++) {
			resault += appliedEffects.get(i).toString2();
		}
		if(appliedEffects.size() != 0)
			resault = "[ " + resault + appliedEffects.get(appliedEffects.size() - 1).toString2() + " ]";
		return resault;
	}
	
	public String toString2() {
		return "Champion name: " + name + "\n" + "maxHP: " + maxHP + "\n" + "mana: " + mana
				+ "\n" + "maxActionPointsPerTurn: " + maxActionPointsPerTurn + "\n" + "attackRange: " + attackRange 
				+ "\n" + "attackDamage: " + attackDamage + "\n" + "speed: " + speed
				+ "\n" + "abilities: " + getAbilitiesString();
	}
	@Override
	public String toString() {
		return "<html> name:" + name + ((leader)?" (Leader)":" (Not Leader)") +", currentHP:" + currentHP + "<br>mana:" + mana + ", currentActionPoints:"
				+ currentActionPoints + "<br>attackRange:" + attackRange + ", attackDamage:" + attackDamage + "<br>speed:"
				+ speed  + "<br>appliedEffects:" + appliedEffects + "<br>condition:" + condition;
	}
	public String toString3() {
		return "name:" + name + ((leader)?" (Leader)":" (Not Leader)") + "\nmana:" + mana + "\ncurrentActionPoints:"
				+ currentActionPoints + "\nattackRange:" + attackRange + "\nattackDamage:" + attackDamage + "\nspeed:"
				+ speed + "\nabilities:" + abilitiesToString2() + "\nappliedEffects:" + effectsToString2() + "\ncondition:" + condition;
	}
}

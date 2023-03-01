package model.world;

import java.util.ArrayList;

import model.effects.Stun;

public class AntiHero extends Champion {

	public AntiHero(String name, int maxHP, int maxMana, int actions, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, maxMana, actions, speed, attackRange, attackDamage);

	}

	@Override
	public void useLeaderAbility(ArrayList<Champion> targets) {
		for (Champion c: targets)
		{
			Stun s = new Stun(2);
			c.getAppliedEffects().add(s);
			s.apply(c);
		}
	}
	public String toString2() {
		return super.toString2() + "\n" + "Type: AntiHero";
	}
	public String toString() {
		return super.toString() + "<br> Type: AntiHero </html>";
	}
	public String toString3() {
		return super.toString3() + "\nType: AntiHero";
	}
}
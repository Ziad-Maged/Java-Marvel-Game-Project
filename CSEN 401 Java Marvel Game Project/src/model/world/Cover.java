package model.world;

import java.awt.Point;
import java.awt.Color;

public class Cover implements Damageable {
	private int currentHP;
//	private static Color coverColor = new Color(0x123456);
	private static Color coverColor = Color.blue;
	private Point location;

	public Cover(int x, int y) {
		this.currentHP = (int)(( Math.random() * 900) + 100);
		location = new Point(x, y);
	}

	public int getCurrentHP() {
		return this.currentHP;
	}

	public void setCurrentHP(int newHp) {
		if (newHp < 0) {
			currentHP = 0;
		
		} else
			currentHP = newHp;
	}

	public Point getLocation() {
		return location;
	}
	
	public Color getCoverColor() {
		return coverColor;
	}
}

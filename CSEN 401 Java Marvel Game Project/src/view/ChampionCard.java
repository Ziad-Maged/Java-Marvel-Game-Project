package view;

import javax.swing.*;

import engine.Game;
import model.world.Champion;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings({"serial"})
public class ChampionCard extends JLabel implements MouseListener{
	private String championName;
	private String championImageName;
	private ImageIcon image;
	private boolean isClicked;
	public ChampionCard(String championName) {
		this.championName = championName;
		championImageName = this.championName + ".png";
		image = new ImageIcon(new ImageIcon(championImageName).getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH));
		this.addMouseListener(this);
		this.setBounds(0, 0, 100, 100);
		this.setIcon(image);
		this.setBackground(Color.black);
		this.setOpaque(true);
	}
	public ImageIcon getImage() {
		return image;
	}
	public String getChampionName() {
		return championName;
	}
	public String getChampionImageName() {
		return championImageName;
	}
	public boolean isClicked() {
		return isClicked;
	}
	public Champion getChampionByName(String name) {
		for(Champion e : Game.getAvailableChampions()) {
			if(e.getName().equals(name)) {
				return e;
			}
		}
		return null;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
		this.setIcon(this.image);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(SelectChampion.getFirstPlayer().getTeam().size() < 3 && !isClicked) {
			SelectChampion.addChampionToFirstPlayer(championName);
			isClicked = true;
			this.setBackground(Color.red);
		}else if(SelectChampion.getSecondPlayer().getTeam().size() < 3 && !isClicked) {
			SelectChampion.addChampionToSecondPlayer(championName);
			isClicked = true;
			this.setBackground(Color.blue);
			if(SelectChampion.getSecondPlayer().getTeam().size() >= 3) {
				SelectChampion.getContinueButton().setEnabled(true);
			}
		}else if(SelectLeader.isSelectingFirstLeader() && !isClicked) {
			SelectLeader.getFirstPlayer().setLeader(getChampionByName(championName));
			SelectLeader.setIsSelectingFirstLeader(false);
			SelectLeader.setIsSelectingSecondLeader(true);
			this.setBackground(Color.green);
			isClicked = true;
			SelectLeader.setFirstPlayerUnClickable();
			SelectLeader.setSecondPlayerClickable();
		}else if(SelectLeader.isSelectingSecondLeader() && !isClicked) {
			SelectLeader.getSecondPlayer().setLeader(getChampionByName(championName));
			SelectLeader.setIsSelectingSecondLeader(false);
			this.setBackground(Color.magenta);
			isClicked = true;
			SelectLeader.setSecondPlayerUnClickable();
			SelectLeader.getContinueButton().setEnabled(true);
		}
		//System.out.println(isClicked);
	}
	public void setClickable(boolean clickability) {
		isClicked = !clickability;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		SelectChampion.setVisisbilityOfImagesAndAttributes(this);
		if(!isClicked)
			this.setBackground(Color.white);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(!isClicked)
			this.setBackground(Color.black);
	}
}

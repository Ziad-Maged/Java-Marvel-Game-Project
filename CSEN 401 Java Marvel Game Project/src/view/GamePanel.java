package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import engine.*;
import model.world.Champion;

@SuppressWarnings({"serial"})
public class GamePanel extends JPanel{
	private static World theWorld;
	private static Game game;
	private static JPanel turnOrderPanel = new JPanel(new GridLayout(1, 6));
	private static JLabel firstPlayerChampions = new JLabel(GameFrame.getFirstPlayer() + "'s Champions: [ ");
	private static JLabel secondPlayerChampions = new JLabel(GameFrame.getSecondPlayer() + "'s Champions: [ ");
	private static ChampionCard[] champions = new ChampionCard[6];
	private static JLabel currentChampionImageLabel = new JLabel();
	private static JTextArea currentChampionAttributesArea = new JTextArea();
	private static JLabel helperLabel = new JLabel("Press H for Help!");
	public GamePanel(Game game, KeyListener e) {
		GamePanel.game = game;
		theWorld = new World(GamePanel.game, e);
		this.setLayout(null);
		this.setBounds(0, 0, 1200, 650);
		this.setPreferredSize(new Dimension(getWidth(), getHeight()));
		this.setBackground(new Color(0x123456));
		this.add(theWorld);
		firstPlayerChampions.setBounds(0, 500, 400, 100);
		firstPlayerChampions.setForeground(Color.black);
		secondPlayerChampions.setBounds(0, 550, 400, 100);
		secondPlayerChampions.setForeground(Color.black);
		helperLabel.setBounds(350, 500, 100, 100);
		helperLabel.setForeground(Color.black);
		updatePlayerChampionsLabels();
		turnOrderPanel.setBounds(500, 0, 780, 170);
		turnOrderPanel.setPreferredSize(new Dimension(780, 170));
		turnOrderPanel.setBackground(Color.white);
		updateTurnOrder();
		this.add(turnOrderPanel);
		this.add(firstPlayerChampions);
		this.add(secondPlayerChampions);
		this.add(helperLabel);
		currentChampionImageLabel.setBounds(500, 170, 400, 400);
		currentChampionImageLabel.setBackground(Color.black);
		currentChampionImageLabel.setPreferredSize(new Dimension(400, 400));
		currentChampionImageLabel.setOpaque(true);
		currentChampionAttributesArea.setBounds(900, 170, getWidth(), 500);
		currentChampionAttributesArea.setPreferredSize(new Dimension(getWidth(), 500));
		currentChampionAttributesArea.setEditable(false);
		this.add(currentChampionImageLabel);
		this.add(currentChampionAttributesArea);
		for(int i = 0; i < champions.length; i++) {
			if( i < GameFrame.getFirstPlayer().getTeam().size()) {
				champions[i] = new ChampionCard(GameFrame.getFirstPlayer().getTeam().get(i).getName());
				champions[i].setBackground(Color.red);
			}else {
				champions[i] = new ChampionCard(GameFrame.getSecondPlayer().getTeam().get(i - 3).getName());
				champions[i].setBackground(Color.green);
			}
			champions[i].setClickable(false);
		}
		updateCurrentChampionImageLabelAndAttributeText();
	}
	public static World getTheWorld() {
		return theWorld;
	}
	public static void updateTurnOrder() {
		turnOrderPanel.removeAll();
		PriorityQueue temp = new PriorityQueue(6);
		while(!GameFrame.getGame().getTurnOrder().isEmpty()) {
			Champion current = (Champion) (GameFrame.getGame().getTurnOrder().peekMin());
			ChampionCard temp2 = new ChampionCard(current.getName());
			temp2.setImage(new ImageIcon(temp2.getImage().getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
			temp2.setClickable(false);
			turnOrderPanel.add(temp2);
			temp.insert(GameFrame.getGame().getTurnOrder().remove());
		}
		while(!temp.isEmpty()) {
			GameFrame.getGame().getTurnOrder().insert(temp.remove());
		}
		turnOrderPanel.repaint();
		turnOrderPanel.revalidate();
	}
	public static void updatePlayerChampionsLabels() {
		
		firstPlayerChampions.setText(GameFrame.getFirstPlayer().getName() + "'s champions: [ ");
		for(int i = 0; i < GameFrame.getFirstPlayer().getTeam().size() - 1; i++) {
			firstPlayerChampions.setText(firstPlayerChampions.getText() + 
					GameFrame.getFirstPlayer().getTeam().get(i).getName() + ", ");
		}
		firstPlayerChampions.setText(firstPlayerChampions.getText() + 
				GameFrame.getFirstPlayer().getTeam().get(
						GameFrame.getFirstPlayer().getTeam().size() - 1).getName() + " ]");
		
		secondPlayerChampions.setText(GameFrame.getSecondPlayer().getName() + "'s champions: [ ");
		for(int i = 0; i < GameFrame.getSecondPlayer().getTeam().size() - 1; i++) {
			secondPlayerChampions.setText(secondPlayerChampions.getText() + 
					GameFrame.getSecondPlayer().getTeam().get(i).getName() + ", ");
		}
		secondPlayerChampions.setText(secondPlayerChampions.getText() + 
				GameFrame.getSecondPlayer().getTeam().get(
						GameFrame.getSecondPlayer().getTeam().size() - 1).getName() + " ]");
	}
	public static void updateCurrentChampionImageLabelAndAttributeText() {
		for(int i = 0; i < champions.length; i++) {
			if(((Champion)GameFrame.getGame().getTurnOrder().peekMin()).getName().equals(
					champions[i].getChampionName())) {
				currentChampionImageLabel.setIcon(new ImageIcon(
						new ImageIcon(champions[i].getChampionImageName()).getImage().getScaledInstance(
								currentChampionImageLabel.getHeight(),
								currentChampionImageLabel.getWidth(), Image.SCALE_SMOOTH)));
				currentChampionAttributesArea.setText(champions[i].getChampionByName(champions[i].getChampionName()).toString3());
				return;
			}
		}
	}
}
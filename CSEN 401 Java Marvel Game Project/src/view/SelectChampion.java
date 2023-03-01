package view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import engine.Game;
import engine.Player;
import model.world.Champion;

@SuppressWarnings({"serial"})
public class SelectChampion extends JPanel{
	private static Player first;
	private static Player second;
	private static JButton continueButton = new JButton("Continue");
	private static JLabel showCaseChampionImageLabel = new JLabel();
	private static JTextArea showCaseChampionAttributesLabel = new JTextArea();
	private ChampionCard[] champions = new ChampionCard[Game.getAvailableChampions().size()];
	private JPanel boardOfCards = new JPanel();
	
	public SelectChampion(Player first, Player second, ActionListener e) {
		SelectChampion.first = first;
		SelectChampion.second = second;
		this.setLayout(null);
		this.setBounds(0, 0, 1200, 650);
		this.setBackground(new Color(0x123456));
		//this.setBackground(Color.gray);
		boardOfCards.setLayout(new GridLayout(5, 3));
		boardOfCards.setBounds(0, 0, 500, 500);
		boardOfCards.setBackground(Color.black);
		for(int i = 0; i < Game.getAvailableChampions().size(); i++) {
			champions[i] = new ChampionCard(Game.getAvailableChampions().get(i).getName());
			champions[i].setPreferredSize(new Dimension(boardOfCards.getWidth(), boardOfCards.getHeight()));
			boardOfCards.add(champions[i]);
		}
		this.add(boardOfCards);
		showCaseChampionImageLabel.setBounds(500,0,400,400);
		showCaseChampionImageLabel.setBackground(Color.red);
		showCaseChampionImageLabel.setPreferredSize(new Dimension(400, 400));
		showCaseChampionImageLabel.setOpaque(true);
		showCaseChampionAttributesLabel.setBounds(900, 0, getWidth(), 400);
		showCaseChampionAttributesLabel.setPreferredSize(new Dimension(getWidth(), 400));
		showCaseChampionAttributesLabel.setEditable(false);
		this.add(showCaseChampionImageLabel);
		this.add(showCaseChampionAttributesLabel);
		
		continueButton.setBounds(getWidth() - 150, getHeight() - 100, 100, 50);
		continueButton.setFocusable(false);
		continueButton.addActionListener(e);
		continueButton.setEnabled(false);
		this.add(continueButton);
	}
	
	public static void addChampionToFirstPlayer(String name) {
		for(Champion c : Game.getAvailableChampions()) {
			if(c.getName().equals(name) && !first.getTeam().contains(c)) {
				first.getTeam().add(c);
				return;
			}
		}
	}
	
	public static void addChampionToSecondPlayer(String name) {
		for(Champion c : Game.getAvailableChampions()) {
			if(c.getName().equals(name) && !second.getTeam().contains(c)) {
				second.getTeam().add(c);
				return;
			}
		}
	}
	
	public static void setVisisbilityOfImagesAndAttributes(ChampionCard c) {
		showCaseChampionImageLabel.setIcon(new ImageIcon(new ImageIcon(c.getChampionImageName()).getImage().getScaledInstance(showCaseChampionImageLabel.getHeight(), showCaseChampionImageLabel.getWidth(), Image.SCALE_SMOOTH)));
		for(Champion e : Game.getAvailableChampions()) {
			if(e.getName().equals(c.getChampionName())) {
				showCaseChampionAttributesLabel.setText(e.toString2());
				break;
			}
		}
	}
	
	public static Player getFirstPlayer() {
		return first;
	}
	
	public static Player getSecondPlayer() {
		return second;
	}
	public static JButton getContinueButton() {
		return continueButton;
	}
}
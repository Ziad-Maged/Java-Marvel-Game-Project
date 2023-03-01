package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import engine.Player;

@SuppressWarnings({"serial"})
public class SelectLeader extends JPanel{
	private static ChampionCard[] teams = new ChampionCard[6];
	private static boolean isSelectingFirstLeader;
	private static boolean isSelectingSecondLeader;
	private static Player first;
	private static Player second;
	private JPanel firstPlayerChampionsPanel = new JPanel(new GridLayout(1, 3));
	private JPanel secondPlayerChampionsPanel = new JPanel(new GridLayout(1, 3));
	private static JButton continueButton = new JButton("Continue");
	public SelectLeader(Player first, Player second, ActionListener e) {
		SelectLeader.first = first;
		SelectLeader.second = second;
		JLabel firstLeaderText = new JLabel(first.getName() + "'s Champions: ");
		JLabel secondLeaderText = new JLabel(second.getName() + "'s Champions: ");
		firstLeaderText.setForeground(Color.black);
		secondLeaderText.setForeground(Color.black);
		JLabel title = new JLabel("Select Your Leaders.");
		title.setForeground(Color.black);
		this.setLayout(null);
		this.setBounds(0, 0, 1200, 650);
		this.setPreferredSize(new Dimension(getWidth(), getHeight()));
		this.setBackground(new Color(0x123456));
		//this.setBackground(Color.gray);
		firstLeaderText.setBounds(0, getHeight() / 4, 150, 50);
		secondLeaderText.setBounds(0, getHeight() / 2, 170, 50);
		title.setBounds(getWidth() / 2, 0, 200, 50);
		firstPlayerChampionsPanel.setBounds(190, getHeight() / 4 - 40, 500, 150);
		secondPlayerChampionsPanel.setBounds(190, getHeight() / 2 - 20, 500, 150);
		for(int i = 0; i < first.getTeam().size(); i++) {
			teams[i] = new ChampionCard(first.getTeam().get(i).getName());
			firstPlayerChampionsPanel.add(teams[i]);
		}
		for(int i = 0; i < second.getTeam().size(); i++) {
			teams[i + 3] = new ChampionCard(second.getTeam().get(i).getName());
			teams[i + 3].setClickable(false);
			secondPlayerChampionsPanel.add(teams[i + 3]);
		}
		this.add(firstLeaderText);
		this.add(secondLeaderText);
		this.add(title);
		this.add(firstPlayerChampionsPanel);
		this.add(secondPlayerChampionsPanel);
		continueButton.setEnabled(false);
		continueButton.setFocusable(false);
		continueButton.setBounds(getWidth() - 150, getHeight() - 100, 100, 50);
		continueButton.addActionListener(e);
		this.add(continueButton);
	}
	
	
	public static void setIsSelectingFirstLeader(boolean isSelectingFirstLeader) {
		SelectLeader.isSelectingFirstLeader = isSelectingFirstLeader;
	}
	public static void setIsSelectingSecondLeader(boolean isSelectingSecondLeader) {
		SelectLeader.isSelectingSecondLeader = isSelectingSecondLeader;
	}
	public static boolean isSelectingFirstLeader() {
		return isSelectingFirstLeader;
	}
	public static boolean isSelectingSecondLeader() {
		return isSelectingSecondLeader;
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
	public static void setFirstPlayerUnClickable() {
		for(int i = 0; i < 3; i++) {
			teams[i].setClickable(false);
		}
	}
	public static void setSecondPlayerClickable() {
		for(int i = 3; i < 6; i++) {
			teams[i].setClickable(true);
		}
	}
	public static void setSecondPlayerUnClickable() {
		for(int i = 3; i < 6; i++) {
			teams[i].setClickable(false);
		}
	}
}
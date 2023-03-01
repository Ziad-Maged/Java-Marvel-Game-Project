package view;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class PlayerNames extends JPanel{
	private String firstPlayer;
	private String secondPlayer;
	private JButton continueButton = new JButton("Continue");
	private JLabel firstName = new JLabel("Enter First Player Name: ");
	private JLabel secondName = new JLabel("Enter Second Player Name: ");
	private JTextField firstText = new JTextField();
	private JTextField secondText = new JTextField();
	
	public PlayerNames(ActionListener e) {
		this.setLayout(null);
		this.setBounds(0, 0, 1200, 650);
		this.setPreferredSize(new Dimension(getWidth(), getHeight()));
		this.setBackground(new Color(0x123456));
		//this.setBackground(Color.gray);
		
		continueButton.addActionListener(e);
		continueButton.setBounds(getWidth() - 150, getHeight() - 100, 100, 50);
		continueButton.setFocusable(false);
		
		firstName.setBounds(0, getHeight() / 4, 150, 50);
		firstName.setForeground(Color.black);
		secondName.setBounds(0, getHeight() / 2, 170, 50);
		secondName.setForeground(Color.black);
		
		firstText.setText("Player 1");
		firstText.setBounds(160, getHeight() / 4 + 10, 150, 30);
		secondText.setText("Player 2");
		secondText.setBounds(180, getHeight() / 2 + 10, 150, 30);
		
		this.add(continueButton);
		this.add(firstName);
		this.add(secondName);
		this.add(secondText);
		this.add(firstText);
	}
	
	public String getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(String firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public String getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(String secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public JButton getContinueButton() {
		return continueButton;
	}
	public JTextField getFirstText() {
		return firstText;
	}
	public JTextField getSecondText() {
		return secondText;
	}
}

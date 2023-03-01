package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class WelcomePanel extends JPanel{
	private JButton newGame = new JButton("New Game");
	private JButton help = new JButton("Help");
	private JButton exit = new JButton("Exit");
	private JLabel label = new JLabel("Marvel Ultimate War");
	
	public WelcomePanel(ActionListener e) {
		label.setBounds(450, 125, 300, 300);
		label.setFont(new Font("MV Boli", Font.PLAIN, 30));
		label.setForeground(Color.red);
		this.setLayout(null);
		this.setBounds(0, 0, 1200, 650);
		this.setPreferredSize(new Dimension(getWidth(), getHeight()));
		this.setBackground(new Color(0x123456));
		this.add(label);
		
		newGame.setBounds(500, 300, 100, 50);
		newGame.addActionListener(e);
		newGame.setFocusable(false);
		help.setBounds(500, 360, 100, 50);
		help.addActionListener(e);
		help.setFocusable(false);
		exit.setBounds(500, 420, 100, 50);
		exit.addActionListener(e);
		exit.setFocusable(false);
		
		this.add(newGame);
		this.add(help);
		this.add(exit);
	}
	
	public JButton getNewGame() {
		return newGame;
	}
	public JButton getHelp() {
		return help;
	}
	public JButton getExit() {
		return exit;
	}
}

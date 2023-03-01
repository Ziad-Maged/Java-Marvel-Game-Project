package view;

import javax.swing.*;
import engine.*;
import exceptions.GameActionException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.world.*;

import java.awt.event.*;
import java.io.IOException;
import java.awt.*;

@SuppressWarnings({"serial"})
public class GameFrame extends JFrame implements ActionListener, KeyListener{
	
	private WelcomePanel welcomePanel = new WelcomePanel(this);
	private PlayerNames playerNamesPanel = new PlayerNames(this);
	private SelectChampion championSelectionPanel;
	private SelectLeader leaderSelectionPanel;
	private static GamePanel theViewGamePanel;
	private static Player first;
	private static Player second;
	private static Game game;
	private static Ability DIRECTIONALAbility;
	public GameFrame() {
		try {
			Game.loadAbilities("Abilities.csv");
			Game.loadChampions("Champions.csv");
		} catch (IOException e1) {}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 650);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.add(welcomePanel, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.setTitle("Marvel Ultimate War");
		ImageIcon image = new ImageIcon("images.jfif");
		this.setIconImage(image.getImage());
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == welcomePanel.getNewGame()) {
			this.remove(welcomePanel);
			this.add(playerNamesPanel, BorderLayout.CENTER);
			this.repaint();
			this.validate();
			JOptionPane.showConfirmDialog(null,
					"You can enter player names or you could use the default names.",
					getTitle(), JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE);
		}else if(e.getSource() == welcomePanel.getHelp()) {
			JOptionPane.showConfirmDialog(null,
	                "You Should use W, A, S, and D to move up, left, down, and right respectivly.\n"
	                + "You Should use UP, DOWN, LEFT, RIGHT arrows to attack up, down, left, and right respectivly.\n"
	                + "You should press the buttons 1, 2, or 3 to cast the champion's first, second, or third abilities respectivly.\n"
	                + "Should the ability be a Singletarget ability you will be asked to choose a cell from the board.\n"
	                + "However, if it is directional, you will be asked to chose a direction using the W, A, S, and D keys for up, left, down, and right respectivly.\n"
	                + "Should the champion be disarmed, you can access his punch(s) ability using the 4 button.\n"
	                + "To use the leader's ability you sould press the button 5.\n"
	                + "You could end the champion's turn by pressing the space bar.",
	                getTitle(),
	                JOptionPane.DEFAULT_OPTION,
	                JOptionPane.PLAIN_MESSAGE);
		}else if(e.getSource() == welcomePanel.getExit()) {
			this.dispose();
		}else if(e.getSource() == playerNamesPanel.getContinueButton()) {
			playerNamesPanel.setFirstPlayer(playerNamesPanel.getFirstText().getText());
			playerNamesPanel.setSecondPlayer(playerNamesPanel.getSecondText().getText());
			first = new Player(playerNamesPanel.getFirstPlayer());
			second = new Player(playerNamesPanel.getSecondPlayer());
			championSelectionPanel = new SelectChampion(first, second, this);
			this.remove(playerNamesPanel);
			this.add(championSelectionPanel);
			this.repaint();
			this.validate();
			JOptionPane.showConfirmDialog(null,
					first.getName() + " should choose his/her champions first"
							+ " then " + second.getName() + " Should choose his/her champions.",
					getTitle(), JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE);
		}else if(e.getSource() == SelectChampion.getContinueButton()) {
			this.remove(championSelectionPanel);
			leaderSelectionPanel = new SelectLeader(first, second, this);
			SelectLeader.setIsSelectingFirstLeader(true);
			this.add(leaderSelectionPanel);
			this.repaint();
			this.validate();
			JOptionPane.showConfirmDialog(null,
					first.getName() + " should choose his/her leader first"
							+ " then " + second.getName() + " Should choose leader.",
					getTitle(), JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE);
		}else if(e.getSource() == SelectLeader.getContinueButton()) {
			this.remove(leaderSelectionPanel);
			this.addKeyListener(this);
			game = new Game(first, second);
			theViewGamePanel = new GamePanel(game, this);
			this.add(theViewGamePanel);
			this.repaint();
			this.validate();
			JOptionPane.showConfirmDialog(null,
					"You May Begin",
					getTitle(), JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE);
			JOptionPane.showConfirmDialog(null,
	                "You Should use W, A, S, and D to move up, left, down, and right respectivly.\n"
	                + "You Should use UP, DOWN, LEFT, RIGHT arrows to attack up, down, left, and right respectivly.\n"
	                + "You should press the buttons 1, 2, or 3 to cast the champion's first, second, or third abilities respectivly.\n"
	                + "Should the ability be a Singletarget ability you will be asked to choose a cell from the board.\n"
	                + "However, if it is directional, you will be asked to chose a direction using the W, A, S, and D keys for up, left, down, and right respectivly.\n"
	                + "Should the champion be disarmed, you can access his punch(s) ability using the 4 button.\n"
	                + "To use the leader's ability you sould press the button 5.\n"
	                + "You could end the champion's turn by pressing the space bar.\n"
	                + "On the right you can see an image of the current champion and the associated attributes.\n"
	                + "At any time if either of the players find the attributes incomplete or want to view the attributes of the other player, you could hover over \nthe player and see the tooltip with all the attributes.",
	                getTitle() + " Help",
	                JOptionPane.DEFAULT_OPTION,
	                JOptionPane.PLAIN_MESSAGE);
		}
		this.repaint();
		this.validate();
	}
	
	public static Player getFirstPlayer() {
		return first;
	}
	
	public static Player getSecondPlayer() {
		return second;
	}
	
	public static Game getGame() {
		return game;
	}
	
	public static void main(String[] args) {
		new GameFrame();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		try {
			Point p = game.getCurrentChampion().getLocation();
			if(e.getKeyCode() == KeyEvent.VK_W && World.isCastingDIRECTIONALAbiity()) {
				World.setCastingDIRECTIONALAbility(false);
				game.castAbility(DIRECTIONALAbility, Direction.UP);
				DIRECTIONALAbility = null;
				World.hyperRender();
			}else if(e.getKeyCode() == KeyEvent.VK_S && World.isCastingDIRECTIONALAbiity()) {
				World.setCastingDIRECTIONALAbility(false);
				game.castAbility(DIRECTIONALAbility, Direction.DOWN);
				DIRECTIONALAbility = null;
				World.hyperRender();
			}else if(e.getKeyCode() == KeyEvent.VK_A && World.isCastingDIRECTIONALAbiity()) {
				World.setCastingDIRECTIONALAbility(false);
				game.castAbility(DIRECTIONALAbility, Direction.LEFT);
				DIRECTIONALAbility = null;
				World.hyperRender();
			}else if(e.getKeyCode() == KeyEvent.VK_D && World.isCastingDIRECTIONALAbiity()) {
				World.setCastingDIRECTIONALAbility(false);
				game.castAbility(DIRECTIONALAbility, Direction.RIGHT);
				DIRECTIONALAbility = null;
				World.hyperRender();
			}else if(e.getKeyCode() == KeyEvent.VK_W && !World.isCastingDIRECTIONALAbiity()) {
				game.move(Direction.UP);
				World.getActualBoard()[(int)p.getX()][(int)p.getY()].render();
				World.getActualBoard()[(int)p.getX() + 1][(int)p.getY()].render();
			}else if(e.getKeyCode() == KeyEvent.VK_S && !World.isCastingDIRECTIONALAbiity()) {
				game.move(Direction.DOWN);
				World.getActualBoard()[(int)p.getX()][(int)p.getY()].render();
				World.getActualBoard()[(int)p.getX() - 1][(int)p.getY()].render();
			}else if(e.getKeyCode() == KeyEvent.VK_A && !World.isCastingDIRECTIONALAbiity()) {
				game.move(Direction.LEFT);
				World.getActualBoard()[(int)p.getX()][(int)p.getY()].render();
				World.getActualBoard()[(int)p.getX()][(int)p.getY() - 1].render();
			}else if(e.getKeyCode() == KeyEvent.VK_D && !World.isCastingDIRECTIONALAbiity()) {
				game.move(Direction.RIGHT);
				World.getActualBoard()[(int)p.getX()][(int)p.getY()].render();
				World.getActualBoard()[(int)p.getX()][(int)p.getY() + 1].render();
			}else if(e.getKeyCode() == KeyEvent.VK_UP && !World.isCastingDIRECTIONALAbiity()) {
				game.attack(Direction.UP);
				World.hyperRender();
			}else if(e.getKeyCode() == KeyEvent.VK_DOWN && !World.isCastingDIRECTIONALAbiity()) {
				game.attack(Direction.DOWN);
				World.hyperRender();
			}else if(e.getKeyCode() == KeyEvent.VK_LEFT && !World.isCastingDIRECTIONALAbiity()) {
				game.attack(Direction.LEFT);
				World.hyperRender();
			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT && !World.isCastingDIRECTIONALAbiity()) {
				game.attack(Direction.RIGHT);
				World.hyperRender();
			}else if(e.getKeyCode() == KeyEvent.VK_SPACE && !World.isCastingDIRECTIONALAbiity()) {
				game.endTurn();
				GamePanel.updateTurnOrder();
				GamePanel.updateTurnOrder();
			}else if(e.getKeyCode() == KeyEvent.VK_1 && !World.isCastingDIRECTIONALAbiity()) {
				if(game.getCurrentChampion().getAbilities().get(0).getCastArea() == AreaOfEffect.SINGLETARGET) {
					Cell.setCastingSINGLETARGETAbility(true);
					Cell.setAbility(game.getCurrentChampion().getAbilities().get(0));
				}else if(game.getCurrentChampion().getAbilities().get(0).getCastArea() == AreaOfEffect.DIRECTIONAL) {
					World.setCastingDIRECTIONALAbility(true);
					DIRECTIONALAbility = game.getCurrentChampion().getAbilities().get(0);
				}else {
					game.castAbility(game.getCurrentChampion().getAbilities().get(0));
				}
				World.hyperRender();
			}else if(e.getKeyCode() == KeyEvent.VK_2 && !World.isCastingDIRECTIONALAbiity()) {
				if(game.getCurrentChampion().getAbilities().get(1).getCastArea() == AreaOfEffect.SINGLETARGET) {
					Cell.setCastingSINGLETARGETAbility(true);
					Cell.setAbility(game.getCurrentChampion().getAbilities().get(1));
				}else if(game.getCurrentChampion().getAbilities().get(1).getCastArea() == AreaOfEffect.DIRECTIONAL) {
					World.setCastingDIRECTIONALAbility(true);
					DIRECTIONALAbility = game.getCurrentChampion().getAbilities().get(1);
				}else {
					game.castAbility(game.getCurrentChampion().getAbilities().get(1));
				}
				World.hyperRender();
			}else if(e.getKeyCode() == KeyEvent.VK_3 && !World.isCastingDIRECTIONALAbiity()) {
				if(game.getCurrentChampion().getAbilities().get(2).getCastArea() == AreaOfEffect.SINGLETARGET) {
					Cell.setCastingSINGLETARGETAbility(true);
					Cell.setAbility(game.getCurrentChampion().getAbilities().get(2));
				}else if(game.getCurrentChampion().getAbilities().get(2).getCastArea() == AreaOfEffect.DIRECTIONAL) {
					World.setCastingDIRECTIONALAbility(true);
					DIRECTIONALAbility = game.getCurrentChampion().getAbilities().get(2);
				}else {
					game.castAbility(game.getCurrentChampion().getAbilities().get(2));
				}
				World.hyperRender();
			}else if(e.getKeyCode() == KeyEvent.VK_4 && !World.isCastingDIRECTIONALAbiity()) {
				if(game.getCurrentChampion().isPunchAvailable() == -1) {
					JOptionPane.showConfirmDialog(null, "Button will Only work if Champion is Disarmed",
							getTitle(), JOptionPane.OK_CANCEL_OPTION);
				}else {
					Cell.setCastingSINGLETARGETAbility(true);
					Cell.setAbility(game.getCurrentChampion().getAbilities().get(game.getCurrentChampion().isPunchAvailable()));
				}
			}else if(e.getKeyCode() == KeyEvent.VK_5 && !World.isCastingDIRECTIONALAbiity()) {
				game.useLeaderAbility();
				World.hyperRender();
			}else if(e.getKeyCode() == KeyEvent.VK_H) {
				JOptionPane.showConfirmDialog(null,
		                "You Should use W, A, S, and D to move up, left, down, and right respectivly.\n"
		                + "You Should use UP, DOWN, LEFT, RIGHT arrows to attack up, down, left, and right respectivly.\n"
		                + "You should press the buttons 1, 2, or 3 to cast the champion's first, second, or third abilities respectivly.\n"
		                + "Should the ability be a Singletarget ability you will be asked to choose a cell from the board.\n"
		                + "However, if it is directional, you will be asked to chose a direction using the W, A, S, and D keys for up, left, down, and right respectivly.\n"
		                + "Should the champion be disarmed, you can access his punch(s) ability using the 4 button.\n"
		                + "To use the leader's ability you sould press the button 5.\n"
		                + "You could end the champion's turn by pressing the space bar.\n"
		                + "On the right you can see an image of the current champion and the associated attributes.\n"
		                + "At any time if either of the players find the attributes incomplete or want to view the attributes of the other player, you could hover over \nthe player and see the tooltip with all the attributes.",
		                getTitle() + " Help",
		                JOptionPane.DEFAULT_OPTION,
		                JOptionPane.PLAIN_MESSAGE);
			}
			if(game.checkGameOver() != null) {
				this.dispose();
				JOptionPane.showConfirmDialog(null,
						game.checkGameOver().getName() + " is The Winner",
						getTitle(), JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				return;
			}
			GamePanel.updateCurrentChampionImageLabelAndAttributeText();
			GamePanel.updatePlayerChampionsLabels();
			this.repaint();
			this.validate();
		}catch(GameActionException e1) {
			JOptionPane.showConfirmDialog(null,
					e1.getMessage(),
					getTitle(), JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE);
		}catch(CloneNotSupportedException e1) {
			
		}
	}
}
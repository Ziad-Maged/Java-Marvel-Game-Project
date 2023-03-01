package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import engine.Game;
import exceptions.*;
import model.abilities.Ability;
import model.world.Champion;
import model.world.Cover;

@SuppressWarnings("serial")
public class Cell extends JLabel implements MouseListener{
	private ImageIcon image;
	private static Game game;
	private int x;
	private int y;
	private static Ability SINGLETARGETAbility;
	private static boolean castingSINGLETARGETAbility;
	public Cell(int x, int y) {
		SINGLETARGETAbility = null;
		this.x = x;
		this.y = y;
		this.addMouseListener(this);
		if(game.getBoard()[x][y] == null) {
			this.setBackground(Color.white);
			this.setBorder(new TitledBorder(null, "Empty Cell"));
		}else if(game.getBoard()[x][y] instanceof Champion) {
			this.setBackground(Color.white);
			this.setIcon(((Champion)game.getBoard()[x][y]).getChampionImage());
			image = ((Champion)game.getBoard()[x][y]).getChampionImage();
			this.setIcon(image);
			this.setBorder(new TitledBorder(null, "HP: " + ((Champion)game.getBoard()[x][y]).getCurrentHP()));
		}else if(game.getBoard()[x][y] instanceof Cover) {
			this.setBackground(((Cover)game.getBoard()[x][y]).getCoverColor());
			this.setBorder(new TitledBorder(null, "Cover HP: " + ((Cover)game.getBoard()[x][y]).getCurrentHP()));
		}
		this.setOpaque(true);
	}
	
	public boolean isCastingSINGLETARGETAbility() {
		return castingSINGLETARGETAbility;
	}

	public static void setCastingSINGLETARGETAbility(boolean castingSINGLETARGETAbility) {
		Cell.castingSINGLETARGETAbility = castingSINGLETARGETAbility;
	}
	
	public Ability getSINGLETARGETAbility() {
		return SINGLETARGETAbility;
	}
	
	public static void setAbility(Ability SINGLETARGETAbility) {
		Cell.SINGLETARGETAbility = SINGLETARGETAbility;
	}
	
	public void setOccupied(Champion c) {
		this.setBackground(Color.white);
		this.setIcon(c.getChampionImage());
	}
	
	public void setUnoccupied() {
		this.setBackground(Color.white);
		this.setIcon(null); 
	}
	public void render() {
		if(game.getBoard()[x][y] == null) {
			setUnoccupied();
			this.setBorder(new TitledBorder(null, "Empty Cell"));
		}else if(game.getBoard()[x][y] instanceof Champion) {
			this.setBackground(Color.white);
			this.setIcon(((Champion)game.getBoard()[x][y]).getChampionImage());
			image = ((Champion)game.getBoard()[x][y]).getChampionImage();
			this.setIcon(image);
			this.setBorder(new TitledBorder(null, "HP: " + ((Champion)game.getBoard()[x][y]).getCurrentHP()));
		}else if(game.getBoard()[x][y] instanceof Cover) {
			this.setBackground(((Cover)game.getBoard()[x][y]).getCoverColor());
			this.setBorder(new TitledBorder(null, "Cover HP: " + ((Cover)game.getBoard()[x][y]).getCurrentHP()));
		}
	}
	public static void setGlobalGame(Game game) {
		Cell.game = game;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(castingSINGLETARGETAbility) {
			try {
				game.castAbility(SINGLETARGETAbility, x, y);
			}catch(CloneNotSupportedException e1) {
				JOptionPane.showConfirmDialog(null, "Coding Error Occured for some Unknown reason.");
			}catch(GameActionException e1) {
				JOptionPane.showConfirmDialog(null,
						e1.getMessage(),
						"Marvel Ultimate War", JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE);
			}
			castingSINGLETARGETAbility = false;
			if(game.getBoard()[x][y] == null) {
				this.setBackground(Color.white);
				this.setBorder(new TitledBorder(null, "Empty Cell"));
			}else if(game.getBoard()[x][y] instanceof Cover) {
				this.setBackground(((Cover)game.getBoard()[x][y]).getCoverColor());
				this.setBorder(new TitledBorder(null, "Cover HP: " + ((Cover)game.getBoard()[x][y]).getCurrentHP()));
			}else if(game.getBoard()[x][y] instanceof Champion) {
				this.setBackground(Color.white);
				this.setBorder(new TitledBorder(null, "HP: " + ((Champion)game.getBoard()[x][y]).getCurrentHP()));
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!castingSINGLETARGETAbility) {
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(!castingSINGLETARGETAbility) {
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(castingSINGLETARGETAbility) {
			if(game.getBoard()[x][y] == null) {
				this.setBackground(Color.magenta);
			}else if(game.getBoard()[x][y] instanceof Cover) {
				this.setBackground(Color.green);
			}else if(game.getBoard()[x][y] instanceof Champion) {
				this.setBackground(Color.red);
			}
		}else {
			if(game.getBoard()[x][y] instanceof Champion) {
				this.setToolTipText(((Champion)game.getBoard()[x][y]).toString());
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(castingSINGLETARGETAbility) {
			if(game.getBoard()[x][y] == null) {
				this.setBackground(Color.white);
			}else if(game.getBoard()[x][y] instanceof Cover) {
				this.setBackground(((Cover)game.getBoard()[x][y]).getCoverColor());
			}else if(game.getBoard()[x][y] instanceof Champion) {
				this.setBackground(Color.white);
			}
		}
	}
}
package view;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import engine.Game;

@SuppressWarnings({"serial"})
public class World extends JPanel {
	private Game game;
	private static Cell[][] actualBoard;
	private static boolean castingDIRECTIONALAbility;
	public World(Game game, KeyListener e) {
		this.game = game;
		Cell.setGlobalGame(this.game);
		actualBoard = new Cell[Game.getBoardheight()][Game.getBoardwidth()];
		this.setLayout(new GridLayout(Game.getBoardheight(), Game.getBoardwidth()));
		this.setBounds(0, 0, 500, 500);
		this.addKeyListener(e);
		for(int i = Game.getBoardheight() - 1; i >= 0; i--) {
			for(int j = 0; j < Game.getBoardwidth(); j++) {
				actualBoard[i][j] = new Cell(i, j);
				this.add(actualBoard[i][j]);
			}
		}
	}
	public static Cell[][] getActualBoard(){
		return actualBoard;
	}
	public static boolean isCastingDIRECTIONALAbiity() {
		return castingDIRECTIONALAbility;
	}
	public static void setCastingDIRECTIONALAbility(boolean casting) {
		castingDIRECTIONALAbility = casting;
	}
	public static void hyperRender() {
		for(int i = Game.getBoardheight() - 1; i >= 0; i--) {
			for(int j = 0; j < Game.getBoardwidth(); j++) {
				actualBoard[i][j].render();
			}
		}
	}
}
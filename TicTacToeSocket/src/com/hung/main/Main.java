package com.hung.main;

import javax.swing.JOptionPane;

import com.hung.game.ClientGame;
import com.hung.game.ServerGame;

public class Main {

	public static void main(String[] args) {
		int choice = Integer.parseInt(JOptionPane.showInputDialog("1 for sever (Player 1) | 2 for client (Player 2)"));

		if (choice == 1) {
			new ServerGame();
		} else if (choice == 2) {
			new ClientGame();
		}

	}

}

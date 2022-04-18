package com.hung.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

import com.hung.game.Game;

public class GUI extends JFrame {

	public GUI(String title, int width, int height) {
		setLayout(new GridLayout(1,0));

		setTitle(title);
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}
}

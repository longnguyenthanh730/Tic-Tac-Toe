package com.hung.ui;

import com.hung.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private final JButton[][] buttons;

    private final Game game;

    public GamePanel(Game game) {
        this.game = game;
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                buttons[x][y] = new JButton();
                add(buttons[x][y]);
                buttons[x][y].setFont(new Font("MV Boli", Font.BOLD, 120));
                buttons[x][y].setFocusable(false);
                buttons[x][y].addActionListener(this);

            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (e.getSource().equals(buttons[x][y])) {
                    if (buttons[x][y].getText().equals("")) {
                        if (game.getThisPlayer() == 1) {
                            buttons[x][y].setForeground(new Color(255, 0, 0));
                            buttons[x][y].setText("X");
                            setEnableButton(false);
                            game.setFields(x, y, Game.PLAYER_ONE);
                            game.sendPacketPlay(x, y, Game.PLAYER_ONE);

                        } else {
                            buttons[x][y].setForeground(new Color(0, 0, 255));
                            buttons[x][y].setText("O");
                            setEnableButton(false);
                            game.setFields(x, y, Game.PLAYER_TWO);
                            game.sendPacketPlay(x, y, Game.PLAYER_TWO);

                        }
                    }
                }
            }
        }

    }

    public void setEnableButton(boolean b) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (buttons[x][y].getText().equals("")) {
                    buttons[x][y].setEnabled(b);

                }
            }
        }
    }

    public void setButton(int x, int y, int value) {
        if (game.getThisPlayer() == 1) {
            game.setFields(x, y, value);
            buttons[x][y].setForeground(new Color(0, 0, 255));
            buttons[x][y].setText("O");

        } else {
            game.setFields(x, y, value);
            buttons[x][y].setForeground(new Color(255, 0, 0));
            buttons[x][y].setText("X");
        }
    }

    public void newGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(UIManager.getColor("buttons.background"));
                setEnableButton(true);
            }
        }
    }

    public void setButtonWhenWin(int a, int b, int c, int d, int x, int y) {
        buttons[a][b].setBackground(Color.GREEN);
        buttons[c][d].setBackground(Color.GREEN);
        buttons[x][y].setBackground(Color.GREEN);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
}

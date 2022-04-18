package com.hung.game;

import com.hung.connect.Connection;
import com.hung.packet.PacketDataChat;
import com.hung.packet.PacketDataPlay;
import com.hung.ui.ChatPanel;
import com.hung.ui.GUI;
import com.hung.ui.GamePanel;

import javax.swing.*;

public abstract class Game {
    public static final int PORT = 11111;
    public static final int WIDTH = 1000, HEIGHT = 550;
	public static final int PLAYER_ONE = 1;
	public static final int PLAYER_TWO = 2;

    private final ChatPanel chatPanel;
    public GamePanel gamePanel;
    protected int thisPlayer;
    protected Connection connection;
    private boolean play = true;
    private int[][] fields;

    public Game(int thisPlayer) {

        gamePanel = new GamePanel(this);
        chatPanel = new ChatPanel(this);
        GUI gui = new GUI("Tic-Tac-Toe", WIDTH, HEIGHT);
        this.thisPlayer = thisPlayer;
        fields = new int[3][3];

        gui.add(gamePanel);
        gui.add(chatPanel);

        gui.setVisible(true);

    }

    public void receivedPacket(Object object) {
        if (play) {
            if (object instanceof PacketDataPlay) {
                PacketDataPlay packet = (PacketDataPlay) object;
                gamePanel.setEnableButton(true);
                if (thisPlayer == PLAYER_ONE) {
                    gamePanel.setButton(packet.getX(), packet.getY(), PLAYER_TWO);
                } else {
                    gamePanel.setButton(packet.getX(), packet.getY(), PLAYER_ONE);
                }

            }
        }
        if (object instanceof PacketDataChat) {
            PacketDataChat dataChat = (PacketDataChat) object;
            chatPanel.updateConverstation(dataChat.getSmg());
        }

    }

    public void sendPacketPlay(int x, int y, int player) {
        connection.sendPacket(new PacketDataPlay(x, y, thisPlayer));
        checkWin(thisPlayer);
    }

    public void sendPacketChat(String smg) {
        connection.sendPacket(new PacketDataChat(smg));
    }

    protected void checkWin(int player) {
        if (check(player)) {
            int choice = JOptionPane.showConfirmDialog(gamePanel,
                    "Player " + player + " won! , do you want to play again?", "Notification",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                fields = new int[3][3];
                gamePanel.newGame();
            } else {
                play = false;
            }
        } else if (tie()) {
            int choice = JOptionPane.showConfirmDialog(gamePanel, "TIE! , do you want to play again?", "Notification",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                fields = new int[3][3];
                gamePanel.newGame();
            } else {
                play = false;
            }
        }
    }

    private boolean check(int currentPlayer) {
        if ((fields[0][0] == currentPlayer) && (fields[0][1] == currentPlayer) && (fields[0][2] == currentPlayer)) {
            gamePanel.setButtonWhenWin(0, 0, 0, 1, 0, 2);
            return true;
        }
        if ((fields[1][0] == currentPlayer) && (fields[1][1] == currentPlayer) && (fields[1][2] == currentPlayer)) {
            gamePanel.setButtonWhenWin(1, 0, 1, 1, 1, 2);
            return true;
        }
        if ((fields[2][0] == currentPlayer) && (fields[2][1] == currentPlayer) && (fields[2][2] == currentPlayer)) {
            gamePanel.setButtonWhenWin(2, 0, 2, 1, 2, 2);
            return true;
        }
        if ((fields[0][0] == currentPlayer) && (fields[1][0] == currentPlayer) && (fields[2][0] == currentPlayer)) {
            gamePanel.setButtonWhenWin(0, 0, 1, 0, 2, 0);
            return true;
        }
        if ((fields[0][1] == currentPlayer) && (fields[1][1] == currentPlayer) && (fields[2][1] == currentPlayer)) {
            gamePanel.setButtonWhenWin(0, 1, 1, 1, 2, 1);
            return true;
        }
        if ((fields[0][2] == currentPlayer) && (fields[1][2] == currentPlayer) && (fields[2][2] == currentPlayer)) {
            gamePanel.setButtonWhenWin(0, 2, 1, 2, 2, 2);
            return true;
        }
        if ((fields[0][0] == currentPlayer) && (fields[1][1] == currentPlayer) && (fields[2][2] == currentPlayer)) {
            gamePanel.setButtonWhenWin(0, 0, 1, 1, 2, 2);
            return true;
        }
        if ((fields[0][2] == currentPlayer) && (fields[1][1] == currentPlayer) && (fields[2][0] == currentPlayer)) {
            gamePanel.setButtonWhenWin(0, 2, 1, 1, 2, 0);
            return true;
        }

        return false;
    }

    private boolean tie() {
        int count = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (fields[x][y] != 0) {
                    count++;
                }
            }
        }
        return count == 9;
    }

    public int getThisPlayer() {
        return thisPlayer;

    }

    public void setFields(int x, int y, int value) {
        this.fields[x][y] = value;
    }

    protected void close() {
        connection.close();
    }

}

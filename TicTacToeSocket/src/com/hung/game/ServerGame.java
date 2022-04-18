package com.hung.game;

import com.hung.connect.Connection;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerGame extends Game {
    private ServerSocket serverSocket;
    private Socket socket;

    public ServerGame() {
        super(Game.PLAYER_ONE);
        try {
            serverSocket = new ServerSocket(Game.PORT);
            socket = serverSocket.accept();
            connection = new Connection(this, socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receivedPacket(Object object) {
        super.receivedPacket(object);

        checkWin(PLAYER_TWO);

    }

    @Override
    protected void close() {
        super.close();
        try {
            serverSocket.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

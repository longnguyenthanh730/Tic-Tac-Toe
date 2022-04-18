package com.hung.connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.hung.game.Game;

public class Connection implements Runnable {
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;

	private boolean running;

	private final Game game;

	public Connection(Game game, Socket socket) {
		this.game = game;
		try {
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(this).start();
	}

	@Override
	public void run() {
		running = true;
		while (running) {
			try {
				Object object = inputStream.readObject();
				game.receivedPacket(object);
			} catch (ClassNotFoundException | IOException e) {
				running = false;
				e.printStackTrace();
			}
		}

	}

	public void sendPacket(Object object) {
		try {
			outputStream.reset();
			outputStream.writeObject(object);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			running = false;
			inputStream.close();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

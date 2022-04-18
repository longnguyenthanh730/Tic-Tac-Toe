package com.hung.packet;

import java.io.Serializable;

public class PacketDataPlay implements Serializable {
	int x, y,thisPlayer;

	public PacketDataPlay(int x, int y,int thisPlayer) {
		this.thisPlayer=thisPlayer;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}

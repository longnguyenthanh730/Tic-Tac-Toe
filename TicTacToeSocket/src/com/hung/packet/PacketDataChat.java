package com.hung.packet;

import java.io.Serializable;

public class PacketDataChat implements Serializable {
	String smg;

	public PacketDataChat(String smg) {
		this.smg = smg;
	}

	public String getSmg() {
		return smg;
	}

}

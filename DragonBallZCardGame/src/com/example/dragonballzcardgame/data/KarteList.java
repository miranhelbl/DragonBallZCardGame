package com.example.dragonballzcardgame.data;

import java.util.ArrayList;

public class KarteList {
	private ArrayList<Karte> list;

	public ArrayList<Karte> getList() {
		return list;
	}

	public KarteList() {
		super();
		list = new ArrayList<Karte>();
	}

	@Override
	public String toString() {
		return "KarteList [list=" + list + "]";
	}
	
	public String getSth()
	{
		return "asd";
	}
}

package model.db;

import java.util.ArrayList;

public class Data {
	private static Data instance;

	private ArrayList<String[]> dbArrayList = new ArrayList<String[]>();
	private String[][] dbDataArray;
	private int votersRegistered = 10;

	private Data() {
		populateDBList();
	}
	
	private void populateDBList() {
		dbDataArray = new String[votersRegistered][4];
		dbArrayList.add(new String[]{"Frances", "Ormond", "Coen", "false"});
		dbArrayList.add(new String[]{"Darryl", "Hall", "Oats", "false"});
		dbArrayList.add(new String[]{"Michael", "Jackson", "Thriller", "false"});
		dbArrayList.add(new String[]{"Freddy", "Mercury", "Queen", "false"});
		dbArrayList.add(new String[]{"Elvis", "Presley", "Donut", "false"});
		dbArrayList.add(new String[]{"Samuel", "Beckett", "Godot", "false"});
		dbArrayList.add(new String[]{"Taylor", "Swift", "Talent", "false"});
		dbArrayList.add(new String[]{"Al", "Pacino", "Scarface", "false"});
		dbArrayList.add(new String[]{"Robert", "Deniro", "Taxi Driver", "false"});
	
	}
	
	public static synchronized Data getInstance() {
		if (instance == null) {
			instance = new Data();
		}
		return instance;
	}

	public ArrayList<String[]> getDbArrayList() {
		return dbArrayList;
	}

	public void setDbArrayList(ArrayList<String[]> dbArrayList) {
		this.dbArrayList = dbArrayList;
	}
	
}

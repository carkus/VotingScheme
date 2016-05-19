package model.db;

public class Data {
	private static Data instance;

	private Data() { }

	public static synchronized Data getInstance() {
		if (instance == null) {
			instance = new Data();
		}
		return instance;
	}
	
}

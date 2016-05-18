package controller;

import java.util.ArrayList;

import model.algorithm.ElGamal;

public class RegistrationController {
	
	private static RegistrationController instance;

	private RegistrationController(){	
        
	}
	
	public static synchronized RegistrationController getInstance() {
		if (instance == null) {
			instance = new RegistrationController();
		}
		return instance;
	} 
	
}

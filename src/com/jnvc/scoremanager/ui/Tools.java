package com.jnvc.scoremanager.ui;

public class Tools {
	private static int screenWidth;
	private static int screenHeight;
	public static int getScreenWidth(){
		screenWidth = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		return screenWidth;
	}
	
	public static int getScreenHeight(){
		screenHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height; 
		return screenHeight;
	}
}

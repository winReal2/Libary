package com.library;

import com.library.controller.LibraryController;

public class Application {
	public static void main(String[] args) {
		LibraryController controller = new LibraryController();
		
		controller.start();
	}
}

package booksys.application.persistency;

import booksys.application.domain.Menu;

public class PersistentMenu extends Menu {
	private int mid;

	public PersistentMenu(int m, String n, int p) {
		super(m, n, p);
		mid = m;
	}

	int getId() {
		return mid;
	}
}

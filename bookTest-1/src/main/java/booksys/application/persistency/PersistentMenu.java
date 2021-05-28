package booksys.application.persistency;

import booksys.application.domain.Menu;

public class PersistentMenu extends Menu {
	private int mid;

	public PersistentMenu(int m, String n, int p, String c, String i) {
		super(m, n, p, c, i);
		mid = m;
	}

	int getId() {
		return mid;
	}
}

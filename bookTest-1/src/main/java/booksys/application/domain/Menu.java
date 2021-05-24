package booksys.application.domain;

public class Menu {

	private int mid;
	private String name;
	private int price;

	public Menu(int m, String n, int p) {
		mid = m;
		name = n;
		price = p;
	}

	public String getName() {
		return name;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}

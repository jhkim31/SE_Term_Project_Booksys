package booksys.application.domain;

public class Menu {

	private int mid;
	private String name;
	private int price;
	private String comment;
	private String image;

	public Menu(int m, String n, int p, String c, String i) {
		mid = m;
		name = n;
		price = p;
		comment = c;
		image = i;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

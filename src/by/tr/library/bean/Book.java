package by.tr.library.bean;

public class Book {
	private String title;
	private int price;
	private String available;

	public Book(){
		this.title = "";
		this.price = 0;
	}

	
	public Book(String title, int price, String available){
		this.title = title;
		this.price = price;
		this.available = available;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "title=" + title +
				"; price=" + price +
				"; available=" + available;
	}

	public Book expand(String string) {
		String[] fields = string.split(";");
		this.setTitle(fields[0].trim().substring(6));
		this.setPrice(Integer.parseInt(fields[1].trim().substring(6)));
		this.setAvailable(fields[2].trim().substring(10));
		return this;
	}
}

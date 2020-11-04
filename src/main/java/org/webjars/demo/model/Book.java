package org.webjars.demo.model;

public class Book {
	private int id;
	
	private String bookname;
	
	private String author;
	
	private String publisher_time;
	
	private int num;
	
	private String	documentwords;
	
	private float price;
	
	private String publisher_place;
	
	private String languages;
	
	private String press;

	private String images;

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getDocumentwords() {
		return documentwords;
	}

	public void setDocumentwords(String documentwords) {
		this.documentwords = documentwords;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getPublisher_place() {
		return publisher_place;
	}

	public void setPublisher_place(String publisher_place) {
		this.publisher_place = publisher_place;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher_time() {
		return publisher_time;
	}

	public void setPublisher_time(String publisher_time) {
		this.publisher_time = publisher_time;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}



	public Book() {
		super();
	}


	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", bookname='" + bookname + '\'' +
				", author='" + author + '\'' +
				", publisher_time='" + publisher_time + '\'' +
				", num=" + num +
				", documentwords='" + documentwords + '\'' +
				", price=" + price +
				", publisher_place='" + publisher_place + '\'' +
				", languages='" + languages + '\'' +
				", press='" + press + '\'' +
				", images='" + images + '\'' +
				'}';
	}
}

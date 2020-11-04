package org.webjars.demo.model;

public class Borrow_Read {
	private String borrow_read;
	
	private String bookname;
	
	private String readname;

	public String getBorrow_read() {
		return borrow_read;
	}

	public void setBorrow_read(String borrow_read) {
		this.borrow_read = borrow_read;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getReadname() {
		return readname;
	}

	public void setReadname(String readname) {
		this.readname = readname;
	}

	public Borrow_Read(String borrow_read, String bookname, String readname) {
		super();
		this.borrow_read = borrow_read;
		this.bookname = bookname;
		this.readname = readname;
	}

	public Borrow_Read() {
		super();
	}

	@Override
	public String toString() {
		return "Borrow_read [borrow_read=" + borrow_read + ", bookname=" + bookname + ", readname=" + readname + "]";
	}
	
	
}

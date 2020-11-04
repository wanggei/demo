package org.webjars.demo.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.webjars.demo.dao.BaseDao;
import org.webjars.demo.dao.BookDao;
import org.webjars.demo.model.Book;

import java.util.List;

@Service("bookService")
public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService{

	@Autowired
	private BookDao bookDao;
	
	@Override
	public BaseDao baseDao() {
		
		return bookDao;
	}
	//分页查询
	@Override
	public PageInfo<Book> getAllByPage(Integer PageSize, Integer PageNum) {
		Page<Book> page= PageHelper.startPage(PageSize,PageNum);
		List<Book> getall=bookDao.getAll();
		PageInfo pageInfo=new PageInfo(getall);
		return pageInfo;
	}

	//根据bid获取一条数据
	@Override
	public Book getOneByBid(String bid) {
		Book book=bookDao.getOneByBid(bid);

		return book;
	}
	//更新数据
	@Override
	public void upBookById(Book b) {
		this.update(b);
	}
	//删除一条数据
	@Override
	public void deleteOneById(Integer bookid) {
		this.delete(bookid);
	}

	//模糊查询
	@Override
	public PageInfo<Book> getBookPage(Integer pageSize, Integer pageNum, String bookname) {

		Page<Book> page=PageHelper.startPage(pageSize,pageNum);

		List<Book> bookList=bookDao.blurSelBookByBo(bookname);

		PageInfo<Book> info=new PageInfo<>(bookList);

		return info;
	}

	@Override
	public void addBook(Book book) {

		this.add(book);
	}

	public List<Book> getAll(){

		return bookDao.getAll();
	}

	@Override
	public List<Book> blurSelBookByBo(String bookname) {

		return bookDao.blurSelBookByBo(bookname);
	}

	@Override
	public Book blurSeBoByBn(String bookname) {

		return bookDao.blurSeBoByBn(bookname);
	}
}

package org.webjars.demo.service;


import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.webjars.demo.model.Book;

import java.util.List;

public interface BookService extends BaseService<Book>{

    //实现分页
    PageInfo<Book> getAllByPage(Integer PageSiez,Integer PageNum);
    //根据id查询一条数据
    Book getOneByBid(String bid);
    //更新一条数据
    void upBookById(Book b);
    //删除一条数据
    void deleteOneById(Integer bookid);
    //模糊查询
    PageInfo<Book> getBookPage(Integer pageNum,Integer pageSize,String bookname);
    //添加一条记录
    public void addBook(Book book);
    //获取所有数据
    public List<Book> getAll();
    //模糊查询
    public List<Book> blurSelBookByBo(String bookname);
    //根据书本名查询
    public Book blurSeBoByBn(String bookname);

}

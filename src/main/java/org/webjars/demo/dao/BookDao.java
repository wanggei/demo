package org.webjars.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.webjars.demo.model.Book;
import org.webjars.demo.model.User;

import java.util.List;

@Mapper
@Repository
public interface BookDao extends BaseDao{

    public List<Book> getAll();

    public  Book getOneByBid(@Param("bid") String bid);

    //模糊查询

    public List<Book> blurSelBookByBo(@Param("bookname") String bookname);

    public Book blurSeBoByBn(@Param("bookname") String bookname);

}

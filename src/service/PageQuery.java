package service;

import enity.Contact;
import enity.PageBean;
import enity.QueryBean;

import java.util.List;

public interface PageQuery {
    PageBean pageQuery(String currentPage, String pageSize, QueryBean queryBean);

    List<Contact> itemQuery(QueryBean queryBean);
}

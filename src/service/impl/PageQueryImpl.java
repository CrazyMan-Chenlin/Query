package service.impl;

import dao.ContactDao;
import dao.QueryDao;
import enity.Contact;
import enity.Department;
import enity.PageBean;
import enity.QueryBean;
import service.PageQuery;

import java.util.List;

public class PageQueryImpl implements PageQuery {
    //处理pageBean的业务逻辑
    @Override
    public PageBean pageQuery(String currentPage, String pageSize, QueryBean queryBean) {
        //用户第一次访问
        if (currentPage == null || currentPage.equals("")) {
            currentPage = "1";
        }

        //如何没有输入，则默认值为5
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "5";
        }

        //封装PageBean对象
        PageBean pageBean = new PageBean();
        //1.设置当前页对象
        pageBean.setCurrentPage(Integer.parseInt(currentPage));
        //2.设置每页显示的数量
        pageBean.setPageSize(Integer.parseInt(pageSize));
        //设置数据,从数据中获取
        ContactDao contactDao = new ContactDao();
        List<Contact> contactList = contactDao.queryData(pageBean.getCurrentPage(), pageBean.getPageSize(), queryBean);
        pageBean.setContactList(contactList);
        //设置总纪录数
        pageBean.setTotalCount(contactDao.queryCount(queryBean));
        return pageBean;
    }

    //处理部门选项
    public List<Department> getAllDepartment() {
        QueryDao queryDao = new QueryDao();
        List<Department> allDepartment = queryDao.getAllDepartment();
        return allDepartment;
    }

    @Override
    public List<Contact> itemQuery(QueryBean queryBean) {
        QueryDao queryDao = new QueryDao();
        List<Contact> contactList = queryDao.itemQueryDao(queryBean);
        return contactList;
    }
}

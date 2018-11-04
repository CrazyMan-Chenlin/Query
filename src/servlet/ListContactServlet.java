package servlet;

import dao.ContactDao;
import enity.Contact;
import enity.Department;
import enity.PageBean;
import enity.QueryBean;
import service.impl.PageQueryImpl;
import util.WebUtil;

import java.io.IOException;
import java.util.List;

@javax.servlet.annotation.WebServlet(name = "ListContactServlet", urlPatterns = "/ListContactServlet")
public class ListContactServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //接受用户传来的当前页面参数
        String currentPage = request.getParameter("currentPage");
        //接受用户输入每页显示的参数
        String pageSize = request.getParameter("pageSize");
        //接受QueryDao对象
        QueryBean queryBean = WebUtil.copyRequestToBean(request, QueryBean.class);
        PageQueryImpl pageQuery = new PageQueryImpl();
        PageBean pageBean = pageQuery.pageQuery(currentPage, pageSize, queryBean);
        //获得部门名对象
        List<Department> allDepartment = pageQuery.getAllDepartment();
        //存放域对象
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("allDepartment", allDepartment);
        //转发
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}

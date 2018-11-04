package servlet;

import enity.Contact;
import enity.PageBean;
import enity.QueryBean;
import service.impl.PageQueryImpl;
import util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "itemQueryServlet", urlPatterns = "/itemQueryServlet")
public class itemQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        QueryBean queryBean = WebUtil.copyRequestToBean(request, QueryBean.class);
        PageQueryImpl pageQuery = new PageQueryImpl();
        List<Contact> contactList = pageQuery.itemQuery(queryBean);
        request.setAttribute("contactList", contactList);
        request.getRequestDispatcher("/query.jsp").forward(request, response);
    }
}

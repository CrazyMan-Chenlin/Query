package dao;

import enity.Contact;
import enity.Department;
import enity.QueryBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.JdbcUtil;

import java.sql.SQLException;
import java.util.List;

public class QueryDao {
    public List<Contact> itemQueryDao(QueryBean queryBean) {
        //恒等式
        StringBuffer sql = new StringBuffer("SELECT * from Contact_Infor WHERE 1=1");
        //StringBuffer 才有.append();方法
        if (queryBean.getAge() != 0) {
            sql.append(" and age like '%" + queryBean.getAge() + "%'");
        }
        if (queryBean.getGender() != null) {
            if (!queryBean.getGender().trim().equals("")) {
                sql.append(" and gender LIKE '%" + queryBean.getGender() + "%'");
            }
        }
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        try {
            List<Contact> query = (List<Contact>) qr.query(sql.toString(), new BeanListHandler(Contact.class), new Object[]{});
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /*
     * 通过deptId查询部门名
     *
     * */
    public Department getDepartmentById(int deptId) {
        try {
            QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
            String sql = "select * from dept where deptId=?";
            Department department = (Department) qr.query(sql, new BeanHandler(Department.class), new Object[]{deptId});
            return department;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /*
     * 返回一个集合
     * */
    public List<Department> getAllDepartment() {
        try {
            QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
            String sql = "select * from dept ";
            List<Department> query = (List<Department>) qr.query(sql, new BeanListHandler(Department.class), new Object[]{});
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        QueryBean queryBean = new QueryBean();
        queryBean.setGender("        ");
        queryBean.setAge(18);
        QueryDao queryDao = new QueryDao();

        List<Contact> contacts = queryDao.itemQueryDao(queryBean);
        for (Contact contact : contacts
                ) {
            System.out.println(contact.toString());
        }
    }
}

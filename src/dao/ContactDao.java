package dao;

import enity.Department;
import enity.QueryBean;
import org.apache.commons.dbutils.ResultSetHandler;
import util.JdbcUtil;
import enity.Contact;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {
    QueryDao queryDao = new QueryDao();

    //提供数据的方法
    public List<Contact> queryData(int currentPage, int pageSize, QueryBean queryBean) {
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        // SELECT * from Contact_Infor LIMIT 0,2;  // 当前页面为1 0=1-1
        //SELECT  * from Contact_Infor Limit 2,2;    //当前页面为2 2=（1-1）*2
        //  SELECT * from Contact_Infor Limit 4,2;      //  当前页面为3 4=（3-1）*2
      /*
   分页查询语句
     select * from Contact_Infor Limit 当前页码-1*每页显示的记录数，每页显示的记录数
*/
        StringBuffer sql = new StringBuffer("select * from Contact_Infor where 1=1");
        //条件查询
        if (queryBean.getGender() != null) {
            if (!queryBean.getGender().equals("")) {
                sql.append(" and gender like '%" + queryBean.getGender() + "%' ");
            }
        }
        if (queryBean.getAge() != 0) {
            sql.append(" and age like '%" + queryBean.getAge() + "%' ");
        }
        if (queryBean.getEmail() != null) {
            if (!queryBean.getEmail().equals("")) {
                sql.append(" and email like '%" + queryBean.getEmail() + "%' ");
            }
        }
        if (queryBean.getPhone() != null) {
            if (!queryBean.getPhone().equals("")) {
                sql.append(" and phone like '%" + queryBean.getPhone() + "%' ");
            }
        }
        if (queryBean.getQq() != 0) {
            sql.append(" and qq like '%" + queryBean.getQq() + "%' ");
        }
        if (queryBean.getDeptId() != 0) {
            sql.append(" and deptId like '%" + queryBean.getDeptId() + "%' ");
        }
        if (queryBean.getName() != null) {
            if (!queryBean.getName().equals("")) {
                sql.append(" and name like '%" + queryBean.getName() + "%' ");
            }
        }
        //分页查询
        sql.append(" limit ?,? ");

        try {
            int startNum = (currentPage - 1) * pageSize;
            List<Contact> contactList = (List<Contact>) qr.query(sql.toString(), new MyContactResultSetHandler(), new Object[]{startNum, pageSize});
            return contactList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //提供总数的方法
    public int queryCount(QueryBean queryBean) {
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        // SELECT * from Contact_Infor LIMIT 0,2;  // 当前页面为1 0=1-1
        //SELECT  * from Contact_Infor Limit 2,2;    //当前页面为2 2=（1-1）*2
        //  SELECT * from Contact_Infor Limit 4,2;      //  当前页面为3 4=（3-1）*2
      /*
   分页查询语句
     select * from Contact_Infor Limit 当前页码-1*每页显示的记录数，每页显示的记录数
*/
        StringBuffer sql = new StringBuffer("select count(*) from Contact_Infor where 1=1");
        //条件查询
        if (queryBean.getGender() != null) {
            if (!queryBean.getGender().equals("")) {
                sql.append(" and gender like '%" + queryBean.getGender() + "%' ");
            }
        }
        if (queryBean.getAge() != 0) {
            sql.append(" and age like '%" + queryBean.getAge() + "%' ");
        }
        if (queryBean.getEmail() != null) {
            if (!queryBean.getEmail().equals("")) {
                sql.append(" and email like '%" + queryBean.getEmail() + "%' ");
            }
        }
        if (queryBean.getPhone() != null) {
            if (!queryBean.getPhone().equals("")) {
                sql.append(" and phone like '%" + queryBean.getPhone() + "%' ");
            }
        }
        if (queryBean.getQq() != 0) {
            sql.append(" and qq like '%" + queryBean.getQq() + "%' ");
        }
        if (queryBean.getDeptId() != 0) {
            sql.append(" and deptId like '%" + queryBean.getDeptId() + "%' ");
        }
        if (queryBean.getName() != null) {
            if (!queryBean.getName().equals("")) {
                sql.append(" and name like '%" + queryBean.getName() + "%' ");
            }
        }
        try {
            Long count = (Long) qr.query(sql.toString(), new ScalarHandler());
            return count.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    public static void main(String[] args) {
        ContactDao contactDao = new ContactDao();
        QueryBean qr = new QueryBean();
        qr.setEmail("269344402@qq.com");
        qr.setGender("男");
        int i = contactDao.queryCount(qr);
        System.out.println(i);

    }

    /*
     * 自行封装结果集
     * 因为 new BeanListHandler(Contact.class); 封装是从contact表中读取数据
     * 即封装的是deptId，而不是department对象
     * 所以要自行封装
     * */
    class MyContactResultSetHandler implements ResultSetHandler {

        @Override
        public Object handle(ResultSet rs) throws SQLException {
            List<Contact> contactList = new ArrayList<>();
            while (rs.next()) {

                Contact contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setGender(rs.getString("gender"));
                contact.setAge(rs.getInt("age"));
                contact.setPhone(rs.getLong("phone"));
                contact.setEmail(rs.getString("email"));
                contact.setQQ(rs.getInt("qq"));
                Department deptId = queryDao.getDepartmentById(rs.getInt("deptId"));
                contact.setDepartment(deptId);
                contactList.add(contact);
            }
            return contactList;
        }
    }
}

package enity;

import java.util.List;

public class PageBean {
    private List<Contact> contactList;//数据
    private int firstPage;//首页
    private int prePage;//上一页
    private int nextPage;//下一页
    private int totalPage;//总页数
    private int currentPage; //当前页
    private int totalCount;//总记录数
    private int PageSize;//每页显示的数量

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    //首页永远都为1
    public int getFirstPage() {
        return 1;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPrePage() {
        //前一页：如果为首页 即为1，如果不是首页，即当前页-1
        return this.getCurrentPage() == 1 ? 1 : this.getCurrentPage() - 1;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    //下一页：如果当前页为末页，则为末页，否则当前页+1
    public int getNextPage() {
        return this.getCurrentPage() == this.getTotalPage() ? this.getTotalPage() : this.getCurrentPage() + 1;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    //总页数：总页数等于总记录数取模分页数，如果为0则为总记录数除以分页数，否则为总记录数除以分页数+1
    public int getTotalPage() {
        return this.getTotalCount() % this.getPageSize() == 0 ? this.getTotalCount() / this.getPageSize()
                : this.getTotalCount() / this.getPageSize() + 1;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }
}

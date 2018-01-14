package com.cqupt.dao.model;

import java.util.List;

public class Page {
    private    int  totalCount;//总记录条数

    private    int  totalPage;//总页码

    private    int  pageSize;//页面大小

    private    int  pageNo;//当前第几页

    private List rows;//返回记录条数

    private int  startRow;


    public void setTotalCount(int totalCount) {

        this.totalCount = totalCount;
        if (this.pageSize == 0) {//默认一页显示10条记录
            this.pageSize = 5;
        }
        if (this.pageNo == 0) { //默认显示第一页
            this.pageNo = 1;
        }
        //计算总页数
        if(totalCount>0){
            int tPage = totalCount / this.pageSize;//总记录数除以页面大小计算总页数
            if (totalCount > tPage * this.pageSize) {//有余数,总页数加1
                tPage += 1;
            }
            this.totalPage = tPage;

        }else{//总记录数等于0
            this.pageNo = 1;
            this.startRow=0;
            this.totalPage=0;
        }
        //计算当前开始行
        int startRow =(this.pageNo-1)*this.pageSize;
        this.startRow = startRow;
    }


    public int getTotalPage() {
        return totalPage;
    }


    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public int getPageSize() {
        if (this.pageSize == 0) {
            this.pageSize = 10;
        }
        return pageSize;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public int getPageNo() {
//        if (this.pageNo == 0) {
//            this.pageNo = 1;
//        }
        if(totalPage-pageNo==0) {
            this.pageNo=1;
        }else {
            this.pageNo=totalPage-pageNo;
        }
        return pageNo;
    }


    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }


    public List getRows() {
        return rows;
    }


    public void setRows(List rows) {
        this.rows = rows;
    }


    public int getStartRow() {
        return startRow;
    }


    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }
}

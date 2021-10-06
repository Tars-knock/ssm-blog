package cn.tarsknock.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;



public class PageBean {

    /**
     * 当前第几页 从1开始
     */
    @Getter private final int page;

    /**
     * 页面大小
     */
//    private final int pageSize;
    @Getter private final int pageSize;

    /**
     * 总 数据条数
     */
    private final int allSize;

    /**
     * 查询从第几条数据开始查询显示
     */
    private int start;

    /**
     * 一共有多少页 计算可得
     */
    @Getter private final int allPage;

    /**
     * 存放数组的页码
     */
    @Getter private int[] navigatepageNums;

    /**
     * 是否有上 下一页
     */
    @Getter private boolean hasPreviousPage;
    @Getter private boolean hasNextPage;

    @Getter private int prePage;
    @Getter private int nextPage;


    public PageBean(int page, int pageSize, int allSize){
        this.page = page;
        this.pageSize = pageSize;
        this.allSize = allSize;

        this.allPage = (int)Math.ceil((double) allSize/pageSize);
        hasNextPage = page<allPage;
        hasPreviousPage = page>1;
        prePage = page-1;
        nextPage = page+1;


        navigatepageNums = new int[allPage];
        for(int i = 0;i<navigatepageNums.length;i++){
            navigatepageNums[i] = i+1;
        }

    }

//
//    public int getPageSize(){
//        return this.pageSize;
//    }



    public int getStart(){
        return (this.page-1)*this.pageSize;
    }


    public static void main(String[] args) {
        PageBean pageBean = new PageBean(1,20,200);
        System.out.println(pageBean.getAllPage());
        System.out.println(pageBean.getPageSize());
    }
}

package com.lemming.lemming.generic;

public class PageNumber {
    private static int pageSize = 10;

    /**
     * 获取合法页数，根据页面数据量大小 pageSize 和数据量总数 count 计算传入的 page 是否在正常范围内，
     * 若大于正常范围，则返回最大页数，若小于正常范围则返回 1（页数从 1 开始）。
     * @param page 传入请求的页数
     * @param count 传入总数据量
     * @return 返回计算后的合法页数
     */
    public static int getValidPage(int page, int count){
        int maxPage = count/pageSize; // 算好的页数
        if (count%pageSize != 0) maxPage++; // 若有余数则加一
        if (page<1){
            return 1;
        }else return Math.min(page, maxPage);
    }

    /**
     * 通过页数获取该页面的数据量的顶部位置。
     * @param page 页数
     * @return 返回页面的顶部位置
     */
    public static int getTop(int page){
        return Math.max((page-1)*getPageSize(),0);
    }

    /**
     * 通过页面数获取该页面的数据量的底部位置。
     * @param page 页数
     * @return 返回页面的底部位置
     */
    public static int getEnd(int page) {
        return page*getPageSize();
    }

    /**
     * 获取设置的页面尺寸
     * @return 页面内的数据量个数
     */
    public static int getPageSize() {
        return pageSize;
    }

    /**
     * 设置页面内显示的最大数据量
     * @param pageSize 要设置的页面内显示的最大数据量数值
     */
    public static void setPageSize(int pageSize) {
        PageNumber.pageSize = pageSize;
    }
}
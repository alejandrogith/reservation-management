package com.example.ReservationManagement.Shared;

public  class BaseRequestParamsDto {



    private int pageIndex  = 1;
    private final int MaxPageSize = 50;
    private int _pageSize = 3;
    public int pageSize;

    public BaseRequestParamsDto(){
        pageSize = (_pageSize > MaxPageSize) ? MaxPageSize : _pageSize;
    }


    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}



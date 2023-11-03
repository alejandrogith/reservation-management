package com.example.ReservationManagement.Shared;

import java.util.List;

public class PaginatedDataDto<T> {




    public int CurrentPage ;
    public int TotalPages ;
    public int PageSize ;
    public long TotalCount ;
    public boolean HasPrevious;
    public boolean HasNext;
    public List<T> Data ;


    public PaginatedDataDto( List<T> data, int pageNumber, int pageSize,long totalCount) {

        TotalCount = totalCount;
        PageSize = pageSize;
        CurrentPage = pageNumber;
        TotalPages = (int)Math.ceil(TotalCount / (double)pageSize);
        Data = data;

         HasPrevious = CurrentPage > 1;
         HasNext = CurrentPage < TotalPages;
    }
    
}

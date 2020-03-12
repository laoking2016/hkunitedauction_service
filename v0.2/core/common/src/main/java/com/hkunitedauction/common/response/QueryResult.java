package com.hkunitedauction.common.response;

import lombok.Data;

@Data
public class QueryResult<T> {

    private int totalCount;

    private T[] list;
}

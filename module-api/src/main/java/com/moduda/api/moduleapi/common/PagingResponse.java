package com.moduda.api.moduleapi.common;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

public record PagingResponse<T> (
        List<T> items,
        int totalPages,
        long totalElements,
        int size,
        int pageNo,
        boolean last
) {
    public PagingResponse<T> from(Page<T> page) {
        return new PagingResponse<T>(page.getContent(),
                                     page.getTotalPages(),
                                     page.getTotalElements(),
                                     page.getSize(),
                                     page.getNumber()+1,
                                     page.isLast());
    }
}

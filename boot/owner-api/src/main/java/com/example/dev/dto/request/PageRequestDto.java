package com.example.dev.dto.request;


import com.example.dev.exception.CommonException;
import com.example.dev.exception.ErrorCode;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
public class PageRequestDto {

    private static int pageNum;
    private static int pageSize;
    private static String sortBy;
    private static String order;

    public static PageRequest toPageRequest(){
        switch (order) {
            case "desc" :
                return PageRequest.of(pageNum, pageSize, Sort.by(sortBy).descending());
            case "asc" :
                return PageRequest.of(pageNum, pageSize, Sort.by(sortBy).ascending());
            default :
                throw new CommonException(ErrorCode.INVALID_INPUT_VALUE);
        }
    }
}

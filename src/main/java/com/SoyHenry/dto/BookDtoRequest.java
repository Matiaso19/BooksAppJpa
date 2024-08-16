package com.SoyHenry.dto;

import lombok.Data;

@Data
public class BookDtoRequest {

    private String author;

    private String title;

    private Integer publishYear;

    private Integer pageQuantity;

}

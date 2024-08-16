package com.SoyHenry.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class BookDtoResponse {

    private String author;

    private String title;

    private Integer publishYear;

    private Integer pageQuantity;

}

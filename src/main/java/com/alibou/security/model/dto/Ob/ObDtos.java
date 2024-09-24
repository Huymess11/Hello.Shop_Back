package com.alibou.security.model.dto.Ob;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ObDtos {
    private String name;
    private Integer price;
    private String brand;
    private String pictureURL;
    private String description;
}

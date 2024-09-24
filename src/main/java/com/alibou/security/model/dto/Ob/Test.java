package com.alibou.security.model.dto.Ob;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//public class Test {
//}
@Getter
@Setter
@Builder
public class Test {
    private Integer id;
    private String name;
    private Integer price;
    private String color;
    private String brand;
    private String country;
    private String pictureURL;
    private String description;
}

package com.alibou.security.model.dto.Ob;

import com.alibou.security.model.dto.global.PaginationDto;
import com.alibou.security.model.entities.Ob;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OblistDtos {

    @JsonProperty("data")
    private List<Ob> data;

    @JsonProperty("pagination")
    private PaginationDto paginationDto;
}


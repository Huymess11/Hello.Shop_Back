package com.alibou.security.model.dto.global;

import com.alibou.security.model.entities.Ob;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailDtos {
    @JsonProperty("detail")
    Optional<Ob> detail;
    @JsonProperty("sg")
    List<Ob> sg;
}

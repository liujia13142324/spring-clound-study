package com.lj.springcloud.micoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors
public class Page<T> implements  Serializable {

    private static final long serialVersionUID = -3720998571176536865L;
    private long total;
    private int totalPage;
    private List<T> content = new ArrayList<>();
    private String pageable;


}

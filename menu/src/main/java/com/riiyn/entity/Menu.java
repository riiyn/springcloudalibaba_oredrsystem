package com.riiyn.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @TableId
    private Integer id;
    private String name;
    private Double price;
    private String flavor;
    private Type type;
}

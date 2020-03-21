package com.tomhuwd.lost.domain;

import com.tomhuwd.lost.common.Base.BaseEntity;
import lombok.Data;


@Data
public class Log extends BaseEntity {
    private Integer id;
    // 操作数据
    private String data;
    // 创建时间
    private String createDate;
    // 备注
    private String remarks;
}

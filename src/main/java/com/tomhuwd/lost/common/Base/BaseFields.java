package com.tomhuwd.lost.common.Base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class BaseFields {
    // 模糊查询字段
    private String likeStrField;
    // 时间间隔查询字段
    private String timeField;

}

package com.tomhuwd.lost.common.Base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;


@Slf4j
@Data
public class BaseEntity {
    // 模糊查询值
    public String likeStr;

    // 日期间隔查询
    public Date startDate;
    public Date endDate;

    // 字段处理
    public BaseFields fields;

    // 创建人
    public String createPer;
    // 创建时间
    public Date createTime;

    // 修改人
    public String updatePer;
    // 修改时间
    public Date updateTime;

    // 参数
    public Map<String, Object> params;



}

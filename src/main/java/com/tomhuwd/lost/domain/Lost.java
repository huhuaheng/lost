package com.tomhuwd.lost.domain;

import com.tomhuwd.lost.common.Base.BaseEntity;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Data
public class Lost extends BaseEntity {
    // 邮箱地址
    @Email(message = "不满足邮箱格式！")
    @NotNull(message = "邮箱不能为空！")
    private String email;
    // url爬取地址
    @NotNull(message = "Url不能为空！")
    private String url;

    private String[] urlArray;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if(url != null && !"".equals(url))
            urlArray = url.split(",");
        this.url = url;
    }




}

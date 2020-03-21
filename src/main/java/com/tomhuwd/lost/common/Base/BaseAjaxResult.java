package com.tomhuwd.lost.common.Base;

import java.util.HashMap;

/**
 * 统一返回对象
 */
public class BaseAjaxResult extends HashMap<String, Object> {
    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type {
        /**
         * 成功
         */
        SUCCESS(200),
        /**
         * 警告
         */
        WARN(301),
        /**
         * 错误
         */
        ERROR(500);
        // 返回值
        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    public BaseAjaxResult() {
    }

    public BaseAjaxResult(Type type, String msg) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    public BaseAjaxResult(Type type, String msg, Object object) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (object != null) super.put(DATA_TAG, object);
    }


    //    ERROR 错误
    public static BaseAjaxResult error() {
        return new BaseAjaxResult(Type.ERROR, null);
    }

    public static BaseAjaxResult error(String msg) {
        return new BaseAjaxResult(Type.ERROR, msg);
    }

    public static BaseAjaxResult error(String msg, Object data) {
        return new BaseAjaxResult(Type.ERROR, msg, data);
    }

    //    SUCCESS 成功
    public static BaseAjaxResult success() {
        return new BaseAjaxResult(Type.SUCCESS, null);
    }

    public static BaseAjaxResult success(String msg) {
        return new BaseAjaxResult(Type.SUCCESS, msg);
    }

    public static BaseAjaxResult success(String msg, Object data) {
        return new BaseAjaxResult(Type.SUCCESS, msg, data);
    }

    //    WARN 警告
    public static BaseAjaxResult warn() {
        return new BaseAjaxResult(Type.WARN, null);
    }

    public static BaseAjaxResult warn(String msg) {
        return new BaseAjaxResult(Type.WARN, msg);
    }

    public static BaseAjaxResult warn(String msg, Object data) {
        return new BaseAjaxResult(Type.WARN, msg, data);
    }

}

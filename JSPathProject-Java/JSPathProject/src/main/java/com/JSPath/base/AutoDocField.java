package com.JSPath.base;


import java.lang.annotation.*;

/**
 * 自动生成接口文档--请求参数，必须要添加此注解
 * @author cenxi
 * @date 2015-11-11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME) //运行时，反射可获得
@Target(ElementType.FIELD) //字段注解
public @interface AutoDocField {
    /**
     * 字段简称
     * @return
     */
    public String note() default "这个人很懒，什么也没留下";
    /**
     * 是否可以为空
     * @return
     */
    boolean nullable() default true;
    /**
     * 长度
     * @return
     */
    int length() default 255;
    /**
     *精确度
     * @return
     */
    int precision() default 0;
    /**
     * 比例度
     * @return
     */
    int scale() default 0;
}

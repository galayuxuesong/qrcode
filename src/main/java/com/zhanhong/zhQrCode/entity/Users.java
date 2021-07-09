package com.zhanhong.zhQrCode.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
@Data
@TableName("USERS")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private int age;
    private String addr;
    @TableField("POST_ADDR")
    private String postAddr;
    private String sex;
    @TableField("CREATE_TIME")
    private Date create_time;
    @TableField("CREATE_USER")
    private String create_user;
    @TableField("UPDATE_TIME")
    private Date update_time;
    @TableField("STATE")
    private String state;
    @TableField("PHONE")
    private String phone;
    private String hospitalname;
    private String grade;
    private String department;
    private String position;
    private String zj;
    private String ptcjname;
    private String interest;
    private String dzblname;

}

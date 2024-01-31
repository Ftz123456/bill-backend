package com.zsc.edu.bill.modules.file.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import com.zsc.edu.bill.modules.system.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 票据附件表
 * @TableName attach_file
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="attach_file")
@Data
public class AttachFile extends BaseEntity {

    /**
     * 文件名 文件名详细说明
     */

    private String saveFilename;

    /**
     * 文件UUID 返回给前端的文件UUID
     */

    private String uuid;

    /**
     * 上传时的文件名 原文件名
     */

    private String originFilename;

    /**
     * 文件大小
     */

    private Long fileSize;

    /**
     * 文件类型
     */

    private String fileType;

    /**
     * 文件扩展名
     */

    private String extendName;

    /**
     * 票据id 附件对应的票据id
     */

    private Long ticketId;
    private String remark;

    /**
     * 
     */

    private String fileUrl;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
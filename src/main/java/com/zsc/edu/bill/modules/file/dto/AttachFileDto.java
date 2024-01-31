package com.zsc.edu.bill.modules.file.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ftz
 * 创建时间:29/1/2024 上午10:00
 * 描述: 附件Dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachFileDto {
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
     *文件url
     */

    private String fileUrl;
}

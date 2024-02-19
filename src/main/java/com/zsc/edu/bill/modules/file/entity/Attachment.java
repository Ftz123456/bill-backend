package com.zsc.edu.bill.modules.file.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.FileSystemResource;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 附件
 *
 * @author harry_yao
 */
@NoArgsConstructor
@Getter
@Setter
@TableName(value ="attach_file")
public class Attachment implements Serializable {

    /**
     * ID，用文件和文件名的SHA-1值生成
     */
    @TableId
    public String id;

    /**
     * 文件名
     */

    public String fileName;

    /**
     * 附件作用类型
     */

    public String mimeType;

    /**
     * 附件功能类型
     */
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    public Type type = Type.其他;

    /**
     * 文件上传时间
     */

    public LocalDateTime uploadTime;

    /**
     * 文件下载链接
     */
    @JsonSerialize
    @TableField(exist = false)
    public String url;

    public Attachment(String id, String fileName, String mimeType, Type type, LocalDateTime uploadTime) {
        this.id = id;
        this.fileName = fileName;
        this.mimeType = mimeType;
        // this.type = type;
        this.uploadTime = uploadTime;
        this.url = "/api/rest/attachment/" + id;
    }

    public void setId(String id) {
        this.id = id;
        this.url = "/api/rest/attachment/" + id;
    }

    public String getUrl() {
        return "/api/rest/attachment/" + id;
    }

    /**
     * 枚举类：附件功能类型
     */
    public enum Type {
        其他,
        头像
    }

    @AllArgsConstructor
    public static final class Wrapper {

        public Attachment attachment;

        public FileSystemResource resource;

    }

}

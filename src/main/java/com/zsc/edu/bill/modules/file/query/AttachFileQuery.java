package com.zsc.edu.bill.modules.file.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zsc.edu.bill.modules.file.entity.AttachFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ftz
 * 创建时间:29/1/2024 上午10:09
 * 描述:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachFileQuery {
    /**
     * 附件名称
     */
    private String name;

    /**
     * 附件类型
     */
    private String type;

    /**
     * 附件大小
     */
    private Long size;

    /**
     * 附件描述
     */
    private String description;

    /**
     * 附件票据id
     */
    private Long TicketId;

    /**
     * 拼接查询条件
     */
    public LambdaQueryWrapper<AttachFile> wrapper() {
        LambdaQueryWrapper<AttachFile> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AttachFile::getSaveFilename, this.name)
                .eq(AttachFile::getFileType, this.type)
                .eq(AttachFile::getFileSize, this.size)
                .eq(AttachFile::getTicketId, this.TicketId);
        return wrapper;
    }
}

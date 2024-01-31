package com.zsc.edu.bill.modules.file.service;

import com.zsc.edu.bill.modules.file.dto.AttachFileDto;
import com.zsc.edu.bill.modules.file.entity.AttachFile;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
* @author fantianzhi
* @description 针对表【attach_file(票据附件表)】的数据库操作Service
* @createDate 2024-01-28 19:48:22
*/
public interface AttachFileService extends IService<AttachFile> {

    Boolean create(AttachFileDto dto);

    Boolean update(AttachFileDto dto, Long id);

    AttachFile upload(MultipartFile file);

    void download(String uuid, HttpServletResponse response);
}

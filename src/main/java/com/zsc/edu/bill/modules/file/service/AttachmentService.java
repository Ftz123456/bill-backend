package com.zsc.edu.bill.modules.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsc.edu.bill.modules.file.entity.Attachment;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @author fantianzhi
* @description 针对表【attach_file(票据附件表)】的数据库操作Service
* @createDate 2024-01-28 19:48:22
*/
public interface AttachmentService {

    Attachment store(Attachment.Type type, MultipartFile file) throws IOException;

    Attachment.Wrapper loadAsWrapper(String id);
}

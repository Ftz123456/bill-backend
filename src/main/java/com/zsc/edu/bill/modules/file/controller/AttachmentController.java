package com.zsc.edu.bill.modules.file.controller;

import com.zsc.edu.bill.exception.StorageException;
import com.zsc.edu.bill.modules.file.entity.Attachment;
import com.zsc.edu.bill.modules.file.service.AttachmentService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 附件Controller
 *
 * @author harry_yao
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/rest/attachment")
public class AttachmentController {

    private final AttachmentService service;

    /**
     * 上传附件
     *
     * @param type 附件功能类型
     * @param file 文件
     * @return 附件信息
     */
    @PostMapping()
    public Attachment upload(
        @RequestParam(required = false) Attachment.Type type,
        @RequestParam("file") MultipartFile file
    ) {
        try {
            if (type == null) {
                type = Attachment.Type.其他;
            }
            return service.store(type, file);
        } catch (IOException e) {
            throw new StorageException("文件上传出错");
        }
    }

    /**
     * 下载附件
     *
     * @param id 附件ID
     * @return 附件文件内容
     */
    @GetMapping("{id}")
    public ResponseEntity<Resource> download(
        @PathVariable("id") String id
    ) {
        Attachment.Wrapper wrapper = service.loadAsWrapper(id);
        if (wrapper.attachment.fileName != null) {
            ContentDisposition contentDisposition = ContentDisposition.builder("attachment").filename(wrapper.attachment.fileName, StandardCharsets.UTF_8).build();
            return ResponseEntity.ok().
                header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString()).
                header(HttpHeaders.CONTENT_TYPE, wrapper.attachment.mimeType).
                body(wrapper.resource);
        }
        return ResponseEntity.ok(wrapper.resource);
    }
    /**
     * 根据附件ID获取附件信息
     * */
    @GetMapping("find/{id}")
    public Attachment getAttachmentInfo(@PathVariable("id") String id) {
        return service.getById(id);
    }


}

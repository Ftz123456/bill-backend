package com.zsc.edu.bill.modules.file.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsc.edu.bill.modules.file.dto.AttachFileDto;
import com.zsc.edu.bill.modules.file.entity.AttachFile;
import com.zsc.edu.bill.modules.file.query.AttachFileQuery;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ftz
 * 创建时间:29/1/2024 上午10:06
 * 描述: 附件Controller
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/rest/attachFile")
public class AttachFileController {

    private final com.zsc.edu.bill.modules.file.service.AttachFileService service;

    /**
     * 分页查询附件列表
     * @return 附件列表
     */
    @GetMapping
    public Page<AttachFile> list(AttachFileQuery query, Page<AttachFile> page) {
        return service.page(page, query.wrapper());
    }
      /**
       * 创建附件
       * @return ture/false
       */
    @PostMapping
    public Boolean create(@RequestBody AttachFileDto dto){
        return service.create(dto);
    }

    /**
     * 更新附件
     * @return ture/false
     */
    @PatchMapping("/{id}")
    public Boolean update(@RequestBody AttachFileDto dto, @PathVariable("id") Long id){

        return service.update(dto, id);
    }

    /**
     * 删除附件
     * @return ture/false
     */
    @DeleteMapping
    public Boolean delete(Long id){
        return service.removeById(id);
    }

    /**
     * 附件上传
     *
     * @return ture/false
     */
    @PostMapping("/upload")
    public AttachFile upload(@RequestParam("file") MultipartFile file) {
        return service.upload(file);
    }

    /**
     * 附件下载
     * @return ture/false
     */
    @GetMapping("/download/{uuid}")
    public void download(@PathVariable("uuid") String uuid, HttpServletResponse response) {
        service.download(uuid, response);
    }
//
//    /**
//     * 附件预览
//     * @return ture/false
//     */
//    @GetMapping("/preview/{uuid}")
//    public void preview(@PathVariable("uuid") String uuid, HttpServletResponse response) {
//        service.preview(uuid, response);
//    }
//
//    /**
//     * 附件预览
//     * @return ture/false
//     */
//    @GetMapping("/preview/{uuid}")
//    public void preview(@PathVariable("uuid") String uuid, HttpServletResponse response) {
//        service.preview(uuid, response);
//    }
//
//    /**
//     * 附件预览
//     * @return ture/false
//     */
//    @GetMapping("/preview/{uuid}")
//    public void preview(@PathVariable("uuid") String uuid, HttpServletResponse response) {
//        service.preview(uuid, response);
//    }
//
//    /**
//     * 附件预览
//     * @return ture/false
//     */
//    @GetMapping("/preview/{uuid}")
//




}

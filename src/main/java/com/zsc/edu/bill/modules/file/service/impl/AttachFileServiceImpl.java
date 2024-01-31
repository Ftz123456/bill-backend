package com.zsc.edu.bill.modules.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsc.edu.bill.exception.ConstraintException;
import com.zsc.edu.bill.modules.file.dto.AttachFileDto;
import com.zsc.edu.bill.modules.file.entity.AttachFile;
import com.zsc.edu.bill.modules.file.mapper.AttachFileMapper;
import com.zsc.edu.bill.modules.file.repo.AttachFileRepository;
import com.zsc.edu.bill.modules.file.service.AttachFileService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
* @author fantianzhi
* @description 针对表【attach_file(票据附件表)】的数据库操作Service实现
* @createDate 2024-01-28 19:48:22
*/
@Service
public class AttachFileServiceImpl extends ServiceImpl<AttachFileRepository, AttachFile>
    implements AttachFileService{
    @Autowired
    private  AttachFileMapper mapper;

    @Override
    public Boolean create(AttachFileDto dto) {
        AttachFile attachFile=mapper.toEntity(dto);
        return save(attachFile);

    }

    @Override
    public Boolean update(AttachFileDto dto, Long id) {
        AttachFile attachFile=getById(id);
        mapper.convert(dto,attachFile);
        return updateById(attachFile);
    }

    @Override
    public AttachFile upload(MultipartFile file) {
        AttachFile attachFile = new AttachFile();
        attachFile.setOriginFilename(file.getOriginalFilename());
        attachFile.setFileType(file.getContentType());
        attachFile.setFileSize(file.getSize());

        attachFile.setUuid(UUID.randomUUID().toString());


        if (file.isEmpty()) {
             throw new ConstraintException("此文件为空");
        }
        // 给文件重命名
        String fileName = UUID.randomUUID() + "." + file.getContentType()
                .substring(file.getContentType().lastIndexOf("/") + 1);
        attachFile.setSaveFilename(fileName);
        attachFile.setFileUrl(getSavePath()+fileName);
        try {
            // 获取保存路径
            String path = getSavePath();
            File files = new File(path, fileName);
            File parentFile = files.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdir();
            }
            file.transferTo(files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attachFile;
    }

    @Override
    public void download(String uuid, HttpServletResponse response) {
        AttachFile attachFile = getOne(new LambdaQueryWrapper<AttachFile>().eq(AttachFile::getUuid, uuid));
        File file = new File(getSavePath(), attachFile.getSaveFilename());
        try {
            // 1、定义输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(file);

            // 2、通过response对象，获取到输出流
            ServletOutputStream outputStream = response.getOutputStream();

            // 3、通过response对象设置响应数据格式(image/jpeg)
            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                // 4、通过输入流读取文件数据，然后通过上述的输出流写回浏览器
                outputStream.write(bytes,0,len);
                // 刷新
                outputStream.flush();
            }

            // 5、关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        File file = new File(getSavePath(), "17913cfb-bff1-4c5c-b77d-ef79f241a79d.png");
//        if (!file.exists()) {
//            throw new ConstraintException("文件不存在");
//        }
//        response.setContentType("application/octet-stream");
//        response.addHeader("Content-Disposition", "attachment;filename=" + "17913cfb-bff1-4c5c-b77d-ef79f241a79d.png");

    }

    public String getSavePath() {
        // 这里需要注意的是ApplicationHome是属于SpringBoot的类
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());

        // 保存目录位置根据项目需求可随意更改
        return applicationHome.getDir().getParentFile()
                .getParentFile().getAbsolutePath() + "\\src\\main\\resources\\static";
    }
}





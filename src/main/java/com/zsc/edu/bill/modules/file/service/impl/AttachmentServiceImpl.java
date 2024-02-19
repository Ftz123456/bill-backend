package com.zsc.edu.bill.modules.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsc.edu.bill.framework.storage.StorageProperties;
import com.zsc.edu.bill.framework.storage.exception.StorageFileEmptyException;
import com.zsc.edu.bill.framework.storage.exception.StorageFileNotFoundException;
import com.zsc.edu.bill.modules.bills.entity.Bill;
import com.zsc.edu.bill.modules.bills.repo.BillRepository;
import com.zsc.edu.bill.modules.bills.service.BillService;
import com.zsc.edu.bill.modules.file.entity.Attachment;
import com.zsc.edu.bill.modules.file.repo.AttachmentRepository;
import com.zsc.edu.bill.modules.file.service.AttachmentService;
import org.apache.tika.Tika;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import jakarta.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 附件Service
 *
 * @author harry_yao
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentRepository, Attachment> implements AttachmentService {

    final static int[] illegalChars = {34, 60, 62, 124, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 58, 42, 63, 92, 47};

    private final AttachmentRepository repo;
    private final Path attachmentPath;
    private final Path tempPath;

    public AttachmentServiceImpl(AttachmentRepository repo, StorageProperties storageProperties) {
        this.repo = repo;
        this.attachmentPath = Paths.get(storageProperties.attachment);
        this.tempPath = Paths.get(storageProperties.temp);
    }

    @PostConstruct
    public void init() throws IOException {
        if (Files.notExists(attachmentPath)) {
            Files.createDirectories(attachmentPath);
        }
        if (Files.notExists(tempPath)) {
            Files.createDirectories(tempPath);
        }
    }

    public Attachment store(Attachment.Type type, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new StorageFileEmptyException();
        }
        MessageDigest digest = DigestUtils.getSha1Digest();
        String filename = file.getOriginalFilename();
        if (filename != null) {
            digest.update(filename.getBytes());
        }
        Path temp = tempPath.resolve(String.valueOf(System.nanoTime()));
        byte[] fileContent = file.getBytes();
        ByteArrayInputStream input = new ByteArrayInputStream(fileContent);
        Tika tika = new Tika();
        String mimeType = tika.detect(input, filename);
        OutputStream output = Files.newOutputStream(temp);
        digest.update(fileContent);
        output.write(fileContent);
        input.close();
        output.flush();
        output.close();
        String sha1 = Hex.encodeHexString(digest.digest());
        return save(temp, sha1, filename, mimeType, type);
    }

    public Attachment store(Attachment.Type type, File file) throws IOException {
        MessageDigest digest = DigestUtils.getSha1Digest();
        String filename = file.getName();
        if (filename != null) {
            digest.update(filename.getBytes());
        }
        Tika tika = new Tika();
        String mimeType = tika.detect(file);
        String sha1 = Hex.encodeHexString(digest.digest());
        return save(file.toPath(), sha1, filename, mimeType, type);
    }

    public Attachment store(Attachment.Type type, Path file) throws IOException {
        String filename = file.getFileName().toString();
        MessageDigest digest = DigestUtils.getSha1Digest();
        if (filename != null) {
            digest.update(filename.getBytes());
        }
        Tika tika = new Tika();
        String mimeType = tika.detect(file);
        InputStream input = Files.newInputStream(file);
        byte[] buf = new byte[8192];
        int n;
        while ((n = input.read(buf)) > 0) {
            digest.update(buf, 0, n);
        }
        String sha1 = Hex.encodeHexString(digest.digest());
        return save(file, sha1, filename, mimeType, type);
    }

    public Resource loadAsResource(String id) {
        Path file = attachmentPath.resolve(id);
        FileSystemResource resource = new FileSystemResource(file);
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new StorageFileNotFoundException();
        }
    }

    public Attachment.Wrapper loadAsWrapper(String id) {
        Path file = attachmentPath.resolve(id);
        FileSystemResource resource = new FileSystemResource(file);
        if (!resource.exists() || !resource.isReadable()) {
            throw new StorageFileNotFoundException();
        }
        Attachment attachment = repo.selectById(id); //.orElseThrow(NotExistException::new);
        return new Attachment.Wrapper(attachment, resource);
    }

    public Attachment findById(String id) {
        return repo.selectById(id); //.orElseThrow(NotExistException::new);
    }

    public List<Attachment> findAllById(Collection<String> ids) {
        return (ids != null && !ids.isEmpty()) ? repo.selectList(new LambdaQueryWrapper<Attachment>().in(Attachment::getId, ids)) : new ArrayList<>();
    }

    public Path convertToTempPath(String fileName) {
        fileName = fileName.replace("/", "");
        Path path;
        try {
            path = tempPath.resolve(fileName);
        } catch (Exception e) {
            StringBuilder cleanName = new StringBuilder();
            for (int i = 0; i < fileName.length(); i++) {
                int c = fileName.charAt(i);
                if (Arrays.binarySearch(illegalChars, c) < 0) {
                    cleanName.append((char) c);
                }
            }
            path = tempPath.resolve(cleanName.toString());
        }
        return path;
    }

    private Attachment save(Path temp, String id, String filename, String mimeType, Attachment.Type type) throws IOException {
        Path dest = attachmentPath.resolve(id);
        if (Files.exists(dest)) {
            return findById(id);
        }
        Files.move(temp, dest);
        Attachment attachment = new Attachment(id, filename, mimeType, type, LocalDateTime.now());
        repo.insert(attachment);
        return attachment;
    }

}

package com.xin.web.controller.common;

import com.xin.common.annotation.Anonymous;
import com.xin.common.config.XinConfig;
import com.xin.common.core.domain.AjaxResult;
import com.xin.common.exception.NonCaptureException;
import com.xin.common.utils.StringUtils;
import com.xin.common.utils.file.FileUploadUtils;
import com.xin.common.utils.file.FileUtils;
import com.xin.framework.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    private final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private ServerConfig serverConfig;

    @Anonymous
    @PostMapping("/upload")
    @SuppressWarnings("DuplicatedCode")
    public AjaxResult uploadFile(MultipartFile file) {
        try {
            log.info("文件 {} 上传中...", file.getOriginalFilename());
            // 上传文件路径
            String filePath = XinConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            log.info("文件 {} 上传成功！", file.getOriginalFilename());
            return ajax;
        } catch (Exception e) {
            throw new NonCaptureException(StringUtils.format("文件 {} 上传失败！", file.getOriginalFilename()), e);
        }
    }
}
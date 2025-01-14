package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * common interface
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "common interfaces")
@Slf4j
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * Upload files
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("Upload files")
    public Result<String> upload(MultipartFile file){
        // Why is the generic type（泛型） of Result a String? Because the data field in the return value of this method is of type String. The data field represents the file upload path. Backend can request files from this upload path (web address).
        // Why is the parameter named file? Because it needs to match the parameter name in the body sent by the frontend.
        log.info("Upload files:{}", file);

        String filePath = null;

        try {
            // get original file name
            String originalFilename = file.getOriginalFilename();
            // get suffix of original file name, e.g.  .jpg/.png
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // create a new file name
            // It can avoid duplicated file names which will make old files be covered.
            String objectName = UUID.randomUUID().toString() + extension;

            // file request path
            filePath = aliOssUtil.upload(file.getBytes(), objectName);

        } catch (IOException e) {
            log.error("The file failed to upload：{}", e);
            return Result.error(MessageConstant.UPLOAD_FAILED);
        }
        return Result.success(filePath);
    }
}

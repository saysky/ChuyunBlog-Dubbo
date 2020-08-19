package com.example.blog.web.controller.admin;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrBuilder;
import com.example.blog.api.exception.MyBusinessException;
import com.example.blog.web.controller.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * <pre>
 *     后台附件控制器
 * </pre>
 *
 * @author : author
 * @date : 2017/12/19
 */
@Slf4j
@Controller
@RequestMapping(value = "/admin/file")
public class AttachmentController extends BaseController {


    /**
     * 上传文件
     *
     * @param file file
     * @return Map
     */
    @PostMapping(value = "/upload", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) {
        Map<String, Object> map = new HashMap<>(1);
        String path = this.upload(file);
        map.put("link", path);
        return map;
    }

    public String upload(MultipartFile file) {
        String path = "";
        try {
            //用户目录
            final StrBuilder uploadPath = new StrBuilder(System.getProperties().getProperty("user.home"));
            uploadPath.append("/sens/upload/" + DateUtil.thisYear()).append("/").append(DateUtil.thisMonth() + 1).append("/");
            final File mediaPath = new File(uploadPath.toString());
            if (!mediaPath.exists()) {
                if (!mediaPath.mkdirs()) {
                    throw new MyBusinessException("上传失败");
                }
            }

            //不带后缀
            String nameWithOutSuffix = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.')).replaceAll(" ", "_").replaceAll(",", "");

            //文件后缀
            final String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);

            //带后缀
            String fileName = nameWithOutSuffix + "." + fileSuffix;

            //判断文件名是否已存在
            File descFile = new File(mediaPath.getAbsoluteFile(), fileName);
            int i = 1;
            while (descFile.exists()) {
                nameWithOutSuffix = nameWithOutSuffix + "(" + i + ")";
                descFile = new File(mediaPath.getAbsoluteFile(), nameWithOutSuffix + "." + fileSuffix);
                i++;
            }
            file.transferTo(descFile);

            //文件原路径
            final StrBuilder fullPath = new StrBuilder(mediaPath.getAbsolutePath());
            fullPath.append("/");
            fullPath.append(nameWithOutSuffix + "." + fileSuffix);

            //压缩文件路径
            final StrBuilder fullSmallPath = new StrBuilder(mediaPath.getAbsolutePath());
            fullSmallPath.append("/");
            fullSmallPath.append(nameWithOutSuffix);
            fullSmallPath.append("_small.");
            fullSmallPath.append(fileSuffix);

            //映射路径
            final StrBuilder filePath = new StrBuilder("/upload/");
            filePath.append(DateUtil.thisYear());
            filePath.append("/");
            filePath.append(DateUtil.thisMonth() + 1);
            filePath.append("/");
            filePath.append(nameWithOutSuffix + "." + fileSuffix);
            path = filePath.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


}

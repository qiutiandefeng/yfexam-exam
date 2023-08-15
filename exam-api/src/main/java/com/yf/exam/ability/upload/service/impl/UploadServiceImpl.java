package com.yf.exam.ability.upload.service.impl;

import com.yf.exam.ability.Constant;
import com.yf.exam.ability.upload.config.UploadConfig;
import com.yf.exam.ability.upload.dto.UploadReqDTO;
import com.yf.exam.ability.upload.dto.UploadRespDTO;
import com.yf.exam.ability.upload.service.UploadService;
import com.yf.exam.ability.upload.utils.FileUtils;
import com.yf.exam.core.exception.ServiceException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 文件上传业务类
 * @author bool
 * @date 2019-07-30 21:02
 */
@Log4j2
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadConfig conf;

    @Override
    public UploadRespDTO upload(UploadReqDTO reqDTO) {


        // 文件内容
        MultipartFile file = reqDTO.getFile();

        System.out.println("++++后缀："+FilenameUtils.getExtension(file.getOriginalFilename()));

        // 验证文件后缀
        boolean allow = FilenameUtils.isExtension(file.getOriginalFilename(), conf.getAllowExtensions());
        if(!allow){
            throw new ServiceException("文件类型不允许上传！");
        }

        // 上传文件夹
        String fileDir = conf.getDir();

        // 真实物理地址
        String fullPath;

        try {

            // 新文件
            String filePath = FileUtils.processPath(file);
            // 文件保存地址
            fullPath = fileDir + filePath;
            // 创建文件夹
            FileUtils.checkDir(fullPath);
            // 上传文件
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(fullPath));

            return this.generateResult(filePath);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("文件上传失败："+e.getMessage());
        }
    }



    @Override
    public void download(HttpServletRequest request, HttpServletResponse response) {

        // 获取真实的文件路径
        String filePath = this.getRealPath(request.getRequestURI());

        // 处理中文问题
        try {
            filePath =  URLDecoder.decode(filePath, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("++++完整路径为："+filePath);

        try {
            FileUtils.writeRange(request, response, filePath);
        } catch (IOException e) {
            response.setStatus(404);
            log.error("预览文件失败" + e.getMessage());
        }
    }


    /**
     * 构造返回
     * @param fileName
     * @return
     */
    private UploadRespDTO generateResult(String fileName) {

        //获取加速域名
        String domain = conf.getUrl();

        // 返回结果
        return new UploadRespDTO(domain + fileName);
    }


    /**
     * 获取真实物理文件地址
     * @param uri
     * @return
     */
    public String getRealPath(String uri){

        String regx = Constant.FILE_PREFIX+"(.*)";

        // 查找全部变量
        Pattern pattern = Pattern.compile(regx);
        Matcher m = pattern.matcher(uri);
        if (m.find()) {
            String str = m.group(1);
            return conf.getDir() + str;
        }

        return null;
    }

}

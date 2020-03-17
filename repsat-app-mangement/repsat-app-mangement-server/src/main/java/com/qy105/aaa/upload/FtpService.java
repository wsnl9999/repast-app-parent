package com.qy105.aaa.upload;

import com.qy105.aaa.properties.FtpProperties;
import com.qy105.aaa.util.DateUtil;
import com.qy105.aaa.util.FileNameUtil;
import com.qy105.aaa.util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.qy105.aaa.staticstatus.StaticCode.*;

/**
 * @description:
 * @author: cat
 * @date: Created in 2020/3/16 14:57
 * @version: 1.0
 */
@Service
public class FtpService {
    @Autowired
    private FtpProperties ftpProperties;

    /**
     *  ftp上传方法
     * @author cat
     * @date 2020/3/16 15:14
     * @param file:
    * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> upload(MultipartFile file,String token) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 1.获取文件的原始名称
        String oldFileName = file.getOriginalFilename();
        // 2.创建新的文件名称
        String newFileName = FileNameUtil.getFileName(token);
        // 3.截取原始名称的后缀
        String substring = oldFileName.substring(oldFileName.lastIndexOf(POINT));
        // 4.把截取出的后缀拼接到新的文件名中
        newFileName = newFileName + substring;
        // 5.创建文件目标的目录
        String filePath = DateUtil.formatDate(new Date(), FORMAT_DATE);
        // 6.使用工具类进行连接Ftp和上传功能
        // 如何获取输入流？  file.getInputStream();

        try {
            Boolean uploadResult = FtpUtil.uploadFile(ftpProperties.getHost(), Integer.parseInt(ftpProperties.getPort()),
                    ftpProperties.getUsername(), ftpProperties.getPassword(), ftpProperties.getBasePath(),
                    filePath, newFileName, file.getInputStream());
            String picPath = ftpProperties.getHttpPath()+"/"+filePath+"/"+newFileName;
            resultMap.put(RESULT, uploadResult);
            resultMap.put(PICPATH, picPath);
        } catch (IOException e) {
            resultMap.put(RESULT, false);
            e.printStackTrace();
        }
        return resultMap;
    }
}

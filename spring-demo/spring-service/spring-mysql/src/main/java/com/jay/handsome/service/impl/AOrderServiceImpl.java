package com.jay.handsome.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jay.handsome.dao.AOrderDao;
import com.jay.handsome.entity.AOrder;
import com.jay.handsome.service.AOrderService;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * <p>
 * a订单 服务实现类
 * </p>
 *
 * @author jay
 * @since 2024-09-11
 */
@Service
public class AOrderServiceImpl extends ServiceImpl<AOrderDao, AOrder> implements AOrderService {

    public static void main(String[] args) {
        String[] urls = {
                "http://www.baidu.com/path/to/image.jpg?param1=value1&param2=value2",
                "http://www.baidu.com/path/to/another/file.png?param1=value1&param2=value2",
                "https://www.baidu.com/path/to/image.jpg?param1=value1&param2=value2",
                "http://example.com/path/to/image.jpg?param1=value1&param2=value2"
        };

        // 定义域名和文件扩展名
        String domain = "www.baidu.com";
        String fileExtension = ".jpg";

        // 遍历URL列表
        for (String urlStr : urls) {
            try {
                // 解析URL
                URL url = new URL(urlStr);

                // 检查域名
                if (domain.equals(url.getHost())) {
                    // 检查路径是否以指定的文件扩展名结尾
                    Pattern pattern = Pattern.compile(".+" + Pattern.quote(fileExtension) + "$");
                    if (pattern.matcher(url.getPath()).matches()) {
                        System.out.println("Matched: " + urlStr);
                    } else {
                        System.out.println("Not matched (wrong file extension): " + urlStr);
                    }
                } else {
                    System.out.println("Not matched (wrong domain): " + urlStr);
                }
            } catch (MalformedURLException e) {
                System.out.println("Invalid URL: " + urlStr);
            }
        }
    }
}

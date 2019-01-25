package org.ltq.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 虚拟路径配置类
 * @author Hasee
 *
 */
@SuppressWarnings("deprecation")
@Configuration
public class VirtualPathConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//文件磁盘图片url 映射
		//配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
		registry.addResourceHandler("/upload/**").addResourceLocations("D:\\MyForumUploadDir\\user_photo");
		registry.addResourceHandler("/commentPic/**").addResourceLocations("D:\\MyForumUploadDir\\comment_picture");
		registry.addResourceHandler("/postPic/**").addResourceLocations("D:\\MyForumUploadDir\\post_picture");
	}
}

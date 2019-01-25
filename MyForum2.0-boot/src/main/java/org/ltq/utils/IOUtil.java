package org.ltq.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class IOUtil {
	public void copyCommentImage(BigInteger commentid,MultipartFile comPic) {
		InputStream in = null;
		OutputStream out = null;
		//存储在服务器的目录
		String dirPath = "D:\\MyForumUploadDir\\comment_picture";
		File file = new File(dirPath);
		//判断是否存在dirPath目录，没有则创建目录
		if(!file.exists()) {
			file.mkdirs();
		}
		//文件写出时文件名为[commentId.jpg]
		File outputFile = new File(dirPath+"\\"+commentid+".jpg");
		try {
			in = new BufferedInputStream(comPic.getInputStream());
			out = new BufferedOutputStream(new FileOutputStream(outputFile));
			int len = -1;
			byte flush[] = new byte[1024];
			while((len=in.read(flush))!=-1) {
				out.write(flush, 0, len);
			}
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(in!=null) {
					in.close();
				}
				if(out!=null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void copyPostImage(int post_id,List<MultipartFile> fileList) {
		//存储在服务器的目录
		String dirPath = "D:\\MyForumUploadDir\\post_picture";
		File file = new File(dirPath);
		//判断是否存在dirPath目录，没有则创建目录
		if(!file.exists()) {
			file.mkdirs();
		}
		int i = 1;
		
		for(MultipartFile temp:fileList) {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(temp.getInputStream());
				out = new BufferedOutputStream(new FileOutputStream(new File(dirPath+"\\"+post_id+"_"+i+".jpg")));
				int len = -1;
				byte[] flush = new byte[1024];
				while((len=in.read(flush))!=-1) {
					out.write(flush, 0, len);
				}
				out.flush();
				i++;
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(in!=null) {
						in.close();
					}
					if(out!=null) {
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

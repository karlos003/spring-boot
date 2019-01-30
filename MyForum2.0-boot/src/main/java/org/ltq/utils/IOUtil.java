package org.ltq.utils;

import org.ltq.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.util.List;

public class IOUtil {
	public void copyCommentImage(BigInteger commentid,MultipartFile comPic) {
		InputStream in = null;
		OutputStream out = null;
		//存储在服务器的目录
		String dirPath = "D:\\MyForumUploadDir";
		File file = new File(dirPath);
		//判断是否存在dirPath目录，没有则创建目录
		if(!file.exists()) {
			file.mkdirs();
		}
		//文件写出时文件名为[commentId.jpg]
		File outputFile = new File(dirPath+"\\"+"c"+commentid+".jpg");
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
		String dirPath = "D:\\MyForumUploadDir";
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
				out = new BufferedOutputStream(new FileOutputStream(new File(dirPath+"\\"+"p"+post_id+"_"+i+".jpg")));
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

	public void copyUserImage(User user,MultipartFile img) {
		BufferedInputStream in = null;
		//存储在服务器中的目录
		String dirPath = "D:\\MyForumUploadDir";
		File file = new File(dirPath);

		//判断有无目录，无则创建
		if (!file.exists()) {
			file.mkdirs();
		}

		//IO流操作
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(img.getInputStream());
			//将输出文件的文件名修改为[u+account.jpg]
			File imgOut = new File(dirPath + "\\" + "u" + user.getUser_account() + ".jpg");
			out = new BufferedOutputStream(new FileOutputStream(imgOut));
			int len = -1;
			byte[] flush = new byte[1024];
			while ((len = in.read(flush)) != -1) {
				out.write(flush, 0, len);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
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

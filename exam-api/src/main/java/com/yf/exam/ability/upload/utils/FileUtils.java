package com.yf.exam.ability.upload.utils;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.yf.exam.core.utils.DateUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

/**
 * 文件工具类
 * @author bool
 */
public class FileUtils {

	/**
	 * 后缀分割符号
	 */
	private static final String SUFFIX_SPLIT = ".";


	/**
	 * 支持以断点的方式输出文件，提供文件在线预览和视频在线播放
	 * @param request
	 * @param response
	 * @param filePath
	 * @throws IOException
	 */
	public static void writeRange(HttpServletRequest request,
                                      HttpServletResponse response, String filePath) throws IOException {

		// 读取文件
		File file = new File(filePath);

		//只读模式
		RandomAccessFile randomFile = new RandomAccessFile(file, "r");
		long contentLength = randomFile.length();
		String range = request.getHeader("Range");
		int start = 0, end = 0;
		if (range != null && range.startsWith("bytes=")) {
			String[] values = range.split("=")[1].split("-");
			start = Integer.parseInt(values[0]);
			if (values.length > 1) {
				end = Integer.parseInt(values[1]);
			}
		}
		int requestSize;
		if (end != 0 && end > start) {
			requestSize = end - start + 1;
		} else {
			requestSize = Integer.MAX_VALUE;
		}

		byte[] buffer = new byte[128];
		response.setContentType(MediaUtils.getContentType(filePath));
		response.setHeader("Accept-Ranges", "bytes");
		response.setHeader("ETag", file.getName());
		response.setHeader("Last-Modified", new Date().toString());
		//第一次请求只返回content length来让客户端请求多次实际数据
		if (range == null) {
			response.setHeader("Content-length", contentLength + "");
		} else {
			//以后的多次以断点续传的方式来返回视频数据
			response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
			long requestStart = 0, requestEnd = 0;
			String[] ranges = range.split("=");
			if (ranges.length > 1) {
				String[] rangeData = ranges[1].split("-");
				requestStart = Integer.parseInt(rangeData[0]);
				if (rangeData.length > 1) {
					requestEnd = Integer.parseInt(rangeData[1]);
				}
			}
			long length;
			if (requestEnd > 0) {
				length = requestEnd - requestStart + 1;
				response.setHeader("Content-length", "" + length);
				response.setHeader("Content-Range", "bytes " + requestStart + "-" + requestEnd + "/" + contentLength);
			} else {
				length = contentLength - requestStart;
				response.setHeader("Content-length", "" + length);
				response.setHeader("Content-Range", "bytes " + requestStart + "-" + (contentLength - 1) + "/" + contentLength);
			}
		}
		ServletOutputStream out = response.getOutputStream();
		int needSize = requestSize;
		randomFile.seek(start);
		while (needSize > 0) {
			int len = randomFile.read(buffer);
			if (needSize < buffer.length) {
				out.write(buffer, 0, needSize);
			} else {
				out.write(buffer, 0, len);
				if (len < buffer.length) {
					break;
				}
			}
			needSize -= buffer.length;
		}
		randomFile.close();
		out.close();
	}




	/**
	 * 重命名文件
	 * @param fileName
	 * @return
	 */
	public static String renameFile(String fileName) {

		//没有后缀名不处理
		if (!fileName.contains(SUFFIX_SPLIT)) {
			return fileName;
		}

		//文件后缀
		String extension = FilenameUtils.getExtension(fileName);

		//以系统时间命名
		return IdWorker.getIdStr() + "."+ extension;

	}


	/**
	 * 处理新的文件路径，为上传文件预设目录，如：2021/01/01/xxx.jpg，要注意的是，前面没有斜杠
	 * @param file 文件
	 * @return
	 */
	public static String processPath(MultipartFile file){

		// 创建OSSClient实例。
		String fileName = file.getOriginalFilename();

		// 需要重命名
		fileName = renameFile(fileName);

		//获得上传的文件夹
		String dir = DateUtils.formatDate(new Date(), "yyyy/MM/dd/");

		return new StringBuffer(dir).append(fileName).toString();

	}

	/**
	 * 检查文件夹是否存在，不存在则创建
	 * @param fileName
	 * @return
	 */
	public static void checkDir(String fileName){
		int index = fileName.lastIndexOf("/");
		if(index == -1){
			return;
		}

		File file = new File(fileName.substring(0,index));
		if(!file.exists()){
			file.mkdirs();
		}
	}


}

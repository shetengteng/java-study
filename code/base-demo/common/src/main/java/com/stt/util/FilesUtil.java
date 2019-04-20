package com.stt.util;

import com.stt.exception.FileCreateFailException;
import com.stt.exception.FileDeleteFailException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2019/2/1.
 */
public class FilesUtil {
	private FilesUtil(){}

	/**
	 * create file when file is not exists or overwirte is true
	 * @param filePath
	 * @param overwrite
	 * @return
	 * @throws IOException
	 */
	public static File createFile(String filePath, boolean overwrite) throws IOException {
		File file = new File(filePath);
		if(!file.exists()){
			boolean createSucceed = file.createNewFile();
			if(!createSucceed){
				throw new FileCreateFailException("file :["+filePath+"] create failed");
			}
			return file;
		}
		if(overwrite){
			boolean deleteSucceed = deleteFile(file);
			if(!deleteSucceed){
				throw new FileDeleteFailException("because of overwrite file,so do delete original file:["+
						filePath+"] failed");
			}
			file = createFile(filePath,false);
		}
		return file;
	}

	public static boolean deleteFile(File file){
		if(!file.canWrite() || !file.canRead()){
			throw new FileDeleteFailException("can not delete file:["+file.getName()+
					"] because of file is being written or can not be read");
		}
		return file.delete();
	}

}

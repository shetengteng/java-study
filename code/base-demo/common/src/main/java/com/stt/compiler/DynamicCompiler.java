package com.stt.compiler;

import com.google.common.io.Files;
import com.stt.exception.CompileFailException;
import com.stt.exception.MkdirsFailException;
import com.stt.util.FilesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Administrator on 2019/1/31.
 */
@Slf4j
public class DynamicCompiler {

	private final static String PACKAGE = "package";
	/**
	 *get the directory of current project running
     */
	private final static String DEFAULT_COMPLIE_DIRECTORY= System.getProperty("user.dir") + File.separator+"hot-deploy";
	/**
	 * URLClassLoader scanning local extension path
	 */
	private final static String CLASS_LOADER_URL_VALUE="file:"+File.separator+DEFAULT_COMPLIE_DIRECTORY+File.separator;

	static{
		File directory = new File(DEFAULT_COMPLIE_DIRECTORY);
		if(!directory.exists()){
			directory.mkdirs();
		}
	}

	public static Class<?> compile(String javaFileName,String javaCode){
		try{
			// get package name and create package directory
			String packageName = parsePackageName(javaCode);
			String path = createPackageDirectories(packageName);

			// create java file
			String javaFilePath = path + File.separator + javaFileName;
			createJavaFile(javaFilePath,javaCode);

			// compile java file to class file
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			int compileResult = compiler.run(null, null, null, javaFilePath);
			if(compileResult != 0){
				throw new CompileFailException("java file ["+javaFileName+"] compile failed");
			}

			// load class file
			URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL(CLASS_LOADER_URL_VALUE)});
			return classLoader.loadClass(
					packageName+"."+javaFileName.substring(0,javaFileName.lastIndexOf(".")));
		}catch (CompileFailException e){
			throw e;
		}catch (Exception e){
			log.error("compile javaFileName:[{}] failed:{}",javaFileName,e);
			throw new CompileFailException("compile java file[ "+javaFileName+"] failed");
		}
	}

	/**
	 * create java file
	 * @param javaFilePath
	 * @param javaCode
	 */
	private static void createJavaFile(String javaFilePath, String javaCode) throws IOException {
		File javaFile = FilesUtil.createFile(javaFilePath,true);
		Files.write(javaCode.getBytes(),javaFile);
	}

	/**
	 * mkdir from package name by replace dot to separator
	 * @param packageName
	 * @return package directory
	 */
	private static String createPackageDirectories(String packageName) {
		if(StringUtils.isEmpty(packageName)){
			throw new IllegalArgumentException("Java code'package info is empty");
		}
		String path = DEFAULT_COMPLIE_DIRECTORY+File.separator+packageName.replace("\\.",File.separator);
		File pathDir = new File(path);
		if(!pathDir.exists() && !pathDir.mkdirs()){
			throw new MkdirsFailException("create package directory:"+path+" failed");
		}
		return path;
	}

	/**
	 * fetch package name from first line of javaCode string
	 * @param javaCode
	 * @return
	 */
	private static String parsePackageName(String javaCode) throws IOException {
		try(BufferedReader bf = new BufferedReader(new StringReader(javaCode))){
			String packageLine = bf.readLine();
			if(StringUtils.isEmpty(packageLine)){
				throw new IllegalArgumentException("Java code'package info is not exists");
			}
			packageLine = packageLine.trim();
			if(!packageLine.startsWith(PACKAGE)){
				throw new IllegalArgumentException("Java code'package info is not exists");
			}
			String packageName = packageLine.substring(7).trim();
			return packageName.substring(0,packageName.lastIndexOf(";"));
		}
	}

}
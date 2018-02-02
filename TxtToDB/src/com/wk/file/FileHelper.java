package com.wk.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @作者:wangkun
 * @日期:2017年10月24日
 * @公司:spd
 * @说明:
*/
public class FileHelper {
	public String readFile(String strFile) throws Exception{
		File file = new File(strFile);
		if(!file.exists()){
			System.out.println("文件不存在!");
			return "";
		}
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader read = new InputStreamReader(fis,"utf-8");
		BufferedReader bufferedReader = new BufferedReader(read);
		StringBuilder sb = new StringBuilder();
		String lineTxt = null;
		while((lineTxt = bufferedReader.readLine()) != null){
			sb.append(lineTxt);
                }
		read.close();
		fis.close();
		return sb.toString();
	}
}

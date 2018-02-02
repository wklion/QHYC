package com.wk.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @����:wangkun
 * @����:2017��10��24��
 * @��˾:spd
 * @˵��:
*/
public class FileHelper {
	public String readFile(String strFile) throws Exception{
		File file = new File(strFile);
		if(!file.exists()){
			System.out.println("�ļ�������!");
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

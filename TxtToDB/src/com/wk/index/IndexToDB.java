package com.wk.index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.google.gson.Gson;
import com.wk.file.FileHelper;
import com.wk.jdbc.DbPoolConnection;
import com.wk.model.Config;

/**
 * @����:wangkun
 * @����:2017年10月24日
 * @��˾:spd
 * @˵��:
*/
public class IndexToDB {
	private static String root=Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1);
	public static void main(String[] args) throws Exception {
		Boolean debug = true;
		//1、连接数据库
		DbPoolConnection dbp = DbPoolConnection.getInstance();
		DruidPooledConnection conn = dbp.getConnection();
		System.out.println("1、连接数据库");
		
		//2、读取配置
		FileHelper fileHelper = new FileHelper();
		String strJson = fileHelper.readFile(root+"/config.json");
		Gson gson = new Gson();
		Config config = gson.fromJson(strJson, Config.class);
		String strIndexFile = config.getIndexFile();
		
		//3、生成sql
		String strInsertSql = "insert into t_index_value(datetime,";
		for(int i=1;i<143;i++){
			String colName = "index"+i;
			strInsertSql += colName;
			strInsertSql += ",";
		}
		strInsertSql = strInsertSql.substring(0, strInsertSql.length()-1);
		strInsertSql+=") values(?,";
		for(int i=1;i<143;i++){
			strInsertSql += "?,";
		}
		strInsertSql = strInsertSql.substring(0, strInsertSql.length()-1);
		strInsertSql += ")";
		
		//4、读取内容
		File file = new File(strIndexFile);
		if(!file.exists()){
			System.out.println("文件不存在:"+strIndexFile);
			return;
		}
		InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
		BufferedReader br = new BufferedReader(reader);
		String line = "";
		line = br.readLine();//忽略第一行
		line = br.readLine();
		PreparedStatement ps = null;
		while (line != null) {
			line = line.trim();
			String[] strs = line.split("\\s+");
			int colSize = strs.length;
			if(colSize!=143){
				System.out.println("列数不为143");
				return;
			}
			String strDateTime = strs[0];
			System.out.println("日期:"+strDateTime);
			//判断是否存在
			String exitSql = "select * from t_index_value where datetime='%s'";
			exitSql = String.format(exitSql, strDateTime);
			ps = conn.prepareStatement(exitSql);
			ResultSet rs = ps.executeQuery();
			rs.last();
			int count = rs.getRow();
			if(count>0){//存在
				line = br.readLine();
				continue;
			}
			rs.close();
			ps.close();
			ps = conn.prepareStatement(strInsertSql);
			for(int i=0;i<colSize;i++){
				if(i==0){
					ps.setString(i+1, strs[i]);
				}
				else{
					double val = Double.parseDouble(strs[i]);
					ps.setDouble(i+1, val);
				}
			}
			ps.executeUpdate();
			line = br.readLine();
		}
		ps.close();
		conn.close();
	}

}

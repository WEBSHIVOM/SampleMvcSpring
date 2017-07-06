package com.inno.dao;

import java.io.InputStream;
import java.util.ArrayList;

import com.inno.utility.FileModel;
import com.inno.utility.LoginModel;
import com.inno.utility.RegisterModel;

public interface DbLog {
	public boolean DbLogCheck(String sql,RegisterModel modelObj);

	 public boolean DbRegCheck(String sql,RegisterModel RegObject, InputStream inputStream);

	 public ArrayList<RegisterModel> viewdbdata(String sql);
	 
	 public boolean Dbupdate(String sql,RegisterModel Updateobj);
	 
	 public ArrayList<RegisterModel> DbSearch(String sql,String searchvalue);
	 public boolean DbDelete(String sql,String deleteModelval);
	 
	 boolean SaveUploadFile(String sql,InputStream FileVAlue,RegisterModel fileModel);
	 
	 public ArrayList<FileModel> dbfileview(String sql);
	 public ArrayList<RegisterModel> getdbdata(String sql,String viewid);
}

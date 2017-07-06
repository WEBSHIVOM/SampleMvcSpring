package com.inno.service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import com.inno.utility.FileModel;
import com.inno.utility.LoginModel;
import com.inno.utility.RegisterModel;

public interface UserLog {
  public boolean UserLogin(RegisterModel mODEL);
  
  
  public ArrayList<RegisterModel> viewdata();
  
  public ArrayList<RegisterModel> searchdata(String searchvalue);
  
  public boolean Updatedb(RegisterModel UpdateObject);

  public boolean RegData(RegisterModel ModelObj, InputStream inputStream);
  
  public boolean Deleteuser(String REG);
  
  public boolean runurl(String url);
  
 public boolean uploadtoDb(InputStream ObjFile,RegisterModel fileModel);
 
        public ArrayList<FileModel> viewdbfile();
}

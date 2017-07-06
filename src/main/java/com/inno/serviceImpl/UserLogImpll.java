package com.inno.serviceImpl;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inno.dao.DbLog;
import com.inno.service.UserLog;
import com.inno.utility.FileModel;
import com.inno.utility.LoginModel;
import com.inno.utility.RegisterModel;
@Service("UserLogServ")
public class UserLogImpll implements UserLog {
	@Autowired
	DbLog Dblogserv;
	@Autowired
	HttpServletRequest requesturl;
	
	@Override
	public boolean UserLogin(RegisterModel ModelObj) {
String sql="select * from springweb.register where name=? and password=?; ";
		
		boolean response = Dblogserv.DbLogCheck(sql, ModelObj);
		
		if(response)
		{
			 System.out.println("aUTHENTICATION");
			return true;
		}
		else
		{
			System.out.println("aUTHENTICATION failed");
		}
		
		
		
		
		return false;
	
	}

	@Override
	public ArrayList<RegisterModel> viewdata() {
		String sql= "select * from springweb.register;";
		
		ArrayList<RegisterModel> ServImpl= Dblogserv.viewdbdata(sql);
			if(ServImpl!=null){
				
				System.out.println("Viewlog serving");
				return ServImpl;
			}
			System.out.println("Viewlog not serving");
			return null;
	}

	@Override
	public ArrayList<RegisterModel> searchdata(String searchvalue) {
		String sql="SELECT * FROM springweb.register WHERE name LIKE ? or address Like ? or email Like ? or id Like ?;";
		ArrayList<RegisterModel> SearchAuth=Dblogserv.DbSearch(sql, searchvalue);
		if(SearchAuth!=null){
			System.out.println("searching db");
			return SearchAuth; 
			
		}
		else{
			System.out.println("serch unsuccess");
		}
		return null;
		
		
	}

	@Override
	public boolean Updatedb(RegisterModel UpdateObject) {
		String sql="update springweb.register set name=?,email=?,address=?  where id=?;";
		boolean response = Dblogserv.Dbupdate(sql, UpdateObject);
				
				if(response)
				{
					 System.out.println(" updation sucess");
					return true;
				}
				else
				{
					System.out.println("updation failed");
				}
				
				return false;
	}

	@Override
 public boolean RegData(RegisterModel ModelObj, InputStream inputStream){
		String sql="insert into springweb.register (name,id,address,email,gender,country,image) values (?,?,?,?,?,?,?);";
		
		boolean response = Dblogserv.DbRegCheck(sql, ModelObj,inputStream);
				
				if(response)
				{
					 System.out.println(" REGister aUTHENTICATION");
					return true;
				}
				else
				{
					System.out.println("aUTHENTICATION failed");
				}
					return false;
			}
	

	@Override
	public boolean Deleteuser(String REG) {
String sql="delete from springweb.register where name=?;";
		
		boolean DeleteServAuth= Dblogserv.DbDelete( sql,REG);
		if(DeleteServAuth){
			System.out.println("Deletion served");
			return true;
		}
		else {
			System.out.println("Deletion not served");
			return false;
		}
	}

	@Override
	public boolean runurl(String url) {
         
		return requesturl.getRequestURI().equals(url);
	}

	@Override
	public boolean uploadtoDb(InputStream ObjFile,RegisterModel Model) {
	
		String sql="update springweb.register  set image=? where id=?;";
		
		boolean uploadauth=Dblogserv.SaveUploadFile(sql, ObjFile,Model);
		if(uploadauth){
			System.out.println("suceess");
			return true;
		}
		
		
		return false;
	}

	@Override
	public ArrayList<FileModel> viewdbfile() {
		
		String sql="select * from springweb.filedata;";
		
	ArrayList<FileModel> dbdata=Dblogserv.dbfileview(sql);
		if(dbdata!=null)
		{
			System.out.println("fetched files from db");
			return dbdata;
		}
		else{
			System.out.println("filed to fetch");
		}
		
		return dbdata;
		
	}

	
	
	

	

}

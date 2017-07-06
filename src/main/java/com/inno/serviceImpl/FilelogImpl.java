package com.inno.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inno.dao.DbFilelog;
import com.inno.service.FileLog;
import com.inno.utility.CountryModel;
import com.inno.utility.FileModel;
import com.inno.utility.RegisterModel;
@Service("FillogServ") 
public class FilelogImpl implements FileLog {
	@Autowired
	DbFilelog DbbYtes;

	@Override
	public FileModel getcopy(int id) {
		
		String sql="select * from springweb.filedata where id=?;";
		
		FileModel dbbytesdata=DbbYtes.dbdata(sql, id);
		
		if(dbbytesdata!=null){
			System.out.println("servive bytes ready");
			return dbbytesdata;}
		else
		{System.out.println("servive bytes ready");
		return dbbytesdata;
	     }
	
	}

	@Override
	public boolean getdelete(int id) {
		String sql="delete from springweb.filedata where id=?;";
		
		boolean deletedbauth=DbbYtes.deletedbdat(sql, id);
		if(deletedbauth){
			System.out.println("true deletion");
			return true;
		}else {
			System.out.println("failed attempt to delete");
			return false;
		}
		
		
	}

	@Override
	public ArrayList<CountryModel> getcountrydata() {
		
		
		String sql="SELECT * FROM springweb.apps_countries;";
		
		ArrayList<CountryModel> dbdata=DbbYtes.getcntrydata(sql);
		if(dbdata!=null){
			System.out.println("get service suceess");
			return dbdata;
		}
		
		return null;
	}

	@Override
	public RegisterModel getdp(String id) {
		
		
		String sql="SELECT * FROM springweb.register where id=?;";
		
		RegisterModel dbquery= DbbYtes.getdpview(sql, id);
		if(dbquery!=null){
			System.out.println("dp fetched succesfully");
			return dbquery;
		}
		
		return null;
	}
}

package com.inno.daoImpl;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inno.dao.DbFilelog;
import com.inno.utility.CountryModel;
import com.inno.utility.FileModel;
import com.inno.utility.RegisterModel;
import com.mysql.jdbc.PreparedStatement;

@Service("DbbYtes")
public class DbfileLogImpl implements DbFilelog{
	@Autowired
	DataSource datasource;	      
			
	@Override
public FileModel dbdata(String sql, int id) {
		byte[] totalbytes=null;
		FileModel fileModel= new FileModel();
		try {
			Connection conn = datasource.getConnection();
			java.sql.PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet resultSet =ps.executeQuery();
			int i=1;
			while(resultSet.next()){
				String filedbtype= resultSet.getString("filetype");
				Blob dbfiledata=resultSet.getBlob("file_data");
				
				byte[] dbbytes= dbfiledata.getBytes(i, (int) dbfiledata.length());
				totalbytes=dbbytes;
				fileModel.setFile_Data(dbbytes);
				fileModel.setFiletype(filedbtype);
				i++;
			}
			if (totalbytes!=null){
				System.out.println("successful byte fetch");
				return  fileModel;
			}
			else{
				System.out.println("failed to fetch byte ");
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return fileModel;

		
		
		
		
		
	}

	@Override
	public boolean deletedbdat(String sql, int id) {
		try{
			Connection connection= datasource.getConnection();
			java.sql.PreparedStatement ps=connection.prepareStatement(sql);
			ps.setLong(1, id);
			int resultSet= ps.executeUpdate();
			if(resultSet==1){
				System.out.println("deletion success");
				return true;
			}
			else{
				System.out.println("deletion  failed");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<CountryModel> getcntrydata(String sql) {
		try{
			Connection connection= datasource.getConnection();
			java.sql.PreparedStatement ps=connection.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			ArrayList<CountryModel> countryDb_data= new ArrayList<>();
			while(rs.next()){
				CountryModel  model= new CountryModel();
				model.setId(rs.getInt("id"));
				model.setCountry_code(rs.getString("country_code"));
				model.setCountry_name(rs.getString("country_name"));
				countryDb_data.add(model);
			}
			if(!countryDb_data.isEmpty()){
				System.out.println("fetched success");
				return countryDb_data;
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RegisterModel getdpview(String sql, String id) {
		try{
			Connection connection= datasource.getConnection();
			java.sql.PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rss=ps.executeQuery();
			RegisterModel rm = new RegisterModel();
			while(rss.next()){
				
				rm.setName(rss.getString("name"));
				rm.setAddress(rss.getString("address"));
				rm.setId(rss.getString("id"));
				rm.setEmail(rss.getString("email"));
				rm.setCountry(rss.getString("country"));
				rm.setGender(rss.getBoolean("gender"));			
				rm.setDataimg(rss.getBytes("image"));
			}
			if(rm!=null){
				return rm;
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

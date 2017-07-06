package com.inno.daoImpl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.inno.dao.DbLog;
import com.inno.utility.FileModel;
import com.inno.utility.LoginModel;
import com.inno.utility.RegisterModel;
import com.mysql.jdbc.Blob;

@Service("Dblogserv")

@Repository
public class DbLogImpl implements DbLog {

	@Autowired
	DataSource datasource;

	@Override
	public boolean DbLogCheck(String sql, RegisterModel ModelObject) {
		try {
			Connection conn = datasource.getConnection();
			java.sql.PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ModelObject.getName());
			ps.setString(2, ModelObject.getPassword());

			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()){
				i++;
				String role= rs.getString("role");
				System.out.println(role);
				ModelObject.setRole(role);
				ModelObject.setName(rs.getString("name"));
				ModelObject.setId(rs.getString("id"));
				ModelObject.setAddress(rs.getString("address"));
		ModelObject.setDataimg(rs.getBytes("image"));
			}
			if (i > 0) {
				System.out.println("databse authenticating");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean DbRegCheck(String sql, RegisterModel Regobj,InputStream inputsast) {
		try {
			Connection conn = datasource.getConnection();
			java.sql.PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Regobj.getName());
			ps.setString(2, Regobj.getId());
			ps.setString(3, Regobj.getAddress());
			ps.setString(4, Regobj.getEmail());
            ps.setBoolean(5, Regobj.isGender());
            ps.setString(6, Regobj.getCountry());
            ps.setBlob(7, inputsast);
			int rs = ps.executeUpdate();
			while (rs == 1) {
				System.out.println("database register authenticating");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<RegisterModel> viewdbdata(String sql) {

		try {
			Connection conn = datasource.getConnection();
			java.sql.PreparedStatement ps = conn.prepareStatement(sql);
             
			ArrayList<RegisterModel> data = new ArrayList<>();
			ResultSet rss = ps.executeQuery();
			while (rss.next()) {
			
				RegisterModel rm = new RegisterModel();
				rm.setName(rss.getString("name"));
				rm.setAddress(rss.getString("address"));
				rm.setId(rss.getString("id"));
				rm.setEmail(rss.getString("email"));
				rm.setCountry(rss.getString("country"));
				rm.setGender(rss.getBoolean("gender"));
				byte[] dbimgdata=rss.getBytes("image");
				
//				byte[] dbimdt=dbimgdata.getBytes(i, (int) dbimgdata.length());
				rm.setDataimg(dbimgdata);
				data.add(rm);
			}

			if (rss != null) {
				System.out.println("database fetch for existing user");
				return data;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + e.getLocalizedMessage());
			e.printStackTrace();
		}

		System.out.println("database says no fetch");
		return null;
	}

	@Override
	public ArrayList<RegisterModel> getdbdata(String sql,String viewid) {

		try {
			Connection conn = datasource.getConnection();
			java.sql.PreparedStatement ps = conn.prepareStatement(sql);
             ps.setString(1, viewid);
			ArrayList<RegisterModel> data = new ArrayList<>();
			ResultSet rss = ps.executeQuery();
			while (rss.next()) {
			
				RegisterModel rm = new RegisterModel();
				rm.setName(rss.getString("name"));
				rm.setAddress(rss.getString("address"));
				rm.setId(rss.getString("id"));
				rm.setEmail(rss.getString("email"));
				rm.setCountry(rss.getString("country"));
				rm.setGender(rss.getBoolean("gender"));
				byte[] dbimgdata=rss.getBytes("image");
				
//				byte[] dbimdt=dbimgdata.getBytes(i, (int) dbimgdata.length());
				rm.setDataimg(dbimgdata);
				data.add(rm);
			}

			if (rss != null) {
				System.out.println("database fetch for existing user");
				return data;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + e.getLocalizedMessage());
			e.printStackTrace();
		}

		System.out.println("database says no fetch");
		return null;
	}

	@Override
	public boolean Dbupdate(String sql, RegisterModel Updateobj) {
		try {
			Connection con = datasource.getConnection();
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, Updateobj.getName());
			ps.setString(2,Updateobj.getEmail());
			ps.setString(3, Updateobj.getAddress());
			ps.setString(4, Updateobj.getId());
			
			int resltst = ps.executeUpdate();

			while (resltst == 1) {

				System.out.println("updation successful");
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public ArrayList<RegisterModel> DbSearch(String sql, String searchvalue) {
		
		try {
			Connection con = datasource.getConnection();
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, searchvalue+"%");
			ps.setString(2, searchvalue+"%");
			ps.setString(3, searchvalue+"%");
			ps.setString(4, searchvalue+"%");
			ArrayList<RegisterModel> searchedb = new ArrayList<>();
			ResultSet resultset = ps.executeQuery();
			while (resultset.next()) {
				RegisterModel rm = new RegisterModel();
				if(resultset.getString("name") != null){
				rm.setName(resultset.getString("name"));
				rm.setAddress(resultset.getString("address"));
				rm.setId(resultset.getString("id"));
				rm.setEmail(resultset.getString("email"));
				rm.setGender(resultset.getBoolean("gender"));
				rm.setCountry(resultset.getString("country"));
				rm.setDataimg(resultset.getBytes("image"));
				searchedb.add(rm);
				}
			}
			if (searchedb. size()!=0) {
				System.out.println("database SEARCHED for existing user");
				return searchedb;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("database says no SUCH USER");

		return null;

	}

	@Override
	public boolean DbDelete(String sql, String DeleteModel) {

		try {
			Connection conn = datasource.getConnection();
			java.sql.PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, DeleteModel);
			int rslt = ps.executeUpdate();
			while (rslt == 1) {
				System.out.println("deletion successful");
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("deletion failed");

		return false;
	}

	@Override
	public boolean SaveUploadFile(String sql, InputStream FileVAlue,RegisterModel  fileModel) {
		try {
			Connection conn = datasource.getConnection();
			java.sql.PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBlob(1, FileVAlue);
           
            ps.setString(2, fileModel.getId());
			int rse = ps.executeUpdate();
			if (rse > 0) {
				System.out.println("dp chnge succesfully in databse also");
				return true;
			} else {
				System.out.println("dp uploaded into db failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public ArrayList<FileModel> dbfileview(String sql) {
		ArrayList<FileModel> fileModel=new ArrayList<FileModel>();
		try {
			Connection conn = datasource.getConnection();
			java.sql.PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rse = ps.executeQuery();
			
			
			while (rse.next()) {
				FileModel rsModel =new FileModel();
				rsModel.setId(rse.getInt("id"));
				rsModel.setFile_Data(rse.getBytes("file_data"));
				System.out.println("file get from db" + rsModel.getId());
			fileModel .add(rsModel);
			}
             return fileModel;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return fileModel; 
	}

}

package com.inno.dao;

import java.util.ArrayList;

import com.inno.utility.CountryModel;
import com.inno.utility.FileModel;
import com.inno.utility.RegisterModel;

public interface DbFilelog {
	public FileModel dbdata(String sql,int id);
	
	public boolean deletedbdat(String sql,int id);
	
	public ArrayList<CountryModel> getcntrydata(String sql);
	
    public RegisterModel getdpview(String sql,String id);
}

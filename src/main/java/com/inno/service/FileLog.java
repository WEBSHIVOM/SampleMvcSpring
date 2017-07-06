package com.inno.service;

import java.util.ArrayList;

import com.inno.utility.CountryModel;
import com.inno.utility.FileModel;
import com.inno.utility.RegisterModel;

public interface FileLog {
	public FileModel getcopy(int id);
    
	
	public boolean getdelete(int id);
	
	public ArrayList<CountryModel> getcountrydata();
	
	public RegisterModel getdp(String id);
}

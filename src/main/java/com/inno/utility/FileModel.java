package com.inno.utility;

public class FileModel {
	private int Id;
	String filetype;
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	private byte[] File_Data;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public byte[] getFile_Data() {
		return File_Data;
	}
	public void setFile_Data(byte[] file_Data) {
		File_Data = file_Data;
	}
	
	
	

}

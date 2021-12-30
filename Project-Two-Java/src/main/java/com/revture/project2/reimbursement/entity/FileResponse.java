package com.revture.project2.reimbursement.entity;

public class FileResponse {

    private String iod;
    private String name;
    private Long size;
    private String url;
    private String contentType;
    private int id;
     
	public FileResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public FileResponse(String iod, String name, Long size, String url, String contentType, int id) {
		super();
		this.iod = iod;
		this.name = name;
		this.size = size;
		this.url = url;
		this.contentType = contentType;
		this.id = id;
	}


	public String getIod() {
		return iod;
	}
	public void setIod(String iod) {
		this.iod = iod;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "FileResponse [iod=" + iod + ", name=" + name + ", size=" + size + ", url=" + url + ", contentType="
				+ contentType + ", id=" + id + "]";
	}
    
	
}
package com.revture.project2.reimbursement.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String iod;

    private String name;

    private String contentType;

    private Long size;
	
    @Lob
    private byte[] data;

    private int id;
    
    public FileEntity() {
		super();
	}
    
	public FileEntity(String iod, String name, String contentType, Long size, int id, byte[] data) {
		super();
		this.iod = iod;
		this.name = name;
		this.contentType = contentType;
		this.size = size;
		this.id = id;
		this.data = data;
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "FileEntity [iod=" + iod + ", name=" + name + ", contentType=" + contentType + ", size=" + size + ", id="
				+ id + ", data=" + Arrays.toString(data) + "]";
	}

	
    
    
}
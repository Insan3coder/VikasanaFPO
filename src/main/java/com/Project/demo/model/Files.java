package com.Project.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "FILES")
public class Files implements Serializable {

	private static final long serialVersionUID = 1L;

	public Files() {
	}

	public Files(String fileName, String fileType, byte[] bs) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileContent = bs;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FILE_ID", updatable = false, nullable = false)
	private String fileId;

	@Column(name = "FILE_TYPE")
	private String fileType;

	@Lob
	@Column(name = "FILE_CONTENT")
	private byte[] fileContent;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private String fileName;
	
	@Column(name = "FILE_DESCRIPTION", nullable = true)
	private String fileDescription;


	public Files(String fileType, byte[] fileContent, String fileDescription) {
		this.fileType = fileType;
		this.fileContent = fileContent;
		this.fileDescription = fileDescription;
	}

	public String getFileId() {
		return fileId;
	}


	public void setFileId(String fileId) {
		this.fileId = fileId;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public byte[] getFileContent() {
		return fileContent;
	}


	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}


	public String getFileDescription() {
		return fileDescription;
	}


	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}
	
}
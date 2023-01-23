package com.Project.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FILES")
public class Files implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FILE_ID", updatable = false, nullable = false)
	private Long fileId;

	@Column(name = "FILE_TYPE")
	private String fileType;

	@Column(name = "FILE_CONTENT")
	private String fileContent;


	@Column(name = "FILE_DESCRIPTION")
	private String fileDescription;


	public Long getFileId() {
		return fileId;
	}


	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public String getFileContent() {
		return fileContent;
	}


	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}


	public String getFileDescription() {
		return fileDescription;
	}


	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}
	
}
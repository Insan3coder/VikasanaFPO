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

	public Files(String fileName, String fileType, String bs, String fileDescription, String filePath) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileContent = bs;
		this.fileDescription = fileDescription;
		this.filePath = filePath;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FILE_ID", updatable = false, nullable = false)
	private Long fileId;

	@Column(name = "FILE_TYPE")
	private String fileType;

	@Column(name = "FILE_PATH", nullable = false)
	private String filePath;

	@Lob
	@Column(name = "FILE_CONTENT", nullable = false)
	private String fileContent;

	@Column(name = "FILE_DESCRIPTION", nullable = true)
	private String fileDescription;

	@Column(name = "FILE_NAME")
	private String fileName;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

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
package model;

import java.io.Serializable;

public class Request implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
	
	private String ClienttName;
	private boolean listFileRequest;
	private String TypeRequest;
	private String fileNameToDownload;
	private String fileNameToUpload;
	
	
	// ....................................................................
	// getter & setter
	
	
	public String getTypeRequest() {
		return TypeRequest;
	}

	public void setTypeRequest(String request) {
		this.TypeRequest = request;
	}

	public String getFileNameToDownload() {
		return fileNameToDownload;
	}

	public void setFileNameToDownload(String fileNameToDownload) {
		this.fileNameToDownload = fileNameToDownload;
	}

	public String getFileNameToUpload() {
		return fileNameToUpload;
	}

	public void setFileNameToUpload(String fileNameToUpload) {
		this.fileNameToUpload = fileNameToUpload;
	}

	public String getClientName() {
		return ClienttName;
	}

	public void setClientName(String clienttName) {
		this.ClienttName = clienttName;
	}

	public boolean isListOfFileRequest() {
		return listFileRequest;
	}

	public void setListOfFileRequest(boolean listOfFileRequest) {
		this.listFileRequest = listOfFileRequest;
	}
}

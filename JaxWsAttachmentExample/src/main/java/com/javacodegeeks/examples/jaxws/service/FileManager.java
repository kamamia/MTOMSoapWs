package com.javacodegeeks.examples.jaxws.service;

import javax.activation.DataHandler;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface FileManager {
	
	void uploadFile(@WebParam(name = "file") DataHandler file);

	DataHandler downloadFile();

	DataHandler downloadFilepdf();

	void uploadFilepdf(DataHandler file);

	DataHandler downloadFilepdfParam(String filename);
}

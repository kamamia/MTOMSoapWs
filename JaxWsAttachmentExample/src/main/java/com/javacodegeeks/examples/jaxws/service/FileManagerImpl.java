package com.javacodegeeks.examples.jaxws.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.soap.MTOM;


@MTOM(enabled = true)
@WebService(endpointInterface = "com.javacodegeeks.examples.jaxws.service.FileManager", 
	serviceName = "FileManager ")
public class FileManagerImpl implements FileManager {

	private Path pathupldpdf = Paths.get("C:/Users/qersh/Desktop/test.pdf");
	
	private Path pathupld = Paths.get("C:/Users/qersh/Desktop/test.jpg");
	
	private Path path = Paths.get("C:/Users/qersh/Desktop/thumbnail.jpg");
	
	private Path pathpdf = Paths.get("C:/Users/qersh/Desktop/Fido.pdf");
	
	
	@Override
	@WebMethod
	public void uploadFile(DataHandler data) {
try{
		 OutputStream os = new FileOutputStream("C:/Users/qersh/Desktop/test.jpg");
	        InputStream is = data.getInputStream();          
	                 byte[] buffer = new byte[1024];
	                 int bytesRead;
	                 //read from is to buffer
	                 while((bytesRead = is.read(buffer)) !=-1){
	                     os.write(buffer, 0, bytesRead);
	                 }
	                 is.close();
	                 //flush OutputStream to write any buffered data to file
	                 os.flush();
	                 os.close();
	    }
	    catch (Exception e)
	    {
	           // throw new Exception(e);
	    }
	    

	}

	
	@Override
	@WebMethod
	public void uploadFilepdf(@XmlMimeType("application/octet-stream") DataHandler file) {

		try (InputStream input = file.getInputStream();
				OutputStream output = new FileOutputStream(
						new File(pathupldpdf.toString()));) {
			
			byte[] b = new byte[100000];
			int bytesRead = 0;
			while ((bytesRead = input.read(b)) != -1) {
				output.write(b, 0, bytesRead);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	//@XmlMimeType("application/octet-stream")
	@WebMethod
//	public  @XmlMimeType("application/octet-stream")
//	public  @XmlMimeType("application/pdf")
//	public @XmlMimeType("application/pdf")
	//@MTOM
	public  @XmlMimeType("application/octet-stream") DataHandler downloadFile() {

		DataSource dataSource = new FileDataSource(
				new File(path.toString()));
		return new DataHandler(dataSource);

	}
	

	
	@Override
//	@XmlMimeType("application/octet-stream")
	@WebMethod
//	public  @XmlMimeType("application/octet-stream")
//	public  @XmlMimeType("application/pdf")
//	public @XmlMimeType("application/pdf")
//	@MTOM
	public  @XmlMimeType("application/octet-stream")  DataHandler downloadFilepdf() {

		DataSource dataSource = new FileDataSource(
				new File(pathpdf.toString()));
		return new DataHandler(dataSource);

	}
	
	

	@Override

	@WebMethod

	public  @XmlMimeType("application/octet-stream")  DataHandler downloadFilepdfParam(@WebParam(name="filename") String filename) {

		DataSource dataSource = new FileDataSource(
				new File("C:/Users/qersh/Desktop/"+filename));
		return new DataHandler(dataSource);

	}
	
	
	
	
	

}

package com.huangpuweb.api.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.RandomStringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("serial")
public class ServletFile extends HttpServlet

{
	private String uploadPath = "/home/admin/youtuoapi/images/"; // �ϴ��ļ���Ŀ¼
	private String tempPath = "/home/admin/youtuoapi/images/"; // ��ʱ�ļ�Ŀ¼
	private File tempPathFile;
	private String fileNameReturn;

	public void init() throws ServletException {
		File uploadFile = new File(uploadPath);
		if (!uploadFile.exists()) {
			uploadFile.mkdirs();
		}
		File tempPathFile = new File(tempPath);
		if (!tempPathFile.exists()) {
			tempPathFile.mkdirs();
		}
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}



	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// Set factory constraints
			factory.setSizeThreshold(4096); // ���û������С��������4kb
			factory.setRepository(tempPathFile);// ���û�����Ŀ¼
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// Set overall request size constraint
			upload.setSizeMax(4194304); // ��������ļ��ߴ磬������4MB
			List<FileItem> items = upload.parseRequest(request);// �õ����е��ļ�
			Iterator<FileItem> i = items.iterator();
			String imagename ="";
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				String fileName = fi.getName();
				if (fileName != null) {
					File fullFile = new File(fi.getName());
					int a=fullFile.getName().indexOf(".");
					imagename= RandomStringUtils.random(8, "123456789abcdefghmlopqrstuvwxyz");
					File savedFile = new File(uploadPath, imagename+".jpg");
					fi.write(savedFile);
				}
			}
			response.setContentType("text/xml; charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(imagename+".jpg");
			System.out.print("upload succeed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}

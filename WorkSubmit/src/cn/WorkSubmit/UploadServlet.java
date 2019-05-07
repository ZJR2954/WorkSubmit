package cn.WorkSubmit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.WorkSubmit.MailUtils;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String username = null;
			response.setContentType("text/html;charset=utf-8");
			DiskFileItemFactory factory=new DiskFileItemFactory();
			File f=new File(getServletContext().getRealPath("\\TempFolder"));
			if(!f.exists()) {
				f.mkdirs();
			}
			factory.setRepository(f);
			ServletFileUpload fileupload=new ServletFileUpload(factory);
			fileupload.setHeaderEncoding("utf-8");
			List<FileItem> fileitems=fileupload.parseRequest(request);
			PrintWriter writer=response.getWriter();
			for(FileItem fileitem:fileitems) {
				if(fileitem.isFormField()) {
					String name=fileitem.getFieldName();
					if(name.equals("name")) {
						if(!fileitem.getString().equals("")) {
							String value=fileitem.getString("utf-8");
							writer.print("�ϴ��ߣ�"+value+"<br />");
							username=value;
						}
					}
				}else {
					String filename=fileitem.getName();
					if(filename != null && !filename.equals("")) {
					writer.print("�ϴ����ļ������ǣ�"+filename+"<br />");
					filename=filename.substring(filename.lastIndexOf("\\") + 1);				
					filename=username+"_"+filename;
					String webPath = "/upload/";
					String filepath = getServletContext().getRealPath(webPath+filename);
					File file = new File(filepath);
					file.getParentFile().mkdirs();
					file.createNewFile();
					InputStream in = fileitem.getInputStream();
					FileOutputStream out = new FileOutputStream(file);
					byte[] buffer = new byte[1024];
					int len;
					while((len = in.read(buffer))>0) 
						out.write(buffer, 0, len);
						in.close();
						out.close();
						fileitem.delete();
						writer.print("�ϴ��ļ��ɹ���<br />");
						//�ж��Ƿ������û����ύ�ɹ�
						UsersDao UsersDao=new UsersDao();
						UsersDao.updateIs_push(username, true);
						if(UsersDao.findAllIs_push()) {//�ж��Ƿ������˶����ύ
							//����һ��ѹ�������ڷ�����·��
							FileOutputStream fos1 = new FileOutputStream(new File(getServletContext().getRealPath("\\WorkFile.zip")));
							filePackage.toZip(getServletContext().getRealPath(webPath), fos1, true);
							//��ѹ������������Ա
							MailUtils.sendMail("763305173@qq.com","�����˶����������", getServletContext().getRealPath("\\WorkFile.zip"));					
						}
					}
				}
			}	
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

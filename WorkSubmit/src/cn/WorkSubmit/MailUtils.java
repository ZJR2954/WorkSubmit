package cn.WorkSubmit;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;

//�ʼ�������
public class MailUtils {
	public static void sendMail(String emailAddress,String text,String fileUrl) throws AddressException,MessagingException{
		//������ȡ�ʼ�ϵͳ��Ϣ���ʵ��
		Properties props=new Properties();
		//�����ʼ�Э��
		props.setProperty("mail.transport.protocol","SMTP");
		//���ý��շ�����ķ�����
		props.setProperty("mail.host", "smtp.qq.com");
		//����SMTP�������Ƿ��û���֤��һ��Ϊtrue
		props.setProperty("mail.smtp.auth", "true");
		//����У��������
		Authenticator auth=new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("zjr2954@qq.com","udbgqtmqbycwbcca");//�����ֱ�Ϊ����������������루��Ȩ�룩
			}
		};
		//ʵ�����ʼ��Ự
		Session session = Session.getInstance(props,auth);
		//ʵ�����ʼ���Ϣ
		MimeMessage message=new MimeMessage(session);
		//�����ʼ��ķ�����
		message.setFrom(new InternetAddress("zjr2954@qq.com"));
		//�����ʼ����ռ���
		message.setRecipient(RecipientType.TO, new InternetAddress(emailAddress));
		//�����ʼ���ͷ�ֶΣ���ʾ���ʼ�����
		message.setSubject("����һ���ʼ�");		
		
		// ����������Ϣ
        Multipart multipart = new MimeMultipart();
		//������Ϣ�岿��
      	BodyPart messageBodyPart=new MimeBodyPart();    
      	//����Ϣ�������ı�����
      	messageBodyPart.setText(text);
        //���ı���Ϣ����ӵ�������Ϣ
        multipart.addBodyPart(messageBodyPart);
               
        // ��������
        if(fileUrl!=null && !fileUrl.equalsIgnoreCase("")) {
	        //��дһ����Ϣ��
	        messageBodyPart=new MimeBodyPart(); 
	        //����Ҫ���͸������ļ�������˫б�ܿ�ͷ
	        String filename = "\\test.zip";
	        //�ҵ��ļ���Դ���ڵ�·������ʵ�����ļ���Դ
	        FileDataSource source = new FileDataSource(fileUrl);
	        messageBodyPart.setDataHandler(new DataHandler(source));
	        messageBodyPart.setFileName(filename);    	        
	        //���ļ���Ϣ����ӵ�������Ϣ
	        multipart.addBodyPart(messageBodyPart);
        }

        //������Ϣ��ı����ʽ
		message.setContent(multipart,"text/html;charset=utf-8");
		//�����ʼ�
		Transport.send(message);
	}
}

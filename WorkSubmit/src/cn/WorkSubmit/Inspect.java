package cn.WorkSubmit;

import java.util.ArrayList;

public class Inspect extends Thread{
	private Thread t;
	
	public void run() {			
		while(true){
			try {
				UsersDao usersDao = new UsersDao();
				ArrayList<User> list=usersDao.findAll();
				ArrayList<String> NoPushEmails=new ArrayList<String>();
				for(int i=0;i<list.size();i++) {
					if(list.get(i).getIs_push()==false) {
						NoPushEmails.add(list.get(i).getEmail());
					}
				}
					Thread.sleep(1000*60*60*5);
					for(String email : NoPushEmails) {
					MailUtils.sendMail(email, "��������δ�ύ�����һֱ���ύ����ÿ5Сʱ����һ�Σ�","");
					System.out.println("������ɹ�");
					}		
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void start() {
		if(t==null) {
			t=new Thread(this);
			t.start();
		}
	}
}

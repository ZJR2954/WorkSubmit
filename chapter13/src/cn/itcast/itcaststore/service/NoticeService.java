package cn.itcast.itcaststore.service;

import java.sql.SQLException;

import cn.itcast.itcaststore.dao.NoticeDao;
import cn.itcast.itcaststore.domain.Notice;

public class NoticeService {
	private NoticeDao dao=new NoticeDao();
	
	public Notice getRecentNotice() {
		// TODO �Զ����ɵķ������
		try {
			return dao.getRecentNotice();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			throw new RuntimeException("��ѯ������ӻ���µ�һ������ʧ�ܣ�");
		}
	}

}

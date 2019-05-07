package cn.itcast.itcaststore.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.itcaststore.domain.Notice;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class NoticeDao {

	public Notice getRecentNotice() throws SQLException {
		// TODO �Զ����ɵķ������
		String sql="select * from notice order by n_time desc limit 0,1";
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Notice>(Notice.class));
	}

}

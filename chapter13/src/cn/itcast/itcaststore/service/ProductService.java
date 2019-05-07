package cn.itcast.itcaststore.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.itcaststore.dao.ProductDao;
import cn.itcast.itcaststore.domain.PageBean;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.exception.FindProductByIdException;

public class ProductService {
	private ProductDao dao=new ProductDao();
	//ͨ��ҳ�������Ʒ
	public PageBean findProductByPage(int currentPage, int currentCount, String category) {
		PageBean bean=new PageBean();
		bean.setCurrentCount(currentCount);
		bean.setCurrentPage(currentPage);		
		bean.setCategory(category);
		try {
			int totalCount = dao.findAllCount(category);
			bean.setTotalCount(totalCount);
			int totalPage=(int)Math.ceil(totalCount*1.0/currentCount);
			bean.setTotalPage(totalPage);
			List<Product> ps=dao.findByPage(currentPage,currentCount,category);
			bean.setPs(ps);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}		
		return bean;
	}
	//ͨ��id������Ʒ
	public Product findProductById(String id)throws FindProductByIdException {
		Product p=new Product();
		try {
				p=dao.findProductById(id);
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
				throw new FindProductByIdException("����ID������Ʒʧ�ܣ�");
			}
		return p;
	}
	//ͨ������ģ��������Ʒ
	public PageBean findBookByName(int currentPage, int currentCount, String searchfield) {
		// TODO �Զ����ɵķ������
		PageBean bean=new PageBean();
		bean.setCurrentCount(currentCount);
		bean.setCurrentPage(currentPage);
		bean.setSearchfield(searchfield);

		try {
			int totalCount = dao.findBookByNameAllCount(searchfield);
			bean.setTotalCount(totalCount);
			int totalPage=(int) Math.ceil(totalCount*1.0/currentCount);
			bean.setTotalPage(totalPage);
			List<Product> ps=dao.findBookByName(currentPage,currentCount,searchfield);
			bean.setPs(ps);
			return bean;
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			throw new RuntimeException("ǰ̨���������������ѯͼ��ʧ�ܣ�");
		}	
	}
	public List<Object[]> getWeekHotProduct() {
		// TODO �Զ����ɵķ������
		try {
			return dao.getWeekHotProduct();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return null;
	}

}

package com.asiainfo.hya.util.db;

import java.sql.PreparedStatement;
import java.util.List;

import org.apache.commons.beanutils.DynaBean;

public interface DBPstatCallBack {
	
	/**
	 * �ص�����
	 * @param conn
	 * @return
	 */
	public List<DynaBean> executeQuery(PreparedStatement stat);

}

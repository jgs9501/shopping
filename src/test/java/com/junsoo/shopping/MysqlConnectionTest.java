package com.junsoo.shopping;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MysqlConnectionTest {
	
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MysqlConnectionTest.class);

	@Inject
	private DataSource ds;
	
	@Test
	public void testConnection() throws Exception {
		Connection con = ds.getConnection();
		System.out.println(con.toString());
		logger.info(con.toString());
		con.close();
	}
}

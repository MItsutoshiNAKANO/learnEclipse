package jp.ne.zaq.rinku.bkbin005;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class CommandServlet
 */
public class CommandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DataSource ds;
	
	/**
	 * @see HttpServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		Context ctx;
		try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			throw new ServletException("can not get new InitialContext", e);
		}
		try {
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/postgres");
		} catch (NamingException e) {
			throw new ServletException("can not lookup DataSource", e);
		}
	}

	private void common(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			common(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		common(request, response);
	}

}

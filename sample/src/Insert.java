/**
 * 
 */


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import jp.ne.zaq.rinku.bkbin005.DbState;
import jp.ne.zaq.rinku.bkbin005.SampleBean;

/**
 * Servlet implementation class Search
 */
public class Insert extends HttpServlet {
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
	
	/**
	 * called by doGet() and doPost()
	 */
    private void common(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String code = request.getParameter("code");
    	String yyyymm = request.getParameter("nengetu");
    	String days = request.getParameter("days");

    	Connection con;
    	try {
			con = ds.getConnection();
		} catch (SQLException e) {
			throw new IOException("could not connect DB", e);
		}
    	PreparedStatement pst;
    	try {
    		pst = con.prepareStatement("INSERT INTO SAMPLE (NISSU, CODE, NENGETSU) VALUES (?, ?, ?)");
   			
		} catch (SQLException e) {
			throw new IOException("wrong prepareStatement", e);
		}
    	SampleBean b = new SampleBean();
    	b.setCode(Integer.parseInt(code));
    	b.setYYYYMM(yyyymm);
    	b.setDays(Integer.parseInt(days));
    	b.setState(DbState.UPDATE);
    	// System.err.println(b); // debug
    	try {
			pst.setInt(1, b.getDays());
			pst.setInt(2, b.getCode());
			pst.setDate(3, b.getSqlDate());
			System.err.println(b.getSqlDate()); // debug
		} catch (SQLException e) {
			throw new IOException("wrong number", e);
		}
    	try {
    		System.err.println("exec insert");
			pst.execute();
		} catch (SQLException e) {
			throw new IOException("wrong executeQuery", e);
		}
    	request.setAttribute("Bean", b);
    	request.getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		common(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
   @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		common(request, response);
	}

}

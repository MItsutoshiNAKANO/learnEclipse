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
public class Search extends HttpServlet {
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
		// throw new ServletException("In init() for test");
	}
	
	/**
	 * called by doGet() and doPost()
	 */
    private void common(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String code = request.getParameter("code");
    	if (code == null || code.equals("")) {
        	System.err.println("code = " + code);
    		request.getRequestDispatcher("/index.jsp").forward(request, response);
    		return;
    	}
    	
    	int codenum;
    	try {
    		codenum = Integer.parseInt(code);
    	} catch (NumberFormatException e) {
        	System.err.println("code is mal " + code);
    		request.getRequestDispatcher("/index.jsp").forward(request, response);
    		return;
    	}

    	String yyyymm = request.getParameter("nengetu");
    	SampleBean b = new SampleBean();
    	try {
    		b.setYYYYMM(yyyymm);
    	} catch (NumberFormatException e) {
    		request.getRequestDispatcher("/index.jsp").forward(request, response);
    		return;
    	}

    	Connection con;
    	try {
			con = ds.getConnection();
		} catch (SQLException e) {
			throw new IOException("could not connect DB", e);
		}
    	PreparedStatement pst;
    	try {
			pst = con.prepareStatement("SELECT CODE, NENGETSU, NISSU FROM SAMPLE WHERE CODE = ? AND NENGETSU = ?");
		} catch (SQLException e) {
			throw new IOException("wrong prepareStatement", e);
		}
    	try {
			pst.setInt(1, codenum);
			pst.setDate(2, b.getSqlDate());
		} catch (SQLException e) {
			throw new IOException("wrong number", e);
		}
    	ResultSet rst;
    	try {
			rst = pst.executeQuery();
		} catch (SQLException e) {
			throw new IOException("wrong executeQuery", e);
		}
		int days;
    	try {
			if (rst.next()) {
				days = rst.getInt("NISSU");
				b.setState(DbState.UPDATE);
			} else {
				days = 0;
				b.setState(DbState.INSERT);
			}
		} catch (SQLException e) {
			throw new IOException("DB was wrong", e);
		}
    	b.setCode(codenum);
    	b.setDays(days);
    	request.setAttribute("Bean", b);
    	request.getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);
    	return;
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



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

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
	
    /** default constructor
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
    }

	/**
	 * called by doGet() and doPost()
	 */
    private void common(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String code = request.getParameter("code");
    	if (code == null || code.equals("")) {
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
			pst = con.prepareStatement("SELECT CORD, NENGETSU, NISSU FROM SAMPLE WHERE CODE= ? AND NENGETSU= ?");
		} catch (SQLException e) {
			throw new IOException("wrong prepareStatement", e);
		}
    	//TODO
    	
    	
    	request.getRequestDispatcher("/detail.jsp").forward(request, response);
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

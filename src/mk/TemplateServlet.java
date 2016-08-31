package mk;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.*;


@SuppressWarnings("serial")
public class TemplateServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		String action = req.getParameter("action");
		
		if (action.equals("add")) {
			addflow(req, resp);
		} else if (action.equals("searchPName"))  {
			searchByProjectName(req,resp);
		} else if (action.equals("searchById")) {
			searchById(req, resp);
		}
	}

	private void addflow(HttpServletRequest req, HttpServletResponse resp) {
		   PersistenceManager pm = PMF.get().getPersistenceManager();

	        Project e = new Project(req.getParameter("pname"), req.getParameter("powner"), new Date());

	        try {
	            pm.makePersistent(e);
	        } finally {
	            pm.close();
	        }
	        
	}

	private void searchByProjectName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/json");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query q = pm.newQuery(Project.class);
		
		q.setFilter("projectName.startsWith(pNameParam)");
		q.setOrdering("projectName desc");
		q.declareParameters("String pNameParam");
		
		
		Query q2 = pm.newQuery(Project.class);
		q2.setOrdering("projectName desc");
		
		
		String parameter = req.getParameter("pname");
	
		
		
		try {
			List<Project> results;
			if (parameter == null || parameter.trim().length() == 0) {
				results = (List<Project>) q2.execute(parameter);
			} else {
				results = (List<Project>) q.execute(parameter);
			}

		  if (!results.isEmpty()) {
			 
			  String json = JSON.toJSONString(results);
			  resp.getWriter().print(json);
		/*    for (Project p : results) {
		    		resp.getWriter().print(p.getId() +"  ");
		    }*/
		  } else {
		    resp.getWriter().print("NOTHING TO SHOW");
		  }
		} finally {
		  q.closeAll();
		}
		
	}
	
	private void searchById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/json");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String parameter = req.getParameter("q");
		if (parameter != null) {
		  Long qid = new Long(parameter);
		  Project p = pm.getObjectById(Project.class,  qid);
		  String json = JSON.toJSONString(p);
		  resp.getWriter().print(json);
			try {
			 /* if (p != null) {
			    	resp.getWriter().print(p.getProjectName());
			  } else {
				  resp.getWriter().print("NOTHING TO SHOW");
			  }*/
			} finally {
			   pm.close();
			}
		}
	}
	
}

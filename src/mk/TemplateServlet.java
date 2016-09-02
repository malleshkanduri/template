package mk;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;


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
			
			String module = req.getParameter("module");
			
			if (module != null) {
				if (module.equals("ms")) {
					getMileStones(req, resp);
				} else if (module.equals("wf") ) {
					getWorkFlow(req, resp);
				}
			} else {
				searchById(req, resp);
			}
		} else if (action.equals("addms")) {
			addMileStone(req,resp);
		} else if (action.equals("addwf")) {
			addWorkFlow(req, resp);
		}
	}

	private void getWorkFlow(HttpServletRequest req, HttpServletResponse resp) throws IOException {


		resp.setContentType("text/json");
		
		
		String parameter = req.getParameter("q");
		Long parameterInLong = Long.parseLong(parameter);
		
		if (parameter != null) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery(WorkFlow.class);
			//q.setFilter("projectId == pNameParam");
			q.setOrdering("name desc");
			//q.declareParameters("Long pNameParam");
			
			try {
				
			List<WorkFlow> results = (List<WorkFlow>) q.execute(parameterInLong);

				if (!results.isEmpty()) {
					String json = JSON.toJSONString(results);
					resp.getWriter().print(json);
				} else {
					resp.getWriter().print("No Results");
				}
			 
			} finally {
			   pm.close();
			}
		}
	
		
	
	}

	private void getMileStones(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("text/json");
		
		
		String parameter = req.getParameter("q");
		Long parameterInLong = Long.parseLong(parameter);
		
		if (parameter != null) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Query q = pm.newQuery(MileStone.class);
			//q.setFilter("projectId == pNameParam");
			q.setOrdering("name desc");
			//q.declareParameters("Long pNameParam");
			
			try {
				
			List<MileStone> results = (List<MileStone>) q.execute(parameterInLong);

				if (!results.isEmpty()) {
					String json = JSON.toJSONString(results);
					resp.getWriter().print(json);
				} else {
					resp.getWriter().print("No Results");
				}
			 
			} finally {
			   pm.close();
			}
		}
	
		
	}

	private void addflow(HttpServletRequest req, HttpServletResponse resp) {
		   PersistenceManager pm = PMF.get().getPersistenceManager();

	        Project e = new Project(req.getParameter("pname"), req.getParameter("powner"), 
	        		new Date(), req.getParameter("comment"), req.getParameter("des"));

	        try {
	            pm.makePersistent(e);
	        } finally {
	            pm.close();
	        }
	        
	}
	
	private void addMileStone(HttpServletRequest req, HttpServletResponse resp) {
		   PersistenceManager pm = PMF.get().getPersistenceManager();

	        MileStone e = new MileStone(
	        		Long.parseLong(req.getParameter("projectId")),
	        		req.getParameter("name"),
	        		Integer.parseInt(req.getParameter("no")),
					 Integer.parseInt(req.getParameter("modelOpen")),
					 Integer.parseInt(req.getParameter("modelClosed")),
					 Integer.parseInt(req.getParameter("rsOpen")),
					 Integer.parseInt(req.getParameter("rsClosed")),
	        		 Integer.parseInt(req.getParameter("duration")));

	        try {
	            pm.makePersistent(e);
	        } finally {
	            pm.close();
	        }
	}
	
	private void addWorkFlow(HttpServletRequest req, HttpServletResponse resp) {
		   PersistenceManager pm = PMF.get().getPersistenceManager();

	        WorkFlow e = new WorkFlow(
	        		Long.parseLong(req.getParameter("projectId")),
	        		req.getParameter("name"),
	        		Integer.parseInt(req.getParameter("no")),
					 Integer.parseInt(req.getParameter("modelOpen")),
					 Integer.parseInt(req.getParameter("modelClosed")),
					 Integer.parseInt(req.getParameter("rsOpen")),
					 Integer.parseInt(req.getParameter("rsClosed")),
	        		 Integer.parseInt(req.getParameter("duration")),
	        		 Integer.parseInt(req.getParameter("task")),
	        		 req.getParameter("msname") );

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

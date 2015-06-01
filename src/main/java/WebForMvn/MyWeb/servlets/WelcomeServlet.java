package WebForMvn.MyWeb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebForMvn.MyWeb.resources.Test;

public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	StringBuilder htmlTestSb;
	String initialization = "<!DOCTYPE html>"
			+ "<html>\n"
			+ "<head>\n"
			+ "<meta charset=\"UTF-8\">\n"
			+ "<title>Test name</title>\n"
			+ "<link rel=\"stylesheet\" href=\"css/style.css\">\n"
			+ "</head>\n"
			+ "<body>\n"
			+ "<form name=\"test\" method=\"post\" >\n"
			+ "<section class=\"questions\">\n";

	String finalization = "<a href=\"index.html\" >Back</a>\n"
			+ "<br>\n"
			+ "</form>" 
			+"</section>\n</body></html>\n";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		htmlTestSb = new StringBuilder();
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
				
		Test myTest = new Test();
		myTest.fetchQuestions();
		this.initTestHTML();
		this.appendQuestionsHTML(myTest);
		this.finalizeHTML();
		
		out.print(htmlTestSb);
		out.close();
		
	}

	void initTestHTML() {
		htmlTestSb.append(initialization);
	}

	void finalizeHTML() {
		htmlTestSb.append(finalization);
	}

	void appendQuestionsHTML(Test test) {

		Test tmpTest = test;

		for (int i = 0; i < tmpTest.questtionStorage.size(); i++) {

			htmlTestSb.append("<p><b>\n"
					+ tmpTest.questtionStorage.get(i).getQuestion()
					+ "</b>\n<br>\n");

			for (int j = 0; j < tmpTest.questtionStorage.get(i).getVariants()
					.size(); j++) {

				htmlTestSb.append("<input type=\"radio\" name=\"var"
						+ i
						+ "\" value=\"ie\"> \n"
						+ tmpTest.questtionStorage.get(i).getVariants().get(j)
								.getText() + "<Br>\n");

			}
			
		}
	}

}

       
    
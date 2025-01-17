package hospital.action;

import java.io.IOException;

import controller.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SymptomChcekAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    try {
	        System.out.println("gdgdgd");
	        response.sendRedirect("/hospitals");
	    } catch (IOException e) {
	        e.printStackTrace();
	        
	    }
	}

}

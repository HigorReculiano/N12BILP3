package ec.ftt.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Errors {
	public static HttpServletResponse badRequest(HttpServletResponse response, String message) throws IOException {
		response.setStatus(400);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		Map<String, String> error = new HashMap<>();
		error.put("error", "bad request");
		error.put("tip", message);
		
		response.getWriter().append(new Gson().toJson(error));
		return response;
	}
}

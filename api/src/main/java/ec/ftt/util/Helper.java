package ec.ftt.util;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.internal.Primitives;

public class Helper {
	public static <T> T getObjectFromJson(BufferedReader br, Class<T> target) throws IOException {
	        StringBuilder sb = new StringBuilder();
	        String str = null;
	        while ((str = br.readLine()) != null) {
	            sb.append(str);
	            System.out.println(str);
	        }
	        
	        Gson gson = new Gson();
	        
	        Object object = gson.fromJson(sb.toString(), target);
	        return Primitives.wrap(target).cast(object);
	}
}

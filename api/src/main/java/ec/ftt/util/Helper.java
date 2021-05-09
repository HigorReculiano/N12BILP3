package ec.ftt.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;

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

	public static void merge(Object obj, Object update){
	    if(!obj.getClass().isAssignableFrom(update.getClass())){
	        return;
	    }

	    Method[] methods = obj.getClass().getMethods();

	    for(Method fromMethod: methods){
	        if(fromMethod.getDeclaringClass().equals(obj.getClass())
	                && fromMethod.getName().startsWith("get")){

	            String fromName = fromMethod.getName();
	            String toName = fromName.replace("get", "set");

	            try {
	                Method toMetod = obj.getClass().getMethod(toName, fromMethod.getReturnType());
	                Object value = fromMethod.invoke(update, (Object[])null);
	                
	                if(value != null){
	                	if (Validator.isNumeric(value.toString()) && Integer.valueOf(value.toString()) == 0) {
		                    continue;
	                	}
	                	toMetod.invoke(obj, value);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            } 
	        }
	    }
	}
}

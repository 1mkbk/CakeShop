//package listener;
//import service.TypeService;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import javax.servlet.http.HttpSessionAttributeListener;
//import javax.servlet.http.HttpSessionEvent;
//import javax.servlet.http.HttpSessionListener;
//import javax.servlet.http.HttpSessionBindingEvent;
//
//@WebListener()
//public class ApplicationListener implements ServletContextListener,
//        HttpSessionListener, HttpSessionAttributeListener {
//    TypeService tsService=new TypeService();
//    // Public constructor is required by servlet spec
//    public ApplicationListener() {
//    }
//    public void contextInitialized(ServletContextEvent sce) {
//        sce.getServletContext().setAttribute("typeList",tsService.GetAllType());
//    }
//    public void contextDestroyed(ServletContextEvent sce) {
//    }
//    public void sessionCreated(HttpSessionEvent se) {
//    }
//    public void sessionDestroyed(HttpSessionEvent se) {
//    }
//    public void attributeAdded(HttpSessionBindingEvent sbe) {
//    }
//    public void attributeRemoved(HttpSessionBindingEvent sbe) {
//    }
//    public void attributeReplaced(HttpSessionBindingEvent sbe) {
//    }
//}

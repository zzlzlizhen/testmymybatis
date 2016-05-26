package lz.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Application Lifecycle Listener implementation class MyListener
 *
 */
public class MyListener implements ServletContextListener{

    public MyListener() {
        // TODO Auto-generated constructor stub
    }
    /**
     * 监听启动服务的时候，把web服务上下文放入到定时Scheduler里面
     */
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    	try {
			Scheduler sched = new StdSchedulerFactory().getScheduler();   
			sched.getContext().put("servletContext", servletContextEvent.getServletContext());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}     

    }

    public void attributeAdded(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void attributeRemoved(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}

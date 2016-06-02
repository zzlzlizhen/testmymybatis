/**
 * 
 */
package lz.aspect;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import lz.annotation.LogAspectAnnotation;
import lz.business.systemManage.service.ExcepLogService;
import lz.business.systemManage.service.OperLogService;
import lz.exception.ControllerException;
import lz.model.ExcepLog;
import lz.model.OperLog;
import lz.model.User;
import lz.utils.IdGenerateUtils;

import org.apache.commons.lang.time.DateFormatUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author lizhen_pc
 *123
 */
@Aspect
@Component
public class LogAspect {
	@Autowired  
	private HttpSession session;  
	@Resource
	private OperLogService operLogService;
	@Resource
	private ExcepLogService excepLogService;
	/**
	 * 切点,对(lz.business包里的所有的方法并且有注入LogAspectAnnotation的方法)切入
	 * 描述：
	 * 作者：李震
	 * 时间：2016年6月2日 上午10:17:18
	 */
	@Pointcut(value="execution(* lz.business..*.*(..)) && @annotation(lz.annotation.LogAspectAnnotation)")
	//@Pointcut(value="@annotation(lz.annotation.LogAspectAnnotation)")
	private void operLogAop(){
		
	}
	/**
	 * 对所有的方法，加入切入点，如果该方法抛出ControllerException,则处理
	 * 描述：
	 * 作者：李震
	 * 时间：2016年6月2日 下午4:45:33
	 */
	@Pointcut(value="execution(* lz.business..*.*(..))")
	private void excepLogAop(){
		
	}
	/**
	 * 切入之前执行
	 * 描述：
	 * 作者：李震
	 * 时间：2016年6月2日 上午10:17:02
	 */
	@Before(value="operLogAop()")
	public void before(){
	}
	/**
	 * 切入的方法（正常或者异常）之后执行
	 * 描述：
	 * 作者：李震
	 * 时间：2016年6月2日 上午10:16:34
	 * @param joinPoint
	 * @param log
	 */
	@After(value="operLogAop() && @annotation(log)")
	public void after(JoinPoint joinPoint,LogAspectAnnotation log){
		
	}
	/**
	 * 切入的方法正常之后执行
	 * 描述：
	 * 作者：李震
	 * 时间：2016年6月2日 上午10:16:03
	 * @param joinPoint
	 * @param log
	 * @param retVal
	 */
	@AfterReturning(value="operLogAop() && @annotation(log)",returning="retVal")
	public void afterReturning(JoinPoint joinPoint,LogAspectAnnotation log,Object retVal){
		User user = (User)session.getAttribute("loginUser");
		if(user==null){
			for(Object o:joinPoint.getArgs()){
				if (o instanceof User){
					user = (User)o;
					break;
				}else if((o instanceof String) && log.logDesc().equals("退出系统")){
					user = new User();
					user.setName((String)o);
				}
			}
		}
		OperLog operLog = new OperLog();
		operLog.setId(IdGenerateUtils.getId());
		operLog.setLogBusiness(log.logBusiness());
		operLog.setLogDesc(log.logDesc());
		operLog.setName(user!=null?user.getName():"");
		operLog.setCreateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		operLogService.insertOperLog(operLog);
	}
	/**
	 * 切入的方法抛出异常之后执行
	 * 描述：
	 * 作者：李震
	 * 时间：2016年6月2日 上午10:15:40
	 * @param joinPoint
	 * @param log
	 * @param e
	 */
	@AfterThrowing(value="excepLogAop()",throwing="e")
	public void afterThrowing(JoinPoint joinPoint,ControllerException e){
		User user = (User)session.getAttribute("loginUser");
		if(user==null){
			for(Object o:joinPoint.getArgs()){
				if (o instanceof User){
					user = (User)o;
					break;
				}
			}
		}
		ExcepLog excepLog = new ExcepLog();
		excepLog.setId(IdGenerateUtils.getId());
		excepLog.setName(user!=null?user.getName():"");
		excepLog.setCreateTime(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
		excepLog.setExceptionTarget(e.getExceptionTarget());
		excepLog.setExceptionDesc(e.getExceptionDesc());
		excepLog.setExceptionBusiness(e.getExceptionBusiness());
		Exception exception = e.getException();
		if(exception!=null){
			String exceptionInfo = exception.toString();
			excepLog.setExceptionInfo(exceptionInfo.length()>5000?exceptionInfo.substring(0, 5000):exceptionInfo);
		}
		excepLogService.insertExcepLog(excepLog);
	}
}

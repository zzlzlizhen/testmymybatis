/**
 * 
 */
package lz.aspect;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import lz.annotation.LogAspectAnnotation;
import lz.business.systemManage.service.OperLogService;
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
	/**
	 * 切点,对(lz.business包里的所有的方法并且有注入LogAspectAnnotation的方法)切入
	 * 描述：
	 * 作者：李震
	 * 时间：2016年6月2日 上午10:17:18
	 */
	@Pointcut(value="execution(* lz.business..*.*(..)) && @annotation(lz.annotation.LogAspectAnnotation)")
	//@Pointcut(value="@annotation(lz.annotation.LogAspectAnnotation)")
	private void aopConfig(){
		
	}
	/**
	 * 切入之前执行
	 * 描述：
	 * 作者：李震
	 * 时间：2016年6月2日 上午10:17:02
	 */
	@Before(value="aopConfig()")
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
	@After(value="aopConfig() && @annotation(log)")
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
	@AfterReturning(value="aopConfig() && @annotation(log)",returning="retVal")
	public void afterReturning(JoinPoint joinPoint,LogAspectAnnotation log,Object retVal){
		User user = (User)session.getAttribute("loginUser");
		if(user==null){
			for(Object o:joinPoint.getArgs()){
				if (o instanceof User){
					user = (User)o;
					break;
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
	@AfterThrowing(value="aopConfig() && @annotation(log)",throwing="e")
	public void afterThrowing(JoinPoint joinPoint,LogAspectAnnotation log,Exception e){
		
	}
}

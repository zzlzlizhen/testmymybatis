/**
 * 
 */
package lz.exception;

/**
 * @author lizhen_pc
 *123
 *自定义controller异常，捕捉异常信息统一处理
 */
public class ControllerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Exception exception;
	
	private String exceptionDesc;
	
	private String exceptionBusiness;
	
	private String exceptionTarget;

	public ControllerException(Exception exception, String exceptionDesc,
			String exceptionBusiness, String exceptionTarget) {
		super();
		this.exception = exception;
		this.exceptionDesc = exceptionDesc;
		this.exceptionBusiness = exceptionBusiness;
		this.exceptionTarget = exceptionTarget;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getExceptionDesc() {
		return exceptionDesc;
	}

	public void setExceptionDesc(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}

	public String getExceptionBusiness() {
		return exceptionBusiness;
	}

	public void setExceptionBusiness(String exceptionBusiness) {
		this.exceptionBusiness = exceptionBusiness;
	}

	public String getExceptionTarget() {
		return exceptionTarget;
	}

	public void setExceptionTarget(String exceptionTarget) {
		this.exceptionTarget = exceptionTarget;
	}
}

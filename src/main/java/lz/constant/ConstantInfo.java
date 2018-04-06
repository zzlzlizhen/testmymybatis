package lz.constant;
/**
 * 保存系统常量
 * @author lizhen_pc
 *
 */
public class ConstantInfo {

	//用户停用，启用常量
	public static final String USER_STOP = "0";
	public static final String USER_START = "1";
	//创建用户时，初始密码
	public static final String INIT_PASSWORD = "123456";
	//资源菜单一级菜单初始化pid
	public static final String ONE_RESOURCE_PID = "0";
	//验证码的类型
	public static final String SECURITY_CODE_PHONE="securityCodePhone";
	public static final String SECURITY_CODE_EMAIL="securityCodeEmail";
	//登录的用户,放入HttpSession里
	public static final String LOGIN_USER = "loginUser";
	//消息类型 messageType 1,表示系统通知消息，2表示用户提醒消息
	public static final String SYSTEM_MESSAGE = "1";
	public static final String USER_MESSAGE="2";
	//消息状态messageStatus 1,表示已保存，2，表示已发布，3，表示已销毁
	public static final String MESSAGE_SAVE="1";
	public static final String MESSAGE_PUBLISH="2";
	public static final String MESSAGE_DESTORY="3";
	//消息是否已读
	public static final String MESSAGE_NOT_READ = "0";
	public static final String MESSAGE_READ = "1";
	
	public static final String YZNZ_CLOUTHES="YZNZ_CLOUTHES";
	
}

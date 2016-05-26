package lz.mail;

import java.util.HashMap;
import java.util.Map;

public class emailTest {  
    public static void main(String[] args) {  
    	if(sendCommonMail()&&sendCommonAttachmentMail()&&sendHtmlAttachmentMail())
    		System.out.println("三类邮件均发送成功");
    }
    /**
     * 普通文本文件
     * @return true 发送成功。false 发送失败
     */
    public static boolean sendCommonMail(){
        MailInfo mailInfo = new MailInfo();  
        mailInfo.setToAddress("275290501@qq.com");
        mailInfo.setContent("测试普通文本文件");
        mailInfo.setSubject("普通文本文件");
        return SimpleMail.sendTextMail(mailInfo);
    }
    /**
     * 普通的带附件的文本文件
     * @return
     */
    public static boolean sendCommonAttachmentMail(){
    	MailInfo mailInfo = new MailInfo();  
        mailInfo.setToAddress("275290501@qq.com");
        mailInfo.setContent("测试普通带附件的文本文件");
        mailInfo.setSubject("普通带附件的文本文件");
        //附件  二个
        String[] attachFileNames={"C:/Users/lizhen_pc/Desktop/123.txt"};  
        mailInfo.setAttachFileNames(attachFileNames);
        return SimpleMail.sendTextMail(mailInfo);
    }
    /**
     * 图文邮件
     * @return
     */
    public static boolean sendHtmlAttachmentMail(){
    	MailInfo mailInfo = new MailInfo();  
        mailInfo.setToAddress("275290501@qq.com");
        mailInfo.setSubject("带附件的html图文邮件");
        //附件  二个
        String[] attachFileNames={"C:/Users/lizhen_pc/Desktop/sql0416.txt","C:/Users/lizhen_pc/Desktop/dump.rdb"};  
        mailInfo.setAttachFileNames(attachFileNames);        

        StringBuffer demo = new StringBuffer();  
        demo.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")  
        .append("<html>")  
        .append("<head>")  
        .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")  
        .append("<title>测试邮件</title>")  
        .append("<style type=\"text/css\">")  
        .append(".test{font-family:\"Microsoft Yahei\";font-size: 18px;color: red;}")  
        .append("</style>")  
        .append("</head>")  
        .append("<body>")  
        .append("<img src='cid:image01'/><span class=\"test\">测试带附件的图文html邮件</span><img src='cid:image02'/>")  
        .append("</body>")  
        .append("</html>");  
        mailInfo.setContent(demo.toString());  
        //html中的图片附件
        Map<String,String> htmlPicFile=new HashMap<String,String>();
        htmlPicFile.put("<image01>","C:\\Users\\lizhen_pc\\Desktop\\pic\\bq1.png");
        htmlPicFile.put("<image02>","C:\\Users\\lizhen_pc\\Desktop\\pic\\bq2.png");
        mailInfo.setHtmlIdFileName(htmlPicFile);
        return SimpleMail.sendHtmlMail(mailInfo);
    }
}  

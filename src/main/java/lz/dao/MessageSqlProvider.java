package lz.dao;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.util.List;
import java.util.Map;

import lz.model.Message;
import lz.model.MessageExample.Criteria;
import lz.model.MessageExample.Criterion;
import lz.model.MessageExample;

public class MessageSqlProvider {

	public String countByExample(MessageExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("t_message");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(MessageExample example) {
        BEGIN();
        DELETE_FROM("t_message");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(Message record) {
        BEGIN();
        INSERT_INTO("t_message");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=CHAR}");
        }
        
        if (record.getMessageType() != null) {
            VALUES("message_type", "#{messageType,jdbcType=CHAR}");
        }
        
        if (record.getMessageHead() != null) {
            VALUES("message_head", "#{messageHead,jdbcType=CHAR}");
        }
        
        if (record.getMessageContent() != null) {
            VALUES("message_content", "#{messageContent,jdbcType=VARCHAR}");
        }
        
        if (record.getMessageStatus() != null) {
            VALUES("message_status", "#{messageStatus,jdbcType=CHAR}");
        }
        
        if (record.getCreatedUser() != null) {
            VALUES("created_user", "#{createdUser,jdbcType=CHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=CHAR}");
        }
        
        if (record.getReceiver() != null) {
            VALUES("receiver", "#{receiver,jdbcType=CHAR}");
        }
        
        if (record.getPublishTime() != null) {
            VALUES("publish_time", "#{publishTime,jdbcType=CHAR}");
        }
        
        if (record.getDestoryTime() != null) {
            VALUES("destory_time", "#{destoryTime,jdbcType=CHAR}");
        }
        
        return SQL();
    }

    public String selectByExample(MessageExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("message_type");
        SELECT("message_head");
        SELECT("message_content");
        SELECT("message_status");
        SELECT("created_user");
        SELECT("create_time");
        SELECT("receiver");
        SELECT("publish_time");
        SELECT("destory_time");
        FROM("t_message");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Message record = (Message) parameter.get("record");
        MessageExample example = (MessageExample) parameter.get("example");
        
        BEGIN();
        UPDATE("t_message");
        
        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=CHAR}");
        }
        
        if (record.getMessageType() != null) {
            SET("message_type = #{record.messageType,jdbcType=CHAR}");
        }
        
        if (record.getMessageHead() != null) {
            SET("message_head = #{record.messageHead,jdbcType=CHAR}");
        }
        
        if (record.getMessageContent() != null) {
            SET("message_content = #{record.messageContent,jdbcType=VARCHAR}");
        }
        
        if (record.getMessageStatus() != null) {
            SET("message_status = #{record.messageStatus,jdbcType=CHAR}");
        }
        
        if (record.getCreatedUser() != null) {
            SET("created_user = #{record.createdUser,jdbcType=CHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{record.createTime,jdbcType=CHAR}");
        }
        
        if (record.getReceiver() != null) {
            SET("receiver = #{record.receiver,jdbcType=CHAR}");
        }
        
        if (record.getPublishTime() != null) {
            SET("publish_time = #{record.publishTime,jdbcType=CHAR}");
        }
        
        if (record.getDestoryTime() != null) {
            SET("destory_time = #{record.destoryTime,jdbcType=CHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("t_message");
        
        SET("id = #{record.id,jdbcType=CHAR}");
        SET("message_type = #{record.messageType,jdbcType=CHAR}");
        SET("message_head = #{record.messageHead,jdbcType=CHAR}");
        SET("message_content = #{record.messageContent,jdbcType=VARCHAR}");
        SET("message_status = #{record.messageStatus,jdbcType=CHAR}");
        SET("created_user = #{record.createdUser,jdbcType=CHAR}");
        SET("create_time = #{record.createTime,jdbcType=CHAR}");
        SET("receiver = #{record.receiver,jdbcType=CHAR}");
        SET("publish_time = #{record.publishTime,jdbcType=CHAR}");
        SET("destory_time = #{record.destoryTime,jdbcType=CHAR}");
        
        MessageExample example = (MessageExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(Message record) {
        BEGIN();
        UPDATE("t_message");
        
        if (record.getMessageType() != null) {
            SET("message_type = #{messageType,jdbcType=CHAR}");
        }
        
        if (record.getMessageHead() != null) {
            SET("message_head = #{messageHead,jdbcType=CHAR}");
        }
        
        if (record.getMessageContent() != null) {
            SET("message_content = #{messageContent,jdbcType=VARCHAR}");
        }
        
        if (record.getMessageStatus() != null) {
            SET("message_status = #{messageStatus,jdbcType=CHAR}");
        }
        
        if (record.getCreatedUser() != null) {
            SET("created_user = #{createdUser,jdbcType=CHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=CHAR}");
        }
        
        if (record.getReceiver() != null) {
            SET("receiver = #{receiver,jdbcType=CHAR}");
        }
        
        if (record.getPublishTime() != null) {
            SET("publish_time = #{publishTime,jdbcType=CHAR}");
        }
        
        if (record.getDestoryTime() != null) {
            SET("destory_time = #{destoryTime,jdbcType=CHAR}");
        }
        
        WHERE("id = #{id,jdbcType=CHAR}");
        
        return SQL();
    }

    protected void applyWhere(MessageExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
  //************************自定义sql**********************
    /**
     * 消息管理列表分页
     * 描述：
     * 作者：李震
     * 时间：2016年7月26日 下午2:54:33
     * @param map
     * @return
     */
    public String selectMessageByPage(Map<String,Object> map){
    	StringBuffer sb = new StringBuffer("select id, message_type, message_head, message_content, message_status, created_user, create_time, receiver,publish_time,destory_time from t_message where 1=1 ");
    	if(map.get("messageHead")!=null){
    		sb.append(" and message_head like #{messageHead} ");
    	}
    	if(map.get("messageType")!=null){
    		sb.append(" and message_type = #{messageType} ");
    	}
    	if(map.get("messageStatus")!=null){
    		sb.append(" and message_status = #{messageStatus} ");
    	}
    	sb.append(" order by id desc ");
    	return sb.toString();
    }
    /**
     * 个人消息列表分页
     * 描述：
     * 作者：李震
     * 时间：2016年7月26日 下午2:54:45
     * @param map
     * @return
     */
    public String selectPersonMessageByPage(Map<String,Object> map){
    	StringBuffer sb = new StringBuffer("SELECT t.id,t.message_head,t.message_content,t.message_type,t.publish_time,u.message_type message_status FROM t_message t LEFT JOIN t_message_user u ON (t.id=u.message_id AND u.user_id=#{userId}) WHERE (t.receiver IS NULL OR t.receiver=#{userName}) and message_status='2' ");
    	if(map.get("messageHead")!=null){
    		sb.append(" and t.message_head like #{messageHead} ");
    	}
    	if(map.get("messageType")!=null){
    		sb.append(" and t.message_type = #{messageType} ");
    	}
    	if(map.get("messageStatus")!=null){
    		String messageStatus = (String)map.get("messageStatus");
    		if(messageStatus.equals("1")){
    			sb.append(" and u.message_type = #{messageStatus} ");
    		}else{
    			sb.append(" and (u.message_type = #{messageStatus} or u.message_type is null)");
    		}
    	}
    	sb.append(" order by id desc ");
    	return sb.toString();
    }
    /**
     * 获取最近发布的一条个人消息
     * 描述：
     * 作者：李震
     * 时间：2016年7月26日 下午2:59:17
     * @param map
     * @return
     */
    public String selectLatestMessage(Map<String,Object> map){
    	StringBuffer sb = new StringBuffer("SELECT t.id,t.message_head,t.message_content,t.message_type,t.publish_time,u.message_type message_status FROM t_message t LEFT JOIN t_message_user u ON (t.id=u.message_id AND u.user_id=#{userId}) WHERE (t.receiver IS NULL OR t.receiver=#{userName}) and message_status='2' order by t.publish_time desc limit 0,1");
    	return sb.toString();
    }
    public String batchDelMessage(Map<String,Object> map){
    	@SuppressWarnings("unchecked")
		List<String> list = (List<String>)map.get("batchDelIds");
    	StringBuffer sb = new StringBuffer("delete from t_message where id in ( ");
    	for(int i=0;i<list.size();i++){
    		sb.append("#{batchDelIds["+i+"]}");
    		if(i<list.size()-1){
    			sb.append(",");
    		}
    	}
    	sb.append(" )");
    	return sb.toString();
    }
}
package lz.model;

import java.util.ArrayList;
import java.util.List;

public class MessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMessageTypeIsNull() {
            addCriterion("message_type is null");
            return (Criteria) this;
        }

        public Criteria andMessageTypeIsNotNull() {
            addCriterion("message_type is not null");
            return (Criteria) this;
        }

        public Criteria andMessageTypeEqualTo(String value) {
            addCriterion("message_type =", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeNotEqualTo(String value) {
            addCriterion("message_type <>", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeGreaterThan(String value) {
            addCriterion("message_type >", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeGreaterThanOrEqualTo(String value) {
            addCriterion("message_type >=", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeLessThan(String value) {
            addCriterion("message_type <", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeLessThanOrEqualTo(String value) {
            addCriterion("message_type <=", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeLike(String value) {
            addCriterion("message_type like", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeNotLike(String value) {
            addCriterion("message_type not like", value, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeIn(List<String> values) {
            addCriterion("message_type in", values, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeNotIn(List<String> values) {
            addCriterion("message_type not in", values, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeBetween(String value1, String value2) {
            addCriterion("message_type between", value1, value2, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageTypeNotBetween(String value1, String value2) {
            addCriterion("message_type not between", value1, value2, "messageType");
            return (Criteria) this;
        }

        public Criteria andMessageHeadIsNull() {
            addCriterion("message_head is null");
            return (Criteria) this;
        }

        public Criteria andMessageHeadIsNotNull() {
            addCriterion("message_head is not null");
            return (Criteria) this;
        }

        public Criteria andMessageHeadEqualTo(String value) {
            addCriterion("message_head =", value, "messageHead");
            return (Criteria) this;
        }

        public Criteria andMessageHeadNotEqualTo(String value) {
            addCriterion("message_head <>", value, "messageHead");
            return (Criteria) this;
        }

        public Criteria andMessageHeadGreaterThan(String value) {
            addCriterion("message_head >", value, "messageHead");
            return (Criteria) this;
        }

        public Criteria andMessageHeadGreaterThanOrEqualTo(String value) {
            addCriterion("message_head >=", value, "messageHead");
            return (Criteria) this;
        }

        public Criteria andMessageHeadLessThan(String value) {
            addCriterion("message_head <", value, "messageHead");
            return (Criteria) this;
        }

        public Criteria andMessageHeadLessThanOrEqualTo(String value) {
            addCriterion("message_head <=", value, "messageHead");
            return (Criteria) this;
        }

        public Criteria andMessageHeadLike(String value) {
            addCriterion("message_head like", value, "messageHead");
            return (Criteria) this;
        }

        public Criteria andMessageHeadNotLike(String value) {
            addCriterion("message_head not like", value, "messageHead");
            return (Criteria) this;
        }

        public Criteria andMessageHeadIn(List<String> values) {
            addCriterion("message_head in", values, "messageHead");
            return (Criteria) this;
        }

        public Criteria andMessageHeadNotIn(List<String> values) {
            addCriterion("message_head not in", values, "messageHead");
            return (Criteria) this;
        }

        public Criteria andMessageHeadBetween(String value1, String value2) {
            addCriterion("message_head between", value1, value2, "messageHead");
            return (Criteria) this;
        }

        public Criteria andMessageHeadNotBetween(String value1, String value2) {
            addCriterion("message_head not between", value1, value2, "messageHead");
            return (Criteria) this;
        }

        public Criteria andMessageContentIsNull() {
            addCriterion("message_content is null");
            return (Criteria) this;
        }

        public Criteria andMessageContentIsNotNull() {
            addCriterion("message_content is not null");
            return (Criteria) this;
        }

        public Criteria andMessageContentEqualTo(String value) {
            addCriterion("message_content =", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotEqualTo(String value) {
            addCriterion("message_content <>", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentGreaterThan(String value) {
            addCriterion("message_content >", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentGreaterThanOrEqualTo(String value) {
            addCriterion("message_content >=", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLessThan(String value) {
            addCriterion("message_content <", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLessThanOrEqualTo(String value) {
            addCriterion("message_content <=", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentLike(String value) {
            addCriterion("message_content like", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotLike(String value) {
            addCriterion("message_content not like", value, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentIn(List<String> values) {
            addCriterion("message_content in", values, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotIn(List<String> values) {
            addCriterion("message_content not in", values, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentBetween(String value1, String value2) {
            addCriterion("message_content between", value1, value2, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageContentNotBetween(String value1, String value2) {
            addCriterion("message_content not between", value1, value2, "messageContent");
            return (Criteria) this;
        }

        public Criteria andMessageStatusIsNull() {
            addCriterion("message_status is null");
            return (Criteria) this;
        }

        public Criteria andMessageStatusIsNotNull() {
            addCriterion("message_status is not null");
            return (Criteria) this;
        }

        public Criteria andMessageStatusEqualTo(String value) {
            addCriterion("message_status =", value, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusNotEqualTo(String value) {
            addCriterion("message_status <>", value, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusGreaterThan(String value) {
            addCriterion("message_status >", value, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusGreaterThanOrEqualTo(String value) {
            addCriterion("message_status >=", value, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusLessThan(String value) {
            addCriterion("message_status <", value, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusLessThanOrEqualTo(String value) {
            addCriterion("message_status <=", value, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusLike(String value) {
            addCriterion("message_status like", value, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusNotLike(String value) {
            addCriterion("message_status not like", value, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusIn(List<String> values) {
            addCriterion("message_status in", values, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusNotIn(List<String> values) {
            addCriterion("message_status not in", values, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusBetween(String value1, String value2) {
            addCriterion("message_status between", value1, value2, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andMessageStatusNotBetween(String value1, String value2) {
            addCriterion("message_status not between", value1, value2, "messageStatus");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIsNull() {
            addCriterion("created_user is null");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIsNotNull() {
            addCriterion("created_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedUserEqualTo(String value) {
            addCriterion("created_user =", value, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserNotEqualTo(String value) {
            addCriterion("created_user <>", value, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserGreaterThan(String value) {
            addCriterion("created_user >", value, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserGreaterThanOrEqualTo(String value) {
            addCriterion("created_user >=", value, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserLessThan(String value) {
            addCriterion("created_user <", value, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserLessThanOrEqualTo(String value) {
            addCriterion("created_user <=", value, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserLike(String value) {
            addCriterion("created_user like", value, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserNotLike(String value) {
            addCriterion("created_user not like", value, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIn(List<String> values) {
            addCriterion("created_user in", values, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserNotIn(List<String> values) {
            addCriterion("created_user not in", values, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserBetween(String value1, String value2) {
            addCriterion("created_user between", value1, value2, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreatedUserNotBetween(String value1, String value2) {
            addCriterion("created_user not between", value1, value2, "createdUser");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("create_time like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("create_time not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("receiver is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("receiver is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(String value) {
            addCriterion("receiver =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(String value) {
            addCriterion("receiver <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(String value) {
            addCriterion("receiver >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(String value) {
            addCriterion("receiver >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(String value) {
            addCriterion("receiver <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(String value) {
            addCriterion("receiver <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLike(String value) {
            addCriterion("receiver like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotLike(String value) {
            addCriterion("receiver not like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<String> values) {
            addCriterion("receiver in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<String> values) {
            addCriterion("receiver not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(String value1, String value2) {
            addCriterion("receiver between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(String value1, String value2) {
            addCriterion("receiver not between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNull() {
            addCriterion("publish_time is null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNotNull() {
            addCriterion("publish_time is not null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeEqualTo(String value) {
            addCriterion("publish_time =", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotEqualTo(String value) {
            addCriterion("publish_time <>", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThan(String value) {
            addCriterion("publish_time >", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThanOrEqualTo(String value) {
            addCriterion("publish_time >=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThan(String value) {
            addCriterion("publish_time <", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThanOrEqualTo(String value) {
            addCriterion("publish_time <=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLike(String value) {
            addCriterion("publish_time like", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotLike(String value) {
            addCriterion("publish_time not like", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIn(List<String> values) {
            addCriterion("publish_time in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotIn(List<String> values) {
            addCriterion("publish_time not in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeBetween(String value1, String value2) {
            addCriterion("publish_time between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotBetween(String value1, String value2) {
            addCriterion("publish_time not between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andDestoryTimeIsNull() {
            addCriterion("destory_time is null");
            return (Criteria) this;
        }

        public Criteria andDestoryTimeIsNotNull() {
            addCriterion("destory_time is not null");
            return (Criteria) this;
        }

        public Criteria andDestoryTimeEqualTo(String value) {
            addCriterion("destory_time =", value, "destoryTime");
            return (Criteria) this;
        }

        public Criteria andDestoryTimeNotEqualTo(String value) {
            addCriterion("destory_time <>", value, "destoryTime");
            return (Criteria) this;
        }

        public Criteria andDestoryTimeGreaterThan(String value) {
            addCriterion("destory_time >", value, "destoryTime");
            return (Criteria) this;
        }

        public Criteria andDestoryTimeGreaterThanOrEqualTo(String value) {
            addCriterion("destory_time >=", value, "destoryTime");
            return (Criteria) this;
        }

        public Criteria andDestoryTimeLessThan(String value) {
            addCriterion("destory_time <", value, "destoryTime");
            return (Criteria) this;
        }

        public Criteria andDestoryTimeLessThanOrEqualTo(String value) {
            addCriterion("destory_time <=", value, "destoryTime");
            return (Criteria) this;
        }

        public Criteria andDestoryTimeLike(String value) {
            addCriterion("destory_time like", value, "destoryTime");
            return (Criteria) this;
        }

        public Criteria andDestoryTimeNotLike(String value) {
            addCriterion("destory_time not like", value, "destoryTime");
            return (Criteria) this;
        }

        public Criteria andDestoryTimeIn(List<String> values) {
            addCriterion("destory_time in", values, "destoryTime");
            return (Criteria) this;
        }

        public Criteria andDestoryTimeNotIn(List<String> values) {
            addCriterion("destory_time not in", values, "destoryTime");
            return (Criteria) this;
        }

        public Criteria andDestoryTimeBetween(String value1, String value2) {
            addCriterion("destory_time between", value1, value2, "destoryTime");
            return (Criteria) this;
        }

        public Criteria andDestoryTimeNotBetween(String value1, String value2) {
            addCriterion("destory_time not between", value1, value2, "destoryTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
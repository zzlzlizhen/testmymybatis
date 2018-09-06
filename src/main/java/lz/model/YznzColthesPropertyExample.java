package lz.model;

import java.util.ArrayList;
import java.util.List;

public class YznzColthesPropertyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YznzColthesPropertyExample() {
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

        public Criteria andColthesIdIsNull() {
            addCriterion("colthes_id is null");
            return (Criteria) this;
        }

        public Criteria andColthesIdIsNotNull() {
            addCriterion("colthes_id is not null");
            return (Criteria) this;
        }

        public Criteria andColthesIdEqualTo(String value) {
            addCriterion("colthes_id =", value, "colthesId");
            return (Criteria) this;
        }

        public Criteria andColthesIdNotEqualTo(String value) {
            addCriterion("colthes_id <>", value, "colthesId");
            return (Criteria) this;
        }

        public Criteria andColthesIdGreaterThan(String value) {
            addCriterion("colthes_id >", value, "colthesId");
            return (Criteria) this;
        }

        public Criteria andColthesIdGreaterThanOrEqualTo(String value) {
            addCriterion("colthes_id >=", value, "colthesId");
            return (Criteria) this;
        }

        public Criteria andColthesIdLessThan(String value) {
            addCriterion("colthes_id <", value, "colthesId");
            return (Criteria) this;
        }

        public Criteria andColthesIdLessThanOrEqualTo(String value) {
            addCriterion("colthes_id <=", value, "colthesId");
            return (Criteria) this;
        }

        public Criteria andColthesIdLike(String value) {
            addCriterion("colthes_id like", value, "colthesId");
            return (Criteria) this;
        }

        public Criteria andColthesIdNotLike(String value) {
            addCriterion("colthes_id not like", value, "colthesId");
            return (Criteria) this;
        }

        public Criteria andColthesIdIn(List<String> values) {
            addCriterion("colthes_id in", values, "colthesId");
            return (Criteria) this;
        }

        public Criteria andColthesIdNotIn(List<String> values) {
            addCriterion("colthes_id not in", values, "colthesId");
            return (Criteria) this;
        }

        public Criteria andColthesIdBetween(String value1, String value2) {
            addCriterion("colthes_id between", value1, value2, "colthesId");
            return (Criteria) this;
        }

        public Criteria andColthesIdNotBetween(String value1, String value2) {
            addCriterion("colthes_id not between", value1, value2, "colthesId");
            return (Criteria) this;
        }

        public Criteria andSizeTypeIsNull() {
            addCriterion("size_type is null");
            return (Criteria) this;
        }

        public Criteria andSizeTypeIsNotNull() {
            addCriterion("size_type is not null");
            return (Criteria) this;
        }

        public Criteria andSizeTypeEqualTo(String value) {
            addCriterion("size_type =", value, "sizeType");
            return (Criteria) this;
        }

        public Criteria andSizeTypeNotEqualTo(String value) {
            addCriterion("size_type <>", value, "sizeType");
            return (Criteria) this;
        }

        public Criteria andSizeTypeGreaterThan(String value) {
            addCriterion("size_type >", value, "sizeType");
            return (Criteria) this;
        }

        public Criteria andSizeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("size_type >=", value, "sizeType");
            return (Criteria) this;
        }

        public Criteria andSizeTypeLessThan(String value) {
            addCriterion("size_type <", value, "sizeType");
            return (Criteria) this;
        }

        public Criteria andSizeTypeLessThanOrEqualTo(String value) {
            addCriterion("size_type <=", value, "sizeType");
            return (Criteria) this;
        }

        public Criteria andSizeTypeLike(String value) {
            addCriterion("size_type like", value, "sizeType");
            return (Criteria) this;
        }

        public Criteria andSizeTypeNotLike(String value) {
            addCriterion("size_type not like", value, "sizeType");
            return (Criteria) this;
        }

        public Criteria andSizeTypeIn(List<String> values) {
            addCriterion("size_type in", values, "sizeType");
            return (Criteria) this;
        }

        public Criteria andSizeTypeNotIn(List<String> values) {
            addCriterion("size_type not in", values, "sizeType");
            return (Criteria) this;
        }

        public Criteria andSizeTypeBetween(String value1, String value2) {
            addCriterion("size_type between", value1, value2, "sizeType");
            return (Criteria) this;
        }

        public Criteria andSizeTypeNotBetween(String value1, String value2) {
            addCriterion("size_type not between", value1, value2, "sizeType");
            return (Criteria) this;
        }

        public Criteria andColourTypeIsNull() {
            addCriterion("colour_type is null");
            return (Criteria) this;
        }

        public Criteria andColourTypeIsNotNull() {
            addCriterion("colour_type is not null");
            return (Criteria) this;
        }

        public Criteria andColourTypeEqualTo(String value) {
            addCriterion("colour_type =", value, "colourType");
            return (Criteria) this;
        }

        public Criteria andColourTypeNotEqualTo(String value) {
            addCriterion("colour_type <>", value, "colourType");
            return (Criteria) this;
        }

        public Criteria andColourTypeGreaterThan(String value) {
            addCriterion("colour_type >", value, "colourType");
            return (Criteria) this;
        }

        public Criteria andColourTypeGreaterThanOrEqualTo(String value) {
            addCriterion("colour_type >=", value, "colourType");
            return (Criteria) this;
        }

        public Criteria andColourTypeLessThan(String value) {
            addCriterion("colour_type <", value, "colourType");
            return (Criteria) this;
        }

        public Criteria andColourTypeLessThanOrEqualTo(String value) {
            addCriterion("colour_type <=", value, "colourType");
            return (Criteria) this;
        }

        public Criteria andColourTypeLike(String value) {
            addCriterion("colour_type like", value, "colourType");
            return (Criteria) this;
        }

        public Criteria andColourTypeNotLike(String value) {
            addCriterion("colour_type not like", value, "colourType");
            return (Criteria) this;
        }

        public Criteria andColourTypeIn(List<String> values) {
            addCriterion("colour_type in", values, "colourType");
            return (Criteria) this;
        }

        public Criteria andColourTypeNotIn(List<String> values) {
            addCriterion("colour_type not in", values, "colourType");
            return (Criteria) this;
        }

        public Criteria andColourTypeBetween(String value1, String value2) {
            addCriterion("colour_type between", value1, value2, "colourType");
            return (Criteria) this;
        }

        public Criteria andColourTypeNotBetween(String value1, String value2) {
            addCriterion("colour_type not between", value1, value2, "colourType");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountIsNull() {
            addCriterion("purchase_count is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountIsNotNull() {
            addCriterion("purchase_count is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountEqualTo(Integer value) {
            addCriterion("purchase_count =", value, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountNotEqualTo(Integer value) {
            addCriterion("purchase_count <>", value, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountGreaterThan(Integer value) {
            addCriterion("purchase_count >", value, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchase_count >=", value, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountLessThan(Integer value) {
            addCriterion("purchase_count <", value, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountLessThanOrEqualTo(Integer value) {
            addCriterion("purchase_count <=", value, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountIn(List<Integer> values) {
            addCriterion("purchase_count in", values, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountNotIn(List<Integer> values) {
            addCriterion("purchase_count not in", values, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountBetween(Integer value1, Integer value2) {
            addCriterion("purchase_count between", value1, value2, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andPurchaseCountNotBetween(Integer value1, Integer value2) {
            addCriterion("purchase_count not between", value1, value2, "purchaseCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountIsNull() {
            addCriterion("sale_count is null");
            return (Criteria) this;
        }

        public Criteria andSaleCountIsNotNull() {
            addCriterion("sale_count is not null");
            return (Criteria) this;
        }

        public Criteria andSaleCountEqualTo(Integer value) {
            addCriterion("sale_count =", value, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountNotEqualTo(Integer value) {
            addCriterion("sale_count <>", value, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountGreaterThan(Integer value) {
            addCriterion("sale_count >", value, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_count >=", value, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountLessThan(Integer value) {
            addCriterion("sale_count <", value, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountLessThanOrEqualTo(Integer value) {
            addCriterion("sale_count <=", value, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountIn(List<Integer> values) {
            addCriterion("sale_count in", values, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountNotIn(List<Integer> values) {
            addCriterion("sale_count not in", values, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountBetween(Integer value1, Integer value2) {
            addCriterion("sale_count between", value1, value2, "saleCount");
            return (Criteria) this;
        }

        public Criteria andSaleCountNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_count not between", value1, value2, "saleCount");
            return (Criteria) this;
        }

        public Criteria andAddByIsNull() {
            addCriterion("add_by is null");
            return (Criteria) this;
        }

        public Criteria andAddByIsNotNull() {
            addCriterion("add_by is not null");
            return (Criteria) this;
        }

        public Criteria andAddByEqualTo(String value) {
            addCriterion("add_by =", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByNotEqualTo(String value) {
            addCriterion("add_by <>", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByGreaterThan(String value) {
            addCriterion("add_by >", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByGreaterThanOrEqualTo(String value) {
            addCriterion("add_by >=", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByLessThan(String value) {
            addCriterion("add_by <", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByLessThanOrEqualTo(String value) {
            addCriterion("add_by <=", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByLike(String value) {
            addCriterion("add_by like", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByNotLike(String value) {
            addCriterion("add_by not like", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByIn(List<String> values) {
            addCriterion("add_by in", values, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByNotIn(List<String> values) {
            addCriterion("add_by not in", values, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByBetween(String value1, String value2) {
            addCriterion("add_by between", value1, value2, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByNotBetween(String value1, String value2) {
            addCriterion("add_by not between", value1, value2, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(String value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(String value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(String value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(String value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(String value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(String value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLike(String value) {
            addCriterion("add_time like", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotLike(String value) {
            addCriterion("add_time not like", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<String> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<String> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(String value1, String value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(String value1, String value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(String value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(String value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(String value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(String value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(String value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLike(String value) {
            addCriterion("update_time like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotLike(String value) {
            addCriterion("update_time not like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<String> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<String> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(String value1, String value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(String value1, String value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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
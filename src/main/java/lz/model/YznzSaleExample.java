package lz.model;

import java.util.ArrayList;
import java.util.List;

public class YznzSaleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YznzSaleExample() {
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

        public Criteria andColthesPropertyIdIsNull() {
            addCriterion("colthes_property_id is null");
            return (Criteria) this;
        }

        public Criteria andColthesPropertyIdIsNotNull() {
            addCriterion("colthes_property_id is not null");
            return (Criteria) this;
        }

        public Criteria andColthesPropertyIdEqualTo(String value) {
            addCriterion("colthes_property_id =", value, "colthesPropertyId");
            return (Criteria) this;
        }

        public Criteria andColthesPropertyIdNotEqualTo(String value) {
            addCriterion("colthes_property_id <>", value, "colthesPropertyId");
            return (Criteria) this;
        }

        public Criteria andColthesPropertyIdGreaterThan(String value) {
            addCriterion("colthes_property_id >", value, "colthesPropertyId");
            return (Criteria) this;
        }

        public Criteria andColthesPropertyIdGreaterThanOrEqualTo(String value) {
            addCriterion("colthes_property_id >=", value, "colthesPropertyId");
            return (Criteria) this;
        }

        public Criteria andColthesPropertyIdLessThan(String value) {
            addCriterion("colthes_property_id <", value, "colthesPropertyId");
            return (Criteria) this;
        }

        public Criteria andColthesPropertyIdLessThanOrEqualTo(String value) {
            addCriterion("colthes_property_id <=", value, "colthesPropertyId");
            return (Criteria) this;
        }

        public Criteria andColthesPropertyIdLike(String value) {
            addCriterion("colthes_property_id like", value, "colthesPropertyId");
            return (Criteria) this;
        }

        public Criteria andColthesPropertyIdNotLike(String value) {
            addCriterion("colthes_property_id not like", value, "colthesPropertyId");
            return (Criteria) this;
        }

        public Criteria andColthesPropertyIdIn(List<String> values) {
            addCriterion("colthes_property_id in", values, "colthesPropertyId");
            return (Criteria) this;
        }

        public Criteria andColthesPropertyIdNotIn(List<String> values) {
            addCriterion("colthes_property_id not in", values, "colthesPropertyId");
            return (Criteria) this;
        }

        public Criteria andColthesPropertyIdBetween(String value1, String value2) {
            addCriterion("colthes_property_id between", value1, value2, "colthesPropertyId");
            return (Criteria) this;
        }

        public Criteria andColthesPropertyIdNotBetween(String value1, String value2) {
            addCriterion("colthes_property_id not between", value1, value2, "colthesPropertyId");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceIsNull() {
            addCriterion("purchase_price is null");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceIsNotNull() {
            addCriterion("purchase_price is not null");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceEqualTo(String value) {
            addCriterion("purchase_price =", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceNotEqualTo(String value) {
            addCriterion("purchase_price <>", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceGreaterThan(String value) {
            addCriterion("purchase_price >", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceGreaterThanOrEqualTo(String value) {
            addCriterion("purchase_price >=", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceLessThan(String value) {
            addCriterion("purchase_price <", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceLessThanOrEqualTo(String value) {
            addCriterion("purchase_price <=", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceLike(String value) {
            addCriterion("purchase_price like", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceNotLike(String value) {
            addCriterion("purchase_price not like", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceIn(List<String> values) {
            addCriterion("purchase_price in", values, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceNotIn(List<String> values) {
            addCriterion("purchase_price not in", values, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceBetween(String value1, String value2) {
            addCriterion("purchase_price between", value1, value2, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceNotBetween(String value1, String value2) {
            addCriterion("purchase_price not between", value1, value2, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceIsNull() {
            addCriterion("real_price is null");
            return (Criteria) this;
        }

        public Criteria andRealPriceIsNotNull() {
            addCriterion("real_price is not null");
            return (Criteria) this;
        }

        public Criteria andRealPriceEqualTo(String value) {
            addCriterion("real_price =", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceNotEqualTo(String value) {
            addCriterion("real_price <>", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceGreaterThan(String value) {
            addCriterion("real_price >", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceGreaterThanOrEqualTo(String value) {
            addCriterion("real_price >=", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceLessThan(String value) {
            addCriterion("real_price <", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceLessThanOrEqualTo(String value) {
            addCriterion("real_price <=", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceLike(String value) {
            addCriterion("real_price like", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceNotLike(String value) {
            addCriterion("real_price not like", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceIn(List<String> values) {
            addCriterion("real_price in", values, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceNotIn(List<String> values) {
            addCriterion("real_price not in", values, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceBetween(String value1, String value2) {
            addCriterion("real_price between", value1, value2, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceNotBetween(String value1, String value2) {
            addCriterion("real_price not between", value1, value2, "realPrice");
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
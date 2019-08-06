package cn.deliver.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andUserOrderIdIsNull() {
            addCriterion("user_order_id is null");
            return (Criteria) this;
        }

        public Criteria andUserOrderIdIsNotNull() {
            addCriterion("user_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserOrderIdEqualTo(Integer value) {
            addCriterion("user_order_id =", value, "userOrderId");
            return (Criteria) this;
        }

        public Criteria andUserOrderIdNotEqualTo(Integer value) {
            addCriterion("user_order_id <>", value, "userOrderId");
            return (Criteria) this;
        }

        public Criteria andUserOrderIdGreaterThan(Integer value) {
            addCriterion("user_order_id >", value, "userOrderId");
            return (Criteria) this;
        }

        public Criteria andUserOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_order_id >=", value, "userOrderId");
            return (Criteria) this;
        }

        public Criteria andUserOrderIdLessThan(Integer value) {
            addCriterion("user_order_id <", value, "userOrderId");
            return (Criteria) this;
        }

        public Criteria andUserOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_order_id <=", value, "userOrderId");
            return (Criteria) this;
        }

        public Criteria andUserOrderIdIn(List<Integer> values) {
            addCriterion("user_order_id in", values, "userOrderId");
            return (Criteria) this;
        }

        public Criteria andUserOrderIdNotIn(List<Integer> values) {
            addCriterion("user_order_id not in", values, "userOrderId");
            return (Criteria) this;
        }

        public Criteria andUserOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("user_order_id between", value1, value2, "userOrderId");
            return (Criteria) this;
        }

        public Criteria andUserOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_order_id not between", value1, value2, "userOrderId");
            return (Criteria) this;
        }

        public Criteria andDriverUidIsNull() {
            addCriterion("driver_uid is null");
            return (Criteria) this;
        }

        public Criteria andDriverUidIsNotNull() {
            addCriterion("driver_uid is not null");
            return (Criteria) this;
        }

        public Criteria andDriverUidEqualTo(Integer value) {
            addCriterion("driver_uid =", value, "driverUid");
            return (Criteria) this;
        }

        public Criteria andDriverUidNotEqualTo(Integer value) {
            addCriterion("driver_uid <>", value, "driverUid");
            return (Criteria) this;
        }

        public Criteria andDriverUidGreaterThan(Integer value) {
            addCriterion("driver_uid >", value, "driverUid");
            return (Criteria) this;
        }

        public Criteria andDriverUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("driver_uid >=", value, "driverUid");
            return (Criteria) this;
        }

        public Criteria andDriverUidLessThan(Integer value) {
            addCriterion("driver_uid <", value, "driverUid");
            return (Criteria) this;
        }

        public Criteria andDriverUidLessThanOrEqualTo(Integer value) {
            addCriterion("driver_uid <=", value, "driverUid");
            return (Criteria) this;
        }

        public Criteria andDriverUidIn(List<Integer> values) {
            addCriterion("driver_uid in", values, "driverUid");
            return (Criteria) this;
        }

        public Criteria andDriverUidNotIn(List<Integer> values) {
            addCriterion("driver_uid not in", values, "driverUid");
            return (Criteria) this;
        }

        public Criteria andDriverUidBetween(Integer value1, Integer value2) {
            addCriterion("driver_uid between", value1, value2, "driverUid");
            return (Criteria) this;
        }

        public Criteria andDriverUidNotBetween(Integer value1, Integer value2) {
            addCriterion("driver_uid not between", value1, value2, "driverUid");
            return (Criteria) this;
        }

        public Criteria andSuretyIdIsNull() {
            addCriterion("surety_id is null");
            return (Criteria) this;
        }

        public Criteria andSuretyIdIsNotNull() {
            addCriterion("surety_id is not null");
            return (Criteria) this;
        }

        public Criteria andSuretyIdEqualTo(Integer value) {
            addCriterion("surety_id =", value, "suretyId");
            return (Criteria) this;
        }

        public Criteria andSuretyIdNotEqualTo(Integer value) {
            addCriterion("surety_id <>", value, "suretyId");
            return (Criteria) this;
        }

        public Criteria andSuretyIdGreaterThan(Integer value) {
            addCriterion("surety_id >", value, "suretyId");
            return (Criteria) this;
        }

        public Criteria andSuretyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("surety_id >=", value, "suretyId");
            return (Criteria) this;
        }

        public Criteria andSuretyIdLessThan(Integer value) {
            addCriterion("surety_id <", value, "suretyId");
            return (Criteria) this;
        }

        public Criteria andSuretyIdLessThanOrEqualTo(Integer value) {
            addCriterion("surety_id <=", value, "suretyId");
            return (Criteria) this;
        }

        public Criteria andSuretyIdIn(List<Integer> values) {
            addCriterion("surety_id in", values, "suretyId");
            return (Criteria) this;
        }

        public Criteria andSuretyIdNotIn(List<Integer> values) {
            addCriterion("surety_id not in", values, "suretyId");
            return (Criteria) this;
        }

        public Criteria andSuretyIdBetween(Integer value1, Integer value2) {
            addCriterion("surety_id between", value1, value2, "suretyId");
            return (Criteria) this;
        }

        public Criteria andSuretyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("surety_id not between", value1, value2, "suretyId");
            return (Criteria) this;
        }

        public Criteria andNoIsNull() {
            addCriterion("no is null");
            return (Criteria) this;
        }

        public Criteria andNoIsNotNull() {
            addCriterion("no is not null");
            return (Criteria) this;
        }

        public Criteria andNoEqualTo(String value) {
            addCriterion("no =", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotEqualTo(String value) {
            addCriterion("no <>", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThan(String value) {
            addCriterion("no >", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThanOrEqualTo(String value) {
            addCriterion("no >=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThan(String value) {
            addCriterion("no <", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThanOrEqualTo(String value) {
            addCriterion("no <=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLike(String value) {
            addCriterion("no like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotLike(String value) {
            addCriterion("no not like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoIn(List<String> values) {
            addCriterion("no in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotIn(List<String> values) {
            addCriterion("no not in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoBetween(String value1, String value2) {
            addCriterion("no between", value1, value2, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotBetween(String value1, String value2) {
            addCriterion("no not between", value1, value2, "no");
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

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
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
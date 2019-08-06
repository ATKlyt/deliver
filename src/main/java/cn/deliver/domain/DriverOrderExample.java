package cn.deliver.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DriverOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DriverOrderExample() {
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andOriginalIdIsNull() {
            addCriterion("original_id is null");
            return (Criteria) this;
        }

        public Criteria andOriginalIdIsNotNull() {
            addCriterion("original_id is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalIdEqualTo(Integer value) {
            addCriterion("original_id =", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdNotEqualTo(Integer value) {
            addCriterion("original_id <>", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdGreaterThan(Integer value) {
            addCriterion("original_id >", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("original_id >=", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdLessThan(Integer value) {
            addCriterion("original_id <", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdLessThanOrEqualTo(Integer value) {
            addCriterion("original_id <=", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdIn(List<Integer> values) {
            addCriterion("original_id in", values, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdNotIn(List<Integer> values) {
            addCriterion("original_id not in", values, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdBetween(Integer value1, Integer value2) {
            addCriterion("original_id between", value1, value2, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdNotBetween(Integer value1, Integer value2) {
            addCriterion("original_id not between", value1, value2, "originalId");
            return (Criteria) this;
        }

        public Criteria andDestinationIdIsNull() {
            addCriterion("destination_id is null");
            return (Criteria) this;
        }

        public Criteria andDestinationIdIsNotNull() {
            addCriterion("destination_id is not null");
            return (Criteria) this;
        }

        public Criteria andDestinationIdEqualTo(Integer value) {
            addCriterion("destination_id =", value, "destinationId");
            return (Criteria) this;
        }

        public Criteria andDestinationIdNotEqualTo(Integer value) {
            addCriterion("destination_id <>", value, "destinationId");
            return (Criteria) this;
        }

        public Criteria andDestinationIdGreaterThan(Integer value) {
            addCriterion("destination_id >", value, "destinationId");
            return (Criteria) this;
        }

        public Criteria andDestinationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("destination_id >=", value, "destinationId");
            return (Criteria) this;
        }

        public Criteria andDestinationIdLessThan(Integer value) {
            addCriterion("destination_id <", value, "destinationId");
            return (Criteria) this;
        }

        public Criteria andDestinationIdLessThanOrEqualTo(Integer value) {
            addCriterion("destination_id <=", value, "destinationId");
            return (Criteria) this;
        }

        public Criteria andDestinationIdIn(List<Integer> values) {
            addCriterion("destination_id in", values, "destinationId");
            return (Criteria) this;
        }

        public Criteria andDestinationIdNotIn(List<Integer> values) {
            addCriterion("destination_id not in", values, "destinationId");
            return (Criteria) this;
        }

        public Criteria andDestinationIdBetween(Integer value1, Integer value2) {
            addCriterion("destination_id between", value1, value2, "destinationId");
            return (Criteria) this;
        }

        public Criteria andDestinationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("destination_id not between", value1, value2, "destinationId");
            return (Criteria) this;
        }

        public Criteria andCarryIsNull() {
            addCriterion("carry is null");
            return (Criteria) this;
        }

        public Criteria andCarryIsNotNull() {
            addCriterion("carry is not null");
            return (Criteria) this;
        }

        public Criteria andCarryEqualTo(Integer value) {
            addCriterion("carry =", value, "carry");
            return (Criteria) this;
        }

        public Criteria andCarryNotEqualTo(Integer value) {
            addCriterion("carry <>", value, "carry");
            return (Criteria) this;
        }

        public Criteria andCarryGreaterThan(Integer value) {
            addCriterion("carry >", value, "carry");
            return (Criteria) this;
        }

        public Criteria andCarryGreaterThanOrEqualTo(Integer value) {
            addCriterion("carry >=", value, "carry");
            return (Criteria) this;
        }

        public Criteria andCarryLessThan(Integer value) {
            addCriterion("carry <", value, "carry");
            return (Criteria) this;
        }

        public Criteria andCarryLessThanOrEqualTo(Integer value) {
            addCriterion("carry <=", value, "carry");
            return (Criteria) this;
        }

        public Criteria andCarryIn(List<Integer> values) {
            addCriterion("carry in", values, "carry");
            return (Criteria) this;
        }

        public Criteria andCarryNotIn(List<Integer> values) {
            addCriterion("carry not in", values, "carry");
            return (Criteria) this;
        }

        public Criteria andCarryBetween(Integer value1, Integer value2) {
            addCriterion("carry between", value1, value2, "carry");
            return (Criteria) this;
        }

        public Criteria andCarryNotBetween(Integer value1, Integer value2) {
            addCriterion("carry not between", value1, value2, "carry");
            return (Criteria) this;
        }

        public Criteria andGoOffIsNull() {
            addCriterion("go_off is null");
            return (Criteria) this;
        }

        public Criteria andGoOffIsNotNull() {
            addCriterion("go_off is not null");
            return (Criteria) this;
        }

        public Criteria andGoOffEqualTo(Date value) {
            addCriterion("go_off =", value, "goOff");
            return (Criteria) this;
        }

        public Criteria andGoOffNotEqualTo(Date value) {
            addCriterion("go_off <>", value, "goOff");
            return (Criteria) this;
        }

        public Criteria andGoOffGreaterThan(Date value) {
            addCriterion("go_off >", value, "goOff");
            return (Criteria) this;
        }

        public Criteria andGoOffGreaterThanOrEqualTo(Date value) {
            addCriterion("go_off >=", value, "goOff");
            return (Criteria) this;
        }

        public Criteria andGoOffLessThan(Date value) {
            addCriterion("go_off <", value, "goOff");
            return (Criteria) this;
        }

        public Criteria andGoOffLessThanOrEqualTo(Date value) {
            addCriterion("go_off <=", value, "goOff");
            return (Criteria) this;
        }

        public Criteria andGoOffIn(List<Date> values) {
            addCriterion("go_off in", values, "goOff");
            return (Criteria) this;
        }

        public Criteria andGoOffNotIn(List<Date> values) {
            addCriterion("go_off not in", values, "goOff");
            return (Criteria) this;
        }

        public Criteria andGoOffBetween(Date value1, Date value2) {
            addCriterion("go_off between", value1, value2, "goOff");
            return (Criteria) this;
        }

        public Criteria andGoOffNotBetween(Date value1, Date value2) {
            addCriterion("go_off not between", value1, value2, "goOff");
            return (Criteria) this;
        }

        public Criteria andDeadlineIsNull() {
            addCriterion("deadline is null");
            return (Criteria) this;
        }

        public Criteria andDeadlineIsNotNull() {
            addCriterion("deadline is not null");
            return (Criteria) this;
        }

        public Criteria andDeadlineEqualTo(Date value) {
            addCriterion("deadline =", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotEqualTo(Date value) {
            addCriterion("deadline <>", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineGreaterThan(Date value) {
            addCriterion("deadline >", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineGreaterThanOrEqualTo(Date value) {
            addCriterion("deadline >=", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineLessThan(Date value) {
            addCriterion("deadline <", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineLessThanOrEqualTo(Date value) {
            addCriterion("deadline <=", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineIn(List<Date> values) {
            addCriterion("deadline in", values, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotIn(List<Date> values) {
            addCriterion("deadline not in", values, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineBetween(Date value1, Date value2) {
            addCriterion("deadline between", value1, value2, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotBetween(Date value1, Date value2) {
            addCriterion("deadline not between", value1, value2, "deadline");
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
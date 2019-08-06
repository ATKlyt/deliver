package cn.deliver.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserOrderExample() {
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

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andPayIsNull() {
            addCriterion("pay is null");
            return (Criteria) this;
        }

        public Criteria andPayIsNotNull() {
            addCriterion("pay is not null");
            return (Criteria) this;
        }

        public Criteria andPayEqualTo(Long value) {
            addCriterion("pay =", value, "pay");
            return (Criteria) this;
        }

        public Criteria andPayNotEqualTo(Long value) {
            addCriterion("pay <>", value, "pay");
            return (Criteria) this;
        }

        public Criteria andPayGreaterThan(Long value) {
            addCriterion("pay >", value, "pay");
            return (Criteria) this;
        }

        public Criteria andPayGreaterThanOrEqualTo(Long value) {
            addCriterion("pay >=", value, "pay");
            return (Criteria) this;
        }

        public Criteria andPayLessThan(Long value) {
            addCriterion("pay <", value, "pay");
            return (Criteria) this;
        }

        public Criteria andPayLessThanOrEqualTo(Long value) {
            addCriterion("pay <=", value, "pay");
            return (Criteria) this;
        }

        public Criteria andPayIn(List<Long> values) {
            addCriterion("pay in", values, "pay");
            return (Criteria) this;
        }

        public Criteria andPayNotIn(List<Long> values) {
            addCriterion("pay not in", values, "pay");
            return (Criteria) this;
        }

        public Criteria andPayBetween(Long value1, Long value2) {
            addCriterion("pay between", value1, value2, "pay");
            return (Criteria) this;
        }

        public Criteria andPayNotBetween(Long value1, Long value2) {
            addCriterion("pay not between", value1, value2, "pay");
            return (Criteria) this;
        }

        public Criteria andDeliveryStartIsNull() {
            addCriterion("delivery_start is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryStartIsNotNull() {
            addCriterion("delivery_start is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryStartEqualTo(Date value) {
            addCriterion("delivery_start =", value, "deliveryStart");
            return (Criteria) this;
        }

        public Criteria andDeliveryStartNotEqualTo(Date value) {
            addCriterion("delivery_start <>", value, "deliveryStart");
            return (Criteria) this;
        }

        public Criteria andDeliveryStartGreaterThan(Date value) {
            addCriterion("delivery_start >", value, "deliveryStart");
            return (Criteria) this;
        }

        public Criteria andDeliveryStartGreaterThanOrEqualTo(Date value) {
            addCriterion("delivery_start >=", value, "deliveryStart");
            return (Criteria) this;
        }

        public Criteria andDeliveryStartLessThan(Date value) {
            addCriterion("delivery_start <", value, "deliveryStart");
            return (Criteria) this;
        }

        public Criteria andDeliveryStartLessThanOrEqualTo(Date value) {
            addCriterion("delivery_start <=", value, "deliveryStart");
            return (Criteria) this;
        }

        public Criteria andDeliveryStartIn(List<Date> values) {
            addCriterion("delivery_start in", values, "deliveryStart");
            return (Criteria) this;
        }

        public Criteria andDeliveryStartNotIn(List<Date> values) {
            addCriterion("delivery_start not in", values, "deliveryStart");
            return (Criteria) this;
        }

        public Criteria andDeliveryStartBetween(Date value1, Date value2) {
            addCriterion("delivery_start between", value1, value2, "deliveryStart");
            return (Criteria) this;
        }

        public Criteria andDeliveryStartNotBetween(Date value1, Date value2) {
            addCriterion("delivery_start not between", value1, value2, "deliveryStart");
            return (Criteria) this;
        }

        public Criteria andConsigneeAreaIdIsNull() {
            addCriterion("consignee_area_id is null");
            return (Criteria) this;
        }

        public Criteria andConsigneeAreaIdIsNotNull() {
            addCriterion("consignee_area_id is not null");
            return (Criteria) this;
        }

        public Criteria andConsigneeAreaIdEqualTo(Integer value) {
            addCriterion("consignee_area_id =", value, "consigneeAreaId");
            return (Criteria) this;
        }

        public Criteria andConsigneeAreaIdNotEqualTo(Integer value) {
            addCriterion("consignee_area_id <>", value, "consigneeAreaId");
            return (Criteria) this;
        }

        public Criteria andConsigneeAreaIdGreaterThan(Integer value) {
            addCriterion("consignee_area_id >", value, "consigneeAreaId");
            return (Criteria) this;
        }

        public Criteria andConsigneeAreaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("consignee_area_id >=", value, "consigneeAreaId");
            return (Criteria) this;
        }

        public Criteria andConsigneeAreaIdLessThan(Integer value) {
            addCriterion("consignee_area_id <", value, "consigneeAreaId");
            return (Criteria) this;
        }

        public Criteria andConsigneeAreaIdLessThanOrEqualTo(Integer value) {
            addCriterion("consignee_area_id <=", value, "consigneeAreaId");
            return (Criteria) this;
        }

        public Criteria andConsigneeAreaIdIn(List<Integer> values) {
            addCriterion("consignee_area_id in", values, "consigneeAreaId");
            return (Criteria) this;
        }

        public Criteria andConsigneeAreaIdNotIn(List<Integer> values) {
            addCriterion("consignee_area_id not in", values, "consigneeAreaId");
            return (Criteria) this;
        }

        public Criteria andConsigneeAreaIdBetween(Integer value1, Integer value2) {
            addCriterion("consignee_area_id between", value1, value2, "consigneeAreaId");
            return (Criteria) this;
        }

        public Criteria andConsigneeAreaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("consignee_area_id not between", value1, value2, "consigneeAreaId");
            return (Criteria) this;
        }

        public Criteria andDeliverAreaIdIsNull() {
            addCriterion("deliver_area_id is null");
            return (Criteria) this;
        }

        public Criteria andDeliverAreaIdIsNotNull() {
            addCriterion("deliver_area_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverAreaIdEqualTo(Integer value) {
            addCriterion("deliver_area_id =", value, "deliverAreaId");
            return (Criteria) this;
        }

        public Criteria andDeliverAreaIdNotEqualTo(Integer value) {
            addCriterion("deliver_area_id <>", value, "deliverAreaId");
            return (Criteria) this;
        }

        public Criteria andDeliverAreaIdGreaterThan(Integer value) {
            addCriterion("deliver_area_id >", value, "deliverAreaId");
            return (Criteria) this;
        }

        public Criteria andDeliverAreaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("deliver_area_id >=", value, "deliverAreaId");
            return (Criteria) this;
        }

        public Criteria andDeliverAreaIdLessThan(Integer value) {
            addCriterion("deliver_area_id <", value, "deliverAreaId");
            return (Criteria) this;
        }

        public Criteria andDeliverAreaIdLessThanOrEqualTo(Integer value) {
            addCriterion("deliver_area_id <=", value, "deliverAreaId");
            return (Criteria) this;
        }

        public Criteria andDeliverAreaIdIn(List<Integer> values) {
            addCriterion("deliver_area_id in", values, "deliverAreaId");
            return (Criteria) this;
        }

        public Criteria andDeliverAreaIdNotIn(List<Integer> values) {
            addCriterion("deliver_area_id not in", values, "deliverAreaId");
            return (Criteria) this;
        }

        public Criteria andDeliverAreaIdBetween(Integer value1, Integer value2) {
            addCriterion("deliver_area_id between", value1, value2, "deliverAreaId");
            return (Criteria) this;
        }

        public Criteria andDeliverAreaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("deliver_area_id not between", value1, value2, "deliverAreaId");
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

        public Criteria andDeliveryEndIsNull() {
            addCriterion("delivery_end is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryEndIsNotNull() {
            addCriterion("delivery_end is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryEndEqualTo(Date value) {
            addCriterion("delivery_end =", value, "deliveryEnd");
            return (Criteria) this;
        }

        public Criteria andDeliveryEndNotEqualTo(Date value) {
            addCriterion("delivery_end <>", value, "deliveryEnd");
            return (Criteria) this;
        }

        public Criteria andDeliveryEndGreaterThan(Date value) {
            addCriterion("delivery_end >", value, "deliveryEnd");
            return (Criteria) this;
        }

        public Criteria andDeliveryEndGreaterThanOrEqualTo(Date value) {
            addCriterion("delivery_end >=", value, "deliveryEnd");
            return (Criteria) this;
        }

        public Criteria andDeliveryEndLessThan(Date value) {
            addCriterion("delivery_end <", value, "deliveryEnd");
            return (Criteria) this;
        }

        public Criteria andDeliveryEndLessThanOrEqualTo(Date value) {
            addCriterion("delivery_end <=", value, "deliveryEnd");
            return (Criteria) this;
        }

        public Criteria andDeliveryEndIn(List<Date> values) {
            addCriterion("delivery_end in", values, "deliveryEnd");
            return (Criteria) this;
        }

        public Criteria andDeliveryEndNotIn(List<Date> values) {
            addCriterion("delivery_end not in", values, "deliveryEnd");
            return (Criteria) this;
        }

        public Criteria andDeliveryEndBetween(Date value1, Date value2) {
            addCriterion("delivery_end between", value1, value2, "deliveryEnd");
            return (Criteria) this;
        }

        public Criteria andDeliveryEndNotBetween(Date value1, Date value2) {
            addCriterion("delivery_end not between", value1, value2, "deliveryEnd");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture1IsNull() {
            addCriterion("goods_picture_1 is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture1IsNotNull() {
            addCriterion("goods_picture_1 is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture1EqualTo(String value) {
            addCriterion("goods_picture_1 =", value, "goodsPicture1");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture1NotEqualTo(String value) {
            addCriterion("goods_picture_1 <>", value, "goodsPicture1");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture1GreaterThan(String value) {
            addCriterion("goods_picture_1 >", value, "goodsPicture1");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture1GreaterThanOrEqualTo(String value) {
            addCriterion("goods_picture_1 >=", value, "goodsPicture1");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture1LessThan(String value) {
            addCriterion("goods_picture_1 <", value, "goodsPicture1");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture1LessThanOrEqualTo(String value) {
            addCriterion("goods_picture_1 <=", value, "goodsPicture1");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture1Like(String value) {
            addCriterion("goods_picture_1 like", value, "goodsPicture1");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture1NotLike(String value) {
            addCriterion("goods_picture_1 not like", value, "goodsPicture1");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture1In(List<String> values) {
            addCriterion("goods_picture_1 in", values, "goodsPicture1");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture1NotIn(List<String> values) {
            addCriterion("goods_picture_1 not in", values, "goodsPicture1");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture1Between(String value1, String value2) {
            addCriterion("goods_picture_1 between", value1, value2, "goodsPicture1");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture1NotBetween(String value1, String value2) {
            addCriterion("goods_picture_1 not between", value1, value2, "goodsPicture1");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture2IsNull() {
            addCriterion("goods_picture_2 is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture2IsNotNull() {
            addCriterion("goods_picture_2 is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture2EqualTo(String value) {
            addCriterion("goods_picture_2 =", value, "goodsPicture2");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture2NotEqualTo(String value) {
            addCriterion("goods_picture_2 <>", value, "goodsPicture2");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture2GreaterThan(String value) {
            addCriterion("goods_picture_2 >", value, "goodsPicture2");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture2GreaterThanOrEqualTo(String value) {
            addCriterion("goods_picture_2 >=", value, "goodsPicture2");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture2LessThan(String value) {
            addCriterion("goods_picture_2 <", value, "goodsPicture2");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture2LessThanOrEqualTo(String value) {
            addCriterion("goods_picture_2 <=", value, "goodsPicture2");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture2Like(String value) {
            addCriterion("goods_picture_2 like", value, "goodsPicture2");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture2NotLike(String value) {
            addCriterion("goods_picture_2 not like", value, "goodsPicture2");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture2In(List<String> values) {
            addCriterion("goods_picture_2 in", values, "goodsPicture2");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture2NotIn(List<String> values) {
            addCriterion("goods_picture_2 not in", values, "goodsPicture2");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture2Between(String value1, String value2) {
            addCriterion("goods_picture_2 between", value1, value2, "goodsPicture2");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture2NotBetween(String value1, String value2) {
            addCriterion("goods_picture_2 not between", value1, value2, "goodsPicture2");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture3IsNull() {
            addCriterion("goods_picture_3 is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture3IsNotNull() {
            addCriterion("goods_picture_3 is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture3EqualTo(String value) {
            addCriterion("goods_picture_3 =", value, "goodsPicture3");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture3NotEqualTo(String value) {
            addCriterion("goods_picture_3 <>", value, "goodsPicture3");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture3GreaterThan(String value) {
            addCriterion("goods_picture_3 >", value, "goodsPicture3");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture3GreaterThanOrEqualTo(String value) {
            addCriterion("goods_picture_3 >=", value, "goodsPicture3");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture3LessThan(String value) {
            addCriterion("goods_picture_3 <", value, "goodsPicture3");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture3LessThanOrEqualTo(String value) {
            addCriterion("goods_picture_3 <=", value, "goodsPicture3");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture3Like(String value) {
            addCriterion("goods_picture_3 like", value, "goodsPicture3");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture3NotLike(String value) {
            addCriterion("goods_picture_3 not like", value, "goodsPicture3");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture3In(List<String> values) {
            addCriterion("goods_picture_3 in", values, "goodsPicture3");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture3NotIn(List<String> values) {
            addCriterion("goods_picture_3 not in", values, "goodsPicture3");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture3Between(String value1, String value2) {
            addCriterion("goods_picture_3 between", value1, value2, "goodsPicture3");
            return (Criteria) this;
        }

        public Criteria andGoodsPicture3NotBetween(String value1, String value2) {
            addCriterion("goods_picture_3 not between", value1, value2, "goodsPicture3");
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
package cn.deliver.domain;

import java.util.ArrayList;
import java.util.List;

public class DriverInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DriverInfoExample() {
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

        public Criteria andUiidIsNull() {
            addCriterion("uiid is null");
            return (Criteria) this;
        }

        public Criteria andUiidIsNotNull() {
            addCriterion("uiid is not null");
            return (Criteria) this;
        }

        public Criteria andUiidEqualTo(Integer value) {
            addCriterion("uiid =", value, "uiid");
            return (Criteria) this;
        }

        public Criteria andUiidNotEqualTo(Integer value) {
            addCriterion("uiid <>", value, "uiid");
            return (Criteria) this;
        }

        public Criteria andUiidGreaterThan(Integer value) {
            addCriterion("uiid >", value, "uiid");
            return (Criteria) this;
        }

        public Criteria andUiidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uiid >=", value, "uiid");
            return (Criteria) this;
        }

        public Criteria andUiidLessThan(Integer value) {
            addCriterion("uiid <", value, "uiid");
            return (Criteria) this;
        }

        public Criteria andUiidLessThanOrEqualTo(Integer value) {
            addCriterion("uiid <=", value, "uiid");
            return (Criteria) this;
        }

        public Criteria andUiidIn(List<Integer> values) {
            addCriterion("uiid in", values, "uiid");
            return (Criteria) this;
        }

        public Criteria andUiidNotIn(List<Integer> values) {
            addCriterion("uiid not in", values, "uiid");
            return (Criteria) this;
        }

        public Criteria andUiidBetween(Integer value1, Integer value2) {
            addCriterion("uiid between", value1, value2, "uiid");
            return (Criteria) this;
        }

        public Criteria andUiidNotBetween(Integer value1, Integer value2) {
            addCriterion("uiid not between", value1, value2, "uiid");
            return (Criteria) this;
        }

        public Criteria andCarTypeIsNull() {
            addCriterion("car_type is null");
            return (Criteria) this;
        }

        public Criteria andCarTypeIsNotNull() {
            addCriterion("car_type is not null");
            return (Criteria) this;
        }

        public Criteria andCarTypeEqualTo(String value) {
            addCriterion("car_type =", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotEqualTo(String value) {
            addCriterion("car_type <>", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeGreaterThan(String value) {
            addCriterion("car_type >", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeGreaterThanOrEqualTo(String value) {
            addCriterion("car_type >=", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeLessThan(String value) {
            addCriterion("car_type <", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeLessThanOrEqualTo(String value) {
            addCriterion("car_type <=", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeLike(String value) {
            addCriterion("car_type like", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotLike(String value) {
            addCriterion("car_type not like", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeIn(List<String> values) {
            addCriterion("car_type in", values, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotIn(List<String> values) {
            addCriterion("car_type not in", values, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeBetween(String value1, String value2) {
            addCriterion("car_type between", value1, value2, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotBetween(String value1, String value2) {
            addCriterion("car_type not between", value1, value2, "carType");
            return (Criteria) this;
        }

        public Criteria andDrivingLicencePictureIsNull() {
            addCriterion("driving_licence_picture is null");
            return (Criteria) this;
        }

        public Criteria andDrivingLicencePictureIsNotNull() {
            addCriterion("driving_licence_picture is not null");
            return (Criteria) this;
        }

        public Criteria andDrivingLicencePictureEqualTo(String value) {
            addCriterion("driving_licence_picture =", value, "drivingLicencePicture");
            return (Criteria) this;
        }

        public Criteria andDrivingLicencePictureNotEqualTo(String value) {
            addCriterion("driving_licence_picture <>", value, "drivingLicencePicture");
            return (Criteria) this;
        }

        public Criteria andDrivingLicencePictureGreaterThan(String value) {
            addCriterion("driving_licence_picture >", value, "drivingLicencePicture");
            return (Criteria) this;
        }

        public Criteria andDrivingLicencePictureGreaterThanOrEqualTo(String value) {
            addCriterion("driving_licence_picture >=", value, "drivingLicencePicture");
            return (Criteria) this;
        }

        public Criteria andDrivingLicencePictureLessThan(String value) {
            addCriterion("driving_licence_picture <", value, "drivingLicencePicture");
            return (Criteria) this;
        }

        public Criteria andDrivingLicencePictureLessThanOrEqualTo(String value) {
            addCriterion("driving_licence_picture <=", value, "drivingLicencePicture");
            return (Criteria) this;
        }

        public Criteria andDrivingLicencePictureLike(String value) {
            addCriterion("driving_licence_picture like", value, "drivingLicencePicture");
            return (Criteria) this;
        }

        public Criteria andDrivingLicencePictureNotLike(String value) {
            addCriterion("driving_licence_picture not like", value, "drivingLicencePicture");
            return (Criteria) this;
        }

        public Criteria andDrivingLicencePictureIn(List<String> values) {
            addCriterion("driving_licence_picture in", values, "drivingLicencePicture");
            return (Criteria) this;
        }

        public Criteria andDrivingLicencePictureNotIn(List<String> values) {
            addCriterion("driving_licence_picture not in", values, "drivingLicencePicture");
            return (Criteria) this;
        }

        public Criteria andDrivingLicencePictureBetween(String value1, String value2) {
            addCriterion("driving_licence_picture between", value1, value2, "drivingLicencePicture");
            return (Criteria) this;
        }

        public Criteria andDrivingLicencePictureNotBetween(String value1, String value2) {
            addCriterion("driving_licence_picture not between", value1, value2, "drivingLicencePicture");
            return (Criteria) this;
        }

        public Criteria andCarPictureIsNull() {
            addCriterion("car_picture is null");
            return (Criteria) this;
        }

        public Criteria andCarPictureIsNotNull() {
            addCriterion("car_picture is not null");
            return (Criteria) this;
        }

        public Criteria andCarPictureEqualTo(String value) {
            addCriterion("car_picture =", value, "carPicture");
            return (Criteria) this;
        }

        public Criteria andCarPictureNotEqualTo(String value) {
            addCriterion("car_picture <>", value, "carPicture");
            return (Criteria) this;
        }

        public Criteria andCarPictureGreaterThan(String value) {
            addCriterion("car_picture >", value, "carPicture");
            return (Criteria) this;
        }

        public Criteria andCarPictureGreaterThanOrEqualTo(String value) {
            addCriterion("car_picture >=", value, "carPicture");
            return (Criteria) this;
        }

        public Criteria andCarPictureLessThan(String value) {
            addCriterion("car_picture <", value, "carPicture");
            return (Criteria) this;
        }

        public Criteria andCarPictureLessThanOrEqualTo(String value) {
            addCriterion("car_picture <=", value, "carPicture");
            return (Criteria) this;
        }

        public Criteria andCarPictureLike(String value) {
            addCriterion("car_picture like", value, "carPicture");
            return (Criteria) this;
        }

        public Criteria andCarPictureNotLike(String value) {
            addCriterion("car_picture not like", value, "carPicture");
            return (Criteria) this;
        }

        public Criteria andCarPictureIn(List<String> values) {
            addCriterion("car_picture in", values, "carPicture");
            return (Criteria) this;
        }

        public Criteria andCarPictureNotIn(List<String> values) {
            addCriterion("car_picture not in", values, "carPicture");
            return (Criteria) this;
        }

        public Criteria andCarPictureBetween(String value1, String value2) {
            addCriterion("car_picture between", value1, value2, "carPicture");
            return (Criteria) this;
        }

        public Criteria andCarPictureNotBetween(String value1, String value2) {
            addCriterion("car_picture not between", value1, value2, "carPicture");
            return (Criteria) this;
        }

        public Criteria andRunningRouteIsNull() {
            addCriterion("running_route is null");
            return (Criteria) this;
        }

        public Criteria andRunningRouteIsNotNull() {
            addCriterion("running_route is not null");
            return (Criteria) this;
        }

        public Criteria andRunningRouteEqualTo(String value) {
            addCriterion("running_route =", value, "runningRoute");
            return (Criteria) this;
        }

        public Criteria andRunningRouteNotEqualTo(String value) {
            addCriterion("running_route <>", value, "runningRoute");
            return (Criteria) this;
        }

        public Criteria andRunningRouteGreaterThan(String value) {
            addCriterion("running_route >", value, "runningRoute");
            return (Criteria) this;
        }

        public Criteria andRunningRouteGreaterThanOrEqualTo(String value) {
            addCriterion("running_route >=", value, "runningRoute");
            return (Criteria) this;
        }

        public Criteria andRunningRouteLessThan(String value) {
            addCriterion("running_route <", value, "runningRoute");
            return (Criteria) this;
        }

        public Criteria andRunningRouteLessThanOrEqualTo(String value) {
            addCriterion("running_route <=", value, "runningRoute");
            return (Criteria) this;
        }

        public Criteria andRunningRouteLike(String value) {
            addCriterion("running_route like", value, "runningRoute");
            return (Criteria) this;
        }

        public Criteria andRunningRouteNotLike(String value) {
            addCriterion("running_route not like", value, "runningRoute");
            return (Criteria) this;
        }

        public Criteria andRunningRouteIn(List<String> values) {
            addCriterion("running_route in", values, "runningRoute");
            return (Criteria) this;
        }

        public Criteria andRunningRouteNotIn(List<String> values) {
            addCriterion("running_route not in", values, "runningRoute");
            return (Criteria) this;
        }

        public Criteria andRunningRouteBetween(String value1, String value2) {
            addCriterion("running_route between", value1, value2, "runningRoute");
            return (Criteria) this;
        }

        public Criteria andRunningRouteNotBetween(String value1, String value2) {
            addCriterion("running_route not between", value1, value2, "runningRoute");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIsNull() {
            addCriterion("order_number is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIsNotNull() {
            addCriterion("order_number is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumberEqualTo(Integer value) {
            addCriterion("order_number =", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotEqualTo(Integer value) {
            addCriterion("order_number <>", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberGreaterThan(Integer value) {
            addCriterion("order_number >", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_number >=", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberLessThan(Integer value) {
            addCriterion("order_number <", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberLessThanOrEqualTo(Integer value) {
            addCriterion("order_number <=", value, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberIn(List<Integer> values) {
            addCriterion("order_number in", values, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotIn(List<Integer> values) {
            addCriterion("order_number not in", values, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberBetween(Integer value1, Integer value2) {
            addCriterion("order_number between", value1, value2, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andOrderNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("order_number not between", value1, value2, "orderNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberIsNull() {
            addCriterion("car_number is null");
            return (Criteria) this;
        }

        public Criteria andCarNumberIsNotNull() {
            addCriterion("car_number is not null");
            return (Criteria) this;
        }

        public Criteria andCarNumberEqualTo(String value) {
            addCriterion("car_number =", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotEqualTo(String value) {
            addCriterion("car_number <>", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberGreaterThan(String value) {
            addCriterion("car_number >", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberGreaterThanOrEqualTo(String value) {
            addCriterion("car_number >=", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberLessThan(String value) {
            addCriterion("car_number <", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberLessThanOrEqualTo(String value) {
            addCriterion("car_number <=", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberLike(String value) {
            addCriterion("car_number like", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotLike(String value) {
            addCriterion("car_number not like", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberIn(List<String> values) {
            addCriterion("car_number in", values, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotIn(List<String> values) {
            addCriterion("car_number not in", values, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberBetween(String value1, String value2) {
            addCriterion("car_number between", value1, value2, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotBetween(String value1, String value2) {
            addCriterion("car_number not between", value1, value2, "carNumber");
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
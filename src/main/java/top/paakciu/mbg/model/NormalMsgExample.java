package top.paakciu.mbg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NormalMsgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NormalMsgExample() {
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

        public Criteria andNmIdIsNull() {
            addCriterion("NM_id is null");
            return (Criteria) this;
        }

        public Criteria andNmIdIsNotNull() {
            addCriterion("NM_id is not null");
            return (Criteria) this;
        }

        public Criteria andNmIdEqualTo(Long value) {
            addCriterion("NM_id =", value, "nmId");
            return (Criteria) this;
        }

        public Criteria andNmIdNotEqualTo(Long value) {
            addCriterion("NM_id <>", value, "nmId");
            return (Criteria) this;
        }

        public Criteria andNmIdGreaterThan(Long value) {
            addCriterion("NM_id >", value, "nmId");
            return (Criteria) this;
        }

        public Criteria andNmIdGreaterThanOrEqualTo(Long value) {
            addCriterion("NM_id >=", value, "nmId");
            return (Criteria) this;
        }

        public Criteria andNmIdLessThan(Long value) {
            addCriterion("NM_id <", value, "nmId");
            return (Criteria) this;
        }

        public Criteria andNmIdLessThanOrEqualTo(Long value) {
            addCriterion("NM_id <=", value, "nmId");
            return (Criteria) this;
        }

        public Criteria andNmIdIn(List<Long> values) {
            addCriterion("NM_id in", values, "nmId");
            return (Criteria) this;
        }

        public Criteria andNmIdNotIn(List<Long> values) {
            addCriterion("NM_id not in", values, "nmId");
            return (Criteria) this;
        }

        public Criteria andNmIdBetween(Long value1, Long value2) {
            addCriterion("NM_id between", value1, value2, "nmId");
            return (Criteria) this;
        }

        public Criteria andNmIdNotBetween(Long value1, Long value2) {
            addCriterion("NM_id not between", value1, value2, "nmId");
            return (Criteria) this;
        }

        public Criteria andNmFromidIsNull() {
            addCriterion("NM_fromid is null");
            return (Criteria) this;
        }

        public Criteria andNmFromidIsNotNull() {
            addCriterion("NM_fromid is not null");
            return (Criteria) this;
        }

        public Criteria andNmFromidEqualTo(Long value) {
            addCriterion("NM_fromid =", value, "nmFromid");
            return (Criteria) this;
        }

        public Criteria andNmFromidNotEqualTo(Long value) {
            addCriterion("NM_fromid <>", value, "nmFromid");
            return (Criteria) this;
        }

        public Criteria andNmFromidGreaterThan(Long value) {
            addCriterion("NM_fromid >", value, "nmFromid");
            return (Criteria) this;
        }

        public Criteria andNmFromidGreaterThanOrEqualTo(Long value) {
            addCriterion("NM_fromid >=", value, "nmFromid");
            return (Criteria) this;
        }

        public Criteria andNmFromidLessThan(Long value) {
            addCriterion("NM_fromid <", value, "nmFromid");
            return (Criteria) this;
        }

        public Criteria andNmFromidLessThanOrEqualTo(Long value) {
            addCriterion("NM_fromid <=", value, "nmFromid");
            return (Criteria) this;
        }

        public Criteria andNmFromidIn(List<Long> values) {
            addCriterion("NM_fromid in", values, "nmFromid");
            return (Criteria) this;
        }

        public Criteria andNmFromidNotIn(List<Long> values) {
            addCriterion("NM_fromid not in", values, "nmFromid");
            return (Criteria) this;
        }

        public Criteria andNmFromidBetween(Long value1, Long value2) {
            addCriterion("NM_fromid between", value1, value2, "nmFromid");
            return (Criteria) this;
        }

        public Criteria andNmFromidNotBetween(Long value1, Long value2) {
            addCriterion("NM_fromid not between", value1, value2, "nmFromid");
            return (Criteria) this;
        }

        public Criteria andNmToidIsNull() {
            addCriterion("NM_toid is null");
            return (Criteria) this;
        }

        public Criteria andNmToidIsNotNull() {
            addCriterion("NM_toid is not null");
            return (Criteria) this;
        }

        public Criteria andNmToidEqualTo(Long value) {
            addCriterion("NM_toid =", value, "nmToid");
            return (Criteria) this;
        }

        public Criteria andNmToidNotEqualTo(Long value) {
            addCriterion("NM_toid <>", value, "nmToid");
            return (Criteria) this;
        }

        public Criteria andNmToidGreaterThan(Long value) {
            addCriterion("NM_toid >", value, "nmToid");
            return (Criteria) this;
        }

        public Criteria andNmToidGreaterThanOrEqualTo(Long value) {
            addCriterion("NM_toid >=", value, "nmToid");
            return (Criteria) this;
        }

        public Criteria andNmToidLessThan(Long value) {
            addCriterion("NM_toid <", value, "nmToid");
            return (Criteria) this;
        }

        public Criteria andNmToidLessThanOrEqualTo(Long value) {
            addCriterion("NM_toid <=", value, "nmToid");
            return (Criteria) this;
        }

        public Criteria andNmToidIn(List<Long> values) {
            addCriterion("NM_toid in", values, "nmToid");
            return (Criteria) this;
        }

        public Criteria andNmToidNotIn(List<Long> values) {
            addCriterion("NM_toid not in", values, "nmToid");
            return (Criteria) this;
        }

        public Criteria andNmToidBetween(Long value1, Long value2) {
            addCriterion("NM_toid between", value1, value2, "nmToid");
            return (Criteria) this;
        }

        public Criteria andNmToidNotBetween(Long value1, Long value2) {
            addCriterion("NM_toid not between", value1, value2, "nmToid");
            return (Criteria) this;
        }

        public Criteria andNmTimeIsNull() {
            addCriterion("NM_time is null");
            return (Criteria) this;
        }

        public Criteria andNmTimeIsNotNull() {
            addCriterion("NM_time is not null");
            return (Criteria) this;
        }

        public Criteria andNmTimeEqualTo(Date value) {
            addCriterion("NM_time =", value, "nmTime");
            return (Criteria) this;
        }

        public Criteria andNmTimeNotEqualTo(Date value) {
            addCriterion("NM_time <>", value, "nmTime");
            return (Criteria) this;
        }

        public Criteria andNmTimeGreaterThan(Date value) {
            addCriterion("NM_time >", value, "nmTime");
            return (Criteria) this;
        }

        public Criteria andNmTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("NM_time >=", value, "nmTime");
            return (Criteria) this;
        }

        public Criteria andNmTimeLessThan(Date value) {
            addCriterion("NM_time <", value, "nmTime");
            return (Criteria) this;
        }

        public Criteria andNmTimeLessThanOrEqualTo(Date value) {
            addCriterion("NM_time <=", value, "nmTime");
            return (Criteria) this;
        }

        public Criteria andNmTimeIn(List<Date> values) {
            addCriterion("NM_time in", values, "nmTime");
            return (Criteria) this;
        }

        public Criteria andNmTimeNotIn(List<Date> values) {
            addCriterion("NM_time not in", values, "nmTime");
            return (Criteria) this;
        }

        public Criteria andNmTimeBetween(Date value1, Date value2) {
            addCriterion("NM_time between", value1, value2, "nmTime");
            return (Criteria) this;
        }

        public Criteria andNmTimeNotBetween(Date value1, Date value2) {
            addCriterion("NM_time not between", value1, value2, "nmTime");
            return (Criteria) this;
        }

        public Criteria andNmStateIsNull() {
            addCriterion("NM_state is null");
            return (Criteria) this;
        }

        public Criteria andNmStateIsNotNull() {
            addCriterion("NM_state is not null");
            return (Criteria) this;
        }

        public Criteria andNmStateEqualTo(Byte value) {
            addCriterion("NM_state =", value, "nmState");
            return (Criteria) this;
        }

        public Criteria andNmStateNotEqualTo(Byte value) {
            addCriterion("NM_state <>", value, "nmState");
            return (Criteria) this;
        }

        public Criteria andNmStateGreaterThan(Byte value) {
            addCriterion("NM_state >", value, "nmState");
            return (Criteria) this;
        }

        public Criteria andNmStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("NM_state >=", value, "nmState");
            return (Criteria) this;
        }

        public Criteria andNmStateLessThan(Byte value) {
            addCriterion("NM_state <", value, "nmState");
            return (Criteria) this;
        }

        public Criteria andNmStateLessThanOrEqualTo(Byte value) {
            addCriterion("NM_state <=", value, "nmState");
            return (Criteria) this;
        }

        public Criteria andNmStateIn(List<Byte> values) {
            addCriterion("NM_state in", values, "nmState");
            return (Criteria) this;
        }

        public Criteria andNmStateNotIn(List<Byte> values) {
            addCriterion("NM_state not in", values, "nmState");
            return (Criteria) this;
        }

        public Criteria andNmStateBetween(Byte value1, Byte value2) {
            addCriterion("NM_state between", value1, value2, "nmState");
            return (Criteria) this;
        }

        public Criteria andNmStateNotBetween(Byte value1, Byte value2) {
            addCriterion("NM_state not between", value1, value2, "nmState");
            return (Criteria) this;
        }

        public Criteria andNmTypeIsNull() {
            addCriterion("NM_type is null");
            return (Criteria) this;
        }

        public Criteria andNmTypeIsNotNull() {
            addCriterion("NM_type is not null");
            return (Criteria) this;
        }

        public Criteria andNmTypeEqualTo(Byte value) {
            addCriterion("NM_type =", value, "nmType");
            return (Criteria) this;
        }

        public Criteria andNmTypeNotEqualTo(Byte value) {
            addCriterion("NM_type <>", value, "nmType");
            return (Criteria) this;
        }

        public Criteria andNmTypeGreaterThan(Byte value) {
            addCriterion("NM_type >", value, "nmType");
            return (Criteria) this;
        }

        public Criteria andNmTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("NM_type >=", value, "nmType");
            return (Criteria) this;
        }

        public Criteria andNmTypeLessThan(Byte value) {
            addCriterion("NM_type <", value, "nmType");
            return (Criteria) this;
        }

        public Criteria andNmTypeLessThanOrEqualTo(Byte value) {
            addCriterion("NM_type <=", value, "nmType");
            return (Criteria) this;
        }

        public Criteria andNmTypeIn(List<Byte> values) {
            addCriterion("NM_type in", values, "nmType");
            return (Criteria) this;
        }

        public Criteria andNmTypeNotIn(List<Byte> values) {
            addCriterion("NM_type not in", values, "nmType");
            return (Criteria) this;
        }

        public Criteria andNmTypeBetween(Byte value1, Byte value2) {
            addCriterion("NM_type between", value1, value2, "nmType");
            return (Criteria) this;
        }

        public Criteria andNmTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("NM_type not between", value1, value2, "nmType");
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
package top.paakciu.mbg.model;

import java.util.ArrayList;
import java.util.List;

public class NormalMsgOfflineExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NormalMsgOfflineExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMsgidIsNull() {
            addCriterion("msgid is null");
            return (Criteria) this;
        }

        public Criteria andMsgidIsNotNull() {
            addCriterion("msgid is not null");
            return (Criteria) this;
        }

        public Criteria andMsgidEqualTo(Long value) {
            addCriterion("msgid =", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidNotEqualTo(Long value) {
            addCriterion("msgid <>", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidGreaterThan(Long value) {
            addCriterion("msgid >", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidGreaterThanOrEqualTo(Long value) {
            addCriterion("msgid >=", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidLessThan(Long value) {
            addCriterion("msgid <", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidLessThanOrEqualTo(Long value) {
            addCriterion("msgid <=", value, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidIn(List<Long> values) {
            addCriterion("msgid in", values, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidNotIn(List<Long> values) {
            addCriterion("msgid not in", values, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidBetween(Long value1, Long value2) {
            addCriterion("msgid between", value1, value2, "msgid");
            return (Criteria) this;
        }

        public Criteria andMsgidNotBetween(Long value1, Long value2) {
            addCriterion("msgid not between", value1, value2, "msgid");
            return (Criteria) this;
        }

        public Criteria andNmoToidIsNull() {
            addCriterion("NMO_toid is null");
            return (Criteria) this;
        }

        public Criteria andNmoToidIsNotNull() {
            addCriterion("NMO_toid is not null");
            return (Criteria) this;
        }

        public Criteria andNmoToidEqualTo(Long value) {
            addCriterion("NMO_toid =", value, "nmoToid");
            return (Criteria) this;
        }

        public Criteria andNmoToidNotEqualTo(Long value) {
            addCriterion("NMO_toid <>", value, "nmoToid");
            return (Criteria) this;
        }

        public Criteria andNmoToidGreaterThan(Long value) {
            addCriterion("NMO_toid >", value, "nmoToid");
            return (Criteria) this;
        }

        public Criteria andNmoToidGreaterThanOrEqualTo(Long value) {
            addCriterion("NMO_toid >=", value, "nmoToid");
            return (Criteria) this;
        }

        public Criteria andNmoToidLessThan(Long value) {
            addCriterion("NMO_toid <", value, "nmoToid");
            return (Criteria) this;
        }

        public Criteria andNmoToidLessThanOrEqualTo(Long value) {
            addCriterion("NMO_toid <=", value, "nmoToid");
            return (Criteria) this;
        }

        public Criteria andNmoToidIn(List<Long> values) {
            addCriterion("NMO_toid in", values, "nmoToid");
            return (Criteria) this;
        }

        public Criteria andNmoToidNotIn(List<Long> values) {
            addCriterion("NMO_toid not in", values, "nmoToid");
            return (Criteria) this;
        }

        public Criteria andNmoToidBetween(Long value1, Long value2) {
            addCriterion("NMO_toid between", value1, value2, "nmoToid");
            return (Criteria) this;
        }

        public Criteria andNmoToidNotBetween(Long value1, Long value2) {
            addCriterion("NMO_toid not between", value1, value2, "nmoToid");
            return (Criteria) this;
        }

        public Criteria andNmoStateIsNull() {
            addCriterion("NMO_state is null");
            return (Criteria) this;
        }

        public Criteria andNmoStateIsNotNull() {
            addCriterion("NMO_state is not null");
            return (Criteria) this;
        }

        public Criteria andNmoStateEqualTo(Byte value) {
            addCriterion("NMO_state =", value, "nmoState");
            return (Criteria) this;
        }

        public Criteria andNmoStateNotEqualTo(Byte value) {
            addCriterion("NMO_state <>", value, "nmoState");
            return (Criteria) this;
        }

        public Criteria andNmoStateGreaterThan(Byte value) {
            addCriterion("NMO_state >", value, "nmoState");
            return (Criteria) this;
        }

        public Criteria andNmoStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("NMO_state >=", value, "nmoState");
            return (Criteria) this;
        }

        public Criteria andNmoStateLessThan(Byte value) {
            addCriterion("NMO_state <", value, "nmoState");
            return (Criteria) this;
        }

        public Criteria andNmoStateLessThanOrEqualTo(Byte value) {
            addCriterion("NMO_state <=", value, "nmoState");
            return (Criteria) this;
        }

        public Criteria andNmoStateIn(List<Byte> values) {
            addCriterion("NMO_state in", values, "nmoState");
            return (Criteria) this;
        }

        public Criteria andNmoStateNotIn(List<Byte> values) {
            addCriterion("NMO_state not in", values, "nmoState");
            return (Criteria) this;
        }

        public Criteria andNmoStateBetween(Byte value1, Byte value2) {
            addCriterion("NMO_state between", value1, value2, "nmoState");
            return (Criteria) this;
        }

        public Criteria andNmoStateNotBetween(Byte value1, Byte value2) {
            addCriterion("NMO_state not between", value1, value2, "nmoState");
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
package com.cusc.cuscai.entity.model;


import com.cusc.cuscai.entity.bo.AgentInfoBO;

import org.noodle.base.AbstractExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgentInfoExample extends AbstractExample<AgentInfoBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public AgentInfoExample() {
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

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public void setLimit(int count) {
        this.limit = String.valueOf(count);
    }

    public void setLimit(int offset, int rows) {
        this.limit = new StringBuilder().append(String.valueOf(offset)).append(",").append(String.valueOf(rows)).toString();
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

        public Criteria andAgentIdIsNull() {
            addCriterion("agent_id is null");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNotNull() {
            addCriterion("agent_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgentIdEqualTo(Integer value) {
            addCriterion("agent_id =", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotEqualTo(Integer value) {
            addCriterion("agent_id <>", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThan(Integer value) {
            addCriterion("agent_id >", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("agent_id >=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThan(Integer value) {
            addCriterion("agent_id <", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThanOrEqualTo(Integer value) {
            addCriterion("agent_id <=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdIn(List<Integer> values) {
            addCriterion("agent_id in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotIn(List<Integer> values) {
            addCriterion("agent_id not in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdBetween(Integer value1, Integer value2) {
            addCriterion("agent_id between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("agent_id not between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andAdminIdIsNull() {
            addCriterion("admin_id is null");
            return (Criteria) this;
        }

        public Criteria andAdminIdIsNotNull() {
            addCriterion("admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdminIdEqualTo(Integer value) {
            addCriterion("admin_id =", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotEqualTo(Integer value) {
            addCriterion("admin_id <>", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThan(Integer value) {
            addCriterion("admin_id >", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("admin_id >=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThan(Integer value) {
            addCriterion("admin_id <", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThanOrEqualTo(Integer value) {
            addCriterion("admin_id <=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdIn(List<Integer> values) {
            addCriterion("admin_id in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotIn(List<Integer> values) {
            addCriterion("admin_id not in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdBetween(Integer value1, Integer value2) {
            addCriterion("admin_id between", value1, value2, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotBetween(Integer value1, Integer value2) {
            addCriterion("admin_id not between", value1, value2, "adminId");
            return (Criteria) this;
        }

        public Criteria andAgentNameIsNull() {
            addCriterion("agent_name is null");
            return (Criteria) this;
        }

        public Criteria andAgentNameIsNotNull() {
            addCriterion("agent_name is not null");
            return (Criteria) this;
        }

        public Criteria andAgentNameEqualTo(String value) {
            addCriterion("agent_name =", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotEqualTo(String value) {
            addCriterion("agent_name <>", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameGreaterThan(String value) {
            addCriterion("agent_name >", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameGreaterThanOrEqualTo(String value) {
            addCriterion("agent_name >=", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLessThan(String value) {
            addCriterion("agent_name <", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLessThanOrEqualTo(String value) {
            addCriterion("agent_name <=", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLike(String value) {
            addCriterion("agent_name like", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotLike(String value) {
            addCriterion("agent_name not like", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameIn(List<String> values) {
            addCriterion("agent_name in", values, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotIn(List<String> values) {
            addCriterion("agent_name not in", values, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameBetween(String value1, String value2) {
            addCriterion("agent_name between", value1, value2, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotBetween(String value1, String value2) {
            addCriterion("agent_name not between", value1, value2, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentStatusIsNull() {
            addCriterion("agent_status is null");
            return (Criteria) this;
        }

        public Criteria andAgentStatusIsNotNull() {
            addCriterion("agent_status is not null");
            return (Criteria) this;
        }

        public Criteria andAgentStatusEqualTo(Integer value) {
            addCriterion("agent_status =", value, "agentStatus");
            return (Criteria) this;
        }

        public Criteria andAgentStatusNotEqualTo(Integer value) {
            addCriterion("agent_status <>", value, "agentStatus");
            return (Criteria) this;
        }

        public Criteria andAgentStatusGreaterThan(Integer value) {
            addCriterion("agent_status >", value, "agentStatus");
            return (Criteria) this;
        }

        public Criteria andAgentStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("agent_status >=", value, "agentStatus");
            return (Criteria) this;
        }

        public Criteria andAgentStatusLessThan(Integer value) {
            addCriterion("agent_status <", value, "agentStatus");
            return (Criteria) this;
        }

        public Criteria andAgentStatusLessThanOrEqualTo(Integer value) {
            addCriterion("agent_status <=", value, "agentStatus");
            return (Criteria) this;
        }

        public Criteria andAgentStatusIn(List<Integer> values) {
            addCriterion("agent_status in", values, "agentStatus");
            return (Criteria) this;
        }

        public Criteria andAgentStatusNotIn(List<Integer> values) {
            addCriterion("agent_status not in", values, "agentStatus");
            return (Criteria) this;
        }

        public Criteria andAgentStatusBetween(Integer value1, Integer value2) {
            addCriterion("agent_status between", value1, value2, "agentStatus");
            return (Criteria) this;
        }

        public Criteria andAgentStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("agent_status not between", value1, value2, "agentStatus");
            return (Criteria) this;
        }

        public Criteria andAgentCreateTimeIsNull() {
            addCriterion("agent_create_time is null");
            return (Criteria) this;
        }

        public Criteria andAgentCreateTimeIsNotNull() {
            addCriterion("agent_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andAgentCreateTimeEqualTo(Date value) {
            addCriterion("agent_create_time =", value, "agentCreateTime");
            return (Criteria) this;
        }

        public Criteria andAgentCreateTimeNotEqualTo(Date value) {
            addCriterion("agent_create_time <>", value, "agentCreateTime");
            return (Criteria) this;
        }

        public Criteria andAgentCreateTimeGreaterThan(Date value) {
            addCriterion("agent_create_time >", value, "agentCreateTime");
            return (Criteria) this;
        }

        public Criteria andAgentCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("agent_create_time >=", value, "agentCreateTime");
            return (Criteria) this;
        }

        public Criteria andAgentCreateTimeLessThan(Date value) {
            addCriterion("agent_create_time <", value, "agentCreateTime");
            return (Criteria) this;
        }

        public Criteria andAgentCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("agent_create_time <=", value, "agentCreateTime");
            return (Criteria) this;
        }

        public Criteria andAgentCreateTimeIn(List<Date> values) {
            addCriterion("agent_create_time in", values, "agentCreateTime");
            return (Criteria) this;
        }

        public Criteria andAgentCreateTimeNotIn(List<Date> values) {
            addCriterion("agent_create_time not in", values, "agentCreateTime");
            return (Criteria) this;
        }

        public Criteria andAgentCreateTimeBetween(Date value1, Date value2) {
            addCriterion("agent_create_time between", value1, value2, "agentCreateTime");
            return (Criteria) this;
        }

        public Criteria andAgentCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("agent_create_time not between", value1, value2, "agentCreateTime");
            return (Criteria) this;
        }

        public Criteria andAgentUpdateTimeIsNull() {
            addCriterion("agent_update_time is null");
            return (Criteria) this;
        }

        public Criteria andAgentUpdateTimeIsNotNull() {
            addCriterion("agent_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andAgentUpdateTimeEqualTo(Date value) {
            addCriterion("agent_update_time =", value, "agentUpdateTime");
            return (Criteria) this;
        }

        public Criteria andAgentUpdateTimeNotEqualTo(Date value) {
            addCriterion("agent_update_time <>", value, "agentUpdateTime");
            return (Criteria) this;
        }

        public Criteria andAgentUpdateTimeGreaterThan(Date value) {
            addCriterion("agent_update_time >", value, "agentUpdateTime");
            return (Criteria) this;
        }

        public Criteria andAgentUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("agent_update_time >=", value, "agentUpdateTime");
            return (Criteria) this;
        }

        public Criteria andAgentUpdateTimeLessThan(Date value) {
            addCriterion("agent_update_time <", value, "agentUpdateTime");
            return (Criteria) this;
        }

        public Criteria andAgentUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("agent_update_time <=", value, "agentUpdateTime");
            return (Criteria) this;
        }

        public Criteria andAgentUpdateTimeIn(List<Date> values) {
            addCriterion("agent_update_time in", values, "agentUpdateTime");
            return (Criteria) this;
        }

        public Criteria andAgentUpdateTimeNotIn(List<Date> values) {
            addCriterion("agent_update_time not in", values, "agentUpdateTime");
            return (Criteria) this;
        }

        public Criteria andAgentUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("agent_update_time between", value1, value2, "agentUpdateTime");
            return (Criteria) this;
        }

        public Criteria andAgentUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("agent_update_time not between", value1, value2, "agentUpdateTime");
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
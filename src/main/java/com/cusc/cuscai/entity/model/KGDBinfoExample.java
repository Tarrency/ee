package com.cusc.cuscai.entity.model;

import com.cusc.cuscai.entity.bo.KGDBinfoBO;
import org.noodle.base.AbstractExample;

import java.util.ArrayList;
import java.util.List;

public class KGDBinfoExample extends AbstractExample<KGDBinfoBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public KGDBinfoExample() {
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

        public Criteria andDbnameIsNull() {
            addCriterion("dbname is null");
            return (Criteria) this;
        }

        public Criteria andDbnameIsNotNull() {
            addCriterion("dbname is not null");
            return (Criteria) this;
        }

        public Criteria andDbnameEqualTo(String value) {
            addCriterion("dbname =", value, "dbname");
            return (Criteria) this;
        }

        public Criteria andDbnameNotEqualTo(String value) {
            addCriterion("dbname <>", value, "dbname");
            return (Criteria) this;
        }

        public Criteria andDbnameGreaterThan(String value) {
            addCriterion("dbname >", value, "dbname");
            return (Criteria) this;
        }

        public Criteria andDbnameGreaterThanOrEqualTo(String value) {
            addCriterion("dbname >=", value, "dbname");
            return (Criteria) this;
        }

        public Criteria andDbnameLessThan(String value) {
            addCriterion("dbname <", value, "dbname");
            return (Criteria) this;
        }

        public Criteria andDbnameLessThanOrEqualTo(String value) {
            addCriterion("dbname <=", value, "dbname");
            return (Criteria) this;
        }

        public Criteria andDbnameLike(String value) {
            addCriterion("dbname like", value, "dbname");
            return (Criteria) this;
        }

        public Criteria andDbnameNotLike(String value) {
            addCriterion("dbname not like", value, "dbname");
            return (Criteria) this;
        }

        public Criteria andDbnameIn(List<String> values) {
            addCriterion("dbname in", values, "dbname");
            return (Criteria) this;
        }

        public Criteria andDbnameNotIn(List<String> values) {
            addCriterion("dbname not in", values, "dbname");
            return (Criteria) this;
        }

        public Criteria andDbnameBetween(String value1, String value2) {
            addCriterion("dbname between", value1, value2, "dbname");
            return (Criteria) this;
        }

        public Criteria andDbnameNotBetween(String value1, String value2) {
            addCriterion("dbname not between", value1, value2, "dbname");
            return (Criteria) this;
        }

        public Criteria andEntitiesIsNull() {
            addCriterion("entities is null");
            return (Criteria) this;
        }

        public Criteria andEntitiesIsNotNull() {
            addCriterion("entities is not null");
            return (Criteria) this;
        }

        public Criteria andEntitiesEqualTo(Integer value) {
            addCriterion("entities =", value, "entities");
            return (Criteria) this;
        }

        public Criteria andEntitiesNotEqualTo(Integer value) {
            addCriterion("entities <>", value, "entities");
            return (Criteria) this;
        }

        public Criteria andEntitiesGreaterThan(Integer value) {
            addCriterion("entities >", value, "entities");
            return (Criteria) this;
        }

        public Criteria andEntitiesGreaterThanOrEqualTo(Integer value) {
            addCriterion("entities >=", value, "entities");
            return (Criteria) this;
        }

        public Criteria andEntitiesLessThan(Integer value) {
            addCriterion("entities <", value, "entities");
            return (Criteria) this;
        }

        public Criteria andEntitiesLessThanOrEqualTo(Integer value) {
            addCriterion("entities <=", value, "entities");
            return (Criteria) this;
        }

        public Criteria andEntitiesIn(List<Integer> values) {
            addCriterion("entities in", values, "entities");
            return (Criteria) this;
        }

        public Criteria andEntitiesNotIn(List<Integer> values) {
            addCriterion("entities not in", values, "entities");
            return (Criteria) this;
        }

        public Criteria andEntitiesBetween(Integer value1, Integer value2) {
            addCriterion("entities between", value1, value2, "entities");
            return (Criteria) this;
        }

        public Criteria andEntitiesNotBetween(Integer value1, Integer value2) {
            addCriterion("entities not between", value1, value2, "entities");
            return (Criteria) this;
        }

        public Criteria andRelationshipsIsNull() {
            addCriterion("relationships is null");
            return (Criteria) this;
        }

        public Criteria andRelationshipsIsNotNull() {
            addCriterion("relationships is not null");
            return (Criteria) this;
        }

        public Criteria andRelationshipsEqualTo(Integer value) {
            addCriterion("relationships =", value, "relationships");
            return (Criteria) this;
        }

        public Criteria andRelationshipsNotEqualTo(Integer value) {
            addCriterion("relationships <>", value, "relationships");
            return (Criteria) this;
        }

        public Criteria andRelationshipsGreaterThan(Integer value) {
            addCriterion("relationships >", value, "relationships");
            return (Criteria) this;
        }

        public Criteria andRelationshipsGreaterThanOrEqualTo(Integer value) {
            addCriterion("relationships >=", value, "relationships");
            return (Criteria) this;
        }

        public Criteria andRelationshipsLessThan(Integer value) {
            addCriterion("relationships <", value, "relationships");
            return (Criteria) this;
        }

        public Criteria andRelationshipsLessThanOrEqualTo(Integer value) {
            addCriterion("relationships <=", value, "relationships");
            return (Criteria) this;
        }

        public Criteria andRelationshipsIn(List<Integer> values) {
            addCriterion("relationships in", values, "relationships");
            return (Criteria) this;
        }

        public Criteria andRelationshipsNotIn(List<Integer> values) {
            addCriterion("relationships not in", values, "relationships");
            return (Criteria) this;
        }

        public Criteria andRelationshipsBetween(Integer value1, Integer value2) {
            addCriterion("relationships between", value1, value2, "relationships");
            return (Criteria) this;
        }

        public Criteria andRelationshipsNotBetween(Integer value1, Integer value2) {
            addCriterion("relationships not between", value1, value2, "relationships");
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
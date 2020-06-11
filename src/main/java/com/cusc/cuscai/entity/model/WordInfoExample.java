package com.cusc.cuscai.entity.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cusc.cuscai.entity.bo.WordInfoBO;
import org.noodle.base.AbstractExample;

public class WordInfoExample extends AbstractExample<WordInfoBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public WordInfoExample() {
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

        public Criteria andWordIdIsNull() {
            addCriterion("word_id is null");
            return (Criteria) this;
        }

        public Criteria andWordIdIsNotNull() {
            addCriterion("word_id is not null");
            return (Criteria) this;
        }

        public Criteria andWordIdEqualTo(Integer value) {
            addCriterion("word_id =", value, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdNotEqualTo(Integer value) {
            addCriterion("word_id <>", value, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdGreaterThan(Integer value) {
            addCriterion("word_id >", value, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("word_id >=", value, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdLessThan(Integer value) {
            addCriterion("word_id <", value, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdLessThanOrEqualTo(Integer value) {
            addCriterion("word_id <=", value, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdIn(List<Integer> values) {
            addCriterion("word_id in", values, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdNotIn(List<Integer> values) {
            addCriterion("word_id not in", values, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdBetween(Integer value1, Integer value2) {
            addCriterion("word_id between", value1, value2, "wordId");
            return (Criteria) this;
        }

        public Criteria andWordIdNotBetween(Integer value1, Integer value2) {
            addCriterion("word_id not between", value1, value2, "wordId");
            return (Criteria) this;
        }

        public Criteria andVocabularyIdIsNull() {
            addCriterion("vocabulary_id is null");
            return (Criteria) this;
        }

        public Criteria andVocabularyIdIsNotNull() {
            addCriterion("vocabulary_id is not null");
            return (Criteria) this;
        }

        public Criteria andVocabularyIdEqualTo(Integer value) {
            addCriterion("vocabulary_id =", value, "vocabularyId");
            return (Criteria) this;
        }

        public Criteria andVocabularyIdNotEqualTo(Integer value) {
            addCriterion("vocabulary_id <>", value, "vocabularyId");
            return (Criteria) this;
        }

        public Criteria andVocabularyIdGreaterThan(Integer value) {
            addCriterion("vocabulary_id >", value, "vocabularyId");
            return (Criteria) this;
        }

        public Criteria andVocabularyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("vocabulary_id >=", value, "vocabularyId");
            return (Criteria) this;
        }

        public Criteria andVocabularyIdLessThan(Integer value) {
            addCriterion("vocabulary_id <", value, "vocabularyId");
            return (Criteria) this;
        }

        public Criteria andVocabularyIdLessThanOrEqualTo(Integer value) {
            addCriterion("vocabulary_id <=", value, "vocabularyId");
            return (Criteria) this;
        }

        public Criteria andVocabularyIdIn(List<Integer> values) {
            addCriterion("vocabulary_id in", values, "vocabularyId");
            return (Criteria) this;
        }

        public Criteria andVocabularyIdNotIn(List<Integer> values) {
            addCriterion("vocabulary_id not in", values, "vocabularyId");
            return (Criteria) this;
        }

        public Criteria andVocabularyIdBetween(Integer value1, Integer value2) {
            addCriterion("vocabulary_id between", value1, value2, "vocabularyId");
            return (Criteria) this;
        }

        public Criteria andVocabularyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("vocabulary_id not between", value1, value2, "vocabularyId");
            return (Criteria) this;
        }

        public Criteria andWordIsNull() {
            addCriterion("word is null");
            return (Criteria) this;
        }

        public Criteria andWordIsNotNull() {
            addCriterion("word is not null");
            return (Criteria) this;
        }

        public Criteria andWordEqualTo(String value) {
            addCriterion("word =", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotEqualTo(String value) {
            addCriterion("word <>", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordGreaterThan(String value) {
            addCriterion("word >", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordGreaterThanOrEqualTo(String value) {
            addCriterion("word >=", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordLessThan(String value) {
            addCriterion("word <", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordLessThanOrEqualTo(String value) {
            addCriterion("word <=", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordLike(String value) {
            addCriterion("word like", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotLike(String value) {
            addCriterion("word not like", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordIn(List<String> values) {
            addCriterion("word in", values, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotIn(List<String> values) {
            addCriterion("word not in", values, "word");
            return (Criteria) this;
        }

        public Criteria andWordBetween(String value1, String value2) {
            addCriterion("word between", value1, value2, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotBetween(String value1, String value2) {
            addCriterion("word not between", value1, value2, "word");
            return (Criteria) this;
        }

        public Criteria andWordUpdateTimeIsNull() {
            addCriterion("word_update_time is null");
            return (Criteria) this;
        }

        public Criteria andWordUpdateTimeIsNotNull() {
            addCriterion("word_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andWordUpdateTimeEqualTo(Date value) {
            addCriterion("word_update_time =", value, "wordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andWordUpdateTimeNotEqualTo(Date value) {
            addCriterion("word_update_time <>", value, "wordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andWordUpdateTimeGreaterThan(Date value) {
            addCriterion("word_update_time >", value, "wordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andWordUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("word_update_time >=", value, "wordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andWordUpdateTimeLessThan(Date value) {
            addCriterion("word_update_time <", value, "wordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andWordUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("word_update_time <=", value, "wordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andWordUpdateTimeIn(List<Date> values) {
            addCriterion("word_update_time in", values, "wordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andWordUpdateTimeNotIn(List<Date> values) {
            addCriterion("word_update_time not in", values, "wordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andWordUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("word_update_time between", value1, value2, "wordUpdateTime");
            return (Criteria) this;
        }

        public Criteria andWordUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("word_update_time not between", value1, value2, "wordUpdateTime");
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
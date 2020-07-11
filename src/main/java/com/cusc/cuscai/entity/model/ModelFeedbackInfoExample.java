package com.cusc.cuscai.entity.model;

import com.cusc.cuscai.entity.bo.ModelFeedbackInfoBO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.noodle.base.AbstractExample;

public class ModelFeedbackInfoExample extends AbstractExample<ModelFeedbackInfoBO> {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected String limit;

    public ModelFeedbackInfoExample() {
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

        public Criteria andFeedbackIdIsNull() {
            addCriterion("feedback_id is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdIsNotNull() {
            addCriterion("feedback_id is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdEqualTo(Long value) {
            addCriterion("feedback_id =", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdNotEqualTo(Long value) {
            addCriterion("feedback_id <>", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdGreaterThan(Long value) {
            addCriterion("feedback_id >", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdGreaterThanOrEqualTo(Long value) {
            addCriterion("feedback_id >=", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdLessThan(Long value) {
            addCriterion("feedback_id <", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdLessThanOrEqualTo(Long value) {
            addCriterion("feedback_id <=", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdIn(List<Long> values) {
            addCriterion("feedback_id in", values, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdNotIn(List<Long> values) {
            addCriterion("feedback_id not in", values, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdBetween(Long value1, Long value2) {
            addCriterion("feedback_id between", value1, value2, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdNotBetween(Long value1, Long value2) {
            addCriterion("feedback_id not between", value1, value2, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andModelTypeIsNull() {
            addCriterion("model_type is null");
            return (Criteria) this;
        }

        public Criteria andModelTypeIsNotNull() {
            addCriterion("model_type is not null");
            return (Criteria) this;
        }

        public Criteria andModelTypeEqualTo(Integer value) {
            addCriterion("model_type =", value, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeNotEqualTo(Integer value) {
            addCriterion("model_type <>", value, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeGreaterThan(Integer value) {
            addCriterion("model_type >", value, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("model_type >=", value, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeLessThan(Integer value) {
            addCriterion("model_type <", value, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeLessThanOrEqualTo(Integer value) {
            addCriterion("model_type <=", value, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeIn(List<Integer> values) {
            addCriterion("model_type in", values, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeNotIn(List<Integer> values) {
            addCriterion("model_type not in", values, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeBetween(Integer value1, Integer value2) {
            addCriterion("model_type between", value1, value2, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("model_type not between", value1, value2, "modelType");
            return (Criteria) this;
        }

        public Criteria andMessageIsNull() {
            addCriterion("message is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsNotNull() {
            addCriterion("message is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEqualTo(String value) {
            addCriterion("message =", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualTo(String value) {
            addCriterion("message <>", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThan(String value) {
            addCriterion("message >", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualTo(String value) {
            addCriterion("message >=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThan(String value) {
            addCriterion("message <", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualTo(String value) {
            addCriterion("message <=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLike(String value) {
            addCriterion("message like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotLike(String value) {
            addCriterion("message not like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageIn(List<String> values) {
            addCriterion("message in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotIn(List<String> values) {
            addCriterion("message not in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageBetween(String value1, String value2) {
            addCriterion("message between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotBetween(String value1, String value2) {
            addCriterion("message not between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNull() {
            addCriterion("answer is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNotNull() {
            addCriterion("answer is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerEqualTo(String value) {
            addCriterion("answer =", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotEqualTo(String value) {
            addCriterion("answer <>", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThan(String value) {
            addCriterion("answer >", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("answer >=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThan(String value) {
            addCriterion("answer <", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThanOrEqualTo(String value) {
            addCriterion("answer <=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLike(String value) {
            addCriterion("answer like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotLike(String value) {
            addCriterion("answer not like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerIn(List<String> values) {
            addCriterion("answer in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotIn(List<String> values) {
            addCriterion("answer not in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerBetween(String value1, String value2) {
            addCriterion("answer between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotBetween(String value1, String value2) {
            addCriterion("answer not between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andPredictIsNull() {
            addCriterion("predict is null");
            return (Criteria) this;
        }

        public Criteria andPredictIsNotNull() {
            addCriterion("predict is not null");
            return (Criteria) this;
        }

        public Criteria andPredictEqualTo(Integer value) {
            addCriterion("predict =", value, "predict");
            return (Criteria) this;
        }

        public Criteria andPredictNotEqualTo(Integer value) {
            addCriterion("predict <>", value, "predict");
            return (Criteria) this;
        }

        public Criteria andPredictGreaterThan(Integer value) {
            addCriterion("predict >", value, "predict");
            return (Criteria) this;
        }

        public Criteria andPredictGreaterThanOrEqualTo(Integer value) {
            addCriterion("predict >=", value, "predict");
            return (Criteria) this;
        }

        public Criteria andPredictLessThan(Integer value) {
            addCriterion("predict <", value, "predict");
            return (Criteria) this;
        }

        public Criteria andPredictLessThanOrEqualTo(Integer value) {
            addCriterion("predict <=", value, "predict");
            return (Criteria) this;
        }

        public Criteria andPredictIn(List<Integer> values) {
            addCriterion("predict in", values, "predict");
            return (Criteria) this;
        }

        public Criteria andPredictNotIn(List<Integer> values) {
            addCriterion("predict not in", values, "predict");
            return (Criteria) this;
        }

        public Criteria andPredictBetween(Integer value1, Integer value2) {
            addCriterion("predict between", value1, value2, "predict");
            return (Criteria) this;
        }

        public Criteria andPredictNotBetween(Integer value1, Integer value2) {
            addCriterion("predict not between", value1, value2, "predict");
            return (Criteria) this;
        }

        public Criteria andFeedbackUpdateTimeIsNull() {
            addCriterion("feedback_update_time is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackUpdateTimeIsNotNull() {
            addCriterion("feedback_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackUpdateTimeEqualTo(Date value) {
            addCriterion("feedback_update_time =", value, "feedbackUpdateTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackUpdateTimeNotEqualTo(Date value) {
            addCriterion("feedback_update_time <>", value, "feedbackUpdateTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackUpdateTimeGreaterThan(Date value) {
            addCriterion("feedback_update_time >", value, "feedbackUpdateTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("feedback_update_time >=", value, "feedbackUpdateTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackUpdateTimeLessThan(Date value) {
            addCriterion("feedback_update_time <", value, "feedbackUpdateTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("feedback_update_time <=", value, "feedbackUpdateTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackUpdateTimeIn(List<Date> values) {
            addCriterion("feedback_update_time in", values, "feedbackUpdateTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackUpdateTimeNotIn(List<Date> values) {
            addCriterion("feedback_update_time not in", values, "feedbackUpdateTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("feedback_update_time between", value1, value2, "feedbackUpdateTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("feedback_update_time not between", value1, value2, "feedbackUpdateTime");
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
package com.github.muguliebe.zfwk.core.config

import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata

class DataSourceAutoConfigExcludedCondition : Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        val beanFactory = context.beanFactory ?: return true
        return !beanFactory.containsBeanDefinition("dataSource")
    }
}

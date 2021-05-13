package cn.inusha.hibernate.redisson.spring.boot.starter.config;

import cn.inusha.hibernate.redisson.spring.boot.starter.factory.SpringJndiRedissonRegionFactory;
import cn.inusha.hibernate.redisson.spring.boot.starter.factory.SpringRedissonRegionFactory;
import cn.inusha.hibernate.redisson.spring.boot.starter.processor.HibernateRedisCacheBeanPostProcessor;
import cn.inusha.hibernate.redisson.spring.boot.starter.properties.FactoryTypeProperties;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.PriorityOrdered;

/**
 * @author InuYasha
 */
@Configurable
public class SpringHibernateCacheAutoConfiguration implements PriorityOrdered {
    public static final String PROPERTIES_PREFIX = "cn.inusha.hibernate.cache";

    @Bean
    @ConditionalOnMissingBean
    public SpringRedissonRegionFactory springRedissonRegionFactory() {
        return new SpringRedissonRegionFactory();
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringJndiRedissonRegionFactory springJndiRedissonRegionFactory() {
        return new SpringJndiRedissonRegionFactory();
    }

    @Bean
    @ConfigurationProperties(prefix = PROPERTIES_PREFIX)
    public FactoryTypeProperties factoryTypeProperties() {
        return new FactoryTypeProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public HibernateRedisCacheBeanPostProcessor
    hibernateRedisCacheBeanPostProcessor(FactoryTypeProperties factoryTypeProperties) {
        return new HibernateRedisCacheBeanPostProcessor(factoryTypeProperties);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}

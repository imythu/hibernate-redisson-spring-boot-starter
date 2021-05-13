package cn.inusha.hibernate.redisson.spring.boot.starter.processor;

import cn.inusha.hibernate.redisson.spring.boot.starter.factory.SpringJndiRedissonRegionFactory;
import cn.inusha.hibernate.redisson.spring.boot.starter.factory.SpringRedissonRegionFactory;
import cn.inusha.hibernate.redisson.spring.boot.starter.properties.FactoryTypeProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.lang.Nullable;

/**
 * @author InuYasha
 */
public class HibernateRedisCacheBeanPostProcessor implements BeanPostProcessor {
    private static final String HIBERNATE_SECOND_LEVEL_CACHE_KEY = "hibernate.cache.region.factory_class";
    private final FactoryTypeProperties factoryTypeProperties;

    public HibernateRedisCacheBeanPostProcessor(FactoryTypeProperties factoryTypeProperties) {
        this.factoryTypeProperties = factoryTypeProperties;
    }

    @Override
    public Object postProcessAfterInitialization(
            @Nullable Object bean,
            @Nullable String beanName) throws BeansException {
        if (bean instanceof JpaProperties) {
            JpaProperties jpaProperties = (JpaProperties) bean;
            jpaProperties.getProperties()
                    .computeIfAbsent(HIBERNATE_SECOND_LEVEL_CACHE_KEY,
                            k -> factoryTypeProperties.getFactoryClass().equals(FactoryTypeProperties.FactoryClass.jndiFactory)
                                    ? SpringJndiRedissonRegionFactory.class.getName() : SpringRedissonRegionFactory.class.getName()
                    );
        }
        return bean;
    }
}

package cn.inusha.hibernate.redisson.spring.boot.starter.factory;

import org.redisson.api.RedissonClient;
import org.redisson.hibernate.RedissonRegionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import java.util.Map;

/**
 * @author InuYasha
 */
public class SpringRedissonRegionFactory extends RedissonRegionFactory implements BeanFactoryAware {
    private static BeanFactory beanFactory;

    @Override
    protected RedissonClient createRedissonClient(Map properties) {
        return beanFactory.getBean(RedissonClient.class);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringRedissonRegionFactory.beanFactory = beanFactory;
    }
}

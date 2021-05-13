package cn.inusha.hibernate.redisson.spring.boot.starter.factory;

import org.redisson.api.RedissonClient;
import org.redisson.hibernate.JndiRedissonRegionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * @author InuYasha
 */
public class SpringJndiRedissonRegionFactory extends JndiRedissonRegionFactory implements BeanFactoryPostProcessor {
    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    protected RedissonClient createRedissonClient(Map properties) {
        return beanFactory.getBean(RedissonClient.class);
    }

    @Override
    public void postProcessBeanFactory(
            @Nullable ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        SpringJndiRedissonRegionFactory.beanFactory = configurableListableBeanFactory;
    }
}

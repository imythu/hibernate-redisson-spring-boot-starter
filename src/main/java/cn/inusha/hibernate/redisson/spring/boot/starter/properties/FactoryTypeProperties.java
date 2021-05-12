package cn.inusha.hibernate.redisson.spring.boot.starter.properties;

import cn.inusha.hibernate.redisson.spring.boot.starter.factory.SpringJndiRedissonRegionFactory;
import cn.inusha.hibernate.redisson.spring.boot.starter.factory.SpringRedissonRegionFactory;

/**
 * @author InuYasha
 */
public class FactoryTypeProperties {
    private FactoryClass factoryClass = FactoryClass.defaultFactory;

    public FactoryClass getFactoryClass() {
        return factoryClass;
    }

    public void setFactoryClass(FactoryClass factoryClass) {
        this.factoryClass = factoryClass;
    }

    @Override
    public String toString() {
        return "FactoryTypeProperties{" +
                "factoryClass=" + factoryClass +
                '}';
    }

    public enum FactoryClass {
        /**
         * {@link SpringRedissonRegionFactory}
         */
        defaultFactory,
        /**
         * {@link SpringJndiRedissonRegionFactory}
         */
        jndiFactory
    }
}

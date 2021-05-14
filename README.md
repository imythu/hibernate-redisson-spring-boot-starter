# hibernate-redisson-spring-boot-starter
### 介绍
使用 `Redisson` 作为 hibernate redis 二级缓存提供器，`redisson-hibernate-53(hibernate-version)` 无法使用 spring boot 配置文件创建的 `org.redisson.api.RedissonClient` bean，
而`redisson-spring-boot-starter` 为 spring boot redis 自动配置的一个实现，且会创建 `org.redisson.api.RedissonClient` bean，hibernate-redisson-spring-boot-starter 的作用就是结合这两个库
的功能从而实现 hibernate redis 二级缓存提供器的 redis client 配置可以使用 spring boot redis 配置，更加的灵活且不需要单独为 hibernate 二级缓存再提供一份 redis 配置。
### 原理
`org.springframework.beans.factory.config.BeanFactoryPostProcessor`调用后才会去创建 bean，
创建 `entityManagerFactory` 这个 bean 的时候会创建二级缓存需要的缓存提供器，
这时候通过 beanFactory 去取 `org.redisson.api.RedissonClient` bean，这个时候
`org.redisson.api.RedissonClient` bean 还没创建，这个时候就会创建这个 bean，创建完后返回。
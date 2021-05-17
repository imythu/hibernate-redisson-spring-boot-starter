# hibernate-redisson-spring-boot-starter

### 介绍

使用 `Redisson` 作为 hibernate redis 二级缓存提供器，`redisson-hibernate-53(hibernate-version)` 无法使用 spring boot
配置文件创建的 `org.redisson.api.RedissonClient` bean， 而`redisson-spring-boot-starter` 为 spring boot redis
自动配置的一个实现，且会创建 `org.redisson.api.RedissonClient` bean，hibernate-redisson-spring-boot-starter 的作用就是结合这两个库 的功能从而实现
hibernate redis 二级缓存提供器的 redis client 配置可以使用 spring boot redis 配置，更加的灵活且不需要单独为 hibernate 二级缓存再提供一份 redis 配置。

### 原理

`org.springframework.beans.factory.config.BeanFactoryPostProcessor`调用后才会去创建 bean， 创建 `entityManagerFactory` 这个 bean
的时候会创建二级缓存需要的缓存提供器， 这时候通过 beanFactory 去取 `org.redisson.api.RedissonClient` bean，这个时候
`org.redisson.api.RedissonClient` bean 还没创建，这个时候就会创建这个 bean，创建完后返回。

### 使用

- 引入依赖即可

    ```xml
    
    <dependency>
        <groupId>cn.inusha</groupId>
        <artifactId>hibernate-redisson-spring-boot-starter</artifactId>
        <version>1.0.1</version>
    </dependency>
    ```

### 针对不同 spring boot 版本、hibernate 版本

- 根据下面的依赖关系排除掉不合适的依赖并引入合适的依赖，比如

    ```xml
    <dependencies>
        <dependency>
            <groupId>cn.inusha</groupId>
            <artifactId>hibernate-redisson-spring-boot-starter</artifactId>
            <version>1.0.1</version>
            <exclusions>
                <exclusion>
                    <artifactId>redisson-spring-data-24</artifactId>
                    <groupId>org.redisson</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <artifactId>redisson-spring-data-22</artifactId>
            <groupId>org.redisson</groupId>
            <version>${redisson.version}</version>
        </dependency>
    </dependencies>
    ```

- redisson-hibernate 依赖关系

    ```xml
    
    <dependency>
        <groupId>org.redisson</groupId>
        <!-- for Hibernate v4.x -->
        <artifactId>redisson-hibernate-4</artifactId>
        <!-- for Hibernate v5.0.x - v5.1.x -->
        <artifactId>redisson-hibernate-5</artifactId>
        <!-- for Hibernate v5.2.x -->
        <artifactId>redisson-hibernate-52</artifactId>
        <!-- for Hibernate v5.3.3+ - v5.4.x -->
        <artifactId>redisson-hibernate-53</artifactId>
        <version>3.15.5</version>
    </dependency>
    ```

- redisson-spring-boot-starter 依赖需要的 redisson-spring-data-xx 和 spring 关系

    |redisson-spring-data<br/>module name|Spring Boot<br/>version|
    |----------------------------|-------------------|
    |redisson-spring-data-16     |1.3.x              |
    |redisson-spring-data-17     |1.4.x              |
    |redisson-spring-data-18     |1.5.x              |
    |redisson-spring-data-20     |2.0.x              |
    |redisson-spring-data-21     |2.1.x              |
    |redisson-spring-data-22     |2.2.x              |
    |redisson-spring-data-23     |2.3.x              |
    |redisson-spring-data-24     |2.4.x              |
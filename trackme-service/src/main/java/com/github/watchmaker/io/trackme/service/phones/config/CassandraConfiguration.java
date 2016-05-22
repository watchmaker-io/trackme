package com.github.watchmaker.io.trackme.service.phones.config;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cassandra.config.CassandraCqlClusterFactoryBean;
import org.springframework.cassandra.config.CompressionType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(
        basePackages = "com.github.watchmaker.io.trackme.service")
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    @Value("${cassandra.keyspace}")
    private String keyspace;

    @Value("${cassandra.hosts}")
    private String hosts;

    @Value("${cassandra.port}")
    private int port;

    @Value("${cassandra.username}")
    private String username;

    @Value("${cassandra.password}")
    private String password;

    @Value("${cassandra.compression}")
    private String compression;


    @Override
    protected String getKeyspaceName() {
        return keyspace;
    }

    @Bean
    public CassandraCqlClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(hosts);
        cluster.setPort(port);
        if (Strings.isNullOrEmpty(username)) {
            cluster.setUsername(username);
            cluster.setUsername(password);
        }
        cluster.setCompressionType(CompressionType.valueOf(compression));
        cluster.setMetricsEnabled(true);

        return cluster;
    }

    @Bean
    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        return new BasicCassandraMappingContext();
    }
}

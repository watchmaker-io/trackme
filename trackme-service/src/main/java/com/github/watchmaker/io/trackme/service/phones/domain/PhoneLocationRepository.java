package com.github.watchmaker.io.trackme.service.phones.domain;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PhoneLocationRepository {
    private CassandraOperations cassandraOperations;


    @Autowired
    public PhoneLocationRepository(CassandraOperations cassandraOperations) {
        this.cassandraOperations = cassandraOperations;
    }

    public List<PhoneLocation> findByUserId(UUID userId) {
        Select s = QueryBuilder
                .select()
                .from(PhoneLocation.COLUMN_FAMILY);
        s.where(QueryBuilder.eq(PhoneLocation.USER_ID, userId));

        return cassandraOperations.select(s, PhoneLocation.class);
    }

    public void save(PhoneLocation phoneLocation) {
        cassandraOperations.insert(phoneLocation);
    }
}

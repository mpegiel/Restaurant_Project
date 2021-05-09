package org.daypilot.demo.machinescheduling.repository;


import org.daypilot.demo.machinescheduling.domain.Event;
import org.daypilot.demo.machinescheduling.domain.ResourceGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public interface ResourceGroupRepository extends CrudRepository<ResourceGroup, Long> {

    public List<ResourceGroup> findAllByOrderByOrdinal();

}
package org.daypilot.demo.machinescheduling.repository;


import org.daypilot.demo.machinescheduling.domain.Resource;
import org.daypilot.demo.machinescheduling.domain.ResourceGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
    public List<Resource> findByParent(ResourceGroup parent);
}
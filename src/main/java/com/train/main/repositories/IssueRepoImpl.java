package com.train.main.repositories;

import com.train.main.entieties.Issue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;

public class IssueRepoImpl extends SimpleJpaRepository<Issue,Long> implements IssueRepoCustom {

    private final EntityManager em;

    public IssueRepoImpl(JpaEntityInformation<Issue, ?> entityInformation, EntityManager em) {
        super(entityInformation, em);
        this.em = em;
    }


    @Override
    public List<Issue> findAllLimited(Specification<Issue> spec, Pageable pageable) {
        TypedQuery<Issue> query = getQuery(spec, pageable.getSort());
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        return query.getResultList(); // brak count, brak informacji o hasNext
    }
}

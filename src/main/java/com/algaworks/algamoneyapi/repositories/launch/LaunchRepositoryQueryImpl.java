package com.algaworks.algamoneyapi.repositories.launch;

import com.algaworks.algamoneyapi.entities.Launch;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class LaunchRepositoryQueryImpl implements LaunchRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Launch> filter(LaunchFilter filter, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Launch> criteria = builder.createQuery(Launch.class);
        Root<Launch> root = criteria.from(Launch.class);

        Predicate[] predicates = createPredicates(filter, builder, root);
        criteria.where(predicates);

        TypedQuery<Launch> query = entityManager.createQuery(criteria);

        int currentPage = pageable.getPageNumber();
        int totalRowsPerPage = pageable.getPageSize();
        int firstRowOfPage = currentPage * totalRowsPerPage;
        query.setFirstResult(firstRowOfPage);
        query.setMaxResults(totalRowsPerPage);

        return new PageImpl<>(query.getResultList(), pageable, totalRows(filter));
    }

    private Predicate[] createPredicates(LaunchFilter launchFilter, CriteriaBuilder builder, Root<Launch> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (launchFilter.getDescription() != null) {
            predicates.add(builder.like(builder.lower(root.get("description")), "%" + launchFilter.getDescription().toLowerCase() + "%"));
        }

        if (launchFilter.getStartExpirationDate() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("expirationDate"), launchFilter.getStartExpirationDate()));
        }

        if (launchFilter.getEndExpirationDate() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("expirationDate"), launchFilter.getEndExpirationDate()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private Long totalRows(LaunchFilter launchFilter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Launch> root = criteria.from(Launch.class);

        Predicate[] predicates = createPredicates(launchFilter, builder, root);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return entityManager.createQuery(criteria).getSingleResult();
    }
}

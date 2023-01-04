package com.algaworks.algamoneyapi.repositories.launch;

import com.algaworks.algamoneyapi.entities.Launch;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class LaunchRepositoryQueryImpl implements LaunchRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Launch> filter(LaunchFilter filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Launch> criteriaQuery = builder.createQuery(Launch.class);
        Root<Launch> root = criteriaQuery.from(Launch.class);

        Predicate[] predicates = createPredicates(filter, builder, root);
        criteriaQuery.where(predicates);

        TypedQuery<Launch> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
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
}

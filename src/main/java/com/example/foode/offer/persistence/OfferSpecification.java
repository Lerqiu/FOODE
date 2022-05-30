package com.example.foode.offer.persistence;

import com.example.foode.offer.presentation.OfferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OfferSpecification implements Specification<OfferDto> {

    private final OfferFilters filters;

    @Override
    public Predicate toPredicate(Root<OfferDto> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filters.getName() != null) {
            predicates.add(
                    criteriaBuilder.like(
                            root.join("product").get("name"),
                            toPattern(filters.getName()))
            );
        }

        if (filters.getCityId() != null) {
            predicates.add(
                    criteriaBuilder.equal(
                            root.get("city"),
                            filters.getCityId())
            );
        }

        if (filters.getPriceFrom() != null) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(
                            root.get("price"),
                            filters.getPriceFrom())
            );
        }

        if (filters.getPriceTo() != null) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(
                            root.get("price"),
                            filters.getPriceTo())
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private String toPattern(String text) {
        return "%" + text + "%";
    }
}

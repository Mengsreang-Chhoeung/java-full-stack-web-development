package com.ms.spring_security_jwt.infrastructure.service.implementation;

import com.ms.spring_security_jwt.infrastructure.exception.BadRequestException;
import com.ms.spring_security_jwt.infrastructure.service.UrlParamService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;
import java.util.stream.Collectors;

public class UrlParamServiceImpl implements UrlParamService {
    private final String q;
    private String internalQ;
    private Set<String> allowedQFields;
    private final String page;
    private final String size;
    private final String isPaged;
    // can use as multiple sorts like: id:desc,createdDate:desc
    private final String sort;

    public UrlParamServiceImpl(String q, String page, String size, String isPaged, String sort) {
        this.q = q;
        this.page = page;
        this.size = size;
        this.isPaged = isPaged;
        this.sort = sort;
    }

    @Override
    public Pageable getPageable() {
        // first check is paged is false then return un-page
        boolean _isPaged = Boolean.parseBoolean(this.isPaged);
        if (!_isPaged) return Pageable.unpaged();

        // then add default page and size if no values
        int _page = this.page == null ? 0 : Integer.parseInt(this.page);
        int _size = this.size == null ? 10 : Integer.parseInt(this.size);

        // prepare sort by default
        Sort _sort = this.getSort();

        return PageRequest.of(_page, _size, _sort);
    }

    @Override
    public <T> Specification<T> getSearch() {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (this.q == null && this.internalQ == null) {
                return criteriaBuilder.conjunction(); // Always true
            }

            // Convert "firstname:abc;lastname:abc" into a Map {firstname=[abc], lastname=[abc]}
            Map<String, List<String>> filters = parseSearchString(this.q);

            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, List<String>> entry : filters.entrySet()) {
                String field = entry.getKey();
                List<String> values = entry.getValue();

                // Check if the field exists in the entity before applying filter
                if (root.getModel().getAttribute(field) != null) {
                    if (values.size() > 1)
                        predicates.add(root.get(field).in(values));
                    else if (values.size() == 1)
                        predicates.add(criteriaBuilder.like(root.get(field), "%" + values.get(0) + "%"));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Map<String, List<String>> parseSearchString(String search) {
        Map<String, List<String>> searchMap = new HashMap<>();

        if (!(this.internalQ == null)) {
            String[] pairs = this.internalQ.split(";"); // Split by semicolon (;)
            for (String pair : pairs) {
                String[] keyValue = pair.split(":", 2); // Split by first colon (:)
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    searchMap.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
                }
            }
        }

        if (!(search == null)) {
            String[] pairs = search.split(";"); // Split by semicolon (;)
            for (String pair : pairs) {
                String[] keyValue = pair.split(":", 2); // Split by first colon (:)
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();

                    if (this.allowedQFields == null || this.allowedQFields.isEmpty()) {
                        searchMap.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
                    } else {
                        if (this.allowedQFields.contains(key)) {
                            searchMap.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
                        } else {
                            throw new BadRequestException("Invalid q '" + key + "' field!");
                        }
                    }
                }
            }
        }

        return searchMap;
    }

    @Override
    public void setInternalSearch(String search) {
        this.internalQ = search;
    }

    @Override
    public void setAllowedSearchFields(Set<String> fields) {
        this.allowedQFields = fields;
    }

    @Override
    public Sort getSort() {
        if (this.sort != null) {
            // sort = id:desc,name:asc
            List<Sort.Order> sortList = Arrays.stream(this.sort.split(",")).filter(it -> !it.isEmpty()).map(it -> {
                // 1. id:desc
                // 2. name:asc
                Sort.Order sortByOrder;
                String[] order = it.split(":");
                // 1. order = ["id", "desc"];
                // 2. order = ["name", "asc"];
                if (order.length > 1) {
                    String key = order[0]; // "id" // "name"
                    String value = order[1]; // "desc" // "asc"
                    if (key != null && value != null) {
                        if (value.equalsIgnoreCase("desc")) {
                            sortByOrder = Sort.Order.desc(key);
                        } else {
                            sortByOrder = Sort.Order.asc(key);
                        }
                    } else {
                        sortByOrder = null;
                    }
                } else {
                    sortByOrder = null;
                }
                return sortByOrder;
            }).collect(Collectors.toList());

            return Sort.by(sortList);
        } else {
            return Sort.unsorted();
        }
    }
}

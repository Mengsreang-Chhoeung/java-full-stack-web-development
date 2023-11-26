package com.ms.crud_api.service;

import com.ms.crud_api.exception.AlreadyExistException;
import com.ms.crud_api.exception.BadRequestException;
import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.model.entity.SkillEntity;
import com.ms.crud_api.model.request.skill.RestoreSkillRequest;
import com.ms.crud_api.model.request.skill.SkillRequest;
import com.ms.crud_api.repository.SkillRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillService {
    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public SkillEntity create(SkillRequest request) throws Exception {
        // prepare request to entity
        SkillEntity data = request.toEntity();

        // check name from request exists or not in db
        if (this.skillRepository.existsByField("name", data.getName(), true))
            throw new AlreadyExistException("Skill name already exists!");

        try {
            // save entity
            return this.skillRepository.save(data);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public SkillEntity update(Long id, SkillRequest request) throws Exception {
        // check from data from database and if it isn't exist then throw error
        SkillEntity foundData = this.findOne(id);

        // check name from request exists or not in db
        if (!Objects.equals(foundData.getName(), request.getName()))
            if (this.skillRepository.existsByField("name", request.getName(), true))
                throw new AlreadyExistException("Skill name already exists!");

        // add request data to existing data
        // #1. need give all field's value to update
        foundData.setName(request.getName());
        foundData.setDescription(request.getDescription());

        // #2. update only field that specify
        // foundData.setName(request.getName() == null ? foundData.getName() : request.getName());
        // foundData.setDescription(request.getDescription() == null ? foundData.getDescription() : request.getDescription());

        try {
            // update entity
            return this.skillRepository.save(foundData);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public Page<SkillEntity> findAll(int page, int limit, boolean isPage, String sort, boolean isTrash, Map<String, String> reqParam) throws BadRequestException {
        if (page <= 0 || limit <= 0) throw new BadRequestException("Invalid pagination!");

        List<Sort.Order> sortByList = new ArrayList<>();
        for (String item : sort.split(",")) {
            String[] srt = item.split(":");
            if (srt.length != 2)
                continue;

            String direction = srt[1].toLowerCase();
            String field = srt[0];

            sortByList.add(new Sort.Order(direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, field));
        }

        Pageable pageable;
        if (isPage) pageable = PageRequest.of(page - 1, limit, Sort.by(sortByList));
        else pageable = Pageable.unpaged();

        return this.skillRepository.findAll((Specification<SkillEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, String> entry : reqParam.entrySet()) {
                if (entry.getKey().startsWith("q_")) {
                    String qKey = entry.getKey().split("q_", 2)[1];
                    String qValue = entry.getValue() == null ? "" : entry.getValue();
                    predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get(qKey).as(String.class)), "%" + qValue.toUpperCase() + "%"));
                }
            }

            if (predicates.size() == 0)
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name").as(String.class)), "%" + "" + "%"));

            return criteriaBuilder.and(
                    isTrash ? criteriaBuilder.isNotNull(root.get("deletedAt")) : criteriaBuilder.isNull(root.get("deletedAt")),
                    criteriaBuilder.or(predicates.toArray(Predicate[]::new))
            );
        }, pageable);
    }

    public SkillEntity findOne(Long id) throws NotFoundException {
        return this.skillRepository.findById(id).orElseThrow(() -> new NotFoundException("Skill not found"));
    }

    private SkillEntity findOneWithSoftDeleted(Long id) throws NotFoundException {
        return this.skillRepository.findById(id).orElseThrow(() -> new NotFoundException("Skill not found"));
    }

    public SkillEntity restore(Long id, RestoreSkillRequest req) throws Exception {
        // get category data from db by id
        SkillEntity skill = this.findOneWithSoftDeleted(id);

        // check name from request exists or not in db
        if (this.skillRepository.existsByField("name", req.getName(), true))
            throw new AlreadyExistException("Skill name already exists!");

        // move deleted_at field to null value
        skill.setDeletedAt(null);
        skill.setName(req.getName());

        try {
            return this.skillRepository.save(skill);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public SkillEntity delete(Long id) throws Exception {
        // get category data from db by id
        SkillEntity skill = this.findOne(id);

        try {
            // if everything work well then delete
            this.skillRepository.deleteById(skill.getId());
//            skill.setDeletedAt(new Date());
//            this.skillRepository.save(skill);
        } catch (Exception ex) {
            throw new Exception(ex);
        }

        // return data
        return skill;
    }

    public boolean validateId(Long id) {
        return this.skillRepository.existsByField("id", id, true);
    }
}

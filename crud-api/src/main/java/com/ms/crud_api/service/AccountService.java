package com.ms.crud_api.service;

import com.ms.crud_api.constant.enums.CrudTypeEnum;
import com.ms.crud_api.exception.AlreadyExistException;
import com.ms.crud_api.exception.BadRequestException;
import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.model.entity.AccountEntity;
import com.ms.crud_api.model.entity.AccountHistoryEntity;
import com.ms.crud_api.model.request.account.AccountRequest;
import com.ms.crud_api.model.request.account.RechargeAccountBalanceRequest;
import com.ms.crud_api.model.request.account.RestoreAccountRequest;
import com.ms.crud_api.repository.AccountRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountHistoryService accountHistoryService;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountHistoryService accountHistoryService) {
        this.accountRepository = accountRepository;
        this.accountHistoryService = accountHistoryService;
    }


    @Transactional(propagation = Propagation.NESTED)
    public void createAccountA() {
        // save account A
        // ...

        // save account B
        this.createAccountB();
    }


    // save point B
    public void createAccountB() {
        // save account B
        // ...

        // save account C
        this.createAccountC();
    }


    // save point C
    public void createAccountC() {
        // save account C
    }

    @Transactional(timeout = 10, rollbackFor = {Exception.class}, noRollbackFor = {AlreadyExistException.class})
    public AccountEntity create(AccountRequest request) throws Exception {
        // casting request to an entity
        AccountEntity data = request.toEntity();
        AccountHistoryEntity accountHistory = new AccountHistoryEntity();
        accountHistory.setName(data.getName());
        accountHistory.setType(CrudTypeEnum.CREATE);
        accountHistory.setBalance(data.getBalance());
        accountHistory.setAccount(data);

        // check name of account exist or not from db
        if (this.accountRepository.existsByNameAndDeletedAtIsNull(data.getName()))
            throw new AlreadyExistException("Account name already exist!");

        try {
            // save account
            AccountEntity account = this.accountRepository.save(data);
            // save account history
            this.accountHistoryService.create(accountHistory);

            // save data
            // update data

            return account;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @Transactional
    public AccountEntity update(Long id, AccountRequest request) throws Exception {
        // check from data from database and if it isn't exist then throw error
        AccountEntity foundData = this.findOne(id);

        // check name from request exists or not in db
        if (!Objects.equals(foundData.getName(), request.getName()))
            if (this.accountRepository.existsByNameAndDeletedAtIsNull(request.getName()))
                throw new AlreadyExistException("Account name already exists!");

        // add request data to existing data
        foundData.setName(request.getName());
        foundData.setBalance(request.getBalance());

        try {
            // update entity
            return this.accountRepository.save(foundData);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public Page<AccountEntity> findAll(int page, int limit, boolean isPage, String sort, boolean isTrash, Map<String, String> reqParam) throws BadRequestException {
        if (page <= 0 || limit <= 0) throw new BadRequestException("Invalid pagination!");

        List<Sort.Order> sortByList = new ArrayList<>();
        for (String item : sort.split(",")) {
            String[] srt = item.split(":");
            if (srt.length != 2) continue;

            String direction = srt[1].toLowerCase();
            String field = srt[0];

            sortByList.add(new Sort.Order(direction.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, field));
        }

        Pageable pageable;
        if (isPage) pageable = PageRequest.of(page - 1, limit, Sort.by(sortByList));
        else pageable = Pageable.unpaged();

        return this.accountRepository.findAll((Specification<AccountEntity>) (root, query, criteriaBuilder) -> {
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

            return criteriaBuilder.and(isTrash ? criteriaBuilder.isNotNull(root.get("deletedAt")) : criteriaBuilder.isNull(root.get("deletedAt")), criteriaBuilder.or(predicates.toArray(Predicate[]::new)));
        }, pageable);
    }

    public AccountEntity findOne(Long id) throws NotFoundException {
        return this.accountRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() -> new NotFoundException("Account not found"));
    }

    private AccountEntity findOneWithSoftDeleted(Long id) throws NotFoundException {
        return this.accountRepository.findById(id).orElseThrow(() -> new NotFoundException("Account not found"));
    }

    @Transactional
    public AccountEntity restore(Long id, RestoreAccountRequest req) throws Exception {
        // get account data from db by id
        AccountEntity account = this.findOneWithSoftDeleted(id);

        // check name from request exists or not in db
        if (this.accountRepository.existsByNameAndDeletedAtIsNull(req.getName()))
            throw new AlreadyExistException("Account name already exists!");

        // move deleted_at field to null value
        account.setDeletedAt(null);
        account.setName(req.getName());

        try {
            return this.accountRepository.save(account);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @Transactional
    public AccountEntity delete(Long id) throws Exception {
        // get account data from db by id
        AccountEntity account = this.findOne(id);

        try {
            account.setDeletedAt(new Date());
            this.accountRepository.save(account);
        } catch (Exception ex) {
            throw new Exception(ex);
        }

        // return data
        return account;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public List<AccountEntity> getReports() throws InterruptedException {
        AccountEntity found1 = this.accountRepository.findById(90L).get();
        System.out.println("Hi balance found1: " + found1.getBalance());
        Thread.sleep(5000);
        System.out.println("After 5s....");

        AccountEntity found2 = this.accountRepository.findById(90L).get();
        System.out.println("Hi balance found2: " + found2.getBalance());

        return new ArrayList<>();
    }

    @Transactional
    public AccountEntity rechargeBalance(Long id, RechargeAccountBalanceRequest request) throws Exception {
        // getting specific account
        AccountEntity found = this.findOneWithSoftDeleted(id);

        // then let's recharge balance
        found.setBalance(found.getBalance() + request.getBalance());

        try {
            return this.accountRepository.save(found);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}

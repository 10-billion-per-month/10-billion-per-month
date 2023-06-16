package com.example.dev.repository;

import com.example.dev.entity.Owner;
import com.example.dev.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findAllByOwner(Owner owner);

    /**
     * 사장님 아이디로 가게 목록 조회
     * @param ownerId
     * @param pageRequest
     * @return
     */
    Page<Store> findAllByOwner_OwnerId(Long ownerId, Pageable pageRequest);

    /**
     * 사장님 가게 총 개수
     * @param ownerId
     * @return
     */
    Integer countStoreByOwner_OwnerId(Long ownerId);

    /**
     * owner and storeId 로 Store 조회
     * @param owner
     * @param storeId
     * @return
     */
    Optional<Store> findStoreByOwnerAndStoreId(Owner owner, Long storeId);

}

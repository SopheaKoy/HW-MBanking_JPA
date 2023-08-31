package co.phea.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {

    Optional<User> findByUuid(String uuid);

    boolean existsByUuid(String uuid);

    List<User> findByIsDeletedFalse();

    @Modifying
    @Query("UPDATE User u SET u.isDeleted = ?1 WHERE u.uuid =?2")
    void updateIsDeleteByUuid(Boolean isDeleted , String uuid);

    @Modifying
    @Query("DELETE from User u where u.uuid =:uuid")
    void deleteByUuid(@Param("uuid") String uuid);


}

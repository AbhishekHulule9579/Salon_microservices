package com.infinite.repository;

import com.infinite.modal.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalonRepository extends JpaRepository<Salon, Long> {

    // Correct method name to match entity field
    Salon findByOwnerId(Long ownerId);

    @Query("""
           SELECT s FROM Salon s
           WHERE lower(s.city) LIKE lower(concat('%', :keyword, '%'))
              OR lower(s.name) LIKE lower(concat('%', :keyword, '%'))
              OR lower(s.address) LIKE lower(concat('%', :keyword, '%'))
           """)
    List<Salon> searchSalons(@Param("keyword") String keyword);
}

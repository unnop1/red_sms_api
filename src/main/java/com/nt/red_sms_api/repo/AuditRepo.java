package com.nt.red_sms_api.repo;

    import org.springframework.data.jpa.repository.JpaRepository;
    import com.nt.red_sms_api.entity.AuditLogEntity;
    
    public interface AuditRepo extends JpaRepository<AuditLogEntity,Long> {
        
        // @Query(value = "SELECT * FROM audit ",
        //              nativeQuery = true)
        // public List<AuditEntity> findAll(Integer offset, Integer limit);
    
        // @Query(value = "SELECT  COUNT(*) FROM audit ", nativeQuery = true)
        // public Integer getTotalCount(Integer offset, Integer limit);
    
    }
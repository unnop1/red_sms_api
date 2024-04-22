package com.nt.red_sms_api.repo.view.smscondition;



import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.red_sms_api.entity.ConfigConditionsEntity;
import com.nt.red_sms_api.entity.view.smscondition.ListSmsCondition;

public interface ListSmsConditionRepo extends JpaRepository<ConfigConditionsEntity,Long> {
    
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start >= ?1 OR cf_con.date_start IS NULL) "+
                    "AND (cf_con.date_end <= ?2 OR cf_con.date_end IS NULL) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListSmsCondition(Timestamp startTime,
                                            Timestamp endTime,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start >= ?1 OR cf_con.date_start IS NULL) "+
                    "AND (cf_con.date_end <= ?2 OR cf_con.date_end IS NULL) ",
                    nativeQuery = true)
    public Integer getListSmsConditionTotalCount(Timestamp startTime, Timestamp endTime);

    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start >= :start_time OR cf_con.date_start IS NULL) "+
                    "AND (cf_con.date_end <= :end_time OR cf_con.date_end IS NULL) "+
                    "AND ( cf_con.ordertype like %:search% OR cf_con.conditions_id like %:search% OR cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListSmsConditionAllLike(@Param(value = "start_time") Timestamp startTime,
                                            @Param(value = "end_time")Timestamp endTime,
                                            @Param(value = "search") String search,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start >= :start_time OR cf_con.date_start IS NULL) "+
                    "AND (cf_con.date_end <= :end_time OR cf_con.date_end IS NULL) "+
                    "AND ( cf_con.ordertype like %:search% OR cf_con.conditions_id like %:search% OR cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public Integer getListSmsConditionAllLikeTotalCount(@Param(value = "start_time") Timestamp startTime,
                                                        @Param(value = "end_time")Timestamp endTime,
                                                        @Param(value = "search") String search);

    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start >= :start_time OR cf_con.date_start IS NULL) "+
                    "AND (cf_con.date_end <= :end_time OR cf_con.date_end IS NULL) "+
                    "AND ( :search_field like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListSmsConditionLike(@Param(value = "start_time") Timestamp startTime,
                                            @Param(value = "end_time")Timestamp endTime,
                                            @Param(value = "search_field") String searchField,
                                            @Param(value = "search") String search,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start >= :start_time OR cf_con.date_start IS NULL) "+
                    "AND (cf_con.date_end <= :end_time OR cf_con.date_end IS NULL) "+
                    "AND ( :search_field like %:search% ) ",
                    nativeQuery = true)
    public Integer getListSmsConditionLikeTotalCount(@Param(value = "start_time") Timestamp startTime,
                                                        @Param(value = "end_time")Timestamp endTime,
                                                        @Param(value = "search_field") String searchField,
                                                        @Param(value = "search") String search);
}


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
    /* WITH TIME */
    @Query(value =  """
                    SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete
                    FROM config_conditions cf_con 
                    WHERE (cf_con.date_start >= ?1) 
                    AND (cf_con.date_end <= ?2) 
                    """
                    ,nativeQuery = true)
    public List<ListSmsCondition> ListSmsConditionWithTime(Timestamp startTime,
                                            Timestamp endTime,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start >= ?1) "+
                    "AND (cf_con.date_end <= ?2) ",
                    nativeQuery = true)
    public Integer getListSmsConditionWithTimeTotalCount(Timestamp startTime, Timestamp endTime);

    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start >= :start_time) "+
                    "AND (cf_con.date_end <= :end_time) "+
                    "AND ( cf_con.ordertype like %:search% OR cf_con.conditions_id like %:search% OR cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListSmsConditionWithTimeAllLike(@Param(value = "start_time") Timestamp startTime,
                                            @Param(value = "end_time")Timestamp endTime,
                                            @Param(value = "search") String search,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start >= :start_time) "+
                    "AND (cf_con.date_end <= :end_time) "+
                    "AND ( cf_con.ordertype like %:search% OR cf_con.conditions_id like %:search% OR cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public Integer getListSmsConditionWithTimeAllLikeTotalCount(@Param(value = "start_time") Timestamp startTime,
                                                        @Param(value = "end_time")Timestamp endTime,
                                                        @Param(value = "search") String search);

    // ordertype
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start >= :start_time) "+
                    "AND (cf_con.date_end <= :end_time) "+
                    "AND ( cf_con.ordertype like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListOrderTypeWithTimeLike(@Param(value = "start_time") Timestamp startTime,
                                            @Param(value = "end_time")Timestamp endTime,
                                            @Param(value = "search") String search,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start >= :start_time) "+
                    "AND (cf_con.date_end <= :end_time) "+
                    "AND ( cf_con.ordertype like %:search% ) ",
                    nativeQuery = true)
    public Integer getListOrderTypeLikeWithTimeTotalCount(@Param(value = "start_time") Timestamp startTime,
                                                        @Param(value = "end_time")Timestamp endTime,
                                                        @Param(value = "search") String search);


    // conditions_id
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start >= :start_time) "+
                    "AND (cf_con.date_end <= :end_time) "+
                    "AND ( cf_con.conditions_id like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListConditionIdWithTimeLike(@Param(value = "start_time") Timestamp startTime,
                                            @Param(value = "end_time")Timestamp endTime,
                                            @Param(value = "search") String search,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start >= :start_time) "+
                    "AND (cf_con.date_end <= :end_time) "+
                    "AND ( cf_con.conditions_id like %:search% ) ",
                    nativeQuery = true)
    public Integer getListConditionIdWithTimeLikeTotalCount(@Param(value = "start_time") Timestamp startTime,
                                                        @Param(value = "end_time")Timestamp endTime,
                                                        @Param(value = "search") String search);


    // message
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start >= :start_time) "+
                    "AND (cf_con.date_end <= :end_time) "+
                    "AND ( cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListMessageWithTimeLike(@Param(value = "start_time") Timestamp startTime,
                                            @Param(value = "end_time")Timestamp endTime,
                                            @Param(value = "search") String search,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start >= :start_time) "+
                    "AND (cf_con.date_end <= :end_time) "+
                    "AND ( cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public Integer getListMessageWithTimeLikeTotalCount(@Param(value = "start_time") Timestamp startTime,
                                                        @Param(value = "end_time")Timestamp endTime,
                                                        @Param(value = "search") String search);


    /* WITHOUT TIME */
    @Query(value =  """
                    SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete
                    FROM config_conditions cf_con 
                    """
                    ,nativeQuery = true)
    public List<ListSmsCondition> ListSmsCondition(Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM config_conditions cf_con ",
                    nativeQuery = true)
    public Integer getListSmsConditionTotalCount();

    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete "+
                    "FROM config_conditions cf_con "+
                    "WHERE ( cf_con.ordertype like %:search% OR cf_con.conditions_id like %:search% OR cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListSmsConditionAllLike(
                    @Param(value = "search") String search,
                    Pageable pageable
    );

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE ( cf_con.ordertype like %:search% OR cf_con.conditions_id like %:search% OR cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public Integer getListSmsConditionAllLikeTotalCount(@Param(value = "search") String search);

    // ordertype
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete "+
                    "FROM config_conditions cf_con "+
                    "WHERE ( cf_con.ordertype like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListOrderTypeLike(
                                            @Param(value = "search") String search,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE ( cf_con.ordertype like %:search% ) ",
                    nativeQuery = true)
    public Integer getListOrderTypeLikeTotalCount(@Param(value = "search") String search);


    // conditions_id
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete "+
                    "FROM config_conditions cf_con "+
                    "WHERE ( cf_con.conditions_id like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListConditionIdLike(
                                            @Param(value = "search") String search,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE ( cf_con.conditions_id like %:search% ) ",
                    nativeQuery = true)
    public Integer getListConditionIdLikeTotalCount(@Param(value = "search") String search);


    // message
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete "+
                    "FROM config_conditions cf_con "+
                    "WHERE ( cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListMessageLike(
                                            @Param(value = "search") String search,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE ( cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public Integer getListMessageLikeTotalCount(@Param(value = "search") String search);

}


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
    
    /* WITH END TIME ONLY */
    @Query(value =  """
                    SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable
                    FROM config_conditions cf_con 
                    WHERE (cf_con.date_end BETWEEN :start_time AND :end_time ) 
                    """
                    ,nativeQuery = true)
    public List<ListSmsCondition> ListSmsConditionWithEndTime(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")   Timestamp endTime,
        Pageable pageable
    );

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_end BETWEEN :start_time AND :end_time ) ",
                    nativeQuery = true)
    public Integer getListSmsConditionWithEndTimeTotalCount(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time") Timestamp endTime
    );

    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_end BETWEEN :start_time AND :end_time )  "+
                    "AND ( UPPER(cf_con.refid) || LOWER(cf_con.refid) || UPPER(cf_con.ordertype) || LOWER(cf_con.ordertype) || cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListSmsConditionWithEndTimeAllLike(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search") String search,
        Pageable pageable
    );

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_end BETWEEN :start_time AND :end_time ) "+
                    "AND ( UPPER(cf_con.refid) || LOWER(cf_con.refid) || UPPER(cf_con.ordertype) || LOWER(cf_con.ordertype) || cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public Integer getListSmsConditionWithEndTimeAllLikeTotalCount(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search") String search
    );

    // ordertype
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_end BETWEEN :start_time AND :end_time ) "+
                    "AND ( UPPER(cf_con.ordertype) || LOWER(cf_con.ordertype) like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListOrderTypeWithEndTimeLike(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search") String search,
        Pageable pageable
    );

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_end BETWEEN :start_time AND :end_time ) "+
                    "AND ( UPPER(cf_con.ordertype) || LOWER(cf_con.ordertype) like %:search% ) ",
                    nativeQuery = true)
    public Integer getListOrderTypeLikeWithEndTimeTotalCount(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search") String search
    );


    // conditions_id
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_end BETWEEN :start_time AND :end_time ) "+
                    "AND ( UPPER(cf_con.refid) || LOWER(cf_con.refid) like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListConditionIdWithEndTimeLike(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search") String search,
        Pageable pageable
    );

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_end BETWEEN :start_time AND :end_time )  "+
                    "AND ( UPPER(cf_con.refid) || LOWER(cf_con.refid) like %:search% ) ",
                    nativeQuery = true)
    public Integer getListConditionIdWithEndTimeLikeTotalCount(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search") String search
    );


    // message
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_end BETWEEN :start_time AND :end_time )  "+
                    "AND ( cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListMessageWithEndTimeLike(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search") String search,
        Pageable pageable
    );

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_end BETWEEN :start_time AND :end_time )  "+
                    "AND ( cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public Integer getListMessageWithEndTimeLikeTotalCount(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")Timestamp endTime,
        @Param(value = "search") String search
    );
    
    
    /* WITH START TIME ONLY */
    @Query(value =  """
                    SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable
                    FROM config_conditions cf_con 
                    WHERE (cf_con.date_start BETWEEN :start_time AND :end_time ) 
                    """
                    ,nativeQuery = true)
    public List<ListSmsCondition> ListSmsConditionWithStartTime(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")   Timestamp endTime,
        Pageable pageable
    );

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start BETWEEN :start_time AND :end_time ) ",
                    nativeQuery = true)
    public Integer getListSmsConditionWithStartTimeTotalCount(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")   Timestamp endTime
    );

    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start BETWEEN :start_time AND :end_time ) "+
                    "AND ( UPPER(cf_con.refid) || LOWER(cf_con.refid) || UPPER(cf_con.ordertype) || LOWER(cf_con.ordertype) || cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListSmsConditionWithStartTimeAllLike(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")   Timestamp endTime,
        @Param(value = "search") String search,
        Pageable pageable
    );

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start BETWEEN :start_time AND :end_time ) "+
                    "AND ( UPPER(cf_con.refid) || LOWER(cf_con.refid) || UPPER(cf_con.ordertype) || LOWER(cf_con.ordertype) || cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public Integer getListSmsConditionWithStartTimeAllLikeTotalCount(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")   Timestamp endTime,
        @Param(value = "search") String search);

    // ordertype
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start BETWEEN :start_time AND :end_time ) "+
                    "AND ( UPPER(cf_con.ordertype) || LOWER(cf_con.ordertype) like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListOrderTypeWithStartTimeLike(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")   Timestamp endTime,
        @Param(value = "search") String search,
        Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start BETWEEN :start_time AND :end_time ) "+
                    "AND ( UPPER(cf_con.ordertype) || LOWER(cf_con.ordertype) like %:search% ) ",
                    nativeQuery = true)
    public Integer getListOrderTypeLikeWithStartTimeTotalCount(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")   Timestamp endTime,
        @Param(value = "search") String search);


    // conditions_id
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start BETWEEN :start_time AND :end_time ) "+
                    "AND ( UPPER(cf_con.refid) || LOWER(cf_con.refid) like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListConditionIdWithStartTimeLike(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")   Timestamp endTime,
        @Param(value = "search") String search,
        Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start BETWEEN :start_time AND :end_time ) "+
                    "AND ( UPPER(cf_con.refid) || LOWER(cf_con.refid) like %:search% ) ",
                    nativeQuery = true)
    public Integer getListConditionIdWithStartTimeLikeTotalCount(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")   Timestamp endTime,
        @Param(value = "search") String search);


    // message
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start BETWEEN :start_time AND :end_time ) "+
                    "AND ( cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListMessageWithStartTimeLike(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")   Timestamp endTime,
        @Param(value = "search") String search,
        Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.date_start BETWEEN :start_time AND :end_time ) "+
                    "AND ( cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public Integer getListMessageWithStartTimeLikeTotalCount(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")   Timestamp endTime,
        @Param(value = "search") String search);
    
    
    /* WITH TIME */
    @Query(value =  """
                    SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable
                    FROM config_conditions cf_con 
                    WHERE (cf_con.created_date BETWEEN :start_time AND :end_time ) 
                    """
                    ,nativeQuery = true)
    public List<ListSmsCondition> ListSmsConditionWithTime(
        @Param(value = "start_time") Timestamp startTime,
        @Param(value = "end_time")   Timestamp endTime,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.created_date BETWEEN :start_time AND :end_time ) ",
                    nativeQuery = true)
    public Integer getListSmsConditionWithTimeTotalCount(
        @Param(value = "start_time") Timestamp startTime, 
        @Param(value = "end_time") Timestamp endTime
    );

    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.created_date BETWEEN :start_time AND :end_time ) "+
                    "AND ( UPPER(cf_con.refid) || LOWER(cf_con.refid) || UPPER(cf_con.ordertype) || LOWER(cf_con.ordertype) || cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListSmsConditionWithTimeAllLike(@Param(value = "start_time") Timestamp startTime,
                                            @Param(value = "end_time")Timestamp endTime,
                                            @Param(value = "search") String search,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.created_date BETWEEN :start_time AND :end_time ) "+
                    "AND ( UPPER(cf_con.refid) || LOWER(cf_con.refid) || UPPER(cf_con.ordertype) || LOWER(cf_con.ordertype) || cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public Integer getListSmsConditionWithTimeAllLikeTotalCount(@Param(value = "start_time") Timestamp startTime,
                                                        @Param(value = "end_time")Timestamp endTime,
                                                        @Param(value = "search") String search);

    // ordertype
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.created_date BETWEEN :start_time AND :end_time ) "+
                    "AND ( UPPER(cf_con.ordertype) || LOWER(cf_con.ordertype) like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListOrderTypeWithTimeLike(@Param(value = "start_time") Timestamp startTime,
                                            @Param(value = "end_time")Timestamp endTime,
                                            @Param(value = "search") String search,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.created_date BETWEEN :start_time AND :end_time ) "+
                    "AND ( UPPER(cf_con.ordertype) || LOWER(cf_con.ordertype) like %:search% ) ",
                    nativeQuery = true)
    public Integer getListOrderTypeLikeWithTimeTotalCount(@Param(value = "start_time") Timestamp startTime,
                                                        @Param(value = "end_time")Timestamp endTime,
                                                        @Param(value = "search") String search);


    // conditions_id
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.created_date BETWEEN :start_time AND :end_time ) "+
                    "AND ( UPPER(cf_con.refid) || LOWER(cf_con.refid) like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListConditionIdWithTimeLike(@Param(value = "start_time") Timestamp startTime,
                                            @Param(value = "end_time")Timestamp endTime,
                                            @Param(value = "search") String search,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.created_date BETWEEN :start_time AND :end_time ) "+
                    "AND ( UPPER(cf_con.refid) || LOWER(cf_con.refid) like %:search% ) ",
                    nativeQuery = true)
    public Integer getListConditionIdWithTimeLikeTotalCount(@Param(value = "start_time") Timestamp startTime,
                                                        @Param(value = "end_time")Timestamp endTime,
                                                        @Param(value = "search") String search);


    // message
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.created_date BETWEEN :start_time AND :end_time ) "+
                    "AND ( cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListMessageWithTimeLike(@Param(value = "start_time") Timestamp startTime,
                                            @Param(value = "end_time")Timestamp endTime,
                                            @Param(value = "search") String search,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE (cf_con.created_date BETWEEN :start_time AND :end_time ) "+
                    "AND ( cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public Integer getListMessageWithTimeLikeTotalCount(@Param(value = "start_time") Timestamp startTime,
                                                        @Param(value = "end_time")Timestamp endTime,
                                                        @Param(value = "search") String search);


    /* WITHOUT TIME */
    @Query(value =  """
                    SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable
                    FROM config_conditions cf_con 
                    """
                    ,nativeQuery = true)
    public List<ListSmsCondition> ListSmsCondition(Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM config_conditions cf_con ",
                    nativeQuery = true)
    public Integer getListSmsConditionTotalCount();

    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable "+
                    "FROM config_conditions cf_con "+
                    "WHERE ( UPPER(cf_con.refid) || LOWER(cf_con.refid) || UPPER(cf_con.ordertype) || LOWER(cf_con.ordertype) || cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListSmsConditionAllLike(
                    @Param(value = "search") String search,
                    Pageable pageable
    );

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE ( UPPER(cf_con.refid) || LOWER(cf_con.refid) || UPPER(cf_con.ordertype) || LOWER(cf_con.ordertype) || cf_con.message like %:search% ) ",
                    nativeQuery = true)
    public Integer getListSmsConditionAllLikeTotalCount(@Param(value = "search") String search);

    // ordertype
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable "+
                    "FROM config_conditions cf_con "+
                    "WHERE ( UPPER(cf_con.ordertype) || LOWER(cf_con.ordertype) like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListOrderTypeLike(
                                            @Param(value = "search") String search,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE ( UPPER(cf_con.ordertype) || LOWER(cf_con.ordertype) like %:search% ) ",
                    nativeQuery = true)
    public Integer getListOrderTypeLikeTotalCount(@Param(value = "search") String search);


    // conditions_id
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable "+
                    "FROM config_conditions cf_con "+
                    "WHERE ( UPPER(cf_con.refid) || LOWER(cf_con.refid) like %:search% ) ",
                    nativeQuery = true)
    public List<ListSmsCondition> ListConditionIdLike(
                                            @Param(value = "search") String search,
                                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) "+
                    "FROM config_conditions cf_con "+
                    "WHERE ( UPPER(cf_con.refid) || LOWER(cf_con.refid) like %:search% ) ",
                    nativeQuery = true)
    public Integer getListConditionIdLikeTotalCount(@Param(value = "search") String search);


    // message
    @Query(value =  "SELECT cf_con.conditions_id,cf_con.ordertype,cf_con.date_start, cf_con.date_end,cf_con.created_date,cf_con.message,cf_con.is_delete, cf_con.refid, cf_con.is_enable "+
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


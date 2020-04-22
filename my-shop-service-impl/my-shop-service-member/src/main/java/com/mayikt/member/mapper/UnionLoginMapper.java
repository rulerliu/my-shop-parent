package com.mayikt.member.mapper;

import com.mayikt.member.entity.UnionLoginDo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UnionLoginMapper {

    @Select("SELECT ID AS ID ,union_name AS  unionname ,\n" +
            "union_public_id AS unionpublicid, union_bean_Id as unionBeanId, app_id AS appid,\n" +
            "app_key AS appkey,redirect_uri as redirecturi,\n" +
            "request_address as requestaddress,is_availability as isavailability\n" +
            " FROM meite_union_login where union_public_id=#{unionPublicId} and is_availability='1'")
    UnionLoginDo selectByUnionLoginId(@Param("unionPublicId") String unionPublicId);
}

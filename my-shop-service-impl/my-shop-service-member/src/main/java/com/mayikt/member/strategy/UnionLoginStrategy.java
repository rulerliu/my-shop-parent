package com.mayikt.member.strategy;

import com.mayikt.member.entity.UnionLoginDo;
import com.mayikt.member.entity.UserDo;

import javax.servlet.http.HttpServletRequest;

public interface UnionLoginStrategy {

    String unionLoginCallback(HttpServletRequest request, UnionLoginDo unionLoginDo);

    UserDo getUserDo(String openid);
}

package com.mayikt.member.strategy;

import com.mayikt.member.entity.UnionLoginDo;

import javax.servlet.http.HttpServletRequest;

public interface UnionLoginStrategy {

    String unionLoginCallback(HttpServletRequest request, UnionLoginDo unionLoginDo);

}

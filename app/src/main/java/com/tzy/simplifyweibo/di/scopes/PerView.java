package com.tzy.simplifyweibo.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Created by skylineTan on 2016/5/31 11:50.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerView {}

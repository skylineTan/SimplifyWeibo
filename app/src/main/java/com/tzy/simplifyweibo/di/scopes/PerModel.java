package com.tzy.simplifyweibo.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Created by skylineTan on 2016/5/23 23:43.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerModel {
}

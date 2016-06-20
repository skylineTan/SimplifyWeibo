package com.tzy.simplifyweibo.di.modules;

import android.view.View;
import com.tzy.simplifyweibo.di.scopes.PerModel;
import com.tzy.simplifyweibo.di.scopes.PerView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by skylineTan on 2016/5/31 11:48.
 */
@Module
public class ViewModule {
    private View mView;

    public ViewModule(View view){
        mView = view;
    }

    @Provides @PerView
    public View provideView(){
        return mView;
    }

}

package com.tzy.simplifyweibo.di.components;

import android.view.View;
import com.tzy.simplifyweibo.di.modules.ViewModule;
import com.tzy.simplifyweibo.di.scopes.PerView;
import com.tzy.simplifyweibo.ui.widget.StatusTextView;
import dagger.Component;

/**
 * Created by skylineTan on 2016/5/31 11:51.
 */
@PerView
@Component(dependencies = AppComponent.class,modules = ViewModule.class)
public interface ViewComponent {

    View view();

    void inject(StatusTextView textView);
}

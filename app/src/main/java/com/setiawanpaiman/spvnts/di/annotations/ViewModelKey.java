package com.setiawanpaiman.spvnts.di.annotations;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;

import java.lang.annotation.*;

/**
 * Created by Setiawan Paiman on 26/4/19.
 */
@MapKey
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}

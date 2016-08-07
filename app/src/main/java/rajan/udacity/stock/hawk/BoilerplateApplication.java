package rajan.udacity.stock.hawk;

import com.crashlytics.android.Crashlytics;

import android.app.Application;
import android.content.Context;

import io.fabric.sdk.android.Fabric;
import rajan.udacity.stock.hawk.injection.component.ApplicationComponent;
import rajan.udacity.stock.hawk.injection.component.DaggerApplicationComponent;
import rajan.udacity.stock.hawk.injection.module.ApplicationModule;
import timber.log.Timber;

public class BoilerplateApplication extends Application  {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Fabric.with(this, new Crashlytics());
        }
    }

    public static BoilerplateApplication get(Context context) {
        return (BoilerplateApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}

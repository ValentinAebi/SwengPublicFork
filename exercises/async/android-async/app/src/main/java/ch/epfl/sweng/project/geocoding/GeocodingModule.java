package ch.epfl.sweng.project.geocoding;

import android.content.Context;
import android.location.Geocoder;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class GeocodingModule {

    @Binds
    public abstract GeocodingService bindGeocodingService(AndroidGeocodingService geocodingServiceImpl);

    @Provides
    public static Geocoder provideGeocoder(@ApplicationContext Context context) {
        return new Geocoder(context);
    }
}

package ru.normno.moviecatalogapp.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.normno.moviecatalogapp.data.remote.MovieCatalogApi
import ru.normno.moviecatalogapp.data.repository.MovieCatalogRepositoryImpl
import ru.normno.moviecatalogapp.domain.repository.MovieCatalogRepository
import ru.normno.moviecatalogapp.domain.usecases.GetMovieCatalog
import ru.normno.moviecatalogapp.domain.usecases.MovieCatalogUseCases
import ru.normno.moviecatalogapp.presentation.catalog.CatalogViewModel
import java.util.concurrent.TimeUnit

object AppModule {
    fun initializeKoin(context: Context) {
        startKoin {
            androidLogger()
            androidContext(context)
            modules(
                networkModule,
                movieCatalogRepositoryModule,
                movieCatalogUsesCasesModule,
                viewModelModule,
            )
        }
    }

    private val movieCatalogRepositoryModule = module {
        single<MovieCatalogRepository> { MovieCatalogRepositoryImpl(get()) }
    }

    private val movieCatalogUsesCasesModule = module {
        single<MovieCatalogUseCases> {
            MovieCatalogUseCases(
                getMovieCatalog = GetMovieCatalog(get())
            )
        }
    }

    private val viewModelModule = module {
        viewModel { CatalogViewModel(get(), get()) }
    }

    private val networkModule = module {
        single { provideHttpClient() }
        single { provideConverterFactory() }
        single { provideRetrofit(get(), get()) }
        single { provideMovieCatalogApi(get()) }
    }

    private fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BASIC
        }
        return OkHttpClient
            .Builder().apply {
                this.addInterceptor(interceptor)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build()
            }.build()

    }

    private fun provideConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return MoshiConverterFactory.create(moshi)
    }

    private fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(MovieCatalogApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    private fun provideMovieCatalogApi(retrofit: Retrofit): MovieCatalogApi =
        retrofit.create(MovieCatalogApi::class.java)
}
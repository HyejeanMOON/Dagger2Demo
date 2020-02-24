package com.hyejeanmoon.dagger2demo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlin.random.Random

class MainActivity : DaggerAppCompatActivity() {

    private val random = Random

    @Inject
    lateinit var viewmodelFactory: MainActivityViewModel.Factory
    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this, viewmodelFactory).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = viewModel.nameLiveData.value.toString()

        viewModel.nameLiveData.observe(this, Observer {
            textView.text = it
        })

        val btn = findViewById<Button>(R.id.btn)

        btn.setOnClickListener {
            viewModel.setName(random.nextInt(50).toString())
        }

    }
}

@Module
abstract class MainActivityModule {
    @Binds
    abstract fun provideActivity(activity: MainActivity): FragmentActivity

    @Module
    abstract class Builder {
        @ContributesAndroidInjector(modules = [MainActivityModule::class])
        abstract fun contributeMainActivity(): MainActivity
    }
}

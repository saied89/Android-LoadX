package com.saied.home.loadingexts

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.jaredrummler.android.colorpicker.ColorPickerDialog
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener
import com.saied.home.androidloadingexts.loadX
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityOld : AppCompatActivity(), ColorPickerDialogListener {
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        viewModel.loadingParamsViewModel.observe(this, Observer {
            loadingTarget.loadX(false)
            loadingTarget.loadX(true, progressbarSize = it!!.size, progressbarColor = it.color)
        })
        setupLoadingViews()

        sizeSeekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.loadingParamsViewModel.value = viewModel.loadingParamsViewModel.value!!.copy(size = progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        chooseColorButton.setOnClickListener {
            ColorPickerDialog.newBuilder()
                    .show(this)
        }
//        loadButtonLinearNegMargin.setOnClickListener {
//         it.loadX(true)
//        }

//        loadButtonConstraint.setOnClickListener {
//            it.loadingV2(true)
//        }
//        constraintLayout.setOnClickListener {
//            loadButtonConstraint.loadingV2(false)
//        }
    }

    override fun onColorSelected(dialogId: Int, color: Int) {
        chooseColorButton.setBackgroundColor(color)
        viewModel.loadingParamsViewModel.value = viewModel.loadingParamsViewModel.value!!.copy(color = color)
    }

    override fun onDialogDismissed(dialogId: Int) {
    }

    private fun setupLoadingViews() {
        loadButtonRelative.setOnClickListener {
            it.loadX(true)
        }
        relativeLayout.setOnClickListener {
            loadButtonRelative.loadX(false)
        }
        loadButtonLinear.setOnClickListener {
            it.loadX(true)
        }
        linearLayoutVertical.setOnClickListener {
            loadButtonLinear.loadX(false)
        }
        loadButtonLinearHorizontal.setOnClickListener {
            it.loadX(true)
        }
        linearLayoutHorizonal.setOnClickListener {
            loadButtonLinearHorizontal.loadX(false)
        }
        loadButtonConstraintLayout.setOnClickListener {
            it.loadX(true)
        }
        constraintLayout.setOnClickListener {
            loadButtonConstraintLayout.loadX(false)
        }

        testTV.setOnClickListener {
            it.loadX(true)
        }
        testTV2.setOnClickListener {
            it.loadX(true)
        }
    }


}

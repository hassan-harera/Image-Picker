package com.harera.imagepicker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.imagepickerlibrary.ImagePickerActivityClass
import com.app.imagepickerlibrary.ImagePickerBottomsheet
import com.app.imagepickerlibrary.bottomSheetActionCamera
import com.app.imagepickerlibrary.bottomSheetActionGallary
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ImagePickerActivityClass.OnResult,
    ImagePickerBottomsheet.ItemClickListener {

    private lateinit var imagePicker: ImagePickerActivityClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            val fragment = ImagePickerBottomsheet()
            fragment.show(supportFragmentManager, "String")
        }
    }

    override fun returnString(item: Uri?) {
        convertImagePathToBitmap(uri = item)?.let {
            findViewById<ImageView>(R.id.image).setImageBitmap(it)
        }
    }

    override fun onItemClick(item: String?) {
        imagePicker = ImagePickerActivityClass(
            context = this,
            this,
            activityResultRegistry,
            activity = this
        )
        imagePicker.cropOptions(true)

        when (item) {
            bottomSheetActionGallary -> {
                imagePicker.choosePhotoFromGallery()
            }

            bottomSheetActionCamera -> {
                imagePicker.takePhotoFromCamera()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imagePicker.onActivityResult(requestCode, resultCode, data)
    }

    override fun doCustomisations(fragment: ImagePickerBottomsheet) {
        super.doCustomisations(fragment)


    }
}
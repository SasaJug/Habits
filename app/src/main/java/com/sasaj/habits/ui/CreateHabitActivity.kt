package com.sasaj.habits.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sasaj.habits.R
import android.graphics.Bitmap
import android.util.Log
import android.graphics.BitmapFactory
import android.content.res.AssetFileDescriptor
import kotlinx.android.synthetic.main.activity_create_habit.*
import java.io.FileNotFoundException


class CreateHabitActivity : AppCompatActivity() {

    val TAG = CreateHabitActivity::class.java.simpleName
    val CHOOSE_IMAGE = 102
    val DEFAULT_MIN_WIDTH_QUALITY = 400
    val minWidthQuality = DEFAULT_MIN_WIDTH_QUALITY

    private var bm: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_habit)
    }

    fun storeHabit(v: View){

        if(etTitle.text.toString().isBlank() || etTitle.toString() == ""){
            titleWrapper.isErrorEnabled = true
            titleWrapper.error = "Title is missing."
        } else {
            titleWrapper.isErrorEnabled = false
            titleWrapper.error = ""
        }

        if(etDescription.text.toString().isBlank()){
            descriptionWrapper.isErrorEnabled = true
            descriptionWrapper.error = "Description is missing."
        } else {
            descriptionWrapper.isErrorEnabled = false
            descriptionWrapper.error = ""
        }

        if(bm == null){
            imageError.visibility = View.VISIBLE
        } else {
            imageError.visibility = View.GONE
        }

    }

    fun chooseImage(v: View){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        val chooser = Intent.createChooser(intent, getString(R.string.chooser_select_image_title))
        startActivityForResult(chooser, CHOOSE_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if(requestCode != CHOOSE_IMAGE || resultCode != Activity.RESULT_OK) {
            return
        }
        val selectedImageUri: Uri = data.data
        bm = getImageResized(this, selectedImageUri)
        bm.let {
            habitImageView.setImageBitmap(bm)
        }
    }

    /**
     * Resize to avoid using too much memory loading big images (e.g.: 2560*1920)
     */
    private fun getImageResized(context: Context, selectedImage: Uri): Bitmap? {
        var bm: Bitmap? = null
        val sampleSizes = intArrayOf(5, 3, 2, 1)
        var i = 0
        do {
            bm = decodeBitmap(context, selectedImage, sampleSizes[i])
            i++
        } while (bm!!.width < minWidthQuality && i < sampleSizes.size)
        return bm
    }

    private fun decodeBitmap(context: Context, theUri: Uri, sampleSize: Int): Bitmap? {
        val options = BitmapFactory.Options()
        options.inSampleSize = sampleSize

        var fileDescriptor: AssetFileDescriptor? = null
        try {
            fileDescriptor = context.contentResolver.openAssetFileDescriptor(theUri, "r")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return null
        }

        val actuallyUsableBitmap = BitmapFactory.decodeFileDescriptor(
                fileDescriptor!!.fileDescriptor, null, options)

        Log.d(TAG, options.inSampleSize.toString() + " sample method bitmap ... " +
                actuallyUsableBitmap.width + " " + actuallyUsableBitmap.height)

        return actuallyUsableBitmap
    }
}

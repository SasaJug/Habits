package com.sasaj.habits.ui

/**
 * Created by sjugurdzija on 5/10/2018
 */

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import com.sasaj.habits.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_create_habit.*


class CreateHabitActivity : AppCompatActivity() {

    val TAG = CreateHabitActivity::class.java.simpleName
    val CHOOSE_IMAGE = 102

    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_habit)
    }

    fun storeHabit(v: View) {

        if (etTitle.isBlank()) {
            titleWrapper.isErrorEnabled = true
            titleWrapper.error = "Title is missing."
        } else {
            titleWrapper.isErrorEnabled = false
            titleWrapper.error = ""
        }

        if (etDescription.isBlank()) {
            descriptionWrapper.isErrorEnabled = true
            descriptionWrapper.error = "Description is missing."
        } else {
            descriptionWrapper.isErrorEnabled = false
            descriptionWrapper.error = ""
        }

        if (selectedImageUri == null) {
            imageError.visibility = View.VISIBLE
        } else {
            imageError.visibility = View.GONE
        }

    }

    fun chooseImage(v: View) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        val chooser = Intent.createChooser(intent, getString(R.string.chooser_select_image_title))
        startActivityForResult(chooser, CHOOSE_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode != CHOOSE_IMAGE || resultCode != Activity.RESULT_OK) {
            return
        }
        selectedImageUri = data.data
        selectedImageUri.let {
            Picasso.get().load(selectedImageUri).into(habitImageView)
        }
    }

}

private fun EditText.isBlank(): Boolean = this.text.toString().isBlank()

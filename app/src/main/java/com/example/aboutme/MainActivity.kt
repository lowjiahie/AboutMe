package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Aleks Haecky")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //creating a binding object that contains a reference to each view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        /**
         * In onCreate(), set the value of the myName variable in the layout file to
         * the value of the myName variable that you just declared.
         * You can't access the variable in the XML directly.
         * You need to access it through the binding object.
         */
        binding.myName = myName
        /**
         *  When the binding object is created,
         *  the compiler generates the names of the views in the binding object from the IDs of the views in the layout,
         *  converting them to camel case. So, for example, done_button is doneButton in the binding object,
         *  nickname_edit becomes becomes nicknameEdit, and nickname_text becomes nicknameText.
         */
        binding.doneButton.setOnClickListener {
            addNickname(it)
        }

    }

    private fun addNickname(view : View){
        /**
         * The nicknameText requires a String, and nicknameEdit.text is an Editable.
         * When using data binding, it is necessary to explicitly convert the Editable to a String.
         */
        //First ways
//        binding.nicknameText.text = binding.nicknameEdit.text.toString()
//        binding.nicknameEdit.visibility = View.GONE
//        binding.doneButton.visibility = View.GONE
//        binding.nicknameText.visibility = View.VISIBLE

        //second ways
        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            /**
             * After set the nickname, to refresh the UI with the new data.
             * Therefore, invalidate all binding expressions so that they are recreated with correct data
             */
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        // Hide the keyboard.
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    }

    /**
     * Tip: If you see compiler errors after you make changes,
     * select Build > Clean Project followed by Build > Rebuild Project.
     * Doing this usually updates the generated files.
     * Otherwise, select File > Invalidate Caches/Restart to do a more thorough cleanup.
     */
}
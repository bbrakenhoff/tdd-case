package com.bbrakenhoff.myapplication.ui.main

import android.content.res.Resources
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bbrakenhoff.myapplication.R
import org.koin.android.viewmodel.ext.android.viewModel

class ChoosePasswordFragment : Fragment() {

    companion object {
        fun newInstance() = ChoosePasswordFragment()
    }

    private val choosePasswordViewModel: ChoosePasswordViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.choose_password_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val chosenPassword = view.findViewById(R.id.chosen_password) as EditText
        chosenPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                choosePasswordViewModel.chosenPassword.value = s.toString()
            }
        })

        val confirmedPassword = view.findViewById(R.id.confirmed_password) as EditText
        confirmedPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                choosePasswordViewModel.confirmedPassword.value = s.toString()
            }
        })

        val confirmButton = view.findViewById(R.id.confirm_button) as Button
        confirmButton.setOnClickListener { choosePasswordViewModel.updateMessage() }

        val message = view.findViewById(R.id.message) as TextView
        choosePasswordViewModel.message.observe(
            viewLifecycleOwner,
            Observer {
                if (it == null) {
                    message.text = null
                } else {
                    message.setText(it.messageResId)
                    message.setTextColor(ContextCompat.getColor(requireContext(), it.colorResId))
                }
            })
    }
}

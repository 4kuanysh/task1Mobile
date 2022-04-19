package kz.kuanysh.task1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CreateDialogFragment : BottomSheetDialogFragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_create_dialog_list_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var title = ""
        var body = ""
        var reminder = false
        view.findViewById<EditText>(R.id.taskInput).doOnTextChanged { text, _, _, _ ->
            title = text.toString()
        }
        view.findViewById<EditText>(R.id.bodyInput).doOnTextChanged { text, _, _, _ ->
            body = text.toString()
        }
        view.findViewById<CheckBox>(R.id.reminder).setOnCheckedChangeListener { _, b ->
            reminder = b
        }
        requireView().findViewById<Button>(R.id.create).setOnClickListener {
            viewModel.createTask(
                Task(
                    id = Task.genId(),
                    title = title,
                    body = body,
                    reminder = reminder
                ).also {
                    Log.d("aast", it.toString())
                }
            )
            dismiss()
        }
    }

}
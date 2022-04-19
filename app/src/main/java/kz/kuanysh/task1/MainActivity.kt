package kz.kuanysh.task1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val adapter: RecyclerAdapter by lazy {
        RecyclerAdapter(
            onDelete = viewModel::delete,
            onItemClicked = viewModel::setReminder
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<RecyclerView>(R.id.tasks).adapter = adapter
        findViewById<FloatingActionButton>(R.id.addTask).setOnClickListener {
            CreateDialogFragment().also {
                it.show(supportFragmentManager, it::class.java.simpleName)
            }
        }

        viewModel.task.observe(this, adapter::update)


    }
}
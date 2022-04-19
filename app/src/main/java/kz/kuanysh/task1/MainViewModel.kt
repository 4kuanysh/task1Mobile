package kz.kuanysh.task1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val taskList = mutableListOf<Task>()
    private val _tasks = MutableLiveData<List<Task>>()
    val task: LiveData<List<Task>> get() = _tasks

    fun createTask(task: Task) {
        taskList.add(task)
        _tasks.value = taskList
    }

    fun delete(task: Task) {
        taskList.remove(task)
        _tasks.value = taskList
    }

    fun setReminder(task: Task) {
        val index = taskList.indexOf(task)
        Log.d("Tag", index.toString())
        taskList.removeAt(index)
        taskList.add(index, task.copy(reminder = !task.reminder))
        _tasks.value = taskList
    }

}

data class Task(
    val id: Int = 0,
    val title: String = "",
    val body: String = "",
    val reminder: Boolean = false,
) {

    companion object {
        private var _id = 0
        fun genId() = ++_id
    }

}
package kz.kuanysh.task1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    private val onDelete: (Task) -> Unit,
    private val onItemClicked: (Task) -> Unit,
) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    private val data = mutableListOf<Task>()

    fun update(tasks: List<Task>) {
        data.clear()
        data.addAll(tasks)
        notifyDataSetChanged()
    }

    inner class RecyclerViewHolder(
        private val rootView: View
    ) : RecyclerView.ViewHolder(rootView) {
        fun bind(task: Task) {
            rootView.findViewById<TextView>(R.id.title).text = task.title
            rootView.findViewById<TextView>(R.id.body).text = task.body
            rootView.findViewById<ImageView>(R.id.delete).setOnClickListener {
                onDelete(task)
            }
            rootView.setOnLongClickListener {
                onItemClicked(task)
                true
            }
            rootView.findViewById<View>(R.id.reminderMarker).isVisible = task.reminder
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder =
        RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}
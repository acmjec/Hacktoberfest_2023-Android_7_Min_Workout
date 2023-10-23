package dev.panwar.a7minutesworkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.panwar.a7minutesworkout.model.BMIModel
import dev.panwar.a7minutesworkout.R
import dev.panwar.a7minutesworkout.databinding.ItemBmiHistoryRowBinding


// TODO(Step 2 : Created a adapter class to bind the to RecyclerView to show the list of completed dates in History Screen.)
// START
class HistoryBmiAdapter(private val items: ArrayList<BMIModel>) :
    RecyclerView.Adapter<HistoryBmiAdapter.ViewHolder>() {

    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBmiHistoryRowBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        )
    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model: BMIModel = items.get(position)

        holder.tvPosition.text = (position + 1).toString()
        holder.tvItemWeight.text="Weight: "+model.weight
        holder.tvItemHeight.text="Height: "+model.height
        holder.tvItemDate.text="Date: "+model.date
        holder.tvItemBmi.text="BMI: "+model.bmi

        // Updating the background color according to the odd/even positions in list.
        if (position % 2 == 0) {
            holder.llHistoryItemMain.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.light_white)
            )
        } else {
            holder.llHistoryItemMain.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.white)
            )
        }
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class ViewHolder(binding: ItemBmiHistoryRowBinding) : RecyclerView.ViewHolder(binding.root) {
        // Holds the TextView that will add each item to
        val llHistoryItemMain = binding.llHistoryItemMain
        val tvItemWeight = binding.tvItemWeight
        val tvItemHeight = binding.tvItemheight
        val tvItemBmi = binding.tvItemBmi
        val tvItemDate = binding.tvItemDate
        val tvPosition = binding.tvPosition
    }
}
// END
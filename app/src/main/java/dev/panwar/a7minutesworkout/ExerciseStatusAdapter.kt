package dev.panwar.a7minutesworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.panwar.a7minutesworkout.databinding.ItemExerciseStatusBinding

//using recyclerview with view binding
class ExerciseStatusAdapter(val Exercises:ArrayList<ExerciseModel>) : RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {
//   view binding recycler view
    class ViewHolder(binding: ItemExerciseStatusBinding): RecyclerView.ViewHolder(binding.root){
        val tvItem=binding.tvItem

    }
//for creating view HOLDER
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return Exercises.size
    }

//after binding view holder....whenever there is a change in view this function is called..
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:ExerciseModel=Exercises[position]
        holder.tvItem.text=model.getId().toString()


    when{
//        which exercise is going on
        model.getIsSelected()->{
//            holder.itemView.context gives the context of item in holder
            holder.tvItem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_thin_color_accent_border)
            holder.tvItem.setTextColor(Color.parseColor("#212121"))
        }
        model.getIsCompleted()->{
            holder.tvItem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_color_accent_background)
            holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
        }
        else->{
            holder.tvItem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_color_gray_background)
            holder.tvItem.setTextColor(Color.parseColor("#212121"))
        }
    }
    }

}
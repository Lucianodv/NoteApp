package com.example.notesapp.adapter

import android.content.ClipData
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.model.Note
import kotlin.random.Random

class NotesAdapter(private val context : Context, val listener: NotesClickListener) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val NotesList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item, parent,false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
    val currentNote = NotesList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected = true
        holder.note.text = currentNote.note
        holder.date.text = currentNote.date
        holder.date.isSelected = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))
        }

        holder.notes_layout.setOnClickListener{
            listener.onItemClick(NotesList[holder.adapterPosition])
        }
        holder.notes_layout.setOnLongClickListener{
            listener.onLongItemClick(NotesList[holder.adapterPosition],holder.notes_layout)
            true
        }
    }

    override fun getItemCount(): Int {
      return  NotesList.size
    }


    fun updateList(newList : List<Note>){
        fullList.clear()
        fullList.addAll(newList)

        NotesList.clear()
        NotesList.addAll(fullList)
        notifyDataSetChanged()

    }

    fun filterList(search : String){

        NotesList.clear()

        for (item in fullList){
            if(item.title?.lowercase()?.contains(search.lowercase()) == true ||
                    item.note?.lowercase()?.contains(search.lowercase()) == true){

                        NotesList.add(item)
            }
        }
        notifyDataSetChanged()
    }


    fun randomColor() : Int {

        val list = ArrayList<Int>()
        list.add(R.color.noteColor_1)
        list.add(R.color.noteColor_2)
        list.add(R.color.noteColor_3)
        list.add(R.color.noteColor_4)
        list.add(R.color.noteColor_5)
        list.add(R.color.noteColor_6)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]





    }


    inner class NoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val notes_layout = itemView.findViewById<CardView>(R.id.card_item_id)
        val title = itemView.findViewById<TextView>(R.id.txt_title)
        val note = itemView.findViewById<TextView>(R.id.txt_note)
        val date = itemView.findViewById<TextView>(R.id.txt_date)

    }

    interface NotesClickListener{



        fun onItemClick(note:Note)
        fun onLongItemClick(note:Note,cardView: CardView)



    }

}
package com.example.aulawhatsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.aulawhatsapp.databinding.ItemContatosBinding
import com.example.aulawhatsapp.databinding.ItemConversasBinding
import com.example.aulawhatsapp.model.Conversa
import com.example.aulawhatsapp.model.Usuario
import com.squareup.picasso.Picasso

class ConversasAdapter(
    private val onClick: (Conversa) -> Unit
) : Adapter<ConversasAdapter.ConversasViewHolder>() {
    private var listaConversas = emptyList<Conversa>()
    fun adicionarLista(lista: List<Conversa>){
        listaConversas = lista
        notifyDataSetChanged()
    }
    inner class ConversasViewHolder(
        private val binding: ItemConversasBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun binding(conversa: Conversa){
            binding.textConversaNome.text = conversa.nome
            binding.textConversaMensagem.text = conversa.ultimaMensagem
            Picasso.get()
                .load(conversa.foto)
                .into(binding.imageConversaFoto)

            binding.clItemConversa.setOnClickListener {
                onClick(conversa)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversasViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemConversasBinding.inflate(
            inflater, parent, false
        )
        return ConversasViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ConversasViewHolder, position: Int) {
        val conversa = listaConversas[position]
        holder.binding(conversa)
    }
    override fun getItemCount(): Int {
        return listaConversas.size
    }
}
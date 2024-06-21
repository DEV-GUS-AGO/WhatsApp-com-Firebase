package com.example.aulawhatsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.aulawhatsapp.databinding.ItemMensagensDestinatarioBinding
import com.example.aulawhatsapp.databinding.ItemMensagensRemetenteBinding
import com.example.aulawhatsapp.model.Mensagem
import com.example.aulawhatsapp.utils.Constantes
import com.google.firebase.auth.FirebaseAuth

class MensagensAdapter : Adapter<ViewHolder>() {

    private var listaMensagens = emptyList<Mensagem>()
    fun adicionarLista(lista: List<Mensagem>){
        listaMensagens = lista
        notifyDataSetChanged()
    }

    class MensagemRemententeViewHolder(
        private val binding: ItemMensagensRemetenteBinding
    ) : ViewHolder(binding.root){

        fun bind(mensagem: Mensagem){
            binding.textMensagemRemetente.text = mensagem.mensagem
        }

        companion object{
            fun inflarLayout(parent: ViewGroup) : MensagemRemententeViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val itemView = ItemMensagensRemetenteBinding.inflate(
                    inflater, parent, false
                )
                return MensagemRemententeViewHolder(itemView)
            }
        }
    }
    class MensagemDestinatarioViewHolder(
        private val binding: ItemMensagensDestinatarioBinding
    ) : ViewHolder(binding.root){

        fun bind(mensagem: Mensagem){
            binding.textMensagemDestinatario.text = mensagem.mensagem
        }

        companion object{
            fun inflarLayout(parent: ViewGroup) : MensagemDestinatarioViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val itemView = ItemMensagensDestinatarioBinding.inflate(
                    inflater, parent, false
                )
                return MensagemDestinatarioViewHolder(itemView)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        val mensagem = listaMensagens[position]
        val idUsuarioLogado = FirebaseAuth.getInstance().currentUser?.uid.toString()
        return if (idUsuarioLogado == mensagem.idUsuario){
            Constantes.TIPO_REMETENTE
        }else{
            Constantes.TIPO_DESTINATARIO
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        if (viewType == Constantes.TIPO_REMETENTE)
            return MensagemRemententeViewHolder.inflarLayout(parent)

        return MensagemDestinatarioViewHolder.inflarLayout(parent)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mensagem = listaMensagens[position]
        when(holder){
            is MensagemRemententeViewHolder -> holder.bind(mensagem)
            is MensagemDestinatarioViewHolder -> holder.bind(mensagem)
        }
        /*val mensagemRemententeViewHolder = holder as MensagemRemententeViewHolder
        mensagemRemententeViewHolder.binding()*/
    }
    override fun getItemCount(): Int {
        return listaMensagens.size
    }
}
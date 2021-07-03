package service.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.DetalheVaga
import com.example.login.R
import com.google.gson.Gson
import enumeradores.IntentExtras
import model.Vaga

class PainelVagaAdapter(private var vagas:  List<Vaga>) : RecyclerView
        .Adapter<PainelVagaAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var txt_titulo: TextView
        var txt_nivel: TextView
        val btnAtualizar: TextView

        init {
            txt_titulo = view.findViewById(R.id.card_vaga_titulo)
            txt_nivel = view.findViewById(R.id.card_vaga_nivel)
            btnAtualizar = view.findViewById(R.id.card_vaga_visualizar)
        }
    }

    fun update( vagas:  List<Vaga>){
        this.vagas = vagas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PainelVagaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_vaga, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PainelVagaAdapter.ViewHolder, position: Int) {
        val vaga = vagas[position]
        holder.txt_titulo.text = vaga.titulo
        holder.txt_nivel.text = vaga.nivel.nome
//        holder.btnAtualizar.setOnClickListener

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetalheVaga::class.java)
            intent.putExtra("vaga", Gson().toJson(vaga))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = vagas.size
}
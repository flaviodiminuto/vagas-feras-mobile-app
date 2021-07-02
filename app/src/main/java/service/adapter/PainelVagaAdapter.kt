package service.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import model.Vaga

class PainelVagaAdapter(private var vagas:  List<Vaga>) : RecyclerView
        .Adapter<PainelVagaAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var txt_titulo: TextView
        var txt_nivel: TextView

        init {
            txt_titulo = view.findViewById(R.id.card_vaga_titulo)
            txt_nivel = view.findViewById(R.id.card_vaga_nivel)
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
    }

    override fun getItemCount() = vagas.size
}
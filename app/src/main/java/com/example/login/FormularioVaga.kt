package com.example.login

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import contratos.Inscrito
import model.Area
import org.jetbrains.annotations.NotNull
import service.AreaService

class FormularioVaga : AppCompatActivity(){

    lateinit var areaSelecionada: Area

    lateinit var spinnerArea: Spinner
    lateinit var spinnerSegmento: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_vaga)
        spinnerArea = findViewById(R.id.form_vaga_spinner_area)
        spinnerSegmento = findViewById(R.id.form_vaga_spinner_segmento)
        areaSelecionada = Area(null, "area 1", "descricao 1")


        val divulgar = true

        if(divulgar)
            prepararParaDivulgar()
        else
            prepararParaConsultar()

    }

    private fun prepararParaConsultar() {
        // TODO("Not yet implemented")
        //  Ocultar campos exclusivos de cadastro
        //  Alterar titulos para a condicao de consulta
    }

    private fun prepararParaDivulgar() {
        //pesquisar areas
        //Configurar edt segmento para buscar segmentos quando a area for selecionada
        val recebeArea = RecebeArea(spinnerArea, areaSelecionada,this)
        val areaService = AreaService(this)
        areaService.findAreas(recebeArea)

    }

    class RecebeArea(private var spinnerArea: Spinner,
                     private var areaSelecionada: Area,
                     private var context: Context) :
            Inscrito, AdapterView.OnItemSelectedListener{
        var area1 = areaSelecionada
        var area2 = Area(null, "area 2", "descricao 2")
        var area3 = Area(null, "area 3", "descricao 3")
        var areasList: List<Area> = mutableListOf(area1, area2, area3)
        var areasNomes: List<String> = mutableListOf(area1.nome, area2.nome, area3.nome)

        init {
            //Todo adicionar a lista de areas aqui
            //  listar os nomes das áreas
            //  Ao selecionar uma area, inserir a area no campo AreaSelecionada
            ArrayAdapter<String>( context,
                    R.layout.support_simple_spinner_dropdown_item,
                    areasNomes)
                .also { arrayAdapter ->
                    arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
                    spinnerArea.adapter = arrayAdapter}
            spinnerArea.onItemSelectedListener = this
        }

        override fun onNothingSelected(p0: AdapterView<*>?) { }

        override fun onItemSelected(item: AdapterView<*>?, view: View?, position: Int, p3: Long) {
            areaSelecionada = areasList.get(position)
            Toast.makeText(this.context, areaSelecionada.nome, Toast.LENGTH_SHORT).show()
        }
        override fun evento(response: String?) {
            Toast.makeText(this.context, response, Toast.LENGTH_LONG).show()
            // TODO("Not yet implemented")
            //  Ao receber a areas
            //  - dicionar a areas.get(0) na propriedade areaSelecionada
            //  - atualizar a lista de nomes com os nomes das areas
            //  - carregar a lista segmentos com os segmentos da area selecionada
        }
    }

    class RecebeSegmento : Inscrito {
        constructor(@NotNull editText: EditText)

        override fun evento(response: String?) {
            // TODO("Not yet implemented")
            //  redigir o que fazer com a responsta quando receber o segmento
            //  - dicionar a area na propriedade segmento
        }

    }
}
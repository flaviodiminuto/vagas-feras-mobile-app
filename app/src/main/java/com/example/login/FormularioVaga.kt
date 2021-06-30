package com.example.login

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import contratos.Inscrito
import model.Area
import model.Nivel
import model.Segmento
import service.AreaService
import service.SegmentoService

class FormularioVaga : AppCompatActivity(){
    //Services
    private lateinit var areaService: AreaService

    lateinit var txt_descricao: EditText

    lateinit var areaSelecionada: Area
    lateinit var nivelSelecionado: Nivel
    lateinit var segmentoSelecionado: Segmento

    lateinit var spinnerArea: Spinner
    lateinit var spinnerSegmento: Spinner
    lateinit var spinnerNivel: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_vaga)
        spinnerArea = findViewById(R.id.form_vaga_spinner_area)
        spinnerSegmento = findViewById(R.id.form_vaga_spinner_segmento)
        spinnerNivel = findViewById(R.id.form_spn_experiencia)

        areaSelecionada = Area(0, "Carregando", "")
        nivelSelecionado = Nivel(0, "Estagio")
        segmentoSelecionado = Segmento(0, areaSelecionada, "Carregando", "")

        this.areaService = AreaService(this)

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
        //  todo - Configurar edt segmento para buscar segmentos quando a area for selecionada
        val areaHandler = AreaHandler(spinnerArea, areaSelecionada, this)
        areaService.findAreas(areaHandler)

        val segmentoHandler = SegmentoHandler(this.spinnerSegmento, this.segmentoSelecionado, this )
        segmentoHandler.find(areaSelecionada.id)

        areaHandler.setSegmentoHadler(segmentoHandler)

        val nivelHandler = NivelHandler(spinnerNivel, this.nivelSelecionado,this)
        nivelHandler.setNivelAdapter()

    }

    class NivelHandler(private var spinnerNivel: Spinner,
                       private var nivelSelecionado: Nivel,
                       private var context: Context): AdapterView.OnItemSelectedListener{

        fun setNivelAdapter() {
            ArrayAdapter.createFromResource(
                    this.context,
                    R.array.niveis_experiencia,
                    R.layout.support_simple_spinner_dropdown_item
            ).also { arrayAdapter ->
                arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
                this.spinnerNivel.adapter = arrayAdapter
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            nivelSelecionado = Nivel(position.toLong())
        }
    }


    class AreaHandler(private var spinnerArea: Spinner,
                      private var areaSelecionada: Area,
                      private var context: Context) :
            Inscrito, AdapterView.OnItemSelectedListener{
        private lateinit var segmentHandler: SegmentoHandler
        var areasList: List<Area> = mutableListOf(areaSelecionada)
        var areasNomes: List<String> = mutableListOf(areaSelecionada.nome)
        lateinit var arrayAdapter: ArrayAdapter<String>

        init {
            setAreaAdapter()
        }

        fun setAreaAdapter(){
            ArrayAdapter<String>( context,
                    R.layout.support_simple_spinner_dropdown_item,
                    areasNomes)
                    .also { arrayAdapter ->
                        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
                        spinnerArea.adapter = arrayAdapter
                        this.arrayAdapter = arrayAdapter}
            spinnerArea.onItemSelectedListener = this
        }
        override fun onNothingSelected(p0: AdapterView<*>?) { }

        override fun onItemSelected(item: AdapterView<*>?, view: View?, position: Int, p3: Long) {
            areaSelecionada = areasList.get(position)
            segmentHandler.find(areaSelecionada.id)
        }
        override fun evento(response: String?) {
            Toast.makeText(this.context, response, Toast.LENGTH_LONG).show()
            var list = Gson().fromJson(response,Array<Area>::class.java).toList()
            val array = ArrayList<String>()
            for (aera in list){
                array.add(aera.nome)
            }
            areasList = list
            areasNomes = array
            setAreaAdapter()
            // TODO("Not yet implemented")
            //  Ao receber a areas
            //  - carregar a lista segmentos com os segmentos da area selecionada
        }

        fun setSegmentoHadler(segmentoHandler: SegmentoHandler) {
            this.segmentHandler = segmentoHandler
        }
    }

    class SegmentoHandler(private var spinnerSegmento: Spinner,
                        private var segmentoSelecionado: Segmento,
                        private var context: Context) : Inscrito, AdapterView.OnItemSelectedListener {

        private var segmentoService = SegmentoService(context)
        var segmentosList  : List<Segmento> = mutableListOf(segmentoSelecionado)
        var segmentoStringList : List<String> = mutableListOf<String>(segmentoSelecionado.nome)
        lateinit var arrayAdapter: ArrayAdapter<String>

        init {
            setAdapter()
        }
        fun setAdapter(){
        ArrayAdapter<String>( context,
            R.layout.support_simple_spinner_dropdown_item,
            segmentoStringList)
            .also { arrayAdapter ->
                arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
                spinnerSegmento.adapter = arrayAdapter
                this.arrayAdapter = arrayAdapter}
            this.spinnerSegmento.onItemSelectedListener = this
        }

        override fun evento(response: String?) {
            segmentosList = Gson().fromJson(response, Array<Segmento>::class.java).toList()
            val array = ArrayList<String>()
            for (segmento in segmentosList)
                array.add(segmento.nome)

            segmentoStringList = array
            if (!segmentoStringList.isEmpty())
                segmentoSelecionado = segmentosList[0]
            setAdapter()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            this.segmentoSelecionado = segmentosList.get(position)
        }

        fun find(id: Long) {
            segmentoService.find(this, id)
        }
    }
}
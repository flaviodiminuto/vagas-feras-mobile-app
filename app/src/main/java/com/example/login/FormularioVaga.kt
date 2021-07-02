package com.example.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.adapter.SkillAdapter
import com.google.gson.Gson
import contratos.Inscrito
import model.*
import service.AreaService
import service.SegmentoService
import service.SkillService
import service.VagaService
import java.lang.Exception

class FormularioVaga : AppCompatActivity(), Inscrito{
    //Services
    private lateinit var areaService: AreaService

    lateinit var vaga: Vaga
    lateinit var areaSelecionada: Area
    lateinit var nivelSelecionado: Nivel
    lateinit var segmentoSelecionado: Segmento

    var skillsRequeridasSelecionadas: List<Skill> = mutableListOf()
    var skillsDesejadasSelecionadas: List<Skill> = mutableListOf()
    var skillsRequeridasList: List<Skill> = mutableListOf()
    var skillsDesejadasList: List<Skill> = mutableListOf()

    lateinit var txt_titulo: EditText
    lateinit var txt_descricao: AppCompatEditText
    lateinit var spinnerArea: Spinner
    lateinit var spinnerSegmento: Spinner
    lateinit var spinnerNivel: Spinner
    lateinit var txtRemuneracaoMinima: EditText
    lateinit var txtRemuneracaoMaxima: EditText
    lateinit var skillRequeridaAutoComplete: AutoCompleteTextView
    lateinit var skillDesejadaAutoComplete: AutoCompleteTextView
    lateinit var btnAdicionarSkillRequerida: AppCompatButton
    lateinit var btnAdicionarSkillDesejada: AppCompatButton
    lateinit var skillRequeridaRecycler: RecyclerView //todo - configurar preenchimento
    lateinit var skillDesejadaRecycler: RecyclerView //todo - configurar preenchimento
    lateinit var adapterSkillsRequeridas: SkillAdapter
    lateinit var adapterSkillsDesejadas: SkillAdapter
    lateinit var btnAcao: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_vaga)
        txt_titulo = findViewById(R.id.form_edt_titulo_vaga)
        txt_descricao = findViewById(R.id.form_edt_descricao)
        spinnerArea = findViewById(R.id.form_vaga_spinner_area)
        spinnerSegmento = findViewById(R.id.form_vaga_spinner_segmento)
        spinnerNivel = findViewById(R.id.form_spn_experiencia)
        txtRemuneracaoMinima = findViewById(R.id.form_edt_remuneracao_minima)
        txtRemuneracaoMaxima = findViewById(R.id.form_edt_remuneracao_maxima)
        skillRequeridaAutoComplete = findViewById(R.id.form_edt_skill_requerida)
        skillDesejadaAutoComplete = findViewById(R.id.form_edt_skill_desejada)
        btnAdicionarSkillRequerida = findViewById(R.id.form_vaga_btn_adicionar_skl_requerida)
        btnAdicionarSkillDesejada = findViewById(R.id.form_vaga_btn_adicionar_skl_desejavel)
        skillRequeridaRecycler  = findViewById(R.id.frm_recycler_skills_requeridas)
        skillDesejadaRecycler  = findViewById(R.id.frm_recycler_skills_desejadas)
        btnAcao = findViewById(R.id.form_vaga_action_button)

        areaSelecionada = Area(1, "ar", "")
        nivelSelecionado = Nivel(1, "Estagio")
        segmentoSelecionado = Segmento(1, areaSelecionada, "seg", "")

        adapterSkillsRequeridas = SkillAdapter(skillsRequeridasSelecionadas)
        adapterSkillsDesejadas = SkillAdapter(skillsDesejadasSelecionadas)
        skillRequeridaRecycler.adapter = adapterSkillsRequeridas
        skillRequeridaRecycler.layoutManager = LinearLayoutManager(this)
        skillDesejadaRecycler.adapter = adapterSkillsDesejadas
        skillDesejadaRecycler.layoutManager = LinearLayoutManager(this)

        this.areaService = AreaService(this)

        //todo - o id da vaga seja null
        // isso caracteriza o carregamento da tela para edicao e nao pesquisa
        if(true) {
            prepararParaDivulgar()
        } else {
            prepararParaConsultar()
        }
    }

    private fun prepararParaConsultar() {
        // TODO("Not yet implemented")
        //  Ocultar campos exclusivos de cadastro
        //  Alterar titulos para a condicao de consulta
    }

    private fun prepararParaDivulgar() {
        val areaHandler = AreaHandler(spinnerArea, areaSelecionada, this)
        areaService.findAreas(areaHandler)

        val segmentoHandler = SegmentoHandler(this.spinnerSegmento, this.segmentoSelecionado, this )
        segmentoHandler.find(areaSelecionada.id)

        areaHandler.setSegmentoHadler(segmentoHandler)

        val nivelHandler = NivelHandler(spinnerNivel, this.nivelSelecionado,this)
        val skillRequeridaHandler = SkillHandler(skillsRequeridasList,
                this.skillsRequeridasSelecionadas,
                skillRequeridaAutoComplete,
                this)
        val skillDesejadaHandler = SkillHandler(skillsDesejadasList,
                this.skillsDesejadasSelecionadas,
                skillDesejadaAutoComplete,
                this)

        btnAdicionarSkillRequerida.setOnClickListener {
            skillsRequeridasSelecionadas = skillRequeridaHandler.adicionarSkill()
            adapterSkillsRequeridas.update(skillsRequeridasSelecionadas)
        }
        btnAdicionarSkillDesejada.setOnClickListener {
            skillsDesejadasSelecionadas = skillDesejadaHandler.adicionarSkill()
            adapterSkillsDesejadas.update(skillsDesejadasSelecionadas)
        }
        btnAcao.setOnClickListener{
            val area = areaHandler.areaSelecionada()
            val segmento = segmentoHandler.segmentoSelecionado()
            val nivel = nivelHandler.nivelSelecionado()
            segmentoSelecionado.area = area
            vaga = Vaga.Builder()
                    .titulo(txt_titulo.text.toString())
                    .descricao(txt_descricao.text.toString())
                    .area(area)
                    .segmento(segmento)
                    .nivel(nivel)
                    .remuneracao_minima(("0"+txtRemuneracaoMinima.text.toString()).toDouble())
                    .remumeracao_maxima(("0"+txtRemuneracaoMaxima.text.toString()).toDouble())
                    .requisitos(skillsRequeridasSelecionadas)
                    .desejaveis(skillsDesejadasSelecionadas)
                    .build()
            val vagaService = VagaService(this)
            println(Gson().toJson(vaga))
            vagaService.save(this, vaga)
        }
    }

    private fun toast(texto: String) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
    }

    class NivelHandler(private var spinnerNivel: Spinner,
                       private var nivelSelecionado: Nivel,
                       private var context: Context): AdapterView.OnItemSelectedListener{
        init {
            setNivelAdapter()
        }
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
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            nivelSelecionado = Nivel(position.toLong())
        }

        fun nivelSelecionado(): Nivel {
            return this.nivelSelecionado
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

        private fun setAreaAdapter(){
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
            areaSelecionada = areasList[position]
            segmentHandler.find(areaSelecionada.id)
        }

        override fun evento(response: String?) {
            val list = Gson().fromJson(response,Array<Area>::class.java).toList()
            val array = ArrayList<String>()
            for (aera in list){
                array.add(aera.nome)
            }
            areasList = list
            areasNomes = array
            if(areasList.isNotEmpty()) areaSelecionada = list[0]
            setAreaAdapter()
        }

        fun setSegmentoHadler(segmentoHandler: SegmentoHandler) {
            this.segmentHandler = segmentoHandler
        }

        fun areaSelecionada(): Area { return this.areaSelecionada}
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
            this.segmentoSelecionado = Segmento(null, Area(), "Segmento" , "")
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            this.segmentoSelecionado = segmentosList.get(position)
        }

        fun find(id: Long) {
            segmentoService.find(this, id)
        }

        fun segmentoSelecionado(): Segmento {
            return this.segmentoSelecionado
        }
    }

    class SkillHandler(private var skillList: List<Skill>,
                       private var skillsSelecionadasList: List<Skill>,
                       private var autoCompleteTextView: AutoCompleteTextView,
                       private var context: Context): Inscrito{
        var skillStringList : List<String> = mutableListOf()
        val skillService = SkillService(context)

        init {
            skillService.find(this)
            setAdapter()
        }
        private fun map(){
            skillStringList = skillList.map { skill -> skill.nome }
        }
        private fun setAdapter(){
            map()
            ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, skillStringList)
                    .also { arrayAdapter ->
                        autoCompleteTextView.setAdapter(arrayAdapter)
                    }
        }

        override fun evento(response: String?) {
            this.skillList = Gson().fromJson(response, Array<Skill>::class.java).toList()
            setAdapter()
        }

        fun adicionarSkill(): List<Skill> {
            val skillSelecionada = skillService.byName(skillList, autoCompleteTextView.text.toString())
            if(skillSelecionada != null){
                val set = skillsSelecionadasList.toHashSet()
                set.add(skillSelecionada)
                skillsSelecionadasList = set.toList()
            }else{
                Toast.makeText(context, "Tente selecionar uma skill da lista", Toast.LENGTH_LONG).show()
            }
            return skillsSelecionadasList
        }
    }

    override fun evento(response: String?) {
        try{
            var vaga = Gson().fromJson<Vaga>(response, Vaga::class.java)
            if(vaga.id == null) throw Exception(response)

            toast("Vaga cadastrada\n"+vaga.titulo)
            startActivity(Intent(this, PainelVagas::class.java))
        }catch (ex: Exception){
            Toast.makeText(this, response, Toast.LENGTH_LONG).show()
        }
    }
}
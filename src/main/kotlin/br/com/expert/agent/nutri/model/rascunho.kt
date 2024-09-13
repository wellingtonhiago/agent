package br.com.expert.agent.nutri.model


data class Paciente(
    val id: String,
    var nome: String,
    var idade: Int,
    var peso: Double,
    var altura: Double,
    var imc: Double = calcularIMC(peso, altura),
    var metasNutricionais: MetasNutricionais? = null,
    var historicoMedico: HistoricoMedico? = null
) {
    companion object {
        // Método para calcular o IMC (Índice de Massa Corporal)
        fun calcularIMC(peso: Double, altura: Double): Double {
            return peso / (altura * altura)
        }
    }

    // Método para atualizar o peso e recalcular o IMC
    fun atualizarPeso(novoPeso: Double) {
        peso = novoPeso
        imc = calcularIMC(peso, altura)
    }

    // Método para verificar se o paciente atingiu suas metas nutricionais
    fun atingiuMetasNutricionais(): Boolean {
        metasNutricionais?.let { metas ->
            return peso <= metas.pesoDesejado && imc <= metas.imcDesejado
        }
        return false
    }
}

// Classe para armazenar metas nutricionais de um paciente
data class MetasNutricionais(
    val pesoDesejado: Double,
    val imcDesejado: Double
)

// Classe para armazenar o histórico médico do paciente
data class HistoricoMedico(
    val condicoesPreExistentes: List<String>,
    val medicamentosAtuais: List<String>,
    val alergias: List<String>
)

fun main() {
     println(bonus("Gerentes", 3))
}

fun bonus(cargo: String, experiencia: Int): Double {
    var valor = 0.00
    if (cargo == "Gerentes") {
        valor = if (experiencia < 2) 2000.00 else 3000.00
    } else if (cargo == "Coordenadores") {
        valor = if (experiencia < 1) 1500.00 else 1800.00
    } else if (cargo == "Engenheiros") {
        valor =1000.00
    } else {
        valor = 500.00
    }
    return valor
}
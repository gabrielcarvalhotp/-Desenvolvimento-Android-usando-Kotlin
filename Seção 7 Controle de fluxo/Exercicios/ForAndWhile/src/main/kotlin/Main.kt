import kotlin.math.pow
import kotlin.system.exitProcess

fun main() {
    desafio()
}

fun desafio() {
    var anaAmount = 0.0
    var paulaAmount = 0.0
    var months = 0

    do {
        anaAmount += 10000.0 * 0.05 * 2
        anaAmount *= 1 + 0.002
        paulaAmount += 10000.0 * 0.05
        paulaAmount *= 1 + 0.008
        months++
    } while (paulaAmount <= anaAmount)
    println("Patrimonio de Paula: $paulaAmount")
    println("Patrimonio de Ana: $anaAmount")
    println("O patrimonio de Paula vai superar o patrimonio de Ana em: $months meses")
}
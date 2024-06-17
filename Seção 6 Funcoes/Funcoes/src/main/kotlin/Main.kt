import kotlin.math.sqrt

fun main() {
    detalhesAno(2)

    val nome = "Gabriel Carvalho de oliveira"
    print(qtdeCaracteres(nome))

    println(elevarCubo(2.0))

    println(milhasEmQuilometros(1))

    println(replaceStr(nome))
}

fun detalhesAno(ano: Int) {
    val meses = ano * 12
    val dias = ano * 365
    val horas = dias * 24
    val minutos = horas * 60
    val segundos = minutos * 60

    println("$ano anos equivalem a:")
    println("$meses meses")
    println("$dias dias")
    println("$horas horas")
    println("$minutos minutos")
    println("$segundos segundos")
}

fun qtdeCaracteres(str: String) = str.length

fun elevarCubo(n: Double) = Math.pow(n, 3.0);

fun milhasEmQuilometros(n: Int): Double = n * 1.6

fun replaceStr(str: String) = str.replace("A", "x", true).lowercase()

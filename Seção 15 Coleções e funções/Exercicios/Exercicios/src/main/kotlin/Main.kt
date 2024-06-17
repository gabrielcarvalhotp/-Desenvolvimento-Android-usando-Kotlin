import entities.UsersLog
import java.io.BufferedReader
import java.io.FileReader
import java.time.Instant
import java.util.Scanner

fun main() {
    exercise2()
}

fun exercise1() {
    val scanner = Scanner(System.`in`)
    scanner.use { scanner ->
        println("Enter file full path: ")
        val path = scanner.nextLine()
        val bufferedReader = BufferedReader(FileReader(path))
        val set = mutableSetOf<UsersLog>()
        var line = bufferedReader.readLine()
        while (line != null) {
            set.add(UsersLog(
                line.split(" ")[0],
                Instant.parse(line.split(" ")[1])))
            line = bufferedReader.readLine()
        }
        println("Total users: " + set.size);
        bufferedReader.close()
    }
}

fun exercise2() {
    val scanner = Scanner(System.`in`)
    scanner.use {
        val set = mutableSetOf<Int>()

        arrayOf("A", "B", "C").forEach {
            print("How many students for course $it? ")
            for (i in 1..scanner.nextInt())
                set.add(scanner.nextInt())
        }
        println("Total students: ${set.size})")
    }
}
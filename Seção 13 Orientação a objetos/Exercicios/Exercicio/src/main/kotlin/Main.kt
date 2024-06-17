import entities.Employee
import entities.Rectangle

fun main() {
    exercise2()
}

fun exircise1() {
    println("Enter rectangle width and height: ")
    val rectangle = Rectangle()
    rectangle.width = readln().toDouble()
    rectangle.height = readln().toDouble()

    println("AREA = ${rectangle.area()}")
    println("PERIMETER = ${rectangle.perimeter()}")
    println("DIAGONAL = ${rectangle.diagonal()}")
}

fun exercise2() {
    val employee = Employee()
    println("Name: ")
    employee.name = readln()
    println("Gross salary: ")
    employee.grossSalary = readln().toDouble()
    println("Tax: ")
    employee.tax = readln().toDouble()

    println(employee)

    print("Which percentage to increase salary? ")
    employee.increaseSalary(readln().toDouble())
    println()
    println("Updated data: $employee ")
}
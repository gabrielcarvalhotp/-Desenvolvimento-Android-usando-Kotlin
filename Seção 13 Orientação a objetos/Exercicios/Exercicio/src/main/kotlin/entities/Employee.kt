package entities

class Employee {
    var name: String = ""
    var grossSalary: Double = 0.0
    var tax: Double = 0.0

    fun netSalary(): Double {
        return grossSalary - tax
    }

    fun increaseSalary(percentage: Double) {
        grossSalary += grossSalary * (percentage / 100)
    }

    override fun toString(): String {
        return "Employee: $name, $ ${String.format("%.2f", netSalary())} "
    }
}
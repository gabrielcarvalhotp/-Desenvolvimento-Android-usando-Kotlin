class WhileExercicies {

    companion object {

        fun exercicio1() {
            var waterTankCapacity = 2000
            var quantity = 0
            while (waterTankCapacity > 0)  {
                waterTankCapacity -= 7
                quantity++
            }
            println("$quantity balões de água")
        }

        fun exercicio2() {
            var i = 1
            while (i <= 50) {
                if (i % 3 == 0) {
                    if (i % 5 == 0)
                        println("$i FizzBuzz")
                     else
                        println("$i Buzz")

                } else if (i % 5 == 0) {
                    println("$i Fizz")
                }
                i++
            }
        }

        fun exercicio3() {
            val name = readlnOrNull()
            if (name != null) {
                var i = 0
                while (i < name.length) {
                    print(name[name.length - i - 1])
                    i++
                }
            }
        }

        fun exercicio4() {
            val name = readlnOrNull()
            if (name != null) {
                var x = 0
                var o = 0
                for (char in name) {
                    if (char == 'x')
                        x++
                    else if (char == 'o')
                        o++
                }
                println("$name -> ${x == o}")
            }
        }


    }

}
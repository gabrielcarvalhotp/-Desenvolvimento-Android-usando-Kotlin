 class ForExercicies {

     companion object {

         fun exercicio1() {
             for (i in 1..50) {
                 print("$i ")
             }
         }

         fun exercicio2() {
             for (i in 50 downTo 1) {
                 print("$i ")
             }
         }

         fun exercicio3() {
             for (i in 1..50) {
                 if (i % 5 != 0)
                     print("$i ")
             }
         }

         fun exercicio4() {
             var sum = 0
             for (i in 1..50) {
                 sum += i
             }
             println(sum)
         }

         fun exercicio5() {
             val number = readlnOrNull()
             if (number != null) {
                 for (i in 1..number.toInt()) {
                     for (j in 1..i) {
                         print("# ")
                     }
                     println()
                 }
             }
         }

     }

}
package com.jppin.calculadoraimc


fun squaret(rows: Int) {
    for (i in 1..rows) {
        for (j in 1..i) {
            print("* ")
        }
        println()
    }
}
fun main() {
    val rows = 5
    squaret(rows)
}

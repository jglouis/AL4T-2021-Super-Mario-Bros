package be.ecam.closure

fun getCounter(): () -> Unit {
    var counter = 0
    return { println(counter++) }
}

fun counterScenario() {
    val counter = getCounter()

    counter()
    counter()
    counter()
    counter()
}
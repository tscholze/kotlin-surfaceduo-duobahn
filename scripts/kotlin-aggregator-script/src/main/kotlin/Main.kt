import api.AutobahnApiRepository

suspend fun main(args: Array<String>) {
    println("DuoBahn Parser")
    println("-------------------")
    println()

    // Trigger fetching.
    AutobahnApiRepository().fetchAutobahns()
}


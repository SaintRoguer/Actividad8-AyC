import kotlin.random.Random


fun main() {
    var cantPuntos = -1
    while(cantPuntos>1000000000 || cantPuntos<0) {
        println("Ingrese la cantidad de puntos")
        cantPuntos = Integer.parseInt(readLine())
        if(cantPuntos>1000000000 || cantPuntos<0)
            println("La cantidad de puntos es invalida")
    }
    val arregloPuntos = mutableListOf<Punto>()
    for(index in 0 until cantPuntos) {
        val x = (Random.nextFloat() + Random.nextInt(100))
        val y = Random.nextFloat() + Random.nextInt(100)
        arregloPuntos.add(Punto(x,y))
    }
        val test = PuntoMasCercano()
        val parPuntos = test.fuerzaBrutaPMC(arregloPuntos)
        println(parPuntos[0].toString())
        println(parPuntos[1].toString())

}

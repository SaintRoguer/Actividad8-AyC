import java.util.concurrent.TimeUnit
import kotlin.jvm.internal.Intrinsics
import kotlin.random.Random
import kotlin.time.TimeSource

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
        val x = (Random.nextDouble(0.0,1000000000.0))
        val y = (Random.nextDouble(0.0,1000000000.0))
        arregloPuntos.add(Punto(x,y))
    }
    for(index in arregloPuntos)
        println(index.toString())

    val test = PuntoMasCercano()
    var tiempoInicial = System.nanoTime()
    val parPuntos = test.fuerzaBrutaPMC(arregloPuntos)
    var tiempoFinal = System.nanoTime()-tiempoInicial
    println("punto inicial: "+parPuntos[0].toString())
    println("punto final: "+parPuntos[1].toString())
    println("Tiempo de ejecucion: " + tiempoFinal)

}

import java.lang.Math.pow
import java.util.concurrent.TimeUnit
import kotlin.jvm.internal.Intrinsics
import kotlin.math.pow
import kotlin.random.Random
import kotlin.time.TimeSource
import Punto
import PuntoMasCercano

fun main() {
    val minRange = 0.0
    val maxRange = 10.0.pow(9.0)
    var cantPuntos = -1
    while(cantPuntos>1000000000 || cantPuntos<0) {
        println("Ingrese la cantidad de puntos")
        cantPuntos = Integer.parseInt(readLine())
        if(cantPuntos>1000000000 || cantPuntos<0)
            println("La cantidad de puntos es invalida")
    }
    val arregloPuntos = mutableListOf<Punto>()
    for(index in 0 until cantPuntos) {
        val x = (Random.nextDouble(maxRange))
        val y = (Random.nextDouble(maxRange))
        arregloPuntos.add(Punto(x, y))
    }
    for(index in arregloPuntos)
        println(index.toString())

    val test = PuntoMasCercano()
    val tiempoInicial = System.nanoTime()
    val parPuntos = test.dycPMCsinOrdenamientoY(arregloPuntos)
    val tiempoFinal = System.nanoTime()-tiempoInicial
    println("Tiempo de ejecucion: $tiempoFinal")
    println("Distancia Minima: $parPuntos")

}

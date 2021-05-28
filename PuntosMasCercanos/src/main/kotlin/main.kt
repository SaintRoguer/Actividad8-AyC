import java.lang.Math.pow
import java.util.concurrent.TimeUnit
import kotlin.jvm.internal.Intrinsics
import kotlin.math.pow
import kotlin.random.Random
import kotlin.time.TimeSource

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
    var arregloPuntos = mutableListOf<Punto>()
  /*  for(index in 0 until cantPuntos) {
        val x = (Random.nextDouble(minRange, maxRange))
        val y = (Random.nextDouble(minRange, maxRange))
        arregloPuntos.add(Punto(x,y))
    }*/
    arregloPuntos.add(Punto(1.0,3.0))
    arregloPuntos.add(Punto(1.0,3.0))
    arregloPuntos.add(Punto(1.0,3.0))
    arregloPuntos.add(Punto(1.0,3.0))
    arregloPuntos = mutableListOf(Punto(1.1,3.0),Punto(1000000.0,3000000.0),Punto(2.0,4.0),Punto(1968875.0,334526434.0))
    for(index in arregloPuntos)
        println(index.toString())

    val test = PuntoMasCercano()
    var tiempoInicial = System.nanoTime()
    val parPuntos = test.dycPMCconOrdenamiento(arregloPuntos)
    var tiempoFinal = System.nanoTime()-tiempoInicial
    println("Tiempo de ejecucion: " + tiempoFinal)
    println("Distancia Minima: "+parPuntos)

}

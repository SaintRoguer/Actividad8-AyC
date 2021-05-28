import org.jetbrains.annotations.TestOnly
import kotlin.math.pow
import kotlin.math.sqrt
import Punto

class PuntoMasCercano {
   // @Rule
   // val benchmarkRule = BenchmarkRule()
    @TestOnly
    fun dycPMCsinOrdenamiento(puntos: List<Punto>): List<Punto>{
        var pmc = listOf<Punto>()
        if (puntos.size <= 8){ //esta condicion se deberia cumplir a la primera division????
            pmc = fuerzaBrutaPMC(puntos)
        }
        else
        {
            val puntosIzq = puntos.subList(0,puntos.size/2)
            val puntosDer = puntos.subList((puntos.size/2)+1,puntos.size)
            dycPMCsinOrdenamiento(puntosIzq)
            dycPMCsinOrdenamiento(puntosDer)
            // pmc = algoritmo de merge, lo picante
        }
        return pmc

    }
    fun dycPMCconOrdenamiento(puntos: List<Punto>): List<Punto>{
        var pmc = listOf<Punto>()
        // puntos = puntos.sortedWith(ComparadorPunto)
        if (puntos.size <= 8){ //esta condicion se deberia cumplir a la primera division????
            pmc = fuerzaBrutaPMC(puntos)
        }
        else
        {
            val puntosIzq = puntos.subList(0,puntos.size/2)
            val puntosDer = puntos.subList((puntos.size/2)+1,puntos.size)
            dycPMCsinOrdenamiento(puntosIzq)
            dycPMCsinOrdenamiento(puntosDer)
            // pmc = algoritmo de merge, lo picante
        }
        return pmc

    }
    fun fuerzaBrutaPMC(lista: List<Punto>): List<Punto>{
        var distancia = Double.MAX_VALUE
        var puntoInicial = Punto(0.0, 0.0)
        var puntoFinal = Punto(0.0, 0.0)
        if(lista.size == 1) {
            distancia = 0.0
            puntoFinal = lista[0]
            puntoInicial = puntoFinal
        }
        else
        for(index in lista.indices){
            var distanciaActual = 0.0
            for(i in index+1 until lista.size){
                if(lista.lastIndex != index)
                    distanciaActual = distanciaEntreDosPuntos(lista[index],lista[i])
                if(distanciaActual<distancia) {
                    distancia = distanciaActual
                    puntoInicial = lista[index]
                    puntoFinal = lista[i]
                }
            }
        }
        val puntosMasCercanos = mutableListOf<Punto>()
        puntosMasCercanos.add(puntoInicial)
        puntosMasCercanos.add(puntoFinal)
        return puntosMasCercanos
    }

    private fun distanciaEntreDosPuntos(punto1: Punto, punto2: Punto): Double =
        sqrt(
            (punto1.x.toDouble() - punto2.x.toDouble()).pow(2.0) +
               (punto1.y.toDouble() - punto2.y.toDouble()).pow(2.0)
        )

}
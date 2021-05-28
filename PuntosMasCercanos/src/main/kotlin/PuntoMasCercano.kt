import kotlin.math.pow
import kotlin.math.sqrt

class PuntoMasCercano {



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
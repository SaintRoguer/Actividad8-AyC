import kotlin.math.pow
import kotlin.math.sqrt

class PuntoMasCercano {



    public fun fuerzaBrutaPMC(lista: List<Punto>): Punto,Punto{
        var distancia = Float.MAX_VALUE
        var puntoInicial = Punto(Float.MAX_VALUE, Float.MAX_VALUE)
        var puntoFinal = Punto(Float.MAX_VALUE, Float.MAX_VALUE)
        for(index in lista.indices){
            var distanciaActual = 0f
            for(i in index+1 .. lista.size)
                if(lista.lastIndex != index)
                    distanciaActual = distanciaEntreDosPuntos(lista[index],lista[i])
                if(distanciaActual<distancia) {
                    distancia = distanciaActual
                    puntoInicial = lista[index]
                    puntoFinal = lista[i]
                }
        }
        return puntoInicial,puntoFinal
    }

    private fun distanciaEntreDosPuntos(punto1: Punto ,punto2: Punto):Float =
        sqrt(
            (punto1.x.toDouble() - punto2.x.toDouble()).pow(2.0) +
                    (punto1.y.toDouble() - punto2.y.toDouble()).pow(2.0)
        ).toFloat()

}
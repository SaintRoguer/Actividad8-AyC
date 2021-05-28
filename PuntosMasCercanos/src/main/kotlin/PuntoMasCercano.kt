import org.jetbrains.annotations.TestOnly
import kotlin.math.pow
import kotlin.math.sqrt
import Punto
import kotlin.math.abs
import kotlin.math.min

class PuntoMasCercano {

    fun dycPMCsinOrdenamientoY(puntos: List<Punto>): Double{
       var pmc: Double
       if (puntos.size <= 3){
           pmc = fuerzaBrutaPMC(puntos)
        }
       else {
            var puntosOrdenadosEnX = puntos.sortedWith(ComparadorPuntoX)
            val puntosIzq = puntosOrdenadosEnX.subList(0,puntos.size/2)
            val puntosDer = puntosOrdenadosEnX.subList((puntos.size/2),puntos.size)
            var distanciaIzq = dycPMCsinOrdenamientoY(puntosIzq)
            var distanciaDer = dycPMCsinOrdenamientoY(puntosDer)
            pmc = min(distanciaIzq,distanciaDer)

            val puntoMedio = puntosOrdenadosEnX[puntos.size/2]
            val puntosOrdenadosEnY= puntos.sortedWith(ComparadorPuntoY)
            var puntosEnLaFranja = mutableListOf<Punto>()
            var elementoActual = 0
            for (i in puntosOrdenadosEnY.indices) {
                if (abs(puntosOrdenadosEnY[i].x - puntoMedio.x) < pmc){
                    puntosEnLaFranja.add(elementoActual,puntosOrdenadosEnY[i])
                    elementoActual++
                }
            }
            pmc = distanciaMinFranja(puntosEnLaFranja,pmc)
       }
       return pmc
    }

    private fun distanciaMinFranja(puntosEnLaFranja: MutableList<Punto>, distanciaMinima: Double): Double {
        var minDist = distanciaMinima
        for (i in puntosEnLaFranja.indices){
            var j = i
            while(j<puntosEnLaFranja.size-1 &&(puntosEnLaFranja[j+1].y - puntosEnLaFranja[j].y<=distanciaMinima)){
                minDist=min(distanciaMinima,distanciaEntreDosPuntos(puntosEnLaFranja[j],puntosEnLaFranja[j+1]))
                j++
            }
        }
        return minDist
    }

    fun dycPMCconOrdenamiento(puntos: List<Punto>): Double{
        var pmc = Double.MAX_VALUE
        val puntosOrdenadosEnX = puntos.sortedWith(ComparadorPuntoX)
        val puntosOrdenadosEnY = puntos.sortedWith(ComparadorPuntoY)
        pmc = PMCconListasOrdenadas(puntosOrdenadosEnX,puntosOrdenadosEnY)
        return pmc
    }

    private  fun PMCconListasOrdenadas(puntosOrdenadosEnX: List<Punto>, puntosOrdenadosEnY: List<Punto>): Double{
        var pmc: Double
        if (puntosOrdenadosEnX.size <= 3){
            pmc = fuerzaBrutaPMC(puntosOrdenadosEnX)
        }
        else {
            val puntosIzq = puntosOrdenadosEnX.subList(0,puntosOrdenadosEnX.size/2)
            val puntosDer = puntosOrdenadosEnX.subList((puntosOrdenadosEnX.size/2),puntosOrdenadosEnX.size)
            var distanciaIzq = dycPMCsinOrdenamientoY(puntosIzq)
            var distanciaDer = dycPMCsinOrdenamientoY(puntosDer)
            pmc = min(distanciaIzq,distanciaDer)

            val puntoMedio = puntosOrdenadosEnX[puntosOrdenadosEnX.size/2]
            var puntosEnLaFranja = mutableListOf<Punto>()
            var elementoActual = 0
            for (i in puntosOrdenadosEnY.indices) {
                if (abs(puntosOrdenadosEnY[i].x - puntoMedio.x) < pmc){
                    puntosEnLaFranja.add(elementoActual,puntosOrdenadosEnY[i])
                    elementoActual++
                }
            }
            pmc = distanciaMinFranja(puntosEnLaFranja,pmc)
        }
        return pmc
    }

    fun fuerzaBrutaPMC(lista: List<Punto>): Double{
        var distancia = Double.MAX_VALUE
        //var puntoInicial = Punto(0.0, 0.0)
        // var puntoFinal = Punto(0.0, 0.0)
        if(lista.size == 1 || lista.isEmpty()) {
            distancia = 0.0
           // puntoFinal = lista[0]
           // puntoInicial = puntoFinal
        }
        else
        for(index in lista.indices){
            var distanciaActual = 0.0
            for(i in index+1 until lista.size){
                if(lista.lastIndex != index)
                    distanciaActual = distanciaEntreDosPuntos(lista[index],lista[i])
                if(distanciaActual<distancia) {
                    distancia = distanciaActual
                   // puntoInicial = lista[index]
                   //  puntoFinal = lista[i]
                }
            }
        }
       // val puntosMasCercanos = mutableListOf<Punto>()
       // puntosMasCercanos.add(puntoInicial)
       // puntosMasCercanos.add(puntoFinal)
        return distancia
    }

    private fun distanciaEntreDosPuntos(punto1: Punto, punto2: Punto): Double =
        sqrt(
            (punto1.x.toDouble() - punto2.x.toDouble()).pow(2.0) +
               (punto1.y.toDouble() - punto2.y.toDouble()).pow(2.0)
        )

}
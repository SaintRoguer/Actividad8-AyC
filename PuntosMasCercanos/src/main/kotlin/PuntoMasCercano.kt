import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.abs
import kotlin.math.min
import Punto

class PuntoMasCercano {

    fun dycPMCsinOrdenamientoY(puntos: List<Punto>): PuntosYDistancia{
        var pmc: PuntosYDistancia
        if (puntos.size <= 3){
           pmc = fuerzaBrutaPMC(puntos)
        }
       else {
            val puntosOrdenadosEnX = puntos.sortedWith(ComparadorPuntoX)
            val puntosIzq = puntosOrdenadosEnX.subList(0,puntos.size/2)
            val puntosDer = puntosOrdenadosEnX.subList((puntos.size/2),puntos.size)
            val distanciaIzq = dycPMCsinOrdenamientoY(puntosIzq)
            val distanciaDer = dycPMCsinOrdenamientoY(puntosDer)
            pmc = if(distanciaIzq.distancia<=distanciaDer.distancia)
                distanciaIzq
            else
                distanciaDer

            val puntoMedio = puntosOrdenadosEnX[puntos.size/2]
            val puntosOrdenadosEnY= puntos.sortedWith(ComparadorPuntoY)
            val puntosEnLaFranja = mutableListOf<Punto>()
            var elementoActual = 0
            for (i in puntosOrdenadosEnY.indices) {
                if (abs(puntosOrdenadosEnY[i].x - puntoMedio.x) < pmc.distancia){
                    puntosEnLaFranja.add(elementoActual,puntosOrdenadosEnY[i])
                    elementoActual++
                }
            }
            pmc = distanciaMinFranja(puntosEnLaFranja,pmc)
       }
       return pmc
    }

    private fun distanciaMinFranja(
        puntosEnLaFranja: MutableList<Punto>,
        distanciaMinima: PuntosYDistancia
    ): PuntosYDistancia {

        for (i in puntosEnLaFranja.indices) {
            var j = i
            while (j < puntosEnLaFranja.size - 1 && (puntosEnLaFranja[j + 1].y - puntosEnLaFranja[j].y <= distanciaMinima.distancia)) {
                if (distanciaMinima.distancia > min(
                        distanciaMinima.distancia,
                        distanciaEntreDosPuntos(puntosEnLaFranja[j], puntosEnLaFranja[j + 1])
                    )
                ) {
                    distanciaMinima.distancia =
                        min(
                            distanciaMinima.distancia,
                            distanciaEntreDosPuntos(puntosEnLaFranja[j], puntosEnLaFranja[j + 1])
                        )
                    if (distanciaMinima.distancia < distanciaEntreDosPuntos(
                            puntosEnLaFranja[j],
                            puntosEnLaFranja[j + 1]
                        )
                    ) {
                        distanciaMinima.puntoUno = distanciaMinima.puntoUno
                        distanciaMinima.puntoDos = distanciaMinima.puntoDos
                    } else {
                        distanciaMinima.puntoUno = puntosEnLaFranja[j]
                        distanciaMinima.puntoDos = puntosEnLaFranja[j + 1]
                    }

                }
                j++
            }
        }
        return distanciaMinima
    }

    fun dycPMCconOrdenamiento(puntos: List<Punto>): PuntosYDistancia{
        val pmc: PuntosYDistancia
        val puntosOrdenadosEnX = puntos.sortedWith(ComparadorPuntoX)
        val puntosOrdenadosEnY = puntos.sortedWith(ComparadorPuntoY)
        pmc = pMCconListasOrdenadas(puntosOrdenadosEnX,puntosOrdenadosEnY)
        return pmc
    }

    private  fun pMCconListasOrdenadas(puntosOrdenadosEnX: List<Punto>, puntosOrdenadosEnY: List<Punto>): PuntosYDistancia{
        var pmc: PuntosYDistancia
        if (puntosOrdenadosEnX.size <= 3){
            pmc = fuerzaBrutaPMC(puntosOrdenadosEnX)
        }
        else {
            val puntosIzq = puntosOrdenadosEnX.subList(0,puntosOrdenadosEnX.size/2)
            val puntosDer = puntosOrdenadosEnX.subList((puntosOrdenadosEnX.size/2),puntosOrdenadosEnX.size)
            val distanciaIzq = dycPMCconOrdenamiento(puntosIzq)
            val distanciaDer = dycPMCconOrdenamiento(puntosDer)
            pmc = if(distanciaIzq.distancia<=distanciaDer.distancia)
                distanciaIzq
            else
                distanciaDer

            val puntoMedio = puntosOrdenadosEnX[puntosOrdenadosEnX.size/2]
            val puntosEnLaFranja = mutableListOf<Punto>()
            var elementoActual = 0
            for (i in puntosOrdenadosEnY.indices) {
                if (abs(puntosOrdenadosEnY[i].x - puntoMedio.x) < pmc.distancia){
                    puntosEnLaFranja.add(elementoActual,puntosOrdenadosEnY[i])
                    elementoActual++
                }
            }
            pmc = distanciaMinFranja(puntosEnLaFranja,pmc)
        }
        return pmc
    }

    fun fuerzaBrutaPMC(lista: List<Punto>): PuntosYDistancia{
        var distancia = Double.MAX_VALUE
        var puntosYDistancia = PuntosYDistancia(Punto(-1.0,-1.0),Punto(-1.0,-1.0),0.0)
        if(lista.isNotEmpty()) {
            if (lista.size == 1) {
                puntosYDistancia = PuntosYDistancia(lista[0], lista[0], 0.0)
            } else
                for (index in lista.indices) {
                    var distanciaActual = 0.0
                    for (i in index + 1 until lista.size) {
                        if (lista.lastIndex != index)
                            distanciaActual = distanciaEntreDosPuntos(lista[index], lista[i])
                        if (distanciaActual < distancia) {
                            distancia = distanciaActual
                            puntosYDistancia.puntoUno = lista[index]
                            puntosYDistancia.puntoDos = lista[i]
                            puntosYDistancia.distancia = distanciaActual
                        }
                    }
                }
        }
        return puntosYDistancia
    }

    private fun distanciaEntreDosPuntos(punto1: Punto, punto2: Punto): Double =
        sqrt(
            (punto1.x - punto2.x).pow(2.0) +
               (punto1.y - punto2.y).pow(2.0)
        )

}
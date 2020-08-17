import java.io.File
import java.lang.Exception
import java.lang.Double.parseDouble

fun isNumeric(string: String): Boolean {
    try {
        val num = parseDouble(string)
    } catch (e: NumberFormatException) {
        return false
    }
        return true
}

fun findMedia(numeros: MutableList<String>): String{
    var total = 0.0
    var size = 0

    for (i in numeros) {
        if (isNumeric(i)) {
            size += 1
            total += i.toDouble()
        }
    }

    total /= size

    return total.toInt().toString()
}

fun findModa(info: MutableList<String>): String {
    var maximum = 0
    var realMaximum = 0
    var moda = ""

    for (inf in info) {

        for (i in info) {
            if (inf.equals(i)) {
                maximum += 1
            }
        }

        if (maximum > realMaximum) {
            realMaximum = maximum
            moda = inf
        }

        maximum = 0
    }

    return moda
}

fun readEstimateCorrect(fileName: String, delimiter: String, emptySpace: String): ArrayList<MutableList<String>>{
    val falseObj = arrayListOf<MutableList<String>>()

    try {
        val lines: List<String> = File(fileName).readLines()
        val objAnalise = arrayListOf<MutableList<String>>()  // Isso é uma lista de listas de Strings
        val quickArray: MutableList<String> = mutableListOf()

        for (line in lines) { // Loop para inserir cada entrada como uma lista de strings em uma lista de listas de strings
            objAnalise.add((line.split(delimiter) as MutableList<String>))
        }

        for(j in objAnalise.indices) { // Loop para verificar e corrigir os valores faltando

            for (k in objAnalise[j].indices) {

                if (objAnalise[j][k] == emptySpace) {  // Condicional para saber se aquela informação se iguala a nossa incognita para valor ausente

                    for (x in objAnalise.indices) {
                        quickArray.add(objAnalise[x][k])
                    }

                    if (j == 0 || j == 1) {

                        for (l in objAnalise.indices) {

                                if (objAnalise[j + l][k] != emptySpace) {

                                    if (isNumeric(objAnalise[j + l][k])) {
                                        objAnalise[j][k] =  findMedia(quickArray) //"1291029"
                                    } else {
                                        objAnalise[j][k] =  findModa(quickArray)  //"HELLOBIACH"
                                    }
                                    quickArray.clear()
                                    break
                                }
                        }

                    } else {

                        for (l in objAnalise.indices) {

                            if (objAnalise[j - l][k] != emptySpace) {

                                if (isNumeric(objAnalise[j - l][k])) {
                                    objAnalise[j][k] = findMedia(quickArray) //"1291029"
                                } else {
                                    objAnalise[j][k] = findModa(quickArray)  //"HELLOBIACH"
                                }

                                quickArray.clear()
                                break
                            }

                        }

                    }
                }
            }

        }

        return objAnalise

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return falseObj
}

fun writeToFile(info: ArrayList<MutableList<String>>, fileName: String) {

    val myfile = File(fileName)

    myfile.bufferedWriter().use { out ->

        for (i in info.indices) {

            for (j in info[i].indices) {

                out.write(info[i][j])

                if (j != info[i].lastIndex) {
                    out.write(",")
                }
            }

            out.write("\n")
        }

    }

    println("File writen")

}

fun main() {
    val arquivo = readEstimateCorrect("archives/adult-stretch.csv", ",", "?") /* Arquivo pra ser lido, delimitador e como o arquivo r
                                                                                                    epresenta os valores faltando ( no caso da maioria é ? pelo que eu vi )
                                                                                                   */
    writeToFile(arquivo,"results/adult-stretchDummy2.csv") /* Aqui ele vai criar um arquivo novo e escrever
                                                            nele ou escrever por cima de um existente
                                                           */
}
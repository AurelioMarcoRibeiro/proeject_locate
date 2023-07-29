package reorganize

import java.util.*

data class AvailableMovie(val name: String, val year: Int, val price: Double) {
    override fun toString(): String {
        return "Nome: $name, Ano: $year, Preço: R$%.2f".format(price)
    }
}

fun listMovies(movies: List<AvailableMovie>, cust: List<Movie>) {

    if (movies.isEmpty()) {
        println("Não há filmes disponíveis. Pressione enter para voltar ao menu principal.")
    return
    } else {
        println("Lista de filmes.")
        movies.forEachIndexed { index, movie ->
            val price = movies[index].price
            println("${index + 1} - $movie -")
        }
    }
}

fun registerMovie(scanner: Scanner, movies: MutableList<AvailableMovie>, cust: MutableList<Movie>) {
    println("Cadastro de Filmes.")

    println("Digite o nome do filme:")
    val name = scanner.nextLine()

    println("Digite o ano de lançamento do filme:")
    val year = scanner.nextInt()
    scanner.nextLine()

    val featuresMovie = AvailableMovie(name, year, price = moviePriceCalculation(year, movies))
    movies.add(featuresMovie)

    val movieInformation = Movie(movie = featuresMovie)
    cust.add(movieInformation)

    println("Filme cadastrado com sucesso!")

    listMovies(movies, cust)
}
fun moviePriceCalculation(year: Int, movies: MutableList<AvailableMovie>): Double {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val yearsToCurrentYear = currentYear - year

    return when {
        yearsToCurrentYear <= 1 -> 15.0
        yearsToCurrentYear <= 5 -> 10.0
        else -> 5.0
    }
}
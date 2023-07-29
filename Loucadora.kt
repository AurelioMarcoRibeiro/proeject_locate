package reorganize

import java.util.*

class Loucadora {

    fun returnLocator(movie: MovieInfo) {
        movie.rented = false
        movie.rentedBy = ""
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val scanner = Scanner(System.`in`)
            val movies = mutableListOf<AvailableMovies>()
            val clients = mutableListOf<Customer>()
            val adress = mutableListOf<Adress>()
            val rented = mutableListOf<MovieInfo>()

            var menu = true
            println("Bem-vindo(a) à Locadora!")
            while (menu) {
                println("O que deseja fazer?")
                println("1 - Alugar Filme.")
                println("2 - Cadastrar Filme.")
                println("3 - Cadastrar Cliente.")
                println("4 - Devolver Filme.")
                println("5 - Sair.")

                val option = scanner.nextLine()

                when (option) {
                    "1" -> {
                        do {
                            rentMovie(scanner, movies, clients, rented)
                            val answer = scanner.nextLine()
                        } while (answer.equals("S", ignoreCase = true))
                    }

                    "2" -> {
                        do {
                            registerMovie(scanner, movies, rented)
                            println("Deseja cadastrar outro filme? S/N")
                            val answer = scanner.nextLine()
                        } while (answer.equals("S", ignoreCase = true))
                    }

                    "3" -> {
                        do {
                            registerClient(scanner, clients, adress)
                            println("Deseja cadastrar mais clientes? S/N")
                            val answer = scanner.nextLine()
                        } while (answer.equals("S", ignoreCase = true))
                    }

                    "4" -> {
                        returnMovie(scanner, movies, clients, rented)
                    }

                    "5" -> {
                        menu = false
                        println("Encerrando!")
                    }

                    else -> {
                        println("Opção inválida. Tente novamente.")

                    }
                }
            }
        }
    }
}


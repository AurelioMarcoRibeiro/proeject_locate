package reorganize

import java.util.*

class Loucadora {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val scanner = Scanner(System.`in`)
            val movies = mutableListOf<AvailableMovie>()
            val customers = mutableListOf<Customer>()
            val rentedMovies = mutableListOf<Movie>()

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
                            rentMovie(scanner, movies, customers, rentedMovies)
                            val answer = scanner.nextLine()
                        } while (answer.equals("S", ignoreCase = true))
                    }

                    "2" -> {
                        do {
                            registerMovie(scanner, movies, rentedMovies)
                            println("Deseja cadastrar outro filme? S/N")
                            val answer = scanner.nextLine()
                        } while (answer.equals("S", ignoreCase = true))
                    }

                    "3" -> {
                        do {
                            val customer = Customer.create(scanner)
                            customers.add(customer)
                            println("Deseja cadastrar mais clientes? S/N")
                            val answer = scanner.nextLine()
                        } while (answer.equals("S", ignoreCase = true))
                    }

                    "4" -> {

                        println("Devolução de Filme.")

                        println("Digite o CPF do cliente:")
                        val clientCPF = scanner.nextLine()

                        val clientFound = customers.find { it.cpf == clientCPF }
                        if (clientFound == null) {
                            println("Cliente com CPF '$clientCPF' não encontrado.")
                        }else{
                            val rentedMoviesByCustomer = Movie.findMoviesByCustomer(clientFound, rentedMovies)
                            Movie.returnMovie(scanner, rentedMoviesByCustomer)
                        }
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


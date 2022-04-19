//package cinema

data class theSeat (var theIndexX: Int, var theIndexY: Int, var theData: String, var thePrice: Int)

fun main() {
    // write your code here
    var checkpoint = false

    var (seatsY, seatsX) = getCinemaSize()

    var theCinema = MutableList(seatsY+1) {  MutableList(seatsX+1) {theSeat(0,0," ", 0)} }

    theCinema = setRoom(theCinema,seatsY,seatsX)

    while (!checkpoint) {
        var theChoice = 0
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        theChoice = readln().toInt()

        when (theChoice) {
            1 -> {
                //Show Seats
                drawRoom(theCinema,seatsY,seatsX)
            }
            2 -> {
                //Buy Ticket
                theCinema = seatReservation(theCinema)
            }
            3 -> {
                //Statistics
                getStatistics(theCinema,seatsY,seatsX)
            }
            0 -> checkpoint = true //exit
        }
    }
}
fun getStatistics(theCinema: MutableList<MutableList<theSeat>>, seatsY: Int, seatsX: Int) {
    var soldSeats = 0
    var currentIncome = 0
    var totalIncome = 0


    for (y in 1 .. seatsY) {
        for (x in 1 .. seatsX) {
            totalIncome += theCinema[y][x].thePrice
        }
    }
    for (y in 1 .. seatsY) {
        for (x in 1 .. seatsX) {
            if (theCinema[y][x].theData == "B") {
                currentIncome += theCinema[y][x].thePrice
                soldSeats++
            }
        }
    }
    var soldPercentage = (soldSeats  / (seatsX * seatsY).toDouble()) * 100
    println()
    println("Number of purchased tickets: $soldSeats")
    print("Percentage: ")
    print("%.2f".format(soldPercentage))
    println("%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")

}

fun setRoom(theCinema: MutableList<MutableList<theSeat>>, seatsY: Int, seatsX: Int) : MutableList<MutableList<theSeat>> {
    var theSeatCount = seatsX * seatsY
    for (y in 1 .. seatsY) {
        theCinema[y][0].theIndexY = y
        theCinema[y][0].theIndexX = 0
        theCinema[y][0].theData = y.toString()

    }
    for (x in 1 .. seatsX) {
        theCinema[0][x].theIndexX = x
        theCinema[0][x].theIndexY = 0
        theCinema[0][x].theData = x.toString()
    }
    if(theSeatCount <= 60) {
        for (y in 1 .. seatsY) {
            for (x in 1 .. seatsX) {
                theCinema[y][x].theIndexY = y
                theCinema[y][x].theIndexX = x
                theCinema[y][x].theData = "S"
                theCinema[y][x].thePrice = 10
            }
        }
    } else {
        for (y in 1 .. seatsY / 2) {
            for (x in 1 .. seatsX) {
                theCinema[y][x].theIndexY = y
                theCinema[y][x].theIndexX = x
                theCinema[y][x].theData = "S"
                theCinema[y][x].thePrice = 10
            }
        }
        for (y in (seatsY / 2 + 1) .. seatsY ) {
            for (x in 1 .. seatsX) {
                theCinema[y][x].theIndexY = y
                theCinema[y][x].theIndexX = x
                theCinema[y][x].theData = "S"
                theCinema[y][x].thePrice = 8
            }
        }

    }
    return theCinema
}

fun seatReservation(theCinema: MutableList<MutableList<theSeat>>): MutableList<MutableList<theSeat>> {
    var seatsX = 0
    var seatsY = 0
    var checkpoint = false
    while (!checkpoint) {
        println("\nEnter a row number:")
        seatsY = readln().toInt()
        println("Enter a seat number in that row:")
        seatsX = readln().toInt()
        if (seatsY !in 1..9 || seatsX !in 1..9) {
            println("\nWrong input!")
        } else {
            if (theCinema[seatsY][seatsX].theData == "S") {
                theCinema[seatsY][seatsX].theData = "B"
                println("\nTicket price: $${theCinema[seatsY][seatsX].thePrice}")
                break
            } else {
                println("\nThat ticket has already been purchased!")
            }
        }
    }
    return theCinema
}
fun drawRoom(tempRoom: MutableList<MutableList<theSeat>>, seatsY: Int, seatsX: Int) {

    println("\nCinema:")
    for (y in 0 .. seatsY) {
        for (x in 0 .. seatsX) {
            print("${tempRoom[y][x].theData} ")
        }
        println()
    }
}
fun getCinemaSize () :Pair<Int, Int> {
    var seatsX = 0
    var seatsY = 0
    println("Enter the number of rows:")
    seatsY = readln().toInt()
    println("Enter the number of seats in each row:")
    seatsX = readln().toInt()

    return Pair(seatsY,seatsX)
}

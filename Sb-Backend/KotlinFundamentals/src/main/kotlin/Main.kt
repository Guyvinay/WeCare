package org.example

// Basic Syntax
fun main() {

    println("Hello, Kotlin!")

    // Immutable variable
    val name: String = "John"

    // Mutable variable
    var age: Int = 25

    println("Name: $name, Age: $age")
    // Variables
    val a: Int = 5
    val b = 10  // Type inference - the type is automatically inferred

    // Conditionals
    val max = if (a > b) a else b

    // Loops
    for (i in 1..5) {
        println(i)
    }

    /*
        Variables a and b are declared, showcasing type declaration and type inference.
        The if expression is used for conditional statements.
        A for loop is used to iterate from 1 to 5.
        The add function demonstrates function declaration and usage.
     */

    // Functions
    fun add(x: Int, y: Int): Int {
        return x + y
    }

    val sum = add(a, b)
    println("Sum: $sum")

    // Strings
    val greeting = "Hello"
    val names = "John"

    // String templates
    val message = "$greeting, $names!"
    println(message)

    // Arrays
    val numbers = arrayOf(1, 2, 3, 4, 5)
    val list_array = listOf<Int>(1,2,3,4,5,6)
    val mutable_list = mutableListOf(1,2,3,4,5,6)
    // Accessing elements
    val firstNumber = numbers[0]
    println("First number: $firstNumber")

    // Modifying elements
    numbers[1] = 10

    // Looping through an array
    for (number in numbers) {
        println(number)
    }

    /*
            String templates use the $ symbol for variable interpolation.
            An array of integers is created using arrayOf.
            Accessing and modifying elements in the array is demonstrated.
            A for loop is used to iterate through the array.
     */

    // Functions
    fun adds(x: Int, y: Int): Int {
        return x + y
    }

    // Lambda expression
    val multiply: (Int, Int) -> Int = { x, y -> x * y }

    val sums = adds(5, 10)
    val product = multiply(3, 4)

    println("Sum: $sums")
    println("Product: $product")

    // Higher-order function
    fun operateOnNumbers(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
        return operation(x, y)
    }

    val result = operateOnNumbers(8, 4, multiply)
    println("Result: $result")

    /*
        A lambda expression is used to define the multiply function in a more concise way.
        The operateOnNumbers function is a higher-order function that takes a function as a parameter.
        The multiply function is passed as an argument to operateOnNumbers.
     */


// Object-Oriented Programming (OOP)

    // Classes and Objects
    class Person(val name: String, var age: Int) {
        fun speak() {
            println("Hello, my name is $name.")
        }
    }

    // Creating objects
    val person1 = Person("Alice", 25)
    val person2 = Person("Bob", 30)

    // Accessing properties and calling methods
    println("${person1.name} is ${person1.age} years old.")
    person2.speak()

    // Inheritance
    open class Animal(val species: String) {
        fun makeSound() {
            println("Some generic sound.")
        }
    }

    class Dog(breed: String) : Animal("Dog") {
        fun bark() {
            println("Woof, woof!")
        }
    }

    val myDog = Dog("Golden Retriever")
    myDog.makeSound()
    myDog.bark()
    /*
        A Person class is defined with a constructor, properties (name and age), and a method (speak).
        Objects (person1 and person2) are created from the Person class.
        Inheritance is demonstrated with an Animal base class and a Dog subclass.
        The myDog object is an instance of the Dog class.
     */


    // Null Safety and Extensions
    // Nullable types
    var nullableString: String? = "Hello, Kotlin"
    nullableString = null

    // Safe calls
    val length = nullableString?.length
    println("Length: $length")

    // Elvis operator
    val nonNullLength = nullableString?.length ?: -1
    println("Non-null Length: $nonNullLength")

    // Extensions
    fun String.addExclamation(): String {
        return "$this!"
    }

    val originalString = "Goodbye"
    val modifiedString = originalString.addExclamation()
    println(modifiedString)

    /*
            The nullableString variable is declared as nullable (String?).
        Safe calls (?.) and the Elvis operator (?:) are used for null safety.
        An extension function (addExclamation) is defined for the String class,
        adding functionality without modifying the original class.
     */

}

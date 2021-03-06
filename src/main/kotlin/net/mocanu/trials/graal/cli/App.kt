package net.mocanu.trials.graal.cli

import picocli.CommandLine
import java.util.concurrent.Callable
import kotlin.system.exitProcess

@CommandLine.Command(
        name = "trials-graal-cli",
        description = ["Says hello"],
        version = ["trials-graal-cli 0.1.0"],
        mixinStandardHelpOptions = true
)
class App : Callable<Int> {
    override fun call(): Int {
        println(greeting)
        return 0
    }

    val greeting: String
        get() = "Hello CLI :)"
}

fun main(args: Array<String>) {
    val exitCode = CommandLine(App()).execute(*args)
    exitProcess(exitCode)
}

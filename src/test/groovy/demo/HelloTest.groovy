package demo

import spock.lang.Specification

/**
 * Created by karrie on 1/8/15.
 */
class HelloTest extends Specification {
    def "SayHello"() {
        expect: hello == "Hello, Gradle!"

        where: hello = new Hello().sayHello()
    }
}

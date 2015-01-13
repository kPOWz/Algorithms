package net.week2

import spock.lang.Specification

/**
 * Created by karrie on 1/12/15.
 */
class DequeTest extends Specification {

    private Deque<String> deque;
    def setup() {
        deque = new Deque<String>()
        assert deque.empty
    }

    def "IsEmpty"() {
        when:
        deque.isEmpty()

        then:
        true

        when:
        deque.addFirst("hello")

        then:
        !deque.isEmpty()
    }

    def "Size"() {
        expect:
        deque.size() == 0
    }

    def "SizeAfterAdd"() {
        when:
        deque.addFirst("hello")

        then:
        deque.size() > 0
    }

    def "AddFirstNullItem"() {
        when:
        deque.addFirst(null)

        then:
        thrown(NullPointerException)
    }

    def "AddFirst"() {
        when:
        deque.addFirst("hello")

        then:
        deque.size() > 0
    }

    def "AddLastNullItem"(){
        when:
        deque.addLast(null)

        then:
        thrown(NullPointerException)
    }

    def "AddLast"() {
        when:
        deque.addLast("hello")

        then:
        deque.size() > 0
    }

    def "RemoveFirst"() {
        when:
        deque.addLast("hello")
        deque.removeFirst()

        then:
        deque.size() == 0
    }

    def "RemoveFirstEmptyDeque"() {
        when:
        deque.removeFirst()

        then:
        thrown(NoSuchElementException)
    }

    def "RemoveFirstTwice"(){
        when:
        deque.addLast("1")
        deque.addLast("2")
        deque.addLast("3")
        def item = deque.removeFirst()

        then:
        deque.size() == 2
        item == "1"

        when:
        def item2 = deque.removeFirst()

        then:
        deque.size() == 1
        item2 == "2"
    }

    def "RemoveLastEmptyDeque"() {
        when:
        deque.removeLast()

        then:
        thrown(NoSuchElementException)
    }

    def "RemoveLast"() {
        when:
        deque.addFirst("hello")
        deque.removeLast()

        then:
        deque.size() == 0
    }

    def "RemoveLastTwice"() {
        when:
        deque.addFirst("3")
        deque.addFirst("2")
        deque.addFirst("1")
        def item = deque.removeLast()

        then:
        deque.size() == 2
        item == "3"

        when:
        def item2 = deque.removeLast()

        then:
        deque.size() == 1
        item2 == "2"
    }

    def "Iterator"() {

    }

    def "IteratorRemove"() {
        when:
        deque.iterator().remove()

        then:
        thrown(UnsupportedOperationException)
    }

    def "Main"() {

    }
}

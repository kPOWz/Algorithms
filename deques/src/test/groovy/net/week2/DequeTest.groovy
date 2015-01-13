package net.week2

import spock.lang.Specification

/**
 * Created by karrie on 1/12/15.
 */
class DequeTest extends Specification {
    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";
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
        deque.addLast(ONE)
        deque.addLast(TWO)
        deque.addLast(THREE)
        def item = deque.removeFirst()

        then:
        deque.size() == 2
        item == ONE

        when:
        def item2 = deque.removeFirst()

        then:
        deque.size() == 1
        item2 == TWO
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
        deque.addFirst(THREE)
        deque.addFirst(TWO)
        deque.addFirst(ONE)
        def item = deque.removeLast()

        then:
        deque.size() == 2
        item == THREE

        when:
        def item2 = deque.removeLast()

        then:
        deque.size() == 1
        item2 == TWO
    }

    def "Iterator"() {
        when:
        deque.addLast(THREE)
        deque.addFirst(TWO)
        deque.addFirst(ONE)
        def itr = deque.iterator();

        then:
        assert itr.hasNext()
        assert itr.next() ==  ONE
        itr.hasNext()
        itr.next() == TWO
        itr.hasNext()
    }

    def "IteratorEnd"(){
        when:
        deque.addLast(ONE)
        def itr = deque.iterator()
        itr.next()
        itr.next()


        then:
        thrown(NoSuchElementException)
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

package net.week2

import spock.lang.Specification

/**
 * Created by karrie on 1/12/15.
 */
class RandomizedQueueTest extends Specification {
    private RandomizedQueue<String> rndQueue;
    def setup() {
        rndQueue = new RandomizedQueue<String>()
        assert rndQueue.empty
    }
    def "IsEmpty"() {

    }

    def "Size"() {

    }

    def "Enqueue"() {
        when:
        rndQueue.enqueue(null)

        then:
        thrown(NullPointerException)
    }

    def "Dequeue"() {
        when:
        rndQueue.dequeue()

        then:
        thrown(NoSuchElementException)
    }

    def "Sample"() {
        when:
        rndQueue.sample()

        then:
        thrown(NoSuchElementException)

    }

    def "Iterator"() {

    }

    def "IteratorEnd"(){
        when:
        rndQueue.enqueue("1")
        def itr = rndQueue.iterator()
        itr.next()
        itr.next()


        then:
        thrown(NoSuchElementException)
    }

    def "IteratorRemove"() {
        when:
        rndQueue.iterator().remove()

        then:
        thrown(UnsupportedOperationException)
    }

    def "Main"() {

    }
}

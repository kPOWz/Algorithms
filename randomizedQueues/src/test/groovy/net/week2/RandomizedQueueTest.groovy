package net.week2

import spock.lang.Specification

/**
 * Created by karrie on 1/12/15.
 *
 */
class RandomizedQueueTest extends Specification {
    private RandomizedQueue<String> rndQueue;
    def setup() {
        rndQueue = new RandomizedQueue<String>()
        assert rndQueue.empty
    }

    def "IsEmpty"() {
        when:
        rndQueue.isEmpty()

        then:
        true

        when:
        rndQueue.enqueue("hello")

        then:
        !rndQueue.isEmpty()
    }

    def "Size"() {
        when:
        rndQueue.enqueue("hello")
        rndQueue.enqueue("yes")

        then:
        rndQueue.size() == 2


    }

    def "EnqueueEdgeCase"() {
        when:
        rndQueue.enqueue(null)

        then:
        thrown(NullPointerException)
    }

    def "Enqueue"(){
        when:
        rndQueue.enqueue("hello")

        then:
        rndQueue.size() == 1

    }

    def "Dequeue"() {
        when:
        rndQueue.enqueue("hello")
        rndQueue.enqueue("hello2")

        then:
        rndQueue.size() == 2

        when:
        def item = rndQueue.dequeue()

        then:
        rndQueue.size() == 1
        !rndQueue.isEmpty()
        item != null
    }

    def "DequeueSingle"(){
        when:
        rndQueue.enqueue("hello")

        then:
        rndQueue.size() == 1

        when:
        def item = rndQueue.dequeue()

        then:
        rndQueue.size() == 0
        rndQueue.isEmpty()
        item != null
    }

    def "DequeueEdgeCase"(){
        when:
        rndQueue.dequeue()

        then:
        thrown(NoSuchElementException)

    }

    def "SampleEdgeCase"() {
        when:
        rndQueue.sample()

        then:
        thrown(NoSuchElementException)
    }

    def "Sample"(){
        when:
        rndQueue.enqueue("hello")
        rndQueue.enqueue("hello2")
        def sample = rndQueue.sample()

        then:
        assert sample != null
    }

    def "SampleSingle"(){
        when:
        rndQueue.enqueue("hello")
        def sample = rndQueue.sample()

        then:
        assert sample != null
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

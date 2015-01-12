package demo

import spock.lang.Specification

/**
 * Created by karrie on 1/8/15.
 */
class StdStatsTest extends Specification {
    def "ArrayMean"() {
        expect: hello >= 0
                hello < 20

        where: hello = new StdStats().arrayMean()
    }
}

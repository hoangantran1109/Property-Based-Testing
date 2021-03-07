package edu.princeton.cs.algs4;

import edu.princeton.cs.algs4.beyond.MultiwayMinPQ;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import org.assertj.core.api.Assertions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;


public class MultiwayMinPQTestClass {

  @Property
  void MultiwayMinPQWithEmptyPriorityQueueTest(@ForAll @IntRange(min = 2, max = 999) int d) {
    MultiwayMinPQ multiwayMinPQ=new MultiwayMinPQ(d);
    Assertions.assertThat(multiwayMinPQ.isEmpty()).isEqualTo(true);
  }

  @Property
  void MultiwayMinPQWithEmptyPriorityQueueUsingComparatorTest(@ForAll @IntRange(min = 2, max = 999) int d ){
    Comparator<String> comp = Comparator.naturalOrder();
    MultiwayMinPQ multiwayMinPQ=new MultiwayMinPQ(comp,d);
    Assertions.assertThat(multiwayMinPQ.isEmpty()).isEqualTo(true);
  }

  @Property
  void MultiwayMinPqSizeWithStringTest(@ForAll String[] array, @ForAll @IntRange(min = 2, max = 999) int d) {
    MultiwayMinPQ multiwayMinPQ=new MultiwayMinPQ(array, d);
    Assertions.assertThat( multiwayMinPQ.size()).isEqualTo(array.length);
  }

  @Property
  void MultiwayMinPqSizeWithIntegerTest(@ForAll Integer[] array, @ForAll @IntRange(min = 2, max = 999) int d ) {
    MultiwayMinPQ multiwayMinPQ=new MultiwayMinPQ(array, d);
    Assertions.assertThat( multiwayMinPQ.size()).isEqualTo(array.length);
  }

  @Property
  void MultiwayMinPqSizeWithDoubleTest(@ForAll Double[] array, @ForAll @IntRange(min = 2, max = 999) int d ) {
    MultiwayMinPQ multiwayMinPQ=new MultiwayMinPQ(array, d);
    Assertions.assertThat( multiwayMinPQ.size()).isEqualTo(array.length);
  }

  @Property
  void MultwayMinPqSizeWithStringComparatorTest( @ForAll String[] array, @ForAll @IntRange(min = 2, max = 999) int d){
    Comparator<String> comp = Comparator.naturalOrder();
    MultiwayMinPQ multiwayMinPQ=new MultiwayMinPQ(comp, array, d);
    Assertions.assertThat( multiwayMinPQ.size()).isEqualTo(array.length);
  }

  @Property
  void MultwayMinPqSizeWithIntComparatorInReverseOrderTest( @ForAll Integer[] array, @ForAll @IntRange(min = 2, max = 999) int d){
    Comparator<Integer> comp = Comparator.reverseOrder();
    MultiwayMinPQ multiwayMinPQ=new MultiwayMinPQ(comp, array, d);
    Assertions.assertThat( multiwayMinPQ.size()).isEqualTo(array.length);
  }

  @Property
  void getMinKeyTest( @ForAll @Size( min =1) Double[] array ){
    Double[] clonedArray = array.clone();
    Arrays.sort(clonedArray);
    MultiwayMinPQ multiwayMinPQ = new MultiwayMinPQ(array, 3);
    Assertions.assertThat(multiwayMinPQ.minKey()).isEqualTo(clonedArray[0]);

  }

  @Property
  void getMinKeyTestInAnEmptyQueue( @ForAll @IntRange(min = 2, max = 999) int d ){
    MultiwayMinPQ multiwayMinPQ = new MultiwayMinPQ(d);
    Assertions.assertThatThrownBy(() -> multiwayMinPQ.minKey()).isInstanceOf(NoSuchElementException.class);
  }

  @Property
  void checkMinKeyAfterDelKeyTest( @ForAll @Size( min =2) Double[] array ){
    Double[] clonedArray = array.clone();
    Arrays.sort(clonedArray);
    MultiwayMinPQ multiwayMinPQ = new MultiwayMinPQ(array, 3);
    multiwayMinPQ.delMin();
    Assertions.assertThat(multiwayMinPQ.minKey()).isEqualTo(clonedArray[1]);
  }

  @Property
  void delMinKeyTestInAnEmptyQueue( @ForAll @IntRange(min = 2, max = 999) int d ){
    MultiwayMinPQ multiwayMinPQ = new MultiwayMinPQ(d);
    Assertions.assertThatThrownBy(() -> multiwayMinPQ.delMin()).isInstanceOf(NoSuchElementException.class);
  }



  @Property
  void iterateThroughAndCheckHasNext( @ForAll @Size( min =1) Double[] array ){
    MultiwayMinPQ multiwayMinPQ = new MultiwayMinPQ(array, 3);
    Assertions.assertThat(multiwayMinPQ.iterator().hasNext());
  }
  @Property
  void iterateThroughAndCheckRemove( @ForAll @Size( min =1) Double[] array ){
    MultiwayMinPQ multiwayMinPQ = new MultiwayMinPQ(array, 3);
    Assertions.assertThatThrownBy(() -> multiwayMinPQ.iterator().remove())
            .isInstanceOf(UnsupportedOperationException.class);
  }
  @Property
  void iterateThroughAndCheckNext( @ForAll @Size( min =1) Double[] array ){
    Double[] clonedArray = array.clone();
    Arrays.sort(clonedArray);
    MultiwayMinPQ multiwayMinPQ = new MultiwayMinPQ(array, 3);
    Assertions.assertThat(multiwayMinPQ.iterator().next()).isEqualTo(clonedArray[0]);
  }
}

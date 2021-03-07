package edu.princeton.cs.algs4;



import edu.princeton.cs.algs4.sorting.InsertionX;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Negative;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Assertions;


import java.util.Arrays;



public class InsertionXTestClass {

  @Property
  void compareInsertionSortTestWithSortInArraysTest(@ForAll Comparable[] array){
    Comparable[] clonedArray = array.clone();
    InsertionX.sort(array);
    Arrays.sort(clonedArray);
    Assertions.assertArrayEquals(array, clonedArray);
  }
  @Property
  void compareInsertionSortTestWithSortInArraysWithOnlyNegativeTest(@ForAll @Negative Comparable[] array){
    Comparable[] clonedArray = array.clone();
    InsertionX.sort(array);
    Arrays.sort(clonedArray);
    Assertions.assertArrayEquals(array, clonedArray);
  }

  @Property
  void compareInsertionSortTestWithSortInArraysWithOnlyPositiveTest(@ForAll @Positive Comparable[] array){
    Comparable[] clonedArray = array.clone();
    InsertionX.sort(array);
    Arrays.sort(clonedArray);
    Assertions.assertArrayEquals(array, clonedArray);
  }

  //No exception was thrown
  @Property
  void sortingAnEmptyArrayTest(){
    Comparable[] array = {} ;
    Comparable[] emptyArray = {} ;
    InsertionX.sort(array);
    Assertions.assertArrayEquals(array, emptyArray );
  }
}

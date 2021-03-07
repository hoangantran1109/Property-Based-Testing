package edu.princeton.cs.algs4;

import edu.princeton.cs.algs4.collections.Vector;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;
import net.jqwik.api.constraints.IntRange;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;

import static org.assertj.core.api.Assertions.byLessThan;

public class VectorTestClass {

    @Property
    void createZeroVectorWithGivenLengthTest(@ForAll @IntRange(min = 0, max = 500) int d) {
        Vector testVector = new Vector(d);
        Assertions.assertThat(testVector.dimension()).isEqualTo(d);
        Assertions.assertThat(testVector.magnitude()).isEqualTo(0);
        for (int i = 0; i < d; i++) {
            Assertions.assertThat(testVector.cartesian(i)).isEqualTo(0);
        }
    }

    @Property
    void vectorDimensionEqualsLengthOfArrayTest(@ForAll @DoubleRange(min = -500, max = 500) double[] array) {
        Vector testVector = new Vector(array);
        Assertions.assertThat(testVector.length()).isEqualTo(array.length);
        Assertions.assertThat(testVector.dimension()).isEqualTo(array.length);
    }

    @Property
    void vectorDimensionEqualsJavaVectorTest(@ForAll @DoubleRange(min = -500, max = 500) double[] array) {
        Vector testVector = new Vector(array);
        java.util.Vector<Double> testVector2 = new java.util.Vector<Double>();
        for (double a : array) {
            testVector2.add(a);
        }
        Assertions.assertThat(testVector.dimension()).isEqualTo(testVector2.size());
        Assertions.assertThat(testVector.length()).isEqualTo(testVector2.size());
    }


    @Property
    void dotProductOfTwoVectorsIsNumberTest(@ForAll @DoubleRange(min = -500, max = 500) double[] array1,
                                            @ForAll @DoubleRange(min = -500, max = 500) double[] array2) {
        Vector testVector1 = new Vector(array1);
        Vector testVector2 = new Vector(array2);

        if (testVector1.dimension() != testVector2.dimension()) {
            Assertions.assertThatThrownBy(() -> testVector1.dot(testVector2)).isInstanceOf(IllegalArgumentException.class);
        } else {
            Assertions.assertThat(testVector1.dot(testVector2)).isNotNaN();

        }

    }

    @Property
    void dotProductEqualRecursiveDotProduct(@ForAll @DoubleRange(min = -500, max = 500) double[] array1,
                                            @ForAll @DoubleRange(min = -500, max = 500) double[] array2) {
        Vector testVector1 = new Vector(array1);
        Vector testVector2 = new Vector(array2);
        if (testVector1.dimension() != testVector2.dimension()) {
            Assertions.assertThatThrownBy(() -> testVector1.plus(testVector2)).isInstanceOf(IllegalArgumentException.class);
        } else {
            Assertions.assertThat(testVector1.dot(testVector2)).isEqualTo(recursiveDotProduct(array1, array2, array1.length - 1));
        }
    }

    @Property
    void magnitudeIsGreaterEqualZeroTest(@ForAll @DoubleRange(min = -500, max = 500) double[] array) {
        Vector testVector = new Vector(array);
        Assertions.assertThat(testVector.magnitude()).isGreaterThanOrEqualTo(0);
    }

    @Property
    void distanceOfVectorToItSelf(@ForAll @DoubleRange(min = -500, max = 500) double[] array) {
        Vector testVector = new Vector(array);
        Assertions.assertThat(testVector.distanceTo(testVector)).isEqualTo(0);
    }

    @Property
    void compareCartersianAndArrayTest(@ForAll @DoubleRange(min = -500, max = 500) double[] array) {
        Vector testVector = new Vector(array);
        for (int i = 0; i < array.length; i++) {
            Assertions.assertThat(testVector.cartesian(i)).isEqualTo(array[i]);
        }
    }

    @Property
    void compareCartersianAndJavaVectorGetterTest(@ForAll @DoubleRange(min = -500, max = 500) double[] array) {
        Vector testVector = new Vector(array);
        java.util.Vector<Double> testVector3 = new java.util.Vector<Double>();
        for (double a : array) {
            testVector3.add(a);
        }
        for (int i = 0; i < array.length; i++) {
            Assertions.assertThat(testVector.cartesian(i)).isEqualTo(testVector3.get(i));
        }
    }


    @Property
    void transitivityOfAddingTwoVectorsTest(@ForAll @DoubleRange(min = -500, max = 500) double[] array1,
                                            @ForAll @DoubleRange(min = -500, max = 500) double[] array2) {
        Vector testVector1 = new Vector(array1);
        Vector testVector2 = new Vector(array2);
        if (array1.length != array2.length) {
            Assertions.assertThatThrownBy(() -> testVector1.plus(testVector2)).isInstanceOf(IllegalArgumentException.class);
        } else {
            assertTwoVectorsWithSameDim(testVector1.plus(testVector2), testVector2.plus(testVector1));
        }
    }

    @Property
    void vectorMinusItselfTest(@ForAll @DoubleRange(min = -500, max = 500) double[] array) {
        Vector testVector = new Vector(array);
        Vector zeroVector = new Vector(testVector.dimension());
        assertTwoVectorsWithSameDim(testVector.minus(testVector), zeroVector);
    }

    @Property
    void vectorMinusZeroTest(@ForAll @DoubleRange(min = -500, max = 500) double[] array) {
        Vector testVector = new Vector(array);
        Vector zeroVector = new Vector(testVector.dimension());
        assertTwoVectorsWithSameDim(testVector.minus(zeroVector), testVector);
    }

    @Property
    void toStringOfVectorIsStringTest(@ForAll @DoubleRange(min = -500, max = 500) double[] array) {
        Vector testVector = new Vector(array);
        Assertions.assertThat(testVector.toString()).isInstanceOf(String.class);

    }

    @Property
    void toStringOfVectorEqualsStringTest(@ForAll @DoubleRange(min = -500, max = 500) double[] array) {
        Vector testVector = new Vector(array);
        java.util.Vector<Double> testVector3 = new java.util.Vector<Double>();
        for (double a : array) {
            testVector3.add(a);
        }
        Assertions.assertThat(testVector.toString()).isEqualTo(vectorToString(testVector3));

    }


    @Property
    void VectorTimesOneTest(@ForAll @DoubleRange(min = 0, max = 999) double[] array) {
        Vector vector = new Vector(array);
        assertTwoVectorsWithSameDim(vector.times(1), vector);
        assertTwoVectorsWithSameDim(vector.scale(1), vector);
    }

    @Property
    void VectorTimesZeroTest(@ForAll @DoubleRange(min = 0, max = 999) double[] array) {
        Vector vector = new Vector(array);
        Vector zeroVector = new Vector(vector.dimension());
        assertTwoVectorsWithSameDim(vector.times(0), zeroVector);
        assertTwoVectorsWithSameDim(vector.scale(0), zeroVector);
    }

    @Property
    void directionOfUnitVectorIsOne(@ForAll @DoubleRange(min = -500, max = 500) double[] array) {
        Vector vector = new Vector(array);
        if (vector.magnitude() == 0) {
            Assertions.assertThatThrownBy(() -> vector.direction()).isInstanceOf(ArithmeticException.class);
        } else {
            Assertions.assertThat(vector.direction().magnitude()).isCloseTo(1, byLessThan(0.0000001));
            Assertions.assertThat(vector.direction().magnitude()).isCloseTo(1, Offset.offset(0.0000001));
        }
    }


    private void assertTwoVectorsWithSameDim(Vector vector1, Vector vector2) {
        for (int i = 0; i < vector1.dimension(); i++) {
            Assertions.assertThat(vector1.cartesian(i)).isEqualTo(vector2.cartesian(i));
        }
    }

    private double recursiveDotProduct(double[] a, double[] b, int arrayLength) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Dimensions don't agree");
        } else if (arrayLength == -1) {
            return 0;
        } else if (arrayLength == 0)
            return a[0] * b[0];
        else {
            return (a[arrayLength] * b[arrayLength]) + recursiveDotProduct(a, b, arrayLength - 1);
        }
    }

    private String vectorToString(java.util.Vector<Double> v1) {
        String testString = "";
        for (int i = 0; i < v1.size(); i++) {
            testString += v1.get(i).toString() + " ";
        }
        return testString;
    }
}

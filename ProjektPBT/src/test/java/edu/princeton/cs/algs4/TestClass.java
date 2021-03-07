package edu.princeton.cs.algs4;

import java.util.List;

import edu.princeton.cs.algs4.collections.Vector;
import net.jqwik.api.constraints.DoubleRange;
import net.jqwik.api.constraints.IntRange;
import org.assertj.core.api.Assertions;

import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Positive;
class TestClass {
	
	@Property
	void mathAbsReturnsValueGreaterOrEqualToInput(@ForAll int n) {
		Assertions.assertThat(Math.abs(n)).isGreaterThanOrEqualTo(n);
	}
	
	@Property
	void divisionWithZeroAllwaysThrowsException(@ForAll int n) {
		Assertions.assertThatThrownBy(() -> divideNBy0(n)).isInstanceOf(ArithmeticException.class);
	}

	@Property
	void assumeFiltersInputsByCondition(@ForAll @Positive short n, @ForAll @Positive short p){
		Assume.that(n > p);
		Assertions.assertThat(n).isGreaterThan(p);
	}
	

	@Property
	void toArrayContainsAllElements(@ForAll List<Object> list){
		Object[] array = list.toArray();
		int index=0;
		for(Object i : list) {
			Assertions.assertThat(i).isEqualTo(array[index++]);
		}
	}





	
	private int divideNBy0(int n) {
		return n/0;
	}
	
}
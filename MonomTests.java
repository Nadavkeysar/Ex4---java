package pack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonomTests {

	@Test
	void testisZero1() {
		Monom m = new Monom(3,4);
		assertFalse(m.isZero());
	}
	@Test
	void testisZero2() {
		Monom m = new Monom(0,0);
		assertTrue(m.isZero());
	}
	@Test
	void testisZero3() {
		Monom m = new Monom(0,9);
		assertTrue(m.isZero());
	}
	@Test
	void testequals1() {
		Monom m1 = new Monom(0,0);
		Monom m2 = new Monom(0,9);

		assertTrue(m1.equals(m2));
	}
	@Test
	void testequals2() {
		Monom m1 = new Monom(1,0);
		Monom m2 = new Monom(0,9);

		assertFalse(m1.equals(m2));
	}
	@Test
	void testequals3() {
		Monom m1 = new Monom(3,5);
		Monom m2 = new Monom(3,5);

		assertTrue(m1.equals(m2));
	}
	@Test
	void testmultiply1() {
		Monom m1 = new Monom(3,5);
		Monom m2 = new Monom(0,0);
		m1.multiply(m2);
		assertEquals(m1._coefficient,m2._coefficient);
	}
	@Test
	void testmultiply2() {
		Monom m1 = new Monom(1,0);
		Monom m2 = new Monom(3,5);
		m1.multiply(m2);
		assertEquals(m1._coefficient,m2._coefficient);
		assertEquals(m1._power, m2._power);
	}
	@Test
	void testmultiply3() {
		Monom m1 = new Monom(1,1);
		Monom m2 = new Monom(3,5);
		Monom m3 = new Monom(3,6);
		m1.multiply(m2);
		assertEquals(m1._coefficient,m3._coefficient);
		assertEquals(m1._power, m3._power);
	}
	@Test
	void testf1() {
		Monom m = new Monom(3,5);
		double x;
		x = m.f(1);
		assertEquals(x, 3);
	}
	@Test
	void testf2() {
		Monom m = new Monom(0,5);
		double x;
		x = m.f(1);
		assertEquals(x, 0);
	}
	@Test
	void testf3() {
		Monom m = new Monom(3,5);
		double x;
		x = m.f(2);
		assertEquals(x, 96);
	}
	@Test
	void testcompareTo1() {
		Monom m = new Monom(3,5);
		Monom m2 = new Monom(3,5);
		assertEquals(m.compareTo(m2), 0);
	}
	@Test
	void testcompareTo2() {
		Monom m = new Monom(3,4);
		Monom m2 = new Monom(3,5);
		assertEquals(m.compareTo(m2), -1);
	}
	@Test
	void testcompareTo3() {
		Monom m = new Monom(3,5);
		Monom m2 = new Monom(2,5);
		assertEquals(m.compareTo(m2), 1);
	}
	@Test
	void testderivative1() {
		Monom m = new Monom(3,5);
		Monom m1 = m.derivative();
		assertEquals(m1._coefficient, 3*5);
		assertEquals(m1._power, 4);
	}
	@Test
	void testderivative2() {
		Monom m = new Monom(3,0);
		Monom m1 = m.derivative();
		assertEquals(m1._coefficient, 0);
		assertEquals(m1._power, 0);
	}
	@Test
	void testderivative3() {
		Monom m = new Monom(-5,3);
		Monom m1 = m.derivative();
		assertEquals(m1._coefficient, -15);
		assertEquals(m1._power, 2);
	}
}

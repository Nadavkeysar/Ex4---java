package pack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import M_4.Monom;
import M_4.Polynom;

class PolynomTest {

	@Test
	void testarea1() {
		Polynom pol = new Polynom();
		pol.add(new Monom(1,1));
		pol.add(new Monom(1,1));
		pol.add(new Monom(1,1));
		double actual = pol.area(0, 2, 0.1);
		double expected = 6;
		assertEquals(expected, actual, 1);
	}
	@Test
	void testarea2() {
		Polynom pol = new Polynom();
		pol.add(new Monom(1,1));
		pol.add(new Monom(0,1));
		double actual = pol.area(0, 2, 1);
		double expected = 2;
		assertEquals(expected, actual, 1);
	}
	@Test
	void testarea3() {
		Polynom pol = new Polynom();
		pol.add(new Monom(2,1));
		double actual = pol.area(0, 3, 0.1);
		double expected = 9;
		assertEquals(expected, actual, 1);
	}
	@Test
	void testderivative1() {
		Polynom p = new Polynom();
		p.add(new Monom(1,0));
		p.add(new Monom(1,1));
		p.add(new Monom(1,2));
		p.derivative();
		Polynom pn = new Polynom();
		pn.add(new Monom(2,1));
		pn.add(new Monom(1,0));
		assertTrue(p.equals(pn));
	}
	@Test
	void testderivative2() {
		Polynom p = new Polynom();
		p.add(new Monom(1,5));
		p.add(new Monom(1,2));
		p.add(new Monom(1,0));
		p.derivative();
		Polynom pn = new Polynom();
		pn.add(new Monom(5,4));
		pn.add(new Monom(2,1));
		assertTrue(p.equals(pn));
	}
	@Test
	void testderivative3() {
		Polynom p = new Polynom();
		p.add(new Monom(1,5));
		p.add(new Monom(1,2));
		p.add(new Monom(-1,5));
		p.derivative();
		Polynom pn = new Polynom();
		pn.add(new Monom(2,1));
		pn.add(new Monom());
		pn.add(new Monom());
		assertTrue(p.equals(pn));
	}
	@Test
	void testequals1() {
		Polynom p1 = new Polynom();
		p1.add(new Monom(1,5));
		p1.add(new Monom(1,2));
		p1.add(new Monom(1,0));
		Polynom p2 = new Polynom();
		p2.add(new Monom(1,5));
		p2.add(new Monom(1,2));
		p2.add(new Monom(1,0));
		assertTrue(p1.equals(p2));
		
	}
	@Test
	void testequals2() {
		Polynom p1 = new Polynom();
		p1.add(new Monom(1,5));
		p1.add(new Monom(1,2));
		p1.add(new Monom(1,0));
		Polynom p2 = new Polynom();
		p2.add(new Monom(1,5));
		p2.add(new Monom(1,2));
		p2.add(new Monom(1,0));
		p2.add(new Monom());
		assertTrue(p1.equals(p2));
		
	}
	@Test
	void testequals3() {
		Polynom p1 = new Polynom();
		p1.add(new Monom(1,5));
		p1.add(new Monom(1,2));
		p1.add(new Monom(1,0));
		p1.add(new Monom(-1,5));
		p1.add(new Monom(-1,2));
		p1.add(new Monom(-1,0));
		Polynom p2 = new Polynom();
		p2.add(new Monom(1,5));
		p2.add(new Monom(1,2));
		p2.add(new Monom(1,0));
		p2.add(new Monom());
		assertFalse(p1.equals(p2));
		
	}
	@Test
	void testequals4() {
		Polynom p1 = new Polynom();
		p1.add(new Monom(1,5));
		p1.add(new Monom(1,2));
		p1.add(new Monom(1,0));
		p1.add(new Monom(-1,5));
		p1.add(new Monom(-1,2));
		p1.add(new Monom(-1,0));
		p1.add(new Monom(-1,5));
		p1.add(new Monom(-1,2));
		p1.add(new Monom(-1,0));
		Polynom p2 = new Polynom();
		p2.add(new Monom(-1,5));
		p2.add(new Monom(-1,2));
		p2.add(new Monom(-1,0));
		p2.add(new Monom());
		assertTrue(p1.equals(p2));
		
	}
	@Test
	void testisZero1() {
		Polynom p1 = new Polynom();
		p1.add(new Monom(1,5));
		p1.add(new Monom(1,2));
		p1.add(new Monom(1,0));
		p1.add(new Monom(-1,5));
		p1.add(new Monom(-1,2));
		p1.add(new Monom(-1,0));
		assertTrue(p1.isZero());
	}
	@Test
	void testisZero2() {
		Polynom p1 = new Polynom();
		assertTrue(p1.isZero());
	}
	@Test
	void testisZero3() {
		Polynom p1 = new Polynom();
		p1.add(new Monom());
		p1.add(new Monom());
		p1.add(new Monom());
		p1.add(new Monom());
		p1.add(new Monom());
		assertTrue(p1.isZero());
	}
	@Test
	void testroot1() {
		Polynom p = new Polynom();
		p.add(new Monom(1,1));
		p.add(new Monom(2,2));
		double r = p.root(-1, 5, 0.01);
		double expected = -0.5;
		assertEquals(r, expected, 3);
	}
	@Test
	void testroot2() {
		Polynom p = new Polynom();
		p.add(new Monom(1,1));
		p.add(new Monom(2,2));
		p.add(new Monom(5,0));
		double r = p.root(-1, 5, 1);
		double expected = -0.5;
		assertEquals(r, expected, 1);
	}
	@Test
	void testroot3() {
		Polynom p = new Polynom();
		p.add(new Monom(5,0));
		double r = p.root(-1, 5, 1);
		double expected = -1;
		assertEquals(r, expected, 1);
	}
	@Test
	void testsubstract1() {
		Polynom p1 = new Polynom();
		p1.add(new Monom(1,1));
		p1.add(new Monom(2,2));
		p1.add(new Monom(5,0));
		Polynom p2 = new Polynom();
		p2.add(new Monom(1,1));
		p2.add(new Monom(2,2));
		p2.add(new Monom(5,0));
		p1.substract(p2);
		assertTrue(Integer.valueOf(p1.toString()) == 0);
	}
	@Test
	void testsubstract2() {
		Polynom p1 = new Polynom();
		p1.add(new Monom(1,1));
		p1.add(new Monom(2,2));
		p1.add(new Monom(5,0));
		Polynom p2 = new Polynom();
		p2.add(new Monom(1,1));
		p2.add(new Monom(5,0));
		p1.substract(p2);
		Polynom p3 = new Polynom();
		p3.add(new Monom(2,2));
		assertTrue(p1.equals(p3));
	}
	@Test
	void testsubstract3() {
		Polynom p1 = new Polynom();
		p1.add(new Monom());
		p1.add(new Monom());
		p1.add(new Monom());
		Polynom p2 = new Polynom();
		p2.add(new Monom(1,1));
		p2.add(new Monom(5,0));
		p1.substract(p2);
		Polynom p3 = new Polynom();
		p3.add(new Monom(-1,1));
		p3.add(new Monom(-5,0));
		assertTrue(p1.equals(p3));
	}

}

package lotto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UnglueckszahlenTest {
	
	@Test
	void testInit() {
		
		Unglueckszahlen.init();
		Unglueckszahlen.allesLoeschen();

		assertTrue( Unglueckszahlen.istLeer() );		
		
		System.out.println(Unglueckszahlen.hinzufuegen("13"));
		System.out.println(Unglueckszahlen.hinzufuegen("7"));
		System.out.println(Unglueckszahlen.hinzufuegen(""));
		System.out.println(Unglueckszahlen.hinzufuegen("24"));
		System.out.println(Unglueckszahlen.hinzufuegen("7"));
		System.out.println(Unglueckszahlen.hinzufuegen("text"));
		System.out.println(Unglueckszahlen.hinzufuegen("81"));
		System.out.println(Unglueckszahlen.hinzufuegen("1"));
		System.out.println(Unglueckszahlen.hinzufuegen("33"));
		System.out.println(Unglueckszahlen.hinzufuegen("17"));
		System.out.println(Unglueckszahlen.hinzufuegen("2"));
		
		
		assertEquals( Unglueckszahlen.getZahlen()[0], 1);
		assertEquals( Unglueckszahlen.getZahlen()[1], 7);
		assertEquals( Unglueckszahlen.getZahlen()[2], 13);
		assertEquals( Unglueckszahlen.getZahlen()[3], 17);
		assertEquals( Unglueckszahlen.getZahlen()[4], 24);
		assertEquals( Unglueckszahlen.getZahlen()[5], 33);
		
		assertEquals( Unglueckszahlen.getZahlen().length, 6);
		
		System.out.println(Unglueckszahlen.entfernen(""));
		System.out.println(Unglueckszahlen.entfernen("7"));
		System.out.println(Unglueckszahlen.entfernen("55"));
		System.out.println(Unglueckszahlen.entfernen("33"));
		System.out.println(Unglueckszahlen.entfernen("test"));
		System.out.println(Unglueckszahlen.entfernen("1"));
		System.out.println(Unglueckszahlen.entfernen("17"));
		System.out.println(Unglueckszahlen.entfernen("33"));
		
		assertEquals( Unglueckszahlen.getZahlen()[0], 13);
		assertEquals( Unglueckszahlen.getZahlen()[1], 24);
		assertEquals( Unglueckszahlen.getZahlen().length, 2);
		
		assertTrue( Unglueckszahlen.istEnthalten(13));
		assertFalse( Unglueckszahlen.istEnthalten(33));
		
		
		
	}

}

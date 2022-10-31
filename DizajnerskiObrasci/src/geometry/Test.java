package geometry;

import java.util.Arrays;

public class Test {
	
      public static void main(String[] args) {
		//vezbe 2
    	  
		Point p=new Point();
		//promenjilva sadrzi referencu na objekat
		System.out.println(p);
		//obelezje x je private i nije vidiljivo iz klase Test
		//System.out.println(p.x);
		
		System.out.println(p.getX()+" "+p.getY()+" "+ p.isSelected());
		p.setX(10);
		p.setY(10);
		p.setSelected(true);
		System.out.println(p.getX()+" "+p.getY()+" "+ p.isSelected());
	
		double result=p.distance(40, 50);
		System.out.println("Distance between points is " + result);
		
		//vezbe 3
		
		Point p1=new Point();
		p1.setX(15);
		p1.setY(27);
		p1.setSelected(true);
		
		Line l1=new Line();
		Rectangle r1=new Rectangle();
		Circle c1=new Circle();
		
		//1. Inicijalizovati x koordinatu tacke p
		// na vrednost y koordinate tacke p1
		System.out.println("Stara vrednost x koordinate tacke p: " + p.getX());
		p.setX(p1.getY());
		System.out.println("Nova vrednost x koordinate tacke p: " + p.getX());
		
		//2. Postaviti za pocetnu tacku linije l1 tacku p
		// a za krajnju tacku linije l1 tacku p1
		l1.setStartPoint(p);
		l1.setEndPoint(p1);
		
		//3. Postaviti y koordinatu krajnje tacke l1 na 23
		System.out.println("Stara vrednost y koordinate krajnje tacke l1: " + l1.getEndPoint().getY());
	    l1.getEndPoint().setY(23);
	    System.out.println("Nova vrednost y koordinate krajnje tacke l1: " + l1.getEndPoint().getY());
	    
		//4. Inicijalizovati x koordinatu pocetne tacke linije l1
	    // na vrednost y koordinate krajnje tacke linije l1
	    System.out.println("Stara vrednost x koordinate pocetne tacke l1: " + l1.getStartPoint().getX());
		l1.getStartPoint().setX(l1.getEndPoint().getY());
		System.out.println("Nova vrednost x koordinate pocetne tacke l1: " + l1.getStartPoint().getX());
		
		//5. Postaviti x koordinatu krajnje tacke l1 na vrednost
		// duzine linije l1 umanjene za vrednost zbira x i y
		// koordinate pocetne tacke linije l1
		System.out.println("Stara vrednost x koordinate krajnje tacke l1: " + l1.getEndPoint().getX());
		l1.getEndPoint().setX((int)l1.length()-(l1.getStartPoint().getX()+l1.getStartPoint().getY()));
		System.out.println("Nova vrednost x koordinate pocetne tacke l1: " + l1.getEndPoint().getX());
		
		//6. Postaviti x koordinatu tacke gore levo pravougaonika
		// r1 na vrednost 10 i y koordinatu na vrednost 15
		r1.setUpperLeftPoint(p1);//inicijalizujemo vrednost tacke gore levo
		r1.getUpperLeftPoint().setX(10);
		r1.getUpperLeftPoint().setY(15);
		System.out.println("X koordinata tacke gore levo: " + r1.getUpperLeftPoint().getX());
		System.out.println("Y koordinata tacke gore levo: " + r1.getUpperLeftPoint().getY());
		
		//7. Postaviti centar kruga c1 na koordinate tacke
		// gore levo od r1
		c1.setCenter(p1);
		//c1.getCenter().setX(r1.getUpperLeft().getX());
		//c1.getCenter().setY(r1.getUpperLeft().getY());
		c1.setCenter(r1.getUpperLeftPoint());
		System.out.println("X koordinata centra kruga " + c1.getCenter().getX());
		System.out.println("Y koordinata centra kruga " + c1.getCenter().getY());
		
		//8. Postaviti x koordinatu centra kruga c1 na vrednost razlike
		// povrsine pravougaonika r1 i y koordinate pocetne tacke linije l1
		r1.setHeight(20);
		r1.setWidth(30);
		c1.getCenter().setX((r1.area()-l1.getStartPoint().getY()));
		System.out.println("X koordinata centra kruga " + c1.getCenter().getX());
		
		//vezbe 4
		
		Point p2=new Point(50,100);
		//postavite false za selected
		System.out.println(p2.isSelected());
		Line l2=new Line(p2,new Point(400,500));
		Rectangle r2=new Rectangle(p2,50,80);
		Circle c2=new Circle(p2, 45);
		
		System.out.println(p2);
		System.out.println(l2);
		System.out.println(r2);
		System.out.println(c2);
		
		Point p5=new Point(40,50);
		Point p6=new Point(40,50);
		System.out.println(p5.equals(p6));
		
		/*
		System.out.println(p5==p2);
		p5=p2;
		System.out.println(p5);
		p2.setX(20);
		System.out.println(p5);
		System.out.println(p5==p2);
		
		Point p6=new Point(20,100);
		
		System.out.println(p6==p2);
		p6=p2;
		System.out.println(p6==p2);
		*/
		
		//vezbe 7
		System.out.println("\n Vezbe 7 - Testiranje metoda i nizova");
		System.out.println("\n Tacka");
		System.out.println(p1);
		p1.moveBy(5, 3);
		System.out.println(p1);
		p1.moveTo(5, 3);
		System.out.println(p1);
		
		
		System.out.println("\n Linija");System.out.println(p1);
		System.out.println(l1);
		l1.moveBy(5, 3);
		System.out.println(l1);
		l1.moveTo(5, 3);//nista se ne menja
		System.out.println(l1);
		
		System.out.println("\n Pravougaonik");
		System.out.println(r1);
		r1.moveBy(5, 3);
		System.out.println(r1);
		r1.moveTo(5, 3);//nista se ne menja
		System.out.println(r1);
		
		System.out.println("\n Krug");
		System.out.println(c1);
		c1.moveBy(5, 3);
		System.out.println(c1);
		c1.moveTo(5, 3);//nista se ne menja
		System.out.println(c1);
		
		Donut d1=new Donut(new Point(800,100), 50, 25);
		System.out.println("\n Krug sa rupom");
		System.out.println(d1);
		d1.moveBy(5, 3);
		System.out.println(d1);
		d1.moveTo(5, 3);//nista se ne menja
		System.out.println(d1);
		
		//sve tacke ukazuju na referencu
		Shape d3=new Donut(p1,10,5);//svaka promena na p1 ce uticati na promenu donuta
		Shape p9=new Point();
		Shape l9=l1;//svaka promena koja se desi za l1, desice se i za l9
		Shape c9=c1;
		System.out.println("\nIspis oblika");
		Shape[] shapes= {d3,p9,l9,c9};
		for(int i=0;i<shapes.length;i++) {
			System.out.println(shapes[i]);
		}
		for(int i=0;i<shapes.length;i++) {
			shapes[i].moveBy(1, 2);
		}
		System.out.println("Ispis oblika nakon moveBy");
		for(int i=0;i<shapes.length;i++) {
			System.out.println(shapes[i]);
		}
		
		int[] ints= {5,2,3,4,1};
		System.out.println("Ispis nesortiranog niza");
		for(int i=0;i<ints.length;i++) {
			System.out.println(ints[i]);
		}
		Arrays.sort(ints);
		System.out.println("Ispis sortiranog niza");
		for(int i=0;i<ints.length;i++) {
			System.out.println(ints[i]);
		}
		
		Point p10=new Point(10,10);
		Point p20=new Point(20,20);
		Point p30=new Point(30,30);
		Point p40=new Point(40,40);
		Point p50=new Point(50,50);
		Point[] points= {p10,p30,p50,p40,p20};
		System.out.println("Ispis nesortiranog niza");
		for(int i=0;i<points.length;i++) {
			System.out.println(points[i]);
		}
		Arrays.sort(points);
		System.out.println("Ispis sortiranog niza");
		for(int i=0;i<points.length;i++) {
			System.out.println(points[i]);
		}
		

		Circle c10=new Circle(p1,10);
		Circle c20=new Circle(p1,20);
		Circle c30=new Circle(p1,30);
		Circle c40=new Circle(p1,40);
		Circle[] circles= {c10,c30,c40,c20};
		System.out.println("Ispis nesortiranog niza");
		for(int i=0;i<circles.length;i++) {
			System.out.println(circles[i]);
		}
		Arrays.sort(circles);
		System.out.println("Ispis sortiranog niza");
		for(int i=0;i<circles.length;i++) {
			System.out.println(circles[i]);
		}
	}
}



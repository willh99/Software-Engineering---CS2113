import java.util.*;

class ObjX {

    String name;

    public ObjX (String s)
    {
	name = s;
    }

    public void print ()
    {
	System.out.println ("ObjX: " + name);
    }

}

class ObjY {

    ObjX x;

    public ObjY (ObjX objx)
    {
	x = objx;
    }

    public ObjX getX ()
    {
	return x;
    }

}


class ObjZ {

    ObjY y;

    public ObjZ (ObjY objy)
    {
	y = objy;
    }

    public ObjY getY ()
    {
	return y;
    }

}


public class ChainedRefs {

    public static void main (String[] argv)
    {
	// Traditional instantiation:
	ObjX x = new ObjX ("Big X");
	ObjY y = new ObjY (x);

	// Traditional access:
	x = y.getX ();
	x.print ();

	// Using chaining to instantiate:
	y = new ObjY (new ObjX ("Lil' X"));

	// Using chaining to access:
	y.getX().print();

	// Another example:
	ArrayList<ObjY> yList = new ArrayList<ObjY> ();
	yList.add (y);
	yList.add (new ObjY (new ObjX ("X Jr.")));
	
	// Chained access:
	yList.get(1).getX().print();

	// Write your exercise code here:
	ObjZ z = new ObjZ (y);
	z.getY().getX().print();
    }

}

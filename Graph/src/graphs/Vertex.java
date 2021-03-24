
package graphs;

public class Vertex {
    String label;
    Vertex( String label ) {
        this.label = label;
    }
    
    @Override
    public boolean equals( Object o ) {
        Vertex v = (Vertex) o;
        if( this.label.equals( v.label ))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }
    
    @Override
    public String toString() {
        return label;
    }
}

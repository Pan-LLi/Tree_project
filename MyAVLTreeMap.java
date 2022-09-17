
import net.datastructures.*;
import org.w3c.dom.Node;

import java.util.Comparator;
import java.util.Arrays;
import java.util.function.UnaryOperator;

public class MyAVLTreeMap<K,V> extends TreeMap<K,V> {
	
  /** Constructs an empty map using the natural ordering of keys. */
  public MyAVLTreeMap() { super(); }

  /**
   * Constructs an empty map using the given comparator to order keys.
   * @param comp comparator defining the order of keys in the map
   */
  public MyAVLTreeMap(Comparator<K> comp) { super(comp); }

  /** Returns the height of the given tree position. */
  protected int height(Position<Entry<K,V>> p) {
    return tree.getAux(p);
  }

  /** Recomputes the height of the given position based on its children's heights. */
  protected void recomputeHeight(Position<Entry<K,V>> p) {
    tree.setAux(p, 1 + Math.max(height(left(p)), height(right(p))));
  }

  /** Returns whether a position has balance factor between -1 and 1 inclusive. */
  protected boolean isBalanced(Position<Entry<K,V>> p) {
    return Math.abs(height(left(p)) - height(right(p))) <= 1;
  }

  /** Returns a child of p with height no smaller than that of the other child. */
  protected Position<Entry<K,V>> tallerChild(Position<Entry<K,V>> p) {
    if (height(left(p)) > height(right(p))) return left(p);     // clear winner
    if (height(left(p)) < height(right(p))) return right(p);    // clear winner
    // equal height children; break tie while matching parent's orientation
    if (isRoot(p)) return left(p);                 // choice is irrelevant
    if (p == left(parent(p))) return left(p);      // return aligned child
    else return right(p);
  }

  /**
   * Utility used to rebalance after an insert or removal operation. This traverses the
   * path upward from p, performing a trinode restructuring when imbalance is found,
   * continuing until balance is restored.
   */
  protected void rebalance(Position<Entry<K,V>> p) {
    int oldHeight, newHeight;
    do {
      oldHeight = height(p);                       // not yet recalculated if internal
      if (!isBalanced(p)) {                        // imbalance detected
        // perform trinode restructuring, setting p to resulting root,
        // and recompute new local heights after the restructuring
        p = restructure(tallerChild(tallerChild(p)));
        recomputeHeight(left(p));
        recomputeHeight(right(p));
      }
      recomputeHeight(p);
      newHeight = height(p);
      p = parent(p);
    } while (oldHeight != newHeight && p != null);
  }

  /** Overrides the TreeMap rebalancing hook that is called after an insertion. */
  @Override
  protected void rebalanceInsert(Position<Entry<K,V>> p) {
    rebalance(p);
  }

  /** Overrides the TreeMap rebalancing hook that is called after a deletion. */
  @Override
  protected void rebalanceDelete(Position<Entry<K,V>> p) {
    if (!isRoot(p))
      rebalance(parent(p));
  }

  /** Ensure that current tree structure is valid AVL (for debug use only). */
  private boolean sanityCheck() {
    for (Position<Entry<K,V>> p : tree.positions()) {
      if (isInternal(p)) {
        if (p.getElement() == null)
          System.out.println("VIOLATION: Internal node has null entry");
        else if (height(p) != 1 + Math.max(height(left(p)), height(right(p)))) {
          System.out.println("VIOLATION: AVL unbalanced node with key " + p.getElement().getKey());
          dump();
          return false;
        }
      }
    }
    return true;
  }
   public Position<Entry<K,V>> Start(MyAVLTreeMap<K,V> mytree){
    return mytree.root();
   }

   public int StartHeight(MyAVLTreeMap<K,V> mytree){
        int h=height(mytree.root());
        return h;
   }

   public void geth(Position<Entry<K,V>> p){
    System.out.println(tree.getAux(p));
   }

  class NewPrint {
    Position<Entry<K, V>> p;
    int height;

    NewPrint(Position<Entry<K, V>> p, int height) {
      this.p = p;
      this.height = height;
    }
  }
  static void printnBlanks(int count) {
    for (int i = 0; i < count; ++i) {
      System.out.print(' ');
    }
  }
    public void printTree(){
      int finalheight = height(tree.root());
      ArrayQueue<NewPrint> q=new ArrayQueue<>();
      int currentheight=finalheight;
      String line="";
      q.enqueue(new NewPrint(tree.root(),finalheight));
      while(!q.isEmpty()) {
        NewPrint a=q.dequeue();
        Position<Entry<K, V>> p = a.p;
        int h=a.height;
          if(h !=currentheight){
            System.out.println();
            System.out.println(line);
            line="";
            currentheight=h;
          }
          if (h == 0) {
            break;
          }
        int rightBlankCnt = 1 << (h - 1);
        printnBlanks(rightBlankCnt - 1);
        line += nBlanksString(rightBlankCnt - 1);
         if (!isRoot(p)) {
         if (p == null || p.getElement() == null) {
            System.out.print(" ");
          } else if (p == left(parent(p))) {
            System.out.print("/");
            }
         else {
            System.out.print("\\");
         }
         }

          if(p==null || p.getElement()==null){
            line+=" ";
          }


          else{
          line += p.getElement().getKey().toString();
          q.enqueue(new NewPrint(left(p),h-1));
          q.enqueue(new NewPrint(right(p),h-1));}

        printnBlanks(rightBlankCnt);
        line += nBlanksString(rightBlankCnt);
      }
    }

  static String nBlanksString(int count) {
    char[] s = new char[count];
    Arrays.fill(s, ' ');
    return new String(s);
  }
}

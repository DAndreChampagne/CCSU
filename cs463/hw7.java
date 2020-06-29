import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

// I use hashmaps rather than arrays, so there is no real sorting. Instead I plugged in counters surrounding
// the galeShapelyMarriage method.

public class hw7 {

    // turn on to show engagements and reengagements
    static boolean _ShowDebugOutput = false;

    public static void main(String[] args) {

        for (int n = 0; n < 100; n += 1) {

            long start = System.nanoTime();
            Population village = Population.newRandom(10);            
            Population.Marriage marriage = village.galeShapelyMarriage();
            long end = System.nanoTime();

            if (_ShowDebugOutput) {
                System.out.println("******************************");
                System.out.println("Final marriages:");

                for (int i=0; i<marriage.wives.length; ++i) {
                    System.out.println("Girl " + i + " is married to boy " + marriage.wives[i]);
                }
            }

            if (marriage.isStable()) {
                System.out.println("Marriage " + n + " is stable (" + (end-start) + "ns).");
            } else {
                throw new IllegalStateException("Marriage "+n+" is not stable!");
            }
        }
    }
    

    public static class Population {
        int[][] boysPreferences; // boysPreferences[b][i] is the ith choice of boy b
        int[][] girlsPreferences; // girlsPreferences[g][i] is the ith choice of girl g

        static Population newRandom(int n) {
            Population pop = new Population();

            pop.boysPreferences = new int[n][n];
            pop.girlsPreferences = new int[n][n];
            
            for (int b = 0; b < n; b += 1) {
                for (int i = 0; i < n; i += 1) {
                    pop.boysPreferences[b][i] = i;
                    pop.girlsPreferences[b][i] = i;
                }
                randomize(pop.boysPreferences[b]);
                randomize(pop.girlsPreferences[b]);
            }
            
            return pop;
        }

        // test method so I can gauge the algorithm
        static Population newFromClass() {
            Population pop = new Population();

            pop.boysPreferences = new int[][] {
                {1,0,2},
                {1,2,0},
                {2,1,0}
            };
            pop.girlsPreferences = new int[][] {
                {1,2,0},
                {2,0,1},
                {1,2,0}
            };
            
            return pop;
        }

        
        public class Marriage {
            int[] wives;

            int husbandOfGirl(int g) {
                for (int b = 0; b < wives.length; b++) {
                    if (wives[b]==g)
                        return b;
                }
                throw new IllegalStateException();
            }

            int wifeOfBoy(int b) {
                return wives[b];
            }
            
            
            // I am far from positive on this
            boolean isStable() {

                for (int i=0; i<wives.length; ++i) {
                    int 
                    while () {

                    }
                }

                return true;
                // for (int i = 0; i<wives.length; ++i) {
                //     if (husbandOfGirl(i) == -1)
                //         return false;
                // }

                // return true;
            }

        }
        
        
        // https://en.wikipedia.org/wiki/Gale–Shapley_algorithm
        // algorithm stable_matching is
        //     Initialize all m ∈ M and w ∈ W to free
        //     while ∃ free man m who still has a woman w to propose to do
        //         w := first woman on m's list to whom m has not yet proposed
        //         if w is free then
        //             (m, w) become engaged
        //         else some pair (m', w) already exists
        //             if w prefers m to m' then
        //                 m' becomes free
        //                 (m, w) become engaged 
        //             else
        //                 (m', w) remain engaged
        //             end if
        //         end if
        //     repeat
        Marriage galeShapelyMarriage() {
            Marriage result = new Marriage();
            HashMap<Integer, Integer> couples = new HashMap<>();
            Set<Integer> boys = new HashSet<>(); 
            int freeBoysCount = boysPreferences.length;
            
            // original method, but I changed it because there could be unbalanced boys/girls
            // for (int i = 0; i <girlsPreferences.length ; i++) {
            //     couples.put(i, null);
            //     boys.add(i);
            // }
            for (int i = 0; i <girlsPreferences.length ; i++)
                couples.put(i, -1);
            for (int i = 0; i <boysPreferences.length ; i++)
                boys.add(i);
            
            while (freeBoysCount > 0) {
                int current = boys.iterator().next();
                
                if (_ShowDebugOutput) {
                    System.out.println ("\nFinding a match for boy " + current);
                }
                
                for (int w = 0; w <boysPreferences[current].length; w++) {
                    // check if current girl is available for the current boy
                    if (couples.get(w) == -1) {
                        couples.put(w, current);
                        boys.remove(current);

                        if (_ShowDebugOutput) {
                            System.out.println("Boy " + current + " became engaged to girl " + w);
                        }
                        
                        break;
                    } else {
                        // if the girl is already engaged, check to see if she prefers the current
                        // boy. If she does, she ends her current engagement and becomes engaged to
                        // the current boy.
                        int currentFiance = couples.get(w);
                        
                        if (engagedGirlPrefersCurrentBoy(current, currentFiance, w)) {
                            couples.put(w, current); // replace current hashtable entry
                            boys.add(currentFiance);
                            boys.remove(current);

                            if (_ShowDebugOutput) {
                                System.out.println("Girl " + w + " broke up with boy " + currentFiance);
                                System.out.println("Girl " + w + " became engaged to boy " + current);
                            }

                            break;
                        } else {
                            // do nothing. Current girl is happy!
                        }
                    }
                }
                
                freeBoysCount = boys.size();
            }

            // I really wish I knew java well enough to make this work. But I can't get
            // Object[] to convert to int[]
            // result.wives = couples.values().toArray();

            result.wives = new int[couples.size()];
            Iterator<Integer> iter = couples.values().iterator();
            for (int i=0; i<couples.size(); ++i) {
                result.wives[i] = iter.next();
            }
            
            return result;
        }

        // helper method
        private boolean engagedGirlPrefersCurrentBoy(int currentBoy, int currentFiance, int girl) {
            int indexCurrentBoy = -1;
            int indexCurrentFiance = -1;
            
            for (int i = 0; i < girlsPreferences[girl].length; i++) {
                if (girlsPreferences[girl][i] == currentBoy)
                    indexCurrentBoy = i;

                if (girlsPreferences[girl][i] == currentFiance)
                    indexCurrentFiance = i;
            }
            
            // girls prefers boy if he's higher on her list
            return (indexCurrentBoy < indexCurrentFiance);
        }
    }
    
    static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    static void randomize(int[] a) {
        Random random = new Random();
        for (int i = 1; i < a.length; i += 1) {
            swap(a, i, random.nextInt(i));
        }
    }
     

}
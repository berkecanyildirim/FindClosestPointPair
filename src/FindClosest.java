import java.awt.*;
import java.awt.geom.Point2D;

public class FindClosest {

    private PointPair closestPointPair;
    private final QuickSort quicksort = new QuickSort();

    /** Constructor
     *
     * @param points --> point array
     */
    public FindClosest(Point2D.Double[] points)
    {
        //Sort points by X coordinate
        quicksort.sort(points, 0, points.length - 1, "compareX");
        this.closestPointPair = calculateClosestPointPair(points, 0, points.length - 1);
        //*********************************do nothing***************************************//
    }

    /** Get closest Point Pair
     *
     * @return closestPointPair
     */
    public PointPair getClosestPointPair()
    {
        return this.closestPointPair;
    }

    /** Main method for calculate and return closest point pair
     *
     * @param p --> point array
     * @param startIndex --> First index of p[]
     * @param lastIndex --> last index of p[]
     * @return
     */
    private PointPair calculateClosestPointPair(Point2D.Double[] p, int startIndex, int lastIndex) {
        PointPair point_pair;
        int size = lastIndex- startIndex;

        if((size)<2){
            Point2D.Double pair1 = new Point2D.Double(Double.MAX_VALUE, Double.MAX_VALUE);
            Point2D.Double pair2 = new Point2D.Double(0, 0);

            point_pair = new PointPair(pair1, pair2);

            for (int i = 0; i < lastIndex; ++i) {
                PointPair minimumPair=new PointPair(p[i], p[i+1]);

                if (minimumPair.getDistance() < point_pair.getDistance()) {
                    point_pair=minimumPair;
                }
            }
            return point_pair;
        }
        else if(size == 2){
            return getClosestPointPair(p[startIndex], p[startIndex + 1], p[lastIndex]);
        }

        int middle = ( (lastIndex + startIndex) / 2);

        PointPair distance_L = calculateClosestPointPair(p, startIndex, middle);
        PointPair distance_R = calculateClosestPointPair(p, middle+1, lastIndex);

        double distance;

        if(distance_L.getDistance()<distance_R.getDistance()){
            distance= distance_L.getDistance();
            point_pair=distance_L;
        }
        else{
            distance=distance_R.getDistance();
            point_pair=distance_R;
        }
        Point2D.Double strippedPair[]=new Point2D.Double[lastIndex+1];
        int j = 0;
        for (int i = startIndex; i <= lastIndex; i++) {
            if (Math.abs(p[i].x - p[middle].x) < distance) {
                strippedPair[j] = p[i];
                j++;
            }
        }

        if(stripClosest(strippedPair,j,point_pair).getDistance()<distance){
            point_pair= stripClosest(strippedPair,j,point_pair);
        }

        return point_pair;

    }

    /** calculate and return closest point pair from 3 points
     *
     * @param p1 --> point 1
     * @param p2 --> point 2
     * @param p3 --> point 3
     * @return
     */
    // It takes three points, takes two of them, makes a PointPair,
    // and calls getClosestPointPair inside and returns the one with the shortest distance between PointPairs.
    private PointPair getClosestPointPair(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        PointPair first_pair= new PointPair(p1,p2);
        PointPair second_pair= new PointPair(p1,p3);
        PointPair third_pair= new PointPair(p2,p3);

        return getClosestPointPair(first_pair, getClosestPointPair(second_pair, third_pair));
    }

    private PointPair getClosestPointPair(PointPair p1, PointPair p2){
        if (p1.getDistance()<p2.getDistance())
            return p1;

        return p2;
    }

    /**
     * A utility function to find the distance between the closest points of
     * strip of given size. All points in strip[] are sorted according to
     * y coordinate. They all have an upper bound on minimum distance as d.
     * Note that this method seems to be a O(n^2) method, but it's a O(n)
     * method as the inner loop runs at most 6 times
     *
     * @param strip --> point array
     * @param size --> strip array element count
     * @param shortestLine --> shortest line calculated so far
     * @return --> new shortest line
     */
    private PointPair stripClosest(Point2D.Double strip[], int size, PointPair shortestLine) {

        PointPair minimumCurr = shortestLine;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size ; j++) {
                if ((strip[j].getY() - strip[i].getY()) < shortestLine.getDistance()) {
                    PointPair pairStripped = new PointPair(strip[i], strip[j]);
                    if (pairStripped.getDistance() <= minimumCurr.getDistance())
                        minimumCurr = pairStripped;
                }
            }
        }
        return minimumCurr;
    }

}
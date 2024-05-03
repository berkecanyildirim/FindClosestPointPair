import java.awt.geom.Point2D;

public class QuickSort {


    /**
     * Default Contructor
     */
    public QuickSort() {
        //Empty constructor --- do nothing
    }

    /**
     * The main function that implements QuickSort
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @param orderBy    --> compareX or compareY
     *                   compareX: sort minimum to maximum arr[] by X point
     *                   compareY: sort minimum to maximum arr[] by Y point
     */
    public void sort(Point2D.Double[] arr, int startIndex, int lastIndex, String orderBy) {
        int middle_index;
        if (lastIndex-startIndex>1) {
            if (orderBy.equals("compareX")) {
                middle_index = partitionX(arr, startIndex, lastIndex);
                sort(arr, startIndex, middle_index - 1, "compareX");
                sort(arr, middle_index , lastIndex, "compareX");
            }
            else if (orderBy.equals("compareY")) {
                middle_index = partitionY(arr, startIndex, lastIndex);
                sort(arr, startIndex, middle_index - 1, "compareY");
                sort(arr, middle_index , lastIndex, "compareY");
            }
        }
    }

    /**
     * A utility function to swap two elements
     *
     * @param arr --> Array to be sorted
     * @param i   --> first index
     * @param j   --> second index
     */
    private void swap(Point2D.Double[] arr, int i, int j) {
        Point2D.Double temporary = arr[i];
        arr[i] = arr[j];
        arr[j] = temporary;
    }

    /**
     * Get Median of 3 order by Point.X
     *
     * @param arr   --> Array to be sorted
     * @param left  --> First index of arr[]
     * @param right --> Last index of arr[]
     * @return
     */
    private Point2D.Double getMedianX(Point2D.Double[] arr, int left, int right) {
        int middleX=(left+right)/2;

        if(arr[left].x > arr[middleX].x)
            swap(arr,left,middleX);

        if(arr[left].x > arr[right].x)
            swap(arr,left,right);

        if(arr[middleX].x > arr[right].x)
            swap(arr,middleX,right);

        swap(arr,middleX,right-1);
        return arr[right-1];
    }

    /**
     * Get Median of 3 order by Point.Y
     *
     * @param arr   --> Array to be sorted
     * @param left  --> First index of arr[]
     * @param right --> Last index of arr[]
     * @return
     */
    private Point2D.Double getMedianY(Point2D.Double[] arr, int left, int right) {
        int middleY=(left+right)/2;

        if(arr[left].y > arr[middleY].y)
            swap(arr,left,middleY);

        if(arr[left].y > arr[right].y)
            swap(arr,left,right);

        if(arr[middleY].y > arr[right].y)
            swap(arr,middleY,right);

        swap(arr,middleY,right-1);
        return arr[right-1];
    }

    /**
     * This function takes median of three as pivot, places
     * the pivot element at the end of the sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     * Sort order by Point.X
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @return pivot index
     */
    private int partitionX(Point2D.Double[] arr, int startIndex, int lastIndex) {
        double pivotX=getMedianX(arr,startIndex,lastIndex).getX();

        int i=startIndex;
        int j=lastIndex;

        while(true){
            while(arr[++i].x<pivotX);
            while(arr[--j].x>pivotX);
            if(i<j) swap(arr,i,j);
            else break;
        }
        swap(arr,i,lastIndex - 1);
        return i;
    }

    /**
     * This function takes median of three as pivot, places
     * the pivot element at the end of the sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     * Sort order by Point.Y
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @return pivot index
     */
    private int partitionY(Point2D.Double[] arr, int startIndex, int lastIndex) {

        double pivotY=getMedianX(arr,startIndex,lastIndex).getY();

        int i=startIndex;
        int j=lastIndex;

        while(true){
            while(arr[++i].x<pivotY);
            while(arr[--j].x>pivotY);
            if(i<j) swap(arr,i,j);
            else break;
        }
        swap(arr,i,lastIndex - 1);
        return i;
    }
}